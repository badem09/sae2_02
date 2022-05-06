package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientTempsItineraire {
    public static void main(String [] arg) throws IOException {
        Scenario s = Scenario.lectureScenario(new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/scenario_1_1.txt"));

        Itineraire it = new Itineraire(s);
        System.out.println(it.listeAdj);
        TempsItineraire ti = new TempsItineraire(it);
        //System.out.println(ti.getDic: oItineraire());
        //ArrayList<String> best = ti.getBestItinéraire();
        System.out.println(ti.toString() + "\n");
        System.out.println(ti.getBestItineraire());
    }
}
/*
[Pr�sidentD�but, Soporifik, Flagadossnote, N�nupiot, Spoink, Caratroc, Excelangue, Maskadra, Drattaknote, Nidoking, Drackhaus, Pr�sidentFin]
[Velizy, Amiens, Angers, Biarritz, Bordeaux, Brest, Clermond_Fd, Calais, Cherbourg, Grenoble, Dijon, Velizy]
longueur : 5209

*/