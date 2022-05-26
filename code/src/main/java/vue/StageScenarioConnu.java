package vue;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Scenario;

import java.io.FileInputStream;
import java.io.IOException;

public class StageScenarioConnu extends Stage {
    private VBoxMenu vBoxMenu;
    private GridPaneOrg gridPaneOrg;




    public StageScenarioConnu() throws IOException {


        VBox vBox = new VBox();
        vBoxMenu = new VBoxMenu();
        ComboBox<String> comboScenario = new ComboBox<>();
        gridPaneOrg = new GridPaneOrg();
        vBox.getChildren().addAll(vBoxMenu, gridPaneOrg);
        Scene scene = new Scene(vBox, 900, 550);
        this.setScene(scene);
        this.getIcons().add(new Image(new FileInputStream(
                "src/main/resources/pokeman.png")));
    }

    public GridPaneOrg getGridPaneOrg() {
        return gridPaneOrg;
    }

    public VBoxMenu getvBoxMenu() {
        return vBoxMenu;
    }
}
