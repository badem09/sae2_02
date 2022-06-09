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

    @Test
    void test1(){
        System.out.println(Integer.toHexString(g.hashCode()));
        System.out.println(Integer.toHexString(s.getDicoAV().hashCode()));
        assertNotEquals(s.getDicoAV().hashCode(),g.getMapAjdEntrant());

    }


}
