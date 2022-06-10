package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Scenario;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class VBoxAjoutScenario extends VBox {
    private TextArea textAreaScenario;
    private Scenario scenario;
    private File selectedFile;

    public VBoxAjoutScenario(Stage stage,VBoxRoot root){
        FileChooser fileChooser = new FileChooser();
        textAreaScenario = new TextArea();
        Label labelTransition = new Label("Le fichier séléctionné : ");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Button button = new Button("Choisissez votre Fichier");
        button.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(stage);
          //  textAreaScenario.setText(String.valueOf(selectedFile));
            try {
                String content = Files.readString(Path.of(selectedFile.getPath()));
                //scenario = Scenario.lectureScenario(String.valueOf(selectedFile));
                textAreaScenario.setText(content);
                textAreaScenario.setWrapText(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button buttonToScenario = new Button("Le convertir en scénario");
        buttonToScenario.setAccessibleText("FileToScenario");
        buttonToScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                StackPane stackPane = root.getStackPane();
                try {
                    Scenario s = Scenario.lectureScenario(selectedFile,false);
                    root.getvBoxAjoutPreview().setScenario(s);
                    root.getvBoxAjoutPreview().setSelectedFile(selectedFile);
                    int last = stackPane.getChildren().size() - 1;
                    while (!(stackPane.getChildren().get(last) instanceof VBoxAjoutPreview)) {
                        stackPane.getChildren().get(0).toFront();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    Alert mauvaisformat = new Alert(Alert.AlertType.ERROR);
                    mauvaisformat.setHeaderText("Mauvais format");
                    mauvaisformat.setContentText("Le contenu du fichier n'est pas approprié." +
                            "\n" + "Veuillez visitez l'aide pour plus d'informations");
                    mauvaisformat.showAndWait();
                   // throw new NoSuchElementException(e);
                }
            }
        });
        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setScene(scene);
        stage.show();

        Label labelTitre = new Label("L'ajout d'un fichier");
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);

        this.getChildren().addAll(vBoxTitre,button,labelTransition, textAreaScenario, buttonToScenario);
        this.setSpacing(10);
        this.setId("opaque");

    }
}
