package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VBoxScenarioConnu extends VBox {

    private final GridPaneOrg gridPaneOrg;
    private final ComboBox<String> comboBoxScenario;
    private final VBoxRoot root;

    public VBoxScenarioConnu(VBoxRoot root) throws IOException {
        this.setId("opaque");

        this.root = root;
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem() != null) {
                    String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                    try {
                        Scenario scenario = Scenario.lectureScenario("code/src/main/resources/data/" + scenarioCourant, false);
                        root.getvBoxScenario().getGridPaneOrg().setScenario(scenario);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        gridPaneOrg = new GridPaneOrg(root);
        Label labelTitre = new Label("Les scénarios enregistrés.");
        labelTitre.setLabelFor(gridPaneOrg);
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBoxTitre,comboBoxScenario,gridPaneOrg);
        this.setFillWidth(true);
        this.setPadding(new Insets(20));
        setSpacing(10);
    }

    public GridPaneOrg getGridPaneOrg() {
        return gridPaneOrg;
    }

    public void setScenario(Scenario parScenario) throws IOException {
        root.getvBoxScenario().getGridPaneOrg().setScenario(parScenario);
    }

    public ComboBox<String> getCombo(){
        return comboBoxScenario;
    }
}




