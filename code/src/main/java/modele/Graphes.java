package modele;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graphes {

    private final Scenario scenario;
    private int ordre;
    private final HashMap<String, ArrayList<String>> listeAdj;

    public Graphes(Scenario parScenario)  {
        scenario = parScenario;
        listeAdj = new HashMap<>(scenario.getDicoVA()); // degrés entrants
        ordre = scenario.getMembreScenario().size();
    }

    public Graphes(HashMap<String, ArrayList<String>> parListeAdj) throws IOException {
        scenario = new Scenario();
        listeAdj = parListeAdj;
        ordre = scenario.getMembreScenario().size();
    }
    public ArrayList<String> setSource(){
        ArrayList<String> currentSources = new ArrayList<>();
        HashSet<String> acheteurs = new HashSet<>(scenario.getAcheteurs()); // en set pour suppr les doublons
        HashSet<String> vendeurs = new HashSet<>(scenario.getVendeurs());

        for (String elem : vendeurs){
            if (! acheteurs.contains(elem)) {
                currentSources.add(elem);
            }
        }
        return currentSources;
    }

    public HashMap<String, ArrayList<String>> getListeAdj(){
        return new HashMap<>(scenario.getDicoVA());
    }

    public int getOrdre() {
        return ordre;
    }
}


