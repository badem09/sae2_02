package modele;

import java.io.IOException;
import java.util.*;

public class Chemin {

    private final Graphes graphe;
    private final ArrayList<ArrayList<String>> allChemin;
    private HashMap<String, ArrayList<String>> mapAdjEntrant;

    private HashMap<String, ArrayList<String>> mapAdjSortant;

    private final ArrayList<ArrayList<String>> cheminGen; // chemin Général.

    public Chemin(Scenario parScenario) {
        graphe = new Graphes(parScenario);
        mapAdjEntrant = new HashMap<>(graphe.getMapAjdEntrant());
        mapAdjSortant = new HashMap<>(graphe.getMapAjdSortant());
        cheminGen = new ArrayList<>();
        allChemin = new ArrayList<>();

        setCheminGen();
        updateMapAdjSortant();
        appelGetChemins();
        ajoutPresident();
    }

    public String getNextSource() { //recherche des sources une par une
        String source = "";
        for (String elem : mapAdjEntrant.keySet()) {
            if (mapAdjEntrant.get(elem).size() == 0) {
                source = elem;
                this.supprSource(source);
                break;
            }
        }
        return source;
    }

    public void supprSource(String source) { //Suppression des sources une par une
        mapAdjEntrant.remove(source);
        for (String elem : mapAdjEntrant.keySet()) {

            // enleve la/les source des autres sommets
            mapAdjEntrant.get(elem).remove(source);
        }
    }

    public void setCheminGen() { // Chemin general selon les sources
        ArrayList<String> sources = new ArrayList<>();
        HashMap<String, ArrayList<String>> mapAdjCopie = graphe.getMapAjdEntrant();
        while (mapAdjCopie.size() != 0) {
            //recherche des sources courantes.
            for (String sommet : mapAdjCopie.keySet()) {
                if (mapAdjCopie.get(sommet).size() == 0) {
                    sources.add(sommet);
                }
            }
            cheminGen.add(sources);
            for (String s : sources) { // Suppression des sources dans le dictionnaire.
                mapAdjCopie.remove(s);
            }
            for (String elem : mapAdjCopie.keySet()) { // Suppression des sources des listes d'adjacence
                for (String s : sources) {        // des autres sommets.
                    mapAdjCopie.get(elem).remove(s);
                }
            }
            sources = new ArrayList<>();
        }
    }

    public boolean estDerriere(String sommet1, String sommet2) {
        // regarde si dans le shema general des sources s1 est derriere s2.

        int s1 = -1;
        int s2 = -1;

        for (ArrayList<String> level : cheminGen) {
            if (level.contains(sommet1)) {
                s1 = cheminGen.indexOf(level);
            }
            if (level.contains(sommet2)) {
                s2 = cheminGen.indexOf(level);
            }
        }
        return s1 < s2 || s1 == s2;
    }

    /**
     * Rajoute des arêtes entre 2 sommets s1 et s2 si s1 n'est pas derriere s2 dans le niveau des sources
     * (voir setCheminGen())
     */
    public void updateMapAdjSortant() {
        for (String sommet1 : graphe.getSommets()) {
            for (String sommet2 : graphe.getSommets()) {
                if (! sommet1.equals(sommet2) && estDerriere(sommet1, sommet2)) {
                    if (! mapAdjSortant.get(sommet1).contains(sommet2))
                        mapAdjSortant.get(sommet1).add(sommet2);
                }
            }
        }
    }

    public void appelGetChemins(){
        for (String sourcePrimaires : cheminGen.get(0)){
            getChemins(new ArrayList<>(),sourcePrimaires,new ArrayList<>());
        }
    }

    private boolean tousPredecesseurPresent(String prochain, ArrayList<String> currentPath) {
        for (String pred : mapAdjEntrant.get(prochain)) {
            if (!currentPath.contains(pred))
                return false;
        }
        return true;
    }

    public boolean tousPresent(ArrayList<String> currentPath) {
        for (String sommet : graphe.getSommets()) {
            if (!currentPath.contains(sommet)) {
                return false;
            }
        }
        return true;
    }


    public void getChemins(ArrayList<String> currentPath, String source, ArrayList<String> listeProchainSautes) {

        currentPath.add(source);
        ArrayList<String> prochainSommets = (ArrayList<String>) mapAdjSortant.get(source).clone();
        if (listeProchainSautes.size() != 0) {
            for (String e : listeProchainSautes) {
                if (!currentPath.contains(e) && !prochainSommets.contains(e) && !mapAdjEntrant.get(e).contains(source)) {
                    prochainSommets.add(e);
                }
            }
        }
        if (prochainSommets.size() > 0) {
            for (String prochain : prochainSommets) {
                listeProchainSautes = (ArrayList<String>) prochainSommets.clone();
                listeProchainSautes.remove(prochain);
                if (!currentPath.contains(prochain) && tousPredecesseurPresent(prochain, currentPath)) {
                    source = prochain;
                    getChemins((ArrayList<String>) currentPath.clone(), source, listeProchainSautes);
                    currentPath.remove(source);
                }
            }
        }
        if (currentPath.size() == graphe.getSommets().size() && tousPresent(currentPath)) {// tousPresent inutile
            allChemin.add(currentPath);
        }
    }

    /**
     * Etant donné un sommet de départ et un chemin parcouru jusque là,
     * retourne les possibilités de sommets suivants.
     * @param depart (String) : sommet de départ.
     * @param currentPath (Arraylist<String>) : Chemin courant.
     * @return possibilites (Arraylist<String>) : Sommets possible d'atteindre comprenant les sommets sautes.
     */
    public ArrayList<String> parcoursProgressif(String depart, ArrayList<String> currentPath,
                                                ArrayList<String> possibilitesCourantes){
        if (depart == "President"){
            return cheminGen.get(0);
        }
        ArrayList<String> possibilites = mapAdjSortant.get(depart);
        for (String sommet : (ArrayList<String>) possibilites.clone()){
            if (! tousPredecesseurPresent(sommet, currentPath) || currentPath.contains(sommet)) {
                possibilites.remove(sommet);
            }
        }
        possibilitesCourantes.remove(depart);
        ArrayList<String> possibilitesSautes = possibilitesCourantes;
        possibilites.removeAll(possibilitesSautes);
        possibilites.addAll(possibilitesSautes);
        return possibilites;
    }

    /**
     * Renvoie la distance d'un sommet aux sommets atteignables parmis ses sommets ajdacent (en sortie).
     * @param currentSource (String) : Sommet.
     * @param possibilitesCourantes (Arraylist<String>) : Sommets atteingnables.
     * @return distance (ArrayList<String>) : distance aux sommets (selon l'indice).
     * @throws IOException (FileNotFoundException).
     */
    public ArrayList<String> getCurrentDistance(String currentSource, ArrayList<String> possibilitesCourantes) throws IOException {

        Villes villes = new Villes();
        ArrayList<String> distance = new ArrayList<>();
        ArrayList listeViles = villes.getTabVilles();
        String villeSource = "";

        for(String sommet : possibilitesCourantes){
            villeSource = villes.getMembreToVilles().get(currentSource);
            if (villes.getMembreToVilles().containsKey(sommet)) {
                ArrayList<Integer> ligne = villes.getTabDistances().get(listeViles.indexOf(villeSource));
                String villeProchain = villes.getMembreToVilles().get(sommet);
                distance.add(ligne.get(listeViles.indexOf(villeProchain)) + " km");
            }
            else {distance.add("Distance inconnue");}
        }
        return distance;
    }

    public String toString() {
        String retour = "";
        for (ArrayList<String> l : allChemin) {
            retour += l.toString() + "\n";
        }
        return retour + "\n" + String.format("en tout %s chemins possibles", allChemin.size());
    }

    public void ajoutPresident() {
        for (ArrayList<String> path : allChemin) {
            path.add(0, "President");
            path.add(path.size(), "President");
        }
    }

    public ArrayList<ArrayList<String>> getAllChemin() {
        return allChemin;
    }

    public ArrayList<ArrayList<String>> getItineraireGen() {
        return cheminGen;
    }

    public Scenario getScenario(){
        return  graphe.getScenario();
    }

    public int size(){
        return allChemin.size();
    }
}
