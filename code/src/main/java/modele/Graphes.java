package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphes {

    private final Scenario scenario;
    private final HashMap<String, ArrayList<String>> mapAjdEntrant;
    private final HashMap<String, ArrayList<String>> mapAjdSortant;

    public Graphes(Scenario parScenario)  {
        scenario = parScenario;
        mapAjdEntrant = new HashMap<>(scenario.getDicoAV()); // degrés entrants
        mapAjdSortant =  new HashMap<>(scenario.getDicoVA()); // degrés sortants
    }

    public HashMap<String, ArrayList<String>> getMapAjdEntrant(){
        HashMap<String, ArrayList<String>> newMapAjdEntrant = new HashMap<>();
        for (String key : mapAjdEntrant.keySet()){
            ArrayList<String> values = (ArrayList<String>) mapAjdEntrant.get(key).clone();
            newMapAjdEntrant.put(key,values);
        }
        return newMapAjdEntrant;
    }

    public HashMap<String, ArrayList<String>> getMapAjdSortant(){
        HashMap<String, ArrayList<String>> newMapAjdSortant = new HashMap<>();
        for (String key : mapAjdSortant.keySet()){
            ArrayList<String> values = (ArrayList<String>) mapAjdSortant.get(key).clone();
            newMapAjdSortant.put(key,values);
        }
        return newMapAjdSortant;
    }

    public ArrayList<String> getSommets(){
        return scenario.getMembreScenario();
    }

    public String mapAjdEntrantToString(){
        String retour = "";
        for (String key : mapAjdEntrant.keySet()){
            retour += key + " : ";
            String values = "";
            for(String elem : mapAjdEntrant.get(key)){
                values += elem + " ";
            }
            retour += values + "\n";
        }
        return retour;
    }

    public String mapAjdSortantToString(){
        String retour = "";
        for (String key : mapAjdSortant.keySet()){
            retour += key + " : ";
            String values = "";
            for(String elem : mapAjdSortant.get(key)){
                values += elem + ", ";
            }
            retour += values + "\n";
        }
        return retour;
    }

    public Scenario getScenario() {
        return scenario;
    }
}


