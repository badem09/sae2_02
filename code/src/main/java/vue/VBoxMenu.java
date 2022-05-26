package vue;

import Controleur.ControleurMenu;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

public class VBoxMenu extends VBox implements IntitulesMenu {

    private ControleurMenu controleurMenu;
    private MenuBar chMenuBar;
    private Scenario scenario;


    private ComboBox<String> comboBoxScenario;
    public VBoxMenu() throws IOException {
        controleurMenu = new ControleurMenu(this);
       // PageMain.getControleurStage().setVBoxMenu(this);
       // controleurStage = PageMain.getControleurStage();

        scenario = new Scenario();
        File suiviScenario = new File("src/main/resources/suivi_scenarios.txt");
        scenario.getListeSuivi(suiviScenario);
        comboBoxScenario =
                new ComboBox<>( FXCollections.observableArrayList(scenario.getListScenarioConnus()));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(controleurMenu);

        chMenuBar = new MenuBar();
        int i = 0;
        for(String intitules : ITEM_MENU) {
            Menu menu = new Menu(intitules);
            chMenuBar.getMenus().add(menu);
            for(int j = 0; j < SOUS_MENU[i].length; j++){
                MenuItem menuItem = new MenuItem(SOUS_MENU[i][j]);
                menuItem.setUserData(SOUS_MENU[i][j]);
                menuItem.setOnAction(controleurMenu);
                chMenuBar.getMenus().get(i).getItems().add(menuItem);
            }
            i++;
        }
        System.out.println(scenario.getListScenarioConnus());
        this.getChildren().addAll(chMenuBar,comboBoxScenario);
    }

    public MenuBar getChMenuBar(){
        return  chMenuBar;
    }
    public ComboBox<String> getComboBoxScenario() {
        return comboBoxScenario;
    }


}
