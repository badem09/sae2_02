package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private VBoxMenu vBoxMenu;
    private GridPaneOrg gridPaneOrg;
   private  ComboBox<String> comboBoxScenario;

    public VBoxScenarioConnu(VBoxRoot root) throws IOException {
        this.setId("opaque");

        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
                String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                try {
                    Scenario scenario = Scenario.lectureScenario("src/main/resources/" + scenarioCourant,false);
                    PageMain.getvBoxScenario().getGridPaneOrg().setScenario(scenario);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gridPaneOrg = new GridPaneOrg();
        Label labelTitre = new Label("Les scénarios enregistrés.");
        labelTitre.setLabelFor(gridPaneOrg);
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBoxTitre,comboBoxScenario,gridPaneOrg);
    }

    public VBoxMenu getvBoxMenu() {
        return vBoxMenu;
    }

    public GridPaneOrg getGridPaneOrg() {
        return gridPaneOrg;
    }

    public void setScenario(Scenario parScenario) throws IOException {
        PageMain.getvBoxScenario().getGridPaneOrg().setScenario(parScenario);
    }
}




