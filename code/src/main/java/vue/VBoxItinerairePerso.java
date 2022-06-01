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

public class VBoxItinerairePerso extends VBox {

  //  private VBoxMenu vBoxMenu;

    private ComboBox<String> comboBoxScenario;

    public VBoxItinerairePerso() {
        this.setId("opaque");
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
            }
        });
        Label labelTitre = new Label("Votre itinéraire personnalisé.");
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBoxTitre,comboBoxScenario);
    }

}
