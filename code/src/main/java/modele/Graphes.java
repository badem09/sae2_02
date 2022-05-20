package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphes {

    private final Scenario scenario;
    private final int ordre;
    private final HashMap<String, ArrayList<String>> mapAjdEntrant;

    private final HashMap<String, ArrayList<String>> mapAjdSortant;

    public Graphes(Scenario parScenario)  {
        scenario = parScenario;
        mapAjdEntrant = new HashMap<>(scenario.getDicoAV()); // degrés entrants
        mapAjdSortant =  new HashMap<>(scenario.getDicoVA());
        ordre = scenario.getMembreScenario().size();
    }

    public HashMap<String, ArrayList<String>> getMapAjdEntrant(){
        return mapAjdEntrant;
    }

    public HashMap<String, ArrayList<String>> getMapAjdSortant(){
        return mapAjdSortant;
    }
    public ArrayList<String> getSommets(){
        return scenario.getMembreScenario();
    }


    public int getOrdre() {
        return ordre;
    }
}


