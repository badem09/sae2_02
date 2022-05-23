package vue;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class VBoxMenu extends VBox implements IntitulesMenu {

    public VBoxMenu() {
        MenuBar menuBar = new MenuBar();
        int i = 0;
        for(String intitules : ITEM_MENU) {
            Menu menu = new Menu(intitules);
            menuBar.getMenus().add(menu);
            for(int j = 0; j < SOUS_MENU[i].length; j++){
                MenuItem menuItem = new MenuItem(SOUS_MENU[i][j]);
                menuItem.setUserData(j);
                menuBar.getMenus().get(i).getItems().add(menuItem);
            }
            i++;
        }
        this.getChildren().add(menuBar);
    }

}
