package modele;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempsItineraireTest {
    TempsItineraireTest() throws IOException {
    }

    Scenario s1 = Scenario.lectureScenario("src/main/resources/scenario_0.txt",false);
    TempsItineraire tempsItineraire1 = new TempsItineraire(new Chemin(s1));

    @Test
    void testTempsItineraire() {
        String[] attenduBestItineraire = new String[]{"PresidentDebut","Sabelettenote", "Sablaireaunote",
                "Kokiyas", "Chenipan","PresidentFin"};
        for(int i =0;i<tempsItineraire1.getListBest().size();i++)
            assertEquals( attenduBestItineraire[i],tempsItineraire1.getListBest().get(i));
        System.out.println("La fonction TempsItineraire est valide");
    }

}

