package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.File;
import java.io.IOException;

public class VBoxAjoutPreview extends VBox {

    private VBoxMenu vBoxMenu;
    private GridPaneOrg gridPaneOrg;
    private ComboBox<String> comboBoxScenario;
    private Scenario scenario;
    private File selectedFile;

    public VBoxAjoutPreview(VBoxRoot root) throws IOException {
        this.setId("opaque");
        gridPaneOrg = new GridPaneOrg();

        Button buttonValider = new Button("Valider");
        buttonValider.setAccessibleText("validerAjoutScenario");
        buttonValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Scenario s = Scenario.lectureScenario(selectedFile,true);
                    StackPane stackPane = root.getStackPane();
                    int last = stackPane.getChildren().size() - 1;
                    while (!(stackPane.getChildren().get(last) instanceof VBoxScenarioConnu)) {
                        stackPane.getChildren().get(0).toFront();
                        root.getVboxScenario().setScenario(s);
                    }
                    Scenario.getSuiviScenario().writeSuiviScenario(selectedFile);
                    stackPane.getChildren().remove(last);
                    stackPane.getChildren().add(new VBoxScenarioConnu(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.getChildren().addAll(gridPaneOrg,buttonValider);
    }


    public void setScenario(Scenario scenario) throws IOException {
        gridPaneOrg.setScenario(scenario);
    }
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
}
