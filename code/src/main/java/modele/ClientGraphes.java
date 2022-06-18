package modele;

import java.io.File;
import java.io.IOException;

public class ClientGraphes {
    public static void main(String[] args) throws IOException {

        Scenario s = Scenario.lectureScenario(
                "src/main/resources/data/scenario_1_1.txt",true);
        Graphes g = new Graphes(s);
       // System.out.println(s.membresToVilles(s.getDicoAV()));
       // System.out.println(g.getSource());
        System.out.println(g.mapAjdEntrantToString());
        System.out.println(g.mapAjdSortantToString());

       // System.out.println(s.getMembreScenario());
      //  [Soporifik, Caratroc, Excelangue, Drattaknote, Nidoking, Maskadra, Spoink, N�nupiot, Flagadossnote, Drackhaus]
/*
[Soporifik, Flagadossnote, N�nupiot, Spoink, Caratroc, Maskadra, Drattaknote, Excelangue, Nidoking, Drackhaus]
[Amiens, Angers, Biarritz, Bordeaux, Brest, Calais, Cherbourg, Clermond_Fd, Grenoble, Dijon]
longueur : 4096

 */
    }
}
