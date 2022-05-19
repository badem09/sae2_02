package modele;
import modele.Scenario;

import java.io.File;

public class ClientScenario {
    public static void main(String[] args) throws Exception{
        Scenario s1 = new Scenario();
        s1 = Scenario.lectureScenario(new File("src/main/resources/scenario_0.txt"));
        System.out.println(s1.getDicoAV());
        System.out.println(s1.getDicoVA());


    }

}