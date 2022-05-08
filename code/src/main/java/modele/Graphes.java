package modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graphes {

    private final Scenario scenario;
    private final int ordre;
    private final HashMap<String, ArrayList<String>> mapAjd;

    public Graphes(Scenario parScenario)  {
        scenario = parScenario;
        mapAjd = new HashMap<>(scenario.getDicoVA()); // degrés entrants
        ordre = scenario.getMembreScenario().size();
    }

    public Graphes(HashMap<String, ArrayList<String>> parListeAdj) throws IOException {
        scenario = new Scenario();
        mapAjd = parListeAdj;
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

    public HashMap<String, ArrayList<String>> getMapAjd(){
        return new HashMap<>(scenario.getDicoVA());
    }

    public int getOrdre() {
        return ordre;
    }
}


