package Controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import modele.Scenario;
import vue.PageMain;
import vue.VBoxMenu;

import java.io.IOException;
import java.util.ArrayList;

public class ControleurMenu implements EventHandler {

    private VBoxMenu vBoxMenu;
    private Scenario scenario;
    private Scenario s1;
    private Scenario s2;
    private Scenario s3;
    private Scenario s4;
    private Scenario s5;
    private ArrayList<Scenario> listeScenario;

    public ControleurMenu(VBoxMenu parVBoxMenu) throws IOException {
        vBoxMenu = parVBoxMenu;
        listeScenario = new ArrayList<>();
        s1 = Scenario.lectureScenario("src/main/resources/scenario_0.txt");
        s2 = Scenario.lectureScenario("src/main/resources/scenario_1_1.txt");
        s3 = Scenario.lectureScenario("src/main/resources/scenario_1_2.txt");
        s4 = Scenario.lectureScenario("src/main/resources/scenario_2_1.txt");
        s5 = Scenario.lectureScenario("src/main/resources/scenario_2_2.txt");
        listeScenario.add(s1);
        listeScenario.add(s2);
        listeScenario.add(s3);
        listeScenario.add(s4);
        listeScenario.add(s5);
    }

    @Override
    public void handle(Event event) {

        MenuBar menuBar = vBoxMenu.getChMenuBar();
        if (event.getSource() instanceof MenuItem){
            System.out.println(scenario.getListScenarioConnus());
            System.out.println(((MenuItem) event.getSource()).getUserData());
        }
        if (event.getSource() instanceof ComboBox<?>) {
            System.out.println ( ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
            String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
            System.out.println(event.getSource());
            try {
                scenario = listeScenario.get(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedIndex());
                PageMain.getCurrentStage().getGridPaneOrg().setScenario(scenario);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
