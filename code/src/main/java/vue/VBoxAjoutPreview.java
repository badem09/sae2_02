package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VBoxAjoutPreview extends VBox {

    private final GridPaneOrg gridPaneOrg;
    private File selectedFile;

    public VBoxAjoutPreview(VBoxRoot root){

        setId("opaque");
        setSpacing(20);
        setPadding(new Insets(20));

        gridPaneOrg = new GridPaneOrg(root);

        // Renvoie sur la page Scenario Enrgistrés.
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
                    SuiviScenario.writeSuiviScenario(selectedFile);
                    ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
                    ((VBoxScenarioConnu) stackPane.getChildren().get(last)).getCombo().setItems(
                            FXCollections.observableArrayList(liste));
                    root.getvBoxAllItineraire().updateCombo( FXCollections.observableArrayList(liste));
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
