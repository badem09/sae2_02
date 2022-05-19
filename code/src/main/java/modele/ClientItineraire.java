package modele;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClientItineraire {
    public static void main(String[] args) throws IOException {
        Scenario s = Scenario.lectureScenario(new File(
                "src/main/resources/scenario_1_1.txt"));
        Itineraire it = new Itineraire(s);
        //it.get()


        TreeMap<String, ArrayList<String>> t = it.mapSourcesSuivantes();

        System.out.println( it.allItineraireToString());

    }
}
/*
Actuellement, j'obtient :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra, Excelangue], [Drattaknote], [Nidoking], [Drackhaus], [Pr�sidentFin]]

Avec ça, on trouve le bon meilleur itineraire :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra], [Drattaknote, Excelangue], [Nidoking], [Drackhaus], [Pr�sidentFin]]*/