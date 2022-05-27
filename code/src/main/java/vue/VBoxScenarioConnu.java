package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;

public class VBoxScenarioConnu extends VBox {
    private VBoxMenu vBoxMenu;
    private GridPaneOrg gridPaneOrg;
   private  ComboBox<String> comboBoxScenario;

    public VBoxScenarioConnu(VBoxRoot root) throws IOException {
        this.setId("opaque");

        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(SuiviScenario.getListeScenarioSuivi()));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
                String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                try {
                    Scenario scenario = Scenario.lectureScenario("src/main/resources/" + scenarioCourant);
                    PageMain.getvBoxScenario().getGridPaneOrg().setScenario(scenario);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gridPaneOrg = new GridPaneOrg();
        this.getChildren().addAll(comboBoxScenario,gridPaneOrg);
    }

    public VBoxMenu getvBoxMenu() {
        return vBoxMenu;
    }

    public GridPaneOrg getGridPaneOrg() {
        return gridPaneOrg;
    }
}




