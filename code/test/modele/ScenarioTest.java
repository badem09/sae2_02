package modele;

import modele.Scenario;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    ScenarioTest() throws IOException {
    }
    Scenario s1 = new Scenario();

    Scenario s2 = Scenario.lectureScenario("code/src/main/resources/data/scenario_0.txt",true);
    @Test
    void testLectureScenarioNotNull()  {
        assertNotEquals(s1, s2);
        System.out.println("testLectureScenarioNotNull : ok \nLe Scénario récupéré puis instancié" +
                " n'est pas null. \n");
    }

    @Test
    void testLectureScenarioCoherent() {
        /*Teste la méthode ajoutVA() */
        String[] attenduA =  new String[] {"Kokiyas", "Chenipan","Kokiyas" };
        String[] attenduV =  new String[] {"Sabelettenote", "Kokiyas", "Sablaireaunote" };

        List<String> recuA =  s2.getAcheteurs();
        List<String> recuV =  s2.getVendeurs();

        Random r = new Random();

        //test au hasard dans la liste
        for (int i=0;i<3;i++){
            int j = r.nextInt(attenduA.length - 1 ) + 1;
            assertEquals( attenduA[j], recuA.get(j) );
            assertEquals( attenduV[j], recuV.get(j) );
        }
        System.out.println("testLectureScenarioCoherent : ok \nLe Scénario instancié" +
                " est cohérent au fichier .txt fournit (ici scenario_0.txt). \n");
   }

}