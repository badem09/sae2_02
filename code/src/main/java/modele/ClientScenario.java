package modele;
import modele.Scenario;

import java.io.File;

public class ClientScenario {
    public static void main(String[] args) throws Exception{
        Scenario s1 = Scenario.lectureScenario("src/main/resources/scenario_0_1.txt",true);
        System.out.println(s1.toString());
        System.out.println(s1.getMembreToString());
        TempsItineraire ti = new TempsItineraire(new Itineraire(s1));
        System.out.println(ti.getBestItineraire());
        Scenario s2 = Scenario.lectureScenario("src/main/resources/scenario_1_1.txt",true);
        Scenario s3 = Scenario.lectureScenario("src/main/resources/scenario_1_2.txt",true);
        Scenario s4 = Scenario.lectureScenario("src/main/resources/scenario_2_1.txt",true);
        Scenario s5 = Scenario.lectureScenario("src/main/resources/scenario_2_2.txt",true);
    }

}