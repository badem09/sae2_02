package modele;

import java.io.File;
import java.io.IOException;

public class ClientGraphes {
    public static void main(String[] args) throws IOException {

        Scenario s = Scenario.lectureScenario(new File(
                "src/main/resources/scenario_0.txt"));
        Graphes g = new Graphes(s);
       // System.out.println(s.membresToVilles(s.getDicoAV()));
       // System.out.println(g.getSource());
        System.out.println(g.getMapAjdEntrant());

       // System.out.println(s.getMembreScenario());

    }
}
