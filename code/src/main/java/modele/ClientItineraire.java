package modele;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClientItineraire {
    public static void main(String[] args) throws IOException {
        Scenario s = Scenario.lectureScenario(new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/scenario_1_1.txt"));
        Itineraire it = new Itineraire(s);
        //it.get()


        TreeMap<String, ArrayList<String>> t = it.itineraireToListAdj();
        System.out.println( it.getAllPath().size());
    }
}
/*
Actuellement, j'obtient :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra, Excelangue], [Drattaknote], [Nidoking], [Drackhaus], [Pr�sidentFin]]

Avec ça, on trouve le bon meilleur itineraire :
[[Pr�sidentD�but], [Soporifik], [N�nupiot, Flagadossnote], [Spoink, Caratroc],
        [Maskadra], [Drattaknote, Excelangue], [Nidoking], [Drackhaus], [Pr�sidentFin]]*/