package vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Scenario;

import java.io.File;

public class VBoxAjoutScenario extends VBox {
    private TextArea textAreaScenario;
    private Scenario scenario;

    public VBoxAjoutScenario(Stage stage){
        FileChooser fileChooser = new FileChooser();
        textAreaScenario = new TextArea("A venir...");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            textAreaScenario.setText(String.valueOf(selectedFile));
           // scenario = Scenario.lectureScenario(String.valueOf(selectedFile));
        });

        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setScene(scene);
        stage.show();
        this.getChildren().addAll(button,textAreaScenario);
        this.setId("opaque");

    }
}
