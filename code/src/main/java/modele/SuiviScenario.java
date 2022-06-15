package modele;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sert à savoir quels scénarios ont été ajoutés a l'application jusqu'ici.
 */
public class SuiviScenario {

    private static ArrayList<String> listeScenarioSuivi;

    public SuiviScenario() throws IOException {
        listeScenarioSuivi = new ArrayList<>();
        RecupereListeSuivi(new File("src/main/resources/data/suivi_scenarios.txt"));
    }

    /**
     * Lit le fichier suivi_scenarios.txt pour récupérer les scénarios déja enregistrés.
     * S'il n'existe pas, la liste sera vide.
     * @param fileSuivi (String) : fichier de suivi.
     * @throws IOException (FileNotFoundException)
     */
    public static void RecupereListeSuivi(File fileSuivi) throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fileSuivi));
        String ligne;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null && ! listeScenarioSuivi.contains(ligne)) {
                listeScenarioSuivi.add(ligne);
            }
        }
        while (ligne != null);
    }

    /**
     * Récupère le fichier de suivi et y écrit le nom du scenario.
     * @param fileScenario (File) : fichier du scenario à enregistrer.
     * @throws IOException (FileNotFoundException).
     */
    public static void writeSuiviScenario(File fileScenario) throws  IOException {

        File suiviScenario = new File("src/main/resources/data/suivi_scenarios.txt");
        RecupereListeSuivi(suiviScenario);
        if (!listeScenarioSuivi.contains(fileScenario.getName())) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(suiviScenario, true));
            writer.write(fileScenario.getName());
            writer.newLine();
            writer.close();
            listeScenarioSuivi.add(fileScenario.getName());
            Collections.sort(listeScenarioSuivi);
        }
    }
    public static ArrayList<String> getListeScenarioSuivi() {
        return listeScenarioSuivi;
    }
}
