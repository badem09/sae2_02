package vue;

import Controleur.ControleurMenu;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.File;
import java.io.IOException;

public class VBoxMenu extends VBox implements IntitulesMenu {

    private ControleurMenu controleurMenu;
    private MenuBar chMenuBar;
    private Scenario scenario;
    private VBoxRoot vBoxRoot;


    private ComboBox<String> comboBoxScenario;
    public VBoxMenu(VBoxRoot parVboxRoot) throws IOException {
        vBoxRoot = parVboxRoot;
        controleurMenu = new ControleurMenu(this,parVboxRoot);
        System.out.println(this.getParent());
       // PageMain.getControleurStage().setVBoxMenu(this);
       // controleurStage = PageMain.getControleurStage();

        scenario = new Scenario();
        File suiviScenario = new File("src/main/resources/suivi_scenarios.txt");
        SuiviScenario.RecupereListeSuivi(suiviScenario);


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
        System.out.println(SuiviScenario.getListeScenarioSuivi());
        this.getChildren().addAll(chMenuBar);
    }

    public MenuBar getChMenuBar(){
        return  chMenuBar;
    }
    public ComboBox<String> getComboBoxScenario() {
        return comboBoxScenario;
    }


}
