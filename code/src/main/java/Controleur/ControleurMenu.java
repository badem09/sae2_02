package Controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import modele.Scenario;
import modele.SuiviScenario;
import vue.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControleurMenu implements EventHandler,IntitulesMenu {

    private VBoxRoot root;
    private VBoxMenu vBoxMenu;
    private Scenario scenario;
    private ArrayList<String> listeScenario;

    public ControleurMenu(VBoxMenu parVBox, VBoxRoot root) throws IOException {
        this.root = root;
        vBoxMenu = parVBox;
        listeScenario = SuiviScenario.getListeScenarioSuivi();
        if (listeScenario == null) {
            Scenario s1 = Scenario.lectureScenario("src/main/resources/scenario_0.txt",true);
            Scenario s2 = Scenario.lectureScenario("src/main/resources/scenario_1_1.txt",true);
            Scenario s3 = Scenario.lectureScenario("src/main/resources/scenario_1_2.txt",true);
            Scenario s4 = Scenario.lectureScenario("src/main/resources/scenario_2_1.txt",true);
            Scenario s5 = Scenario.lectureScenario("src/main/resources/scenario_2_2.txt",true);
        }
    }

    @Override
    public void handle(Event event) {
        //"Scénarios connus"
        MenuBar menuBar = vBoxMenu.getChMenuBar();
        if (event.getSource() instanceof MenuItem) {
            System.out.println(((MenuItem) event.getSource()).getUserData());

            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[0][0]) {
                System.out.println(SuiviScenario.getListeScenarioSuivi());
                System.out.println(((MenuItem) event.getSource()).getUserData());
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxAjoutScenario)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }

            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[0][1]) {
                System.out.println(SuiviScenario.getListeScenarioSuivi());
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxScenarioConnu)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }
            if (((MenuItem) event.getSource()).getUserData() == SOUS_MENU[1][0]) {
                System.out.println(SuiviScenario.getListeScenarioSuivi());
                System.out.println(((MenuItem) event.getSource()).getUserData());
                StackPane stackPane = root.getStackPane();
                int last = stackPane.getChildren().size() - 1;
                while (!(stackPane.getChildren().get(last) instanceof VBoxAllItineraire)) {
                    stackPane.getChildren().get(0).toFront();
                }
            }
        }
    }
}


