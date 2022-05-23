package vue;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class VBoxMenu extends VBox implements IntitulesMenu {

    public VBoxMenu() {
        MenuBar menuBar = new MenuBar();
        for(String intitules : ITEM_MENU) {
            Menu menu = new Menu(intitules);
            menuBar.getMenus().add(menu);
            if (menuBar.getMenus().indexOf(menu) == 0){
                for(int i = 0; i < SOUS_MENU.length; i++){
                    MenuItem menuItem = new MenuItem(SOUS_MENU[i]);
                    menuItem.setUserData(i);
                    menuBar.getMenus().get(0).getItems().add(menuItem);
                }
            }
        }


        this.getChildren().add(menuBar);
    }

}
