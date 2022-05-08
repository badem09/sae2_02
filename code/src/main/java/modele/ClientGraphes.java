package modele;

import java.io.File;
import java.io.IOException;

public class ClientGraphes {
    public static void main(String[] args) throws IOException {

        Scenario s = Scenario.lectureScenario(new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Data/scenario_1_1.txt"));
        Graphes g = new Graphes(s);
       // System.out.println(s.membresToVilles(s.getDicoAV()));
        System.out.println(g.setSource());
        System.out.println(g.getMapAjd());

        System.out.println(s.getMembreScenario());

    }
}
