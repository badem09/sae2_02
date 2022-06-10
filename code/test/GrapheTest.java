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
    Scenario s = Scenario.lectureScenario(

            "src/main/resources/scenario_0.txt");
    Graphes g = new Graphes(s);

    String adrDicoGraphe = Integer.toHexString(g.getMapAjdEntrant().hashCode());
    String adrDicoScenario = Integer.toHexString(s.getDicoAV().hashCode());


    @Test
    void test1(){
        assertNotEquals(adrDicoGraphe,adrDicoScenario);
        System.out.println("La fonction est correcte, grosse dedicace a dembou bou");

    }


}
