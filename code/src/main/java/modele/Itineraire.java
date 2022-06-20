package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Itineraire {

    private final Chemin chemin;
    private final Villes villes;
    private final HashMap<ArrayList<String>, Integer> dicoItineraire;
    private final int nbPages;
    private final HashMap<String, String> membresVilles ;


    public Itineraire(Chemin parItineraire) throws IOException {
        chemin = parItineraire;
        dicoItineraire = new HashMap<>();
        villes = new Villes();
        membresVilles = chemin.getScenario().getMembreInconnus();
        setDicoItineraire();
        if (chemin.getAllChemin().size() % 8 == 0) {
            nbPages = chemin.getAllChemin().size() / 8;
        }
        else {
            nbPages = chemin.getAllChemin().size() / 8+1;
        }
    }

    private void setDicoItineraire() {
        ArrayList<ArrayList<Integer>> tabDistance = villes.getTabDistances();
        for (ArrayList<String> it : chemin.getAllChemin()) {
            int sum = 0;
            for (int i = 0; i < it.size() - 1; i++) {
                if (membresVilles.get(it.get(i)) == "Ville non renseignée !" ||
                     membresVilles.get(it.get(i + 1)) == "Ville non renseignée !" ){
                 break;
                 //Si 1 ville est inconnue: itinéraire incalculable
             }
                    String avant = membresVilles.get(it.get(i));
                    String apres = membresVilles.get(it.get(i + 1));
                    int indexAvant = villes.getListeVilles().indexOf(avant);
                    int indexApres = villes.getListeVilles().indexOf(apres);
                    sum += tabDistance.get(indexAvant).get(indexApres);
            }
            dicoItineraire.put(it, sum);
        }
    }

    public String  getBestItineraire(){
        String r = "";
        long min = 1000000000;
        ArrayList<String> best = new ArrayList<>();
        for (ArrayList<String> s : dicoItineraire.keySet()){
            if (dicoItineraire.get(s)<min){
                min = dicoItineraire.get(s);
                best = s;
            }
        }
        r += best + "\n" + membresToVilles(best)+ "\n"  +
                "longueur : " + dicoItineraire.get(best) + " km" + "\n" +"\n";
        return r ;
    }

    public  ArrayList<String> membresToVilles(ArrayList<String> itineraire){
        ArrayList<String> retour = new ArrayList<>();
        for (String membre : itineraire){
            retour.add(membresVilles.get(membre));
        }
        return retour;
    }

    public String toString(){
        String r = "";
        for (ArrayList<String> s : dicoItineraire.keySet()){
         //  System.out.println(s);
           r += s + "\n" + membresToVilles(s)+ "\n"  +
                   "longueur : " + dicoItineraire.get(s) + " km" + "\n" +"\n";
        }
        return r;
    }

    /**
     * Second toString() utilisé pour la pagination voir HBoxPagination
     * @param start (int) indice du premier itinéraire.
     * @param stop (int) indice du dernier itinéraire (exclus).
     * @return
     */
    public String toString(int start, int stop){
        ArrayList<ArrayList<String>> tabItineraire = chemin.getAllChemin();
        String retour = "";
        if(stop > tabItineraire.size()){
            stop = tabItineraire.size();
        }
        if (start < 0 ){
            start = 0;
        }
        for (int i = start; i < stop ; i++){
            retour += tabItineraire.get(i) + "\n" +
                    membresToVilles(tabItineraire.get(i)) +
                    "\n" + "longueur : " + dicoItineraire.get(tabItineraire.get(i)) + " km" +"\n" +"\n";
        }
        return retour;
    }

    public ArrayList<String> getListBest(){
        // retourne en liste le meilleur itinéraire.
        String r = "";
        long min = 1000000000;
        ArrayList<String> best = new ArrayList<>();
        for (ArrayList<String> it : dicoItineraire.keySet()){
            if (dicoItineraire.get(it)<min){
                min = dicoItineraire.get(it);
                best = it;
            }
        }
        return best ;
    }

    public String getCurrentDistance(ArrayList<String> currentPath){

        int sum = 0;
        for (int i = 0; i < currentPath.size() - 1; i++) {
            if (membresVilles.get(currentPath.get(i)) == "Ville non renseignée !" ||
                    membresVilles.get(currentPath.get(i + 1)) == "Ville non renseignée !" ){
                break;
                //Si 1 ville est inconnue: itinéraire incalculable
            }
            String avant = membresVilles.get(currentPath.get(i));
            String apres = membresVilles.get(currentPath.get(i + 1));
            int indexAvant = villes.getListeVilles().indexOf(avant);
            int indexApres = villes.getListeVilles().indexOf(apres);
            sum += villes.getTabDistances().get(indexAvant).get(indexApres);

        }
        return "Chemin : " + currentPath.toString() + "\n" + "Distance : " + sum + " km";
    }


    public int getNbPages() {
        return nbPages;
    }

    public int getNbItineraire(){
        return chemin.getAllChemin().size();
    }

    public Chemin getChemin() {
        return chemin;
    }
}


