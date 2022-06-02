package vue;

import Controleur.ControleurItinerairePerso;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.Scenario;
import modele.SuiviScenario;
import modele.TempsItineraire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class VBoxItinerairePerso extends VBox {

  //  private VBoxMenu vBoxMenu;

    private ComboBox<String> comboBoxScenario;
    private ScrollPane scrollPossibilites ;

    private Map<String , TempsItineraire> mapItineraire;
    private Itineraire currentItineraire ;
    private TextArea textItineraire;
    private TextArea textMembres;
    private ControleurItinerairePerso controleur ;

    public VBoxItinerairePerso(VBoxRoot root) throws IOException {
        this.setId("opaque");
        mapItineraire = root.getVboxScenario().getGridPaneOrg().getMapItineraire();
        controleur = new ControleurItinerairePerso(this,mapItineraire);

        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");

        comboBoxScenario.setOnAction(controleur);
        Label labelTitre = new Label("Votre itinéraire personnalisé.");
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label labelPossibilite = new Label("Les prochains membres possibles :");
        Label labelMembre = new Label("Les membres rentrés jusqu'ici :");
        Label labelItineraire = new Label("L'itinéraire jusqu'ici :");

        scrollPossibilites = new ScrollPane();
        textItineraire = new TextArea();
        textMembres = new TextArea();

        scrollPossibilites.setPrefHeight(300);
        scrollPossibilites.setPrefWidth(715);

        int ligne = 0;

        gridPane.add(labelPossibilite,0,ligne,1,1);
        gridPane.add(labelMembre,1,ligne,1,1);
        ligne ++;
        gridPane.add(scrollPossibilites,0,ligne,1,1);
        gridPane.add(textMembres,1,ligne,1,1);
        ligne ++;
        gridPane.add(labelItineraire,0,ligne,1,1);
        gridPane.add(textItineraire,0,ligne+1,2,1);

        Button valider = new Button("Valider");
        valider.setOnAction(controleur);

        this.getChildren().addAll(vBoxTitre,comboBoxScenario,gridPane,valider);
    }

    public ScrollPane getScrollPossibilites() {
        return scrollPossibilites;
    }

    public TextArea getTextItineraire() {
        return textItineraire;
    }

    public TextArea getTextMembres() {
        return textMembres;
    }
}
