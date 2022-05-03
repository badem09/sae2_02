package modele;
import modele.Scenario;

import java.io.File;

public class ClientScenario {
    public static void main(String[] args) throws Exception{
        Scenario s1 = new Scenario();
        s1 = Scenario.lectureSenario(new File(
                "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/scenario_1_1.txt"));
        System.out.println(s1.getVilles().getTabDistances());


    }

}