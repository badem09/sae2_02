package modele;

public class ClientScenario {
    public static void main(String[] args) throws Exception{
        Scenario s1 = Scenario.lectureScenario("src/main/resources/data/scenario_0.txt",true);
        Scenario s2 = Scenario.lectureScenario("src/main/resources/data/scenario_1_1.txt",true);
        System.out.println(s1.getMembreToString());
        System.out.println(s2.getMembreToString());

    }

}