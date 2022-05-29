package modele;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SuiviScenario {
    private static ArrayList<String> listeScenarioSuivi;

    public SuiviScenario() throws IOException {
        listeScenarioSuivi = new ArrayList<>();
        RecupereListeSuivi(new File("src/main/resources/suivi_scenarios.txt"));
    }
    public static boolean RecupereListeSuivi(File fileSuivi) throws IOException {
        fileSuivi.createNewFile();
        if (listeScenarioSuivi == null) {
            listeScenarioSuivi = new ArrayList<>();
        }
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fileSuivi));
        String ligne;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null && ! listeScenarioSuivi.contains(ligne)) {
                listeScenarioSuivi.add(ligne);
            }
        }
        while (ligne != null);
        return listeScenarioSuivi.size() > 0;
    }

    public static void writeSuiviScenario(File fileScenario) throws  IOException {

        File suiviScenario = new File("src/main/resources/suivi_scenarios.txt");
        boolean nouveau = suiviScenario.createNewFile();
        //System.out.println(nouveau);

        RecupereListeSuivi(suiviScenario);
        // System.out.println(suiviScenario);
        if (!listeScenarioSuivi.contains(fileScenario.getName())) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(suiviScenario, true));
            writer.write(fileScenario.getName());
            writer.newLine();
            writer.close();
            listeScenarioSuivi.add(fileScenario.getName());
            Collections.sort(listeScenarioSuivi);
        }

   //     System.out.println(listeScenarioSuivi);
    }
    public static ArrayList<String> getListeScenarioSuivi() {
        return listeScenarioSuivi;
    }

}
