package vue;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class StageScenarioConnu extends Stage {

    public StageScenarioConnu() throws IOException {
        VBox vBox = new VBox();
        VBoxMenu vBoxMenu = new VBoxMenu();
        ComboBox<String> comboScenario = new ComboBox<>();
        GridPaneOrg gridPaneOrg = new GridPaneOrg();
        vBox.getChildren().addAll(vBoxMenu, gridPaneOrg);
        Scene scene = new Scene(vBox, 900, 550);
        this.setScene(scene);
        this.getIcons().add(new Image(new FileInputStream(
                "src/main/resources/pokeman.png")));

    }

}
