package modele;

import java.util.*;

public class Itineraire {

    private final Graphes graphe;
    private ArrayList<ArrayList<String>> allItineraire;
    private HashMap<String, ArrayList<String>> mapAdjEntrant;

    private HashMap<String, ArrayList<String>> mapAdjSortant;

    private final ArrayList<ArrayList<String>> itineraireGen; // itinéraire Général.

    public Itineraire(Scenario parScenario) {
        graphe = new Graphes(parScenario);
        mapAdjEntrant = new HashMap<>(graphe.getMapAjdEntrant());
        mapAdjSortant = new HashMap<>(graphe.getMapAjdSortant());
        itineraireGen = new ArrayList<>();
        allItineraire = new ArrayList<>();

        setItineraireGen();
        updateMapAdjSortant();
        getChemin(new ArrayList<String>(), "", new ArrayList<>());
        ajoutPresident();
    }

    public String getNextSource() { //recherche des sources courantes.
        mapAdjEntrant = graphe.getMapAjdEntrant();
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

    public void supprSource(String source) {
        mapAdjEntrant.remove(source);
        for (String elem : mapAdjEntrant.keySet()) {

            // enleve la/les source des autres sommets
            mapAdjEntrant.get(elem).remove(source);
        }
    }

    public void setItineraireGen() {
        ArrayList<String> sources = new ArrayList<>(); //premiere source
        HashMap<String, ArrayList<String>> mapAdj = new HashMap<>();
        mapAdj.putAll(mapAdjEntrant);

        while (mapAdj.size() != 0) {
            //recherche des sources courantes.
            for (String elem : mapAdj.keySet()) {
                if (mapAdj.get(elem).size() == 0) {
                    sources.add(elem);
                }
            }
            //Suppression des sources courantes.
            if (sources.size() > 0) {
                itineraireGen.add(sources);
                mapAdj = this.supprSource1(sources, mapAdj);
                sources = new ArrayList<>();
            }
            System.out.println(itineraireGen);
        }
    }

    public HashMap<String, ArrayList<String>> supprSource1(ArrayList<String> sources, HashMap<String, ArrayList<String>> mapAdj) {
        for (String s : sources) { // enleve les source de la liste d'adjacence
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
        HashMap<String, ArrayList<String>> mapSameLevel = mapSameLevel();
        for (String sommet1 : graphe.getSommets()) {
            for (String sommet2 : graphe.getSommets()) {
                if (sommet1 != sommet2 && estDerriere(sommet1, sommet2)) {
                    if (!mapAdjSortant.get(sommet1).contains(sommet2))
                        mapAdjSortant.get(sommet1).add(sommet2);
                }
            }
        }
    }

    public void getChemin(ArrayList<String> currentPath, String source, ArrayList<String> listeProchainSautes) {
        if (source == "") {
            source = getNextSource();
        }
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
        if (currentPath.size() == graphe.getSommets().size() && tousPresent(currentPath)) {
            allItineraire.add(currentPath);
            System.out.println(currentPath + " " + allItineraire.size());
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
    public String allItineraireToString() {

        StringBuilder retour = new StringBuilder();
        for (ArrayList<String> l : allItineraire) {
            retour.append(l.toString()).append("\n");
        }
        return retour + "\n" + String.format("en tout %s chemins possibles", allItineraire.size());
    }

    /**
     * Renvoie un dictionnaire permettant d'accéder aux sommets de même niveau d'un sommet (par
     * rapport aux sources)
     *
     * @return mapSameLevel : Dictionnaire ; key = sommet // values = listes des sommets de même niveau
     */
    public HashMap<String, ArrayList<String>> mapSameLevel() {
        HashMap<String, ArrayList<String>> mapSameLevel = new HashMap<>();
        for (ArrayList<String> niveau : itineraireGen) {  // pour chaque sous liste (niveau de sources)
            for (String s1 : niveau) {
                for (String s2 : niveau) {
                    if (s1.compareTo(s2) != 0) { // si sont différentes
                        if (mapSameLevel.containsKey(s1)) {
                            mapSameLevel.get(s1).add(s2);
                        } else {
                            mapSameLevel.put(s1, new ArrayList<>());
                            mapSameLevel.get(s1).add(s2);
                        }
                    }
                }
            }
        }
        return mapSameLevel;
    }

    public void ajoutPresident() {
        for (ArrayList<String> path : allItineraire) {
            path.add(0, "PresidentDebut");
            path.add(path.size(), "PresidentFin");
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
}
