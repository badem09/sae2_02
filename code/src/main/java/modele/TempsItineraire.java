package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TempsItineraire {

    private Itineraire itineraire;
    private static Villes villes;
    private HashMap<ArrayList<String>, Integer> dicoItineraire;
    private int nbPages;
    private HashMap<String, String> membresVilles ;


    public TempsItineraire(Itineraire parItineraire) throws IOException {
        itineraire = parItineraire;
        dicoItineraire = new HashMap<>();
        villes = new Villes();
        membresVilles = itineraire.getScenario().getMembreInconnus();
        setDicoItineraire();
        if (itineraire.getAllItineraire().size() % 8 == 0) {
            nbPages = itineraire.getAllItineraire().size() / 8;
        }
        else {
            nbPages = itineraire.getAllItineraire().size() / 8+1;

        }

    }

    private void setDicoItineraire() {
        ArrayList<ArrayList<Integer>> tabDistance = villes.getTabDistances();
       // HashMap<String, String> membresVilles = villes.getMembreToVilles();
       // System.out.println(this.itineraire.getAllPath());
        for (ArrayList<String> it : itineraire.getAllItineraire()) {
            int sum = 0;
            for (int i = 0; i < it.size() - 1; i++) {
                if (membresVilles.get(it.get(i)) == "Ville non renseignée !" ||
                     membresVilles.get(it.get(i + 1)) == "Ville non renseignée !" ){
                 break;
                 //Si 1 ville est inconnue: itinéraire incalculable
             }
                    String avant = membresVilles.get(it.get(i));
                    String apres = membresVilles.get(it.get(i + 1));
                    int indexAvant = villes.getTabVilles().indexOf(avant);
                    int indexApres = villes.getTabVilles().indexOf(apres);
                    sum += tabDistance.get(indexAvant).get(indexApres);

            }

            dicoItineraire.put(it, sum);
        }
    }

    public HashMap<ArrayList<String>, Integer> getDicoItineraire(){
        return dicoItineraire;
    }
    public String  getBestItineraire(){
        // What if yen a 2?
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
     //   HashMap<String, String> membresVilles = villes.getMembreToVilles();
        ArrayList<String> retour = new ArrayList<>();
        for (String membre : itineraire){
            //retour.set(retour.indexOf(membre),membresVilles.get(membre));
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
    public String toString(int start, int stop){
        ArrayList<ArrayList<String>> tabItineraire = itineraire.getAllItineraire();
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
        for (ArrayList<String> s : dicoItineraire.keySet()){
            if (dicoItineraire.get(s)<min){
                min = dicoItineraire.get(s);
                best = s;
            }
        }
        return best ;
    }

    public int getNbPages() {
        return nbPages;
    }

    public int getNbItineraire(){
        return itineraire.getAllItineraire().size();
    }

    public Itineraire getItineraire() {
        return itineraire;
    }
}


