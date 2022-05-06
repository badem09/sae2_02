package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TempsItineraire {

    private Itineraire itineraire;
    private static Villes villes;
    private HashMap<ArrayList<String>, Integer> dicoItineraire;

    public TempsItineraire(Itineraire parItineraire) throws IOException {
        itineraire = parItineraire;
        dicoItineraire = new HashMap<>();
        villes = new Villes();

        setDicoItineraire();
    }

    private void setDicoItineraire() {
        ArrayList<ArrayList<Integer>> tabDistance = villes.getTabDistances();
        HashMap<String, String> membresVilles = villes.getMembreToVilles();
       // System.out.println(this.itineraire.getAllPath());
        for (ArrayList<String> it : itineraire.getAllPath()) {
            int sum = 0;
            for (int i = 0; i < it.size() - 1; i++) {
                //System.out.println(it);
                //System.out.println(it.get(i));
                //System.out.println(it.get(i+1));
                String avant = membresVilles.get(it.get(i));
                String apres = membresVilles.get(it.get(i+1));
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
    public ArrayList<String>  getBestItinéraire(){
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

    public static ArrayList<String> membresToVilles(ArrayList<String> itineraire){
        HashMap<String, String> membresVilles = villes.getMembreToVilles();
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
           r += s + "\n" + TempsItineraire.membresToVilles(s)+ "\n"  +
                   "longueur : " + dicoItineraire.get(s) + "\n" +"\n";
        }
        return r;
    }
}

