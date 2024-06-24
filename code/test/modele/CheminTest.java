package modele;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CheminTest {

    private Chemin it0;
    private Chemin it1;
    private Chemin it2;
    private Chemin it3;
    private Chemin it4;

    CheminTest() throws IOException {
       it0 = new Chemin(Scenario.lectureScenario(
               "code/src/main/resources/data/scenario_0.txt",true));
       it1 = new Chemin(Scenario.lectureScenario(
                "code/src/main/resources/data/scenario_1_1.txt",true));
       it2 = new Chemin(Scenario.lectureScenario(
                "code/src/main/resources/data/scenario_1_2.txt",true));
       it3 = new Chemin(Scenario.lectureScenario(
                "code/src/main/resources/data/scenario_2_1.txt",true));
    }

    @Test
    void testTousLesChemins(){
        assertEquals(it0.size(),2);
        assertEquals(it1.size(),22);
        assertEquals(it2.size(),186);
        assertEquals(it3.size(),11232);

        System.out.println("testTousLesChemins : ok\n" + "Le nombre d'itinéraire correspond à ce qui est attendu.");
    }

    @Test
    void testGetNextSource() {
        assertEquals(it0.getNextSource(),"Sabelettenote");
        assertEquals(it0.getNextSource(),"Sablaireaunote");

        assertEquals(it1.getNextSource(),"Soporifik");
        assertEquals(it1.getNextSource(),"Nénupiot");

        System.out.println("testGetNextSource : ok\n" + "Les sources obtenues sont les bonnes.");


    }

    @Test
    void estDerriere() {
        assert it0.estDerriere("Chenipan","Sabelettenote") == false; // Chenipan est devant Sabelettenote
        assert it0.estDerriere("Sabelettenote","Sablaireaunote") == true; // sont au même niveau
        assert it0.estDerriere("Sabelettenote","Chenipan") == true;    } //Chenipan est devant Sabelettenote

    @Test
    void tousPresent() {
        String [] array1 = {"Chenipan","Sabelettenote"};
        ArrayList<String> liste = new ArrayList<>(Arrays.asList(array1));
        assert it0.tousPresent(liste) == false;

        liste.add("Sablaireaunote");
        liste.add("Kokiyas");
        assert it0.tousPresent(liste) == true;

    }

    @Test
    void parcoursProgressif() {

    }

    @Test
    void getCurrentDistance() {
    }
}