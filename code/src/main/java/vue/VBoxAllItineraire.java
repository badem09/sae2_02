package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.Scenario;
import modele.SuiviScenario;
import modele.TempsItineraire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VBoxAllItineraire extends VBox {
    private Scenario currentScenario ;
    private TextArea textItineraire;
    private ComboBox<String> comboBoxScenario;
    private  int nbPages;
    private HBoxPagination pagination;
    private TempsItineraire tempsItineraire;

    public VBoxAllItineraire(){
        this.setId("opaque");
        this.setSpacing(10);

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
                    Itineraire it = new Itineraire(currentScenario);
                    tempsItineraire = new TempsItineraire(it);
                    // textItineraire.setText(tempsItineraire.toString(0,8));
                    nbPages = tempsItineraire.getNbPages();
                    pagination.setLabelCurrentPage("1");
                    textItineraire.setText(tempsItineraire.toString(0,8));
                    pagination.setLabelMaxPage(String.valueOf(nbPages));
                    System.out.println(nbPages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        this.getChildren().addAll(comboBoxScenario,textItineraire,pagination);
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
}
