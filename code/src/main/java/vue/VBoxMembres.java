package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import modele.SuiviScenario;
import modele.Villes;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VBoxMembres extends VBox {
    TextField textField;
    Villes villes;

    public VBoxMembres() throws IOException {
        textField = new TextField();
        villes = new Villes();
        this.setId("opaque");

        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        ComboBox<String> comboBoxMembre = new ComboBox<>();
        comboBoxMembre.setEditable(true);
        comboBoxMembre.setItems(FXCollections.observableArrayList(liste));

        this.getChildren().add(comboBoxMembre);

    }
}
