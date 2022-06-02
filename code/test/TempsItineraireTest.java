import modele.Itineraire;
import modele.Scenario;
import modele.TempsItineraire;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempsItineraireTest {
    TempsItineraireTest() throws IOException {
    }
    Scenario s1 = Scenario.lectureScenario(

            "src/main/resources/scenario_0.txt");
    Itineraire itineraire1 = new Itineraire(s1);
    TempsItineraire tempsItineraire1 = new TempsItineraire(itineraire1);

    @Test
    void testBestItineraire() {
        System.out.println(tempsItineraire1.getBestItineraire());
    }

    void testGetAllItineraire() {
        String[] attenduBestItineraire =  new String[] {"Velizy", "Tours","Lyon", Paris, Grenoble, Velizy] };
        //System.out.println(itineraire1.getAllItineraire());
    }


    //void testTempsItineraire() {
        //System.out.println(itineraire1.getB);
    }

