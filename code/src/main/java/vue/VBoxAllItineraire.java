package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modele.Chemin;
import modele.Itineraire;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VBoxAllItineraire extends VBox {
    private Scenario currentScenario ;
    private final TextArea textItineraire;
    private final ComboBox<String> comboBoxScenario;
    private  int nbPages;
    private final HBoxPagination pagination;
    private Itineraire itineraire;
    private final Label labelNbItineraire;
    private final VBoxRoot root ;
    private final Map<String, Itineraire> mapItineraire;

    public VBoxAllItineraire(VBoxRoot parRoot){

        this.setId("opaque");
        this.setSpacing(10);
        mapItineraire = new HashMap<>();
        root = parRoot;
        labelNbItineraire = new Label();
        pagination = new HBoxPagination(this);
        textItineraire = new TextArea();
        textItineraire.setPrefHeight(570);
        textItineraire.setWrapText(true);
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                try {
                    currentScenario = Scenario.lectureScenario("code/src/main/resources/data/" + scenarioCourant,false);
                    if ( ! mapItineraire.containsKey(scenarioCourant)){
                        Chemin it = new Chemin(currentScenario);
                        itineraire = new Itineraire(it);
                        root.getVboxScenario().getGridPaneOrg().updateMapItineraire(scenarioCourant, itineraire);
                    }
                    else {
                        itineraire = mapItineraire.get(scenarioCourant);
                    }
                    nbPages = itineraire.getNbPages();
                    pagination.setLabelCurrentPage("1");
                    textItineraire.setText(itineraire.toString(0,8));
                    pagination.setLabelMaxPage(String.valueOf(nbPages));
                    labelNbItineraire.setText("Total : " + itineraire.getNbItineraire() + " itinéraires");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Label labelTitre = new Label("Tous les itinéraires");
        labelTitre.setAlignment(Pos.TOP_CENTER );
        VBox vBoxTitre = new VBox(labelTitre);
        vBoxTitre.setAlignment(Pos.CENTER);
        labelNbItineraire.setAlignment(Pos.BOTTOM_RIGHT);
        BorderPane paneTotal = new BorderPane();
        paneTotal.setRight(labelNbItineraire);
        paneTotal.setLeft(pagination);
        setPadding(new Insets(20));
        setSpacing(10);
        this.getChildren().addAll(vBoxTitre,comboBoxScenario,textItineraire,paneTotal);
    }

    public TextArea getTextItineraire() {
        return textItineraire;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void updateCombo(ObservableList<String> liste){
        comboBoxScenario.setItems(liste);
    }

    public void updateMapItineraire(String fileName , Itineraire ti){
        mapItineraire.put(fileName,ti);
    }
}
