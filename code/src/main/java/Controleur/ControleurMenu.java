package Controleur;

import javafx.scene.control.*;
import modele.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import vue.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControleurMenu implements EventHandler,IntitulesMenu {

    private final VBoxRoot root;

    public ControleurMenu(VBoxRoot root) throws IOException {
        this.root = root;
        ArrayList<String> listeScenario = SuiviScenario.getListeScenarioSuivi();

        // si aucun scenario enregistré pour l'instant, je les stocke.
        if (listeScenario == null) {
            Scenario s1 = Scenario.lectureScenario("code/src/main/resources/data/scenario_0.txt",true);
            Scenario s2 = Scenario.lectureScenario("code/src/main/resources/data/scenario_1_1.txt",true);
            Scenario s3 = Scenario.lectureScenario("code/src/main/resources/data/scenario_1_2.txt",true);
            Scenario s4 = Scenario.lectureScenario("code/src/main/resources/data/scenario_2_1.txt",true);
            Scenario s5 = Scenario.lectureScenario("code/src/main/resources/data/scenario_2_2.txt",true);
        }
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof MenuItem) {
            //"Nouveau Scénario"

            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[0][0]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxAjoutScenario)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            //Scénario Enregistrés
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[0][1]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxScenarioConnu)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            //Itinéraire --→ Tous les Itinéraires
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[2][1]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxAllItineraire)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            // Itinéraire --> Itinéraire Personnalisé
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[2][0]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxItinerairePerso)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            //Aide
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[3][0]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxAide)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            //Membres
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[1][0]) {
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof HBoxInfo)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }
        }
    }
}


