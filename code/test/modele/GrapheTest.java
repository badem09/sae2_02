package modele;

import modele.Graphes;
import modele.Scenario;
import org.junit.jupiter.api.Test;
import modele.Scenario;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException;

public class GrapheTest {
    GrapheTest() throws IOException {
    }
    Scenario s = Scenario.lectureScenario("code/src/main/resources/data/scenario_0.txt",false);
    Graphes g = new Graphes(s);
    HashMap<String,ArrayList<String>> dicoScenario = s.getDicoAV();
    HashMap<String,ArrayList<String>> dicoGraphe = s.getDicoAV();


    @Test
    void testIndependantAjout() {
        dicoScenario.put("helloScenario",new ArrayList<>());
        dicoGraphe.put("helloGraphe",new ArrayList<>());

        assert ! (dicoScenario.containsKey("helloGraphe"));
        assert ! (dicoGraphe.containsKey("helloScenario"));
        System.out.println("testIndependantAjout : ok" + "\n" + "L'ajout dans un  dictionnaire n'impacte pas l'autre"
        + "\n");
    }


    @Test
    void testDifferentAdr(){
        String adrDicoScenario = String.valueOf(System.identityHashCode(dicoScenario));
        String adrDicoGraphe = Integer.toHexString(System.identityHashCode(g.getMapAjdEntrant()));
        assertNotEquals(adrDicoGraphe,adrDicoScenario);
        System.out.println("testDifferentAdr : ok" + "\n" + "Les dictionnaires n'ont pas la même adresse mémoire\n");
    }
}
