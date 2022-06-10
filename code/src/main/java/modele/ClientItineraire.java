package modele;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClientItineraire {
    public static void main(String[] args) throws IOException {
        Scenario s = Scenario.lectureScenario(
                "src/main/resources/scenario_1_1.txt",true);
        Itineraire it = new Itineraire(s);
        ArrayList<String> list = new ArrayList<>();
        list.add("Soporifik");
        System.out.println(it.parcoursProgressif("Soporifik", list));

        //it.get()


        //TreeMap<String, ArrayList<String>> t = it.mapSourcesSuivantes();
      // System.out.println(it.allItineraireToString());
        //System.out.println(it.mapAdjSortant);
        //System.out.println(it.mapAdjSortant);
       // Villes ville = new Villes();
        //System.out.println(ville.getMembreToVilles().get("PresidentDebut"));

    }
}
/*
Actuellement, j'obtient :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra, Excelangue], [Drattaknote], [Nidoking], [Drackhaus], [Pr�sidentFin]]

Avec ça, on trouve le bon meilleur itineraire :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra], [Drattaknote, Excelangue], [Nidoking], [Drackhaus], [Pr�sidentFin]]*/