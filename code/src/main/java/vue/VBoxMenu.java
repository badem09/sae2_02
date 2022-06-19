package vue;

import Controleur.ControleurMenu;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.*;
import java.io.File;
import java.io.IOException;

public class VBoxMenu extends VBox implements IntitulesMenu {

    private ControleurMenu controleurMenu;
    private final MenuBar chMenuBar;
    public VBoxMenu(VBoxRoot parVboxRoot) throws IOException {

        controleurMenu = new ControleurMenu(this,parVboxRoot);
        File suiviScenario = new File("src/main/resources/data/suivi_scenarios.txt");
        SuiviScenario.RecupereListeSuivi(suiviScenario);
        chMenuBar = new MenuBar();
        int i = 0;

        for(String intitules : MENUS) {
            Menu menu = new Menu(intitules);
            menu.setUserData(intitules);
            menu.setMnemonicParsing(true);
            menu.setOnAction(controleurMenu);
            chMenuBar.getMenus().add(menu);
            for(int j = 0; j < SOUS_MENU[i].length; j++){
                MenuItem menuItem = new MenuItem(SOUS_MENU[i][j]);
                menuItem.setUserData(SOUS_MENU[i][j]);
                menuItem.setOnAction(controleurMenu);
                menuItem.setMnemonicParsing(true);
                chMenuBar.getMenus().get(i).getItems().add(menuItem);
            }
            i++;

            if (intitules == "_Aide" || intitules == "_Membres"){
                // sert à ne pas afficher le menuItem et mais à éxecuter son action associé automatiquement.
                // Son action devient celle du menu.
                menu.showingProperty().addListener(
                        (observableValue, oldValue, newValue) -> {
                            if (newValue) {
                                menu.getItems().get(0).fire();
                            }
                        }
                );
            }
        }
        this.getChildren().addAll(chMenuBar);
    }
}
