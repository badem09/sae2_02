package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientTempsItineraire {
    public static void main(String [] arg) throws IOException {
        Scenario s = Scenario.lectureScenario(new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/scenario_1_1.txt"));
        Itineraire it = new Itineraire(s);

        TempsItineraire ti = new TempsItineraire(it);
        //System.out.println(ti.getDicoItineraire());
        ArrayList<String> best = ti.getBestItinéraire();
        int time = ti.getDicoItineraire().get(ti.getBestItinéraire());
       // System.out.println(TempsItineraire.membresToVilles(best).toString() + " " + time);
        System.out.println(ti.toString());
    }
}
