package vue;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ComboBox ayant pour éléments les scénarios engisgtrés jusqu'ici.
 */
public class ComboBoxScenario extends ComboBox<String> {

    public ComboBoxScenario(VBoxRoot root){
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        this.setItems(FXCollections.observableArrayList(liste));
        this.setValue("Séléctionnez votre Scénario");
        this.setOnAction(event -> {
            String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
            try {
                Scenario scenario = Scenario.lectureScenario("code/src/main/resources/data/" + scenarioCourant,false);
                root.getvBoxScenario().getGridPaneOrg().setScenario(scenario);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
