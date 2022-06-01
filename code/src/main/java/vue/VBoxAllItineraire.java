package vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.Scenario;
import modele.SuiviScenario;
import modele.TempsItineraire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VBoxAllItineraire extends VBox {
    private Scenario currentScenario ;
    private TextArea textItineraire;
    private ComboBox<String> comboBoxScenario;
    private  int nbPages;
    private HBoxPagination pagination;
    private TempsItineraire tempsItineraire;
    private Label labelNbItineraire;
    private VBoxRoot root ;
    private Map<String,TempsItineraire> mapItineraire;

    public VBoxAllItineraire(VBoxRoot parRoot){
        this.setId("opaque");
        this.setSpacing(10);
        mapItineraire = new HashMap<>();
        root = parRoot;
        labelNbItineraire = new Label();
        pagination = new HBoxPagination(this);
        textItineraire = new TextArea();
        textItineraire.setPrefHeight(570);
        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
        comboBoxScenario.setValue("Séléctionner votre Scénario");
        comboBoxScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //   ((ComboBox<?>) event.getSource()).setVisible(isVisible());
                System.out.println(((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem());
                String scenarioCourant = (String) ((ComboBox<?>) event.getSource()).getSelectionModel().getSelectedItem();
                try {
                    currentScenario = Scenario.lectureScenario("src/main/resources/" + scenarioCourant,false);
                  //  Itineraire it = new Itineraire(currentScenario);
                   // tempsItineraire = new TempsItineraire(it);
                   // tempsItineraire = root.getVboxScenario().getGridPaneOrg().getMapItineraire().get(scenarioCourant);
                    // textItineraire.setText(tempsItineraire.toString(0,8));
                    if ( ! mapItineraire.containsKey(scenarioCourant)){
                        Itineraire it = new Itineraire(currentScenario);
                        tempsItineraire = new TempsItineraire(it);
                        root.getVboxScenario().getGridPaneOrg().updateMapItineraire(scenarioCourant,tempsItineraire);
                    }
                    else {
                        tempsItineraire = mapItineraire.get(scenarioCourant);
                    }
                    nbPages = tempsItineraire.getNbPages();
                    pagination.setLabelCurrentPage("1");
                    textItineraire.setText(tempsItineraire.toString(0,8));
                    pagination.setLabelMaxPage(String.valueOf(nbPages));
                    labelNbItineraire.setText("Total : " + tempsItineraire.getNbItineraire() + " itinéraires");
                    System.out.println(nbPages);
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
        this.getChildren().addAll(vBoxTitre,comboBoxScenario,textItineraire,paneTotal);
    }

    public TextArea getTextItineraire() {
        return textItineraire;
    }
    public Scenario getCurrentScenario(){
        return currentScenario;
    }

    public TempsItineraire getTempsItineraire() {
        return tempsItineraire;
    }

    public void updateCombo(ObservableList<String> liste){
        comboBoxScenario.setItems(liste);
    }

    public void updateMapItineraire(String fileName , TempsItineraire ti){
        mapItineraire.put(fileName,ti);
    }
}
