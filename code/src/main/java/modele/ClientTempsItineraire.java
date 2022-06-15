package modele;

import java.io.IOException;

public class ClientTempsItineraire {
    public static void main(String [] arg) throws IOException {
        Scenario s = Scenario.lectureScenario("src/main/resources/data/scenario_1_1.txt",true);

        Chemin it = new Chemin(s);
        System.out.println(it.getItineraireGen());
        Itineraire ti = new Itineraire(it);
        //System.out.println(ti.getDic: oItineraire());
        //ArrayList<String> best = ti.getBestItinéraire();
       // System.out.println(ti.toString() + "\n");
        System.out.println(ti.getBestItineraire());
    }
}
/*
[Pr�sidentD�but, Soporifik, Flagadossnote, N�nupiot, Spoink, Caratroc, Excelangue, Maskadra, Drattaknote, Nidoking, Drackhaus, Pr�sidentFin]
[Velizy, Amiens, Angers, Biarritz, Bordeaux, Brest, Clermond_Fd, Calais, Cherbourg, Grenoble, Dijon, Velizy]
longueur : 5209

*/