package modele;

import java.io.IOException;
import java.util.*;

public class Itineraire {

    private final Graphes graphe;
    private final ArrayList<ArrayList<String>> allItineraire;
    private HashMap<String, ArrayList<String>> mapAdjEntrant;

    private HashMap<String, ArrayList<String>> mapAdjSortant;

    private final ArrayList<ArrayList<String>> itineraireGen; // itinéraire Général.

    public Itineraire(Scenario parScenario) {
        //Refaire un toString qui prennent qu'un intervale psk pour le dernier Scenario
        // Faire un seul string prend trop de temps.
        graphe = new Graphes(parScenario);
        mapAdjEntrant = new HashMap<>(graphe.getMapAjdEntrant());
        mapAdjSortant = new HashMap<>(graphe.getMapAjdSortant());
        itineraireGen = new ArrayList<>();
        allItineraire = new ArrayList<>();

        setItineraireGen();
        updateMapAdjSortant();
        appelGetChemins();
        //getChemin(new ArrayList<>(), "", new ArrayList<>());
        ajoutPresident();
    }


    public int size(){
        return allItineraire.size();
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

    public void setItineraireGen() { // Itineraire general selon les sources
        ArrayList<String> sources = new ArrayList<>(); //premiere source
        HashMap<String, ArrayList<String>> mapAdj = graphe.getMapAjdEntrant();
        while (mapAdj.size() != 0) {
            //recherche des sources courantes.
            for (String elem : mapAdj.keySet()) {
                if (mapAdj.get(elem).size() == 0) {
                    sources.add(elem);
                }
            }
            //Suppression des sources
            if (sources.size() > 0) {
                itineraireGen.add(sources);
                mapAdj = this.supprSource1(sources, mapAdj);
                sources = new ArrayList<>();
            }
        }
    }

    public HashMap<String, ArrayList<String>> supprSource1(ArrayList<String> sources, HashMap<String, ArrayList<String>> mapAdj) {
        for (String s : sources) { // Suppression pour les sources générales
            mapAdj.remove(s);
        }
        for (String elem : mapAdj.keySet()) {
            for (String s : sources) {
                // enleve la/les source des autres sommets
                mapAdj.get(elem).remove(s);
            }
        }
        return mapAdj;
    }

    public boolean estDerriere(String sommet1, String sommet2) {
        // regarde si dans le shema general des sources s1 est derrierre s2 ou pas.

        int s1 = -1;
        int s2 = -1;

        for (ArrayList<String> level : itineraireGen) {
            if (level.contains(sommet1)) {
                s1 = itineraireGen.indexOf(level);
            }
            if (level.contains(sommet2)) {
                s2 = itineraireGen.indexOf(level);
            }
        }
        return s1 < s2 || s1 == s2;
    }

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
        for (String sourcePrimaires : itineraireGen.get(0)){
            getChemin(new ArrayList<>(),sourcePrimaires,new ArrayList<>());
        }
    }

    public void getChemin(ArrayList<String> currentPath, String source, ArrayList<String> listeProchainSautes) {

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
                    getChemin((ArrayList<String>) currentPath.clone(), source, listeProchainSautes);
                    currentPath.remove(source);
                }
            }
        }
        // if (mapAdjSortant.get(source).size() == 0 && tousPresent(currentPath) && ! nouveauPath(currentPath) ) {
        if (currentPath.size() == graphe.getSommets().size() && tousPresent(currentPath)) {// tousPresent inutile
            allItineraire.add(currentPath);
           // System.out.println( allItineraire.size() + " " + currentPath);
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

    /**
     * Renvoie tous les chemins sous forme d'un tableau.
     *
     * @return retour : Tous les chemins
     */
    public String toString() {
        String retour = "";
        for (ArrayList<String> l : allItineraire) {
            retour += l.toString() + "\n";
        }
        return retour + "\n" + String.format("en tout %s chemins possibles", allItineraire.size());
    }

    public void ajoutPresident() {
        for (ArrayList<String> path : allItineraire) {
            path.add(0, "President");
            path.add(path.size(), "President");
        }
    }

    /**
     * Accesseur
     *
     * @return allItinéraire
     */
    public ArrayList<ArrayList<String>> getAllItineraire() {
        return allItineraire;
    }

    /**
     * Accesseur
     *
     * @return itineraireGen
     */
    public ArrayList<ArrayList<String>> getItineraireGen() {
        return itineraireGen;
    }

    public Scenario getScenario(){
        return  graphe.getScenario();
    }

    public ArrayList<String> parcoursProgressif(String depart, ArrayList<String> currentPath){
        if (depart == ""){
            return itineraireGen.get(0);
        }
        ArrayList<String> possibilites = mapAdjSortant.get(depart);
        for (String sommet : (ArrayList<String>) possibilites.clone()){
            if (! tousPredecesseurPresent(sommet, currentPath) || currentPath.contains(sommet)) {
                possibilites.remove(sommet);
            }
        }
        return possibilites;
    }

    public ArrayList<String> getCurrentDistance(String currentSource, ArrayList<String> possibilitesCourantes) throws IOException {
        Villes villes = new Villes();
        ArrayList<String> distance = new ArrayList<>();
        ArrayList listeViles = villes.getTabVilles();
        String villeSource = "Velizy";

        for(String sommet : possibilitesCourantes){
            if (currentSource != ""){
                villeSource = villes.getMembreToVilles().get(currentSource);
            }
            if (villes.getMembreToVilles().containsKey(sommet)) {
                ArrayList<Integer> ligne = villes.getTabDistances().get(listeViles.indexOf(villeSource));
                String villeProchain = villes.getMembreToVilles().get(sommet);
                distance.add(String.valueOf(ligne.get(listeViles.indexOf(villeProchain))) + " km");
            }
            else {distance.add("Distance inconnue");}
        }
        return distance;
    }
}
