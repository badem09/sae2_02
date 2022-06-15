package modele;

import java.io.IOException;

public class ClientItineraire {
    public static void main(String[] args) throws IOException {
        Scenario s = Scenario.lectureScenario(
                "src/main/resources/data/scenario_1_2.txt",true);
        Chemin it = new Chemin(s);
        System.out.println(it.size());
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