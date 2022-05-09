package modele;

import java.util.*;
import java.util.stream.Collectors;

public class Itineraire {

    private final Graphes graphe;
    private static ArrayList<ArrayList<String>> allItineraire;
    private HashMap<String, ArrayList<String>> mapAdj;
    private final ArrayList<ArrayList<String>> itineraireGen; // itinéraire Général.

    public Itineraire(Scenario parScenario){
        graphe = new Graphes(parScenario);
        mapAdj = new HashMap<>(graphe.getMapAjd());
        itineraireGen = new ArrayList<>();
        allItineraire = new ArrayList<>();
        setItineraireGen();
        setAllItineraire();
    }

    /**
     * Recherche le squelette des itinéraires en utilisant la méthode des sources.
     * Chaque source est mise dans un niveau.
     * Dans le cas ou il y plusieurs sources, elles sont placées dans le même niveau.
     * @author Ba Demba
     */
    public void setItineraireGen() {
        ArrayList<String> sources = graphe.setSource(); //premiere source


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
                sources = this.supprSource(sources);
            }
        }
        // this.itineraire = itineraire;
        String v1 = "PrésidentDébut";
        String v2 = "PrésidentFin";
        ArrayList<String> l1 = new ArrayList<>();
        l1.add(v1);
        ArrayList<String> l2 = new ArrayList<>();
        l2.add(v2);
        if (!itineraireGen.contains(l1)) {
            itineraireGen.add(0, l1);
        }
        if (!itineraireGen.contains(l2)) {
            itineraireGen.add(itineraireGen.size(), l2);
        }

        System.out.println(itineraireGen);
    }

    /**
     *Supprime les sources de la liste d'adjacence et renvoie une liste vide.
     * @param sources : Sources précedentes
     * @return Nouvelle liste vide
     * @author Ba Demba
     */
    public ArrayList<String> supprSource(ArrayList<String> sources) {
        for (String s : sources) { // enleve les source de la liste d'adjacence
            mapAdj.remove(s);
        }
        for (String elem : mapAdj.keySet()) {
            for (String s : sources) {
                // enleve la/les source des autres sommets
                mapAdj.get(elem).remove(s);
            }
        }
        return new ArrayList<>();
    }

    /**
     * A partir de l'itinéraire général des sources (itineraireGen), renvoie un dictionnaire ayant pour clé
     * un sommet et en valeur les sources suivantes.
     * @return sourcesSuivantes (Treemap) : key = sommet  // values = Arraylist (sources suivantes)
     * @author Ba Demba
     */
    public TreeMap<String, ArrayList<String>> mapSourcesSuivantes() {

        TreeMap<String, ArrayList<String>> sourcesSuivantes = new TreeMap<>();
        //remplissage du dico avec des listes.
        for (String e : itineraireGen.get(itineraireGen.size() - 1)) {
            sourcesSuivantes.put(e, new ArrayList<>());
        }
        for (int i = 0; i < itineraireGen.size() - 1; i++) {
            ArrayList<String> currentSommets = itineraireGen.get(i);
            if (itineraireGen.get(i).size() == 1) { // si une seule source
                sourcesSuivantes.put(currentSommets.get(0), itineraireGen.get(i + 1));
            } else { // sinon pour chaque sommet du niveau, on mets les prochaines sources
                for (String nextSource : currentSommets) {
                    sourcesSuivantes.put(nextSource, itineraireGen.get(i + 1));
                }
            }
        }
        return sourcesSuivantes;

    }

    /**
     * Appelle la fonction recursive de recherche des chemins.
     * "Paramêtres" :
     *      visite :
     *      currentPath :
     *      sourcesSuivantes
     *      Source :
     * @author : Ba Demba
     */
    public void setAllItineraire() {
        System.out.println(itineraireGen);
        TreeMap<String, Boolean> visite = new TreeMap<>();
        ArrayList<String> currentPath = new ArrayList<>();
        TreeMap<String, ArrayList<String>> sourcesSuivantes = mapSourcesSuivantes();
        String source = this.itineraireGen.get(0).get(0);

        currentPath.add(source);
        dfs(sourcesSuivantes, source, visite, currentPath, sourcesSameLevel());

        ArrayList<ArrayList<String>> autresArriere = autresitinerairesArriere(allItineraire);
        ArrayList<ArrayList<String>> autresAvant = autresitineraires(allItineraire);

        for (ArrayList<String> autresIt :  autresArriere){
            allItineraire.add(autresIt);
        }
        for (ArrayList<String> autresIt :  autresAvant){
            allItineraire.add(autresIt);
        }

    }



    /**
     * Fonction récursive qui implémente une recherche arborescente des chemins.
     *
     * Inspirations :
     *
     * <a href="https://www.baeldung.com/cs/simple-paths-between-two-vertices">Inspiration 1</a> \n
     * <a href="https://www.geeksforgeeks.org/find-paths-given-source-destination/?ref=lbp">Inspiration 2</a>
     *
     * Point faible : Certains chemins possibles sont ignorées (voir "A COMPLETER")
     *
     * @param sourcesSuivantes voir mapSourcesSuivantes()
     * @param source Première source (UN DES DEFAUTS DE MON PROGRAMME)
     * @param visite Permet de savoir quel sommet a été visité dans le sommet ou non.
     * @param currentPath Liste des sommets du chemin courant calculé.
     * @param sameLevel voir listSameLevel()
     * @author : Ba Demba
     *
     *
     */
    private void dfs(TreeMap<String, ArrayList<String>> sourcesSuivantes, String source, TreeMap<String,
            Boolean> visite , ArrayList<String> currentPath, HashMap<String, ArrayList<String>> sameLevel){
        /*
          Principe : recherche arborescente
         */

        boolean montee = true;
        visite.put(source,true);

        // Si le parcours d'un chemin est fini, je le stocke.
        if (sourcesSuivantes.get(source).size()==0 && !allItineraire.contains(currentPath) && currentPath.size() == 12) {
            ArrayList<String> pathAjoute = new ArrayList<>(currentPath); // copie car sinon problème.
            allItineraire.add(pathAjoute);

        } // Sinon, je continue de chercher.
        else {
            if (sameLevel.containsKey(source)) { // s'il y a plusieurs sources. On récupère celles du même niveau que
                                                 //  le sommet courant
                for ( String prochainSameLevel : sameLevel.get(source) ) {
                    // prochainSameLevel --> prochaine source du même niveau.
                    if (!visite.containsKey(prochainSameLevel)
                            || ! visite.get(prochainSameLevel)){
                        //si le sommet n'est pas visité.
                        // possible d'enlever conditions '!visite.containsKey(prochainSameLevel)' en mettant de base tout les points en false dans visite

                        source = prochainSameLevel;
                        if (!currentPath.contains(prochainSameLevel)) {
                            currentPath.add(source); //Ajout au chemin courant.
                            visite.put(source, true);

                            // recherche des autres sommet sources du même niveau.
                            dfs(sourcesSuivantes, source, visite, currentPath, sameLevel);

                            // Une fois que la recherche de chemin avec le sommet à cette place est terminée,
                            // on l'enlève du chemin courant.
                            currentPath.remove(prochainSameLevel);
                            visite.put(prochainSameLevel,false);
                            montee = false;

                        }
                    }
                }

                // si recherche en montée, on va chercher les prochaines sources parmi les sommets adjacents.
                if (montee){
                    for (String prochainSameLevel : sameLevel.get(source)) {
                        source = prochainSameLevel;
                        for (String prochainNextLevel : sourcesSuivantes.get(source)) {
                            if (!visite.containsKey(prochainNextLevel) || ! visite.get(prochainNextLevel)) {
                                if (!currentPath.contains(prochainNextLevel)) {
                                    currentPath.add(prochainNextLevel);

                                    source = prochainNextLevel;
                                    visite.put(source, true);
                                    dfs(sourcesSuivantes, source, visite, currentPath, sameLevel);
                                    currentPath.remove(prochainNextLevel);
                                    visite.put(prochainNextLevel, false);
                                }
                            }
                        }
                    }
                }
            }
            else { // S'il n'y a qu'une seule source à ce niveau,
                // on va chercher les prochaines sources parmi les sommets adjacents.
                for (String prochainNextLevel : sourcesSuivantes.get(source)){
                    if ( ! visite.containsKey(prochainNextLevel )
                            ||  ! visite.get(prochainNextLevel)){
                        if (! currentPath.contains(prochainNextLevel)) {
                            currentPath.add(prochainNextLevel);
                            source = prochainNextLevel;
                            visite.put(source, true);
                            dfs(sourcesSuivantes, source, visite, currentPath, sameLevel);
                            currentPath.remove(prochainNextLevel);
                            visite.put(source, false);
                        }
                    }
                }
            }
            visite.put(source,false);
        }
    }

    /**
     * Renvoie tous les chemins sous forme d'un tableau.
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
     * @return mapSameLevel : Dictionnaire ; key = sommet // values = listes des sommets de même niveau
     */
    public HashMap<String, ArrayList<String>> sourcesSameLevel() {
        HashMap<String, ArrayList<String>> mapSameLevel = new HashMap<>();
        for (ArrayList<String> niveau : itineraireGen) {  // pour chaque sous liste (niveau de sources)
            for (String s1 : niveau) {
                for (String s2 : niveau) {
                    if (s1.compareTo(s2) != 0) { // si sont différentes
                        if (mapSameLevel.containsKey(s1)){
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

    /**
     * Accesseur
     * @return allItinéraire
     */
    public ArrayList<ArrayList<String>> getAllItineraire() {
        return allItineraire;
    }

    /**
     * Accesseur
     * @return itineraireGen
     */
    public ArrayList<ArrayList<String>> getItineraireGen() {
        return itineraireGen;
    }

    /**
     * Recherche les autres itineraires possibles à partir de ceux trouvés avec la rechercheItinéraire en échangeant
     * les sommets si possible. C'est-à-dire si le sommet 1 n'a pas besoin du sommet 2. C'est-à-dire si
     * le sommet 1 n'est pas vendeur du sommet 2.
     * @return autres ArrayList<ArrayList<String>>
     */
   public ArrayList<ArrayList<String>> autresitinerairesArriere(ArrayList<ArrayList<String>> allItineraire){
       /// faire un mode recursif
        boolean ajout = true;
        ArrayList<ArrayList<String>> copie =new ArrayList<>();
       Iterator<ArrayList<String>> iterator = allItineraire.iterator();
       while(iterator.hasNext())
       {
           //Add the object clones
           copie.add((ArrayList<String>) iterator.next().clone());
       }
        ArrayList<ArrayList<String>> autres = new ArrayList<>();
        mapAdj = graphe.getMapAjd();
        for (ArrayList<String> autreItinéraire : copie){
            for (int i = autreItinéraire.size()-1 ; i > 0 ; i-- ) {
                int j = i - 1;
                if (mapAdj.containsKey(autreItinéraire.get(i)) && mapAdj.containsKey(autreItinéraire.get(j))) {
                    while (j > 1 && ! mapAdj.get(autreItinéraire.get(j)).contains(autreItinéraire.get(j))) {
                        //System.out.println(autreItinéraire.get(i) + " " + autreItinéraire.get(j));
                        Collections.swap(autreItinéraire, j, i);

                        for (ArrayList<String> itRecur : allItineraire) {

                            if ( sameListe(itRecur,autreItinéraire)) {
                              //  System.out.println(itRecur + " \n " + autreItinéraire + "\n");
                                ajout = false;
                            }
                        }
                        if (ajout && !autres.contains(autreItinéraire)) {
                            autres.add(autreItinéraire);
                        }

                        j--;
                        ajout = true ;
                    }
                }
            }
        }
        return autres;
    }

    public ArrayList<ArrayList<String>> autresitineraires(ArrayList<ArrayList<String>> allItineraire){
        boolean ajout = true;
        ArrayList<ArrayList<String>> copie =new ArrayList<>();
        Iterator<ArrayList<String>> iterator = allItineraire.iterator();
        while(iterator.hasNext())
        {
            copie.add((ArrayList<String>) iterator.next().clone());
        }
        ArrayList<ArrayList<String>> autres = new ArrayList<>();
        mapAdj = graphe.getMapAjd();
        for (ArrayList<String> autreItinéraire : copie){
            for (int i = 0 ; i< autreItinéraire.size()-1 ; i++ ) {
                int j = i + 1;
                if (mapAdj.containsKey(autreItinéraire.get(i)) && mapAdj.containsKey(autreItinéraire.get(j))) {
                    while (j < autreItinéraire.size()-1 && ! mapAdj.get(autreItinéraire.get(j)).contains(autreItinéraire.get(i))) {
                        //System.out.println(autreItinéraire.get(i) + " " + autreItinéraire.get(j));
                        Collections.swap(autreItinéraire, i, j);

                        for (ArrayList<String> itRecur : allItineraire) {

                            if ( sameListe(itRecur,autreItinéraire)) {
                                //  System.out.println(itRecur + " \n " + autreItinéraire + "\n");
                                ajout = false;
                            }
                        }
                        if (ajout && !autres.contains(autreItinéraire)) {
                            autres.add(autreItinéraire);
                        }

                        j++;
                        ajout = true ;
                    }
                }
            }
        }
        return autres;
    }

    public boolean sameListe (ArrayList<String> liste1 , ArrayList<String> liste2){
        for (int i = 0; i< liste1.size();i++){
            for (int j = 0; j < liste2.size(); j++){
                if (liste1.get(i) != liste2.get(j) && i == j){
                    return false;
                }
            }
        }
        return  true;
    }
}




