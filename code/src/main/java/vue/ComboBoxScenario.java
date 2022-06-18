package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ComboBoxScenario extends ComboBox {
    private ComboBox<String> comboBoxScenario ;


    public ComboBoxScenario() throws IOException {
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        this.setItems(FXCollections.observableArrayList(liste));
        this.setValue("Séléctionnez votre Scénario");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
                String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                try {
                    Scenario scenario = Scenario.lectureScenario("src/main/resources/data/" + scenarioCourant,false);
                    PageMain.getvBoxScenario().getGridPaneOrg().setScenario(scenario);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
