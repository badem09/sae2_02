package Controleur;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.Scenario;
import modele.TempsItineraire;
import modele.Villes;
import vue.VBoxItinerairePerso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControleurItinerairePerso implements EventHandler {

    private Map<String , TempsItineraire> mapItineraire;
    private VBoxItinerairePerso root ;

    private String currentSource ;

    private String prochaineSource;
    private ArrayList<String> currentPath;
    private Itineraire currentItineraire;
    private ArrayList<String> possibilitesCourantes;
    private Villes villes ;
    private TempsItineraire curentTempIt ;


    public ControleurItinerairePerso(VBoxItinerairePerso root , Map<String , TempsItineraire> parMap) throws IOException {
        this.root = root;
        this.mapItineraire = parMap;
        currentSource = "";
        prochaineSource = "";
        currentPath = new ArrayList<>();
        currentPath.add("PresidentDebut");
        villes = new Villes();

    }

    // bug quand appuie 2 fois sur valider sans radioButton



    @Override
    public void handle(Event event) {

        if (event.getSource() instanceof ComboBox<?>) {
            String fileName = (String) ((ComboBox<?>) event.getSource())
                    .getSelectionModel().getSelectedItem();

            try {
                Scenario scenario = Scenario.lectureScenario("src/main/resources/" + fileName, false);
                if (mapItineraire.containsKey(fileName)) {
                    currentItineraire = mapItineraire.get(scenario).getItineraire();
                    curentTempIt = mapItineraire.get(scenario);
                } else {
                    currentItineraire = new Itineraire(scenario);
                    curentTempIt = new TempsItineraire(currentItineraire);
                }

                System.out.println(curentTempIt);
                possibilitesCourantes = currentItineraire.parcoursProgressif(currentSource,currentPath);
                    VBox vBox = new VBox();
                    ToggleGroup toggleGroup = new ToggleGroup();
                    for (String elem : possibilitesCourantes) {
                        RadioButton radioButton = new RadioButton(elem);
                        radioButton.setOnAction(this);
                        radioButton.setToggleGroup(toggleGroup);
                        vBox.getChildren().add(radioButton);
                    }
                    root.getScrollPossibilites().setContent(vBox);
            }
             catch(IOException e){
                    throw new RuntimeException(e);
            }
        }

        if (event.getSource() instanceof RadioButton){

            currentSource = ((RadioButton) event.getSource()).getText();
            System.out.println(currentSource);
        }

        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getText() == "Valider" && ! currentPath.contains(currentSource)){
                currentPath.add(currentSource);
                root.getTextItineraire().setText(curentTempIt.getCurrentDistance(currentPath));
                root.getTextMembres().appendText(currentSource + " : " +
                        villes.getMembreToVilles().get(currentSource) + "\n");
                ArrayList<String> sautes = (ArrayList<String>) possibilitesCourantes.clone();
                sautes.remove(currentSource);
                possibilitesCourantes = currentItineraire.parcoursProgressif(currentSource,currentPath);

                // pour merge sans duplicates
                sautes.removeAll(possibilitesCourantes);
                possibilitesCourantes.addAll(sautes);
                VBox vBox = new VBox();
                if (possibilitesCourantes.size() > 0) {

                    ToggleGroup toggleGroup = new ToggleGroup();
                    for (String elem : possibilitesCourantes) {
                        RadioButton radioButton = new RadioButton(elem);
                        radioButton.setOnAction(this);
                        radioButton.setToggleGroup(toggleGroup);
                        vBox.getChildren().add(radioButton);
                    }
                }
                else {
                    vBox.getChildren().add(new Label("Vous êtes arrivés"));
                    currentPath.add("PresidentFin");
                    root.getTextItineraire().setText(curentTempIt.getCurrentDistance(currentPath));
                }
                root.getScrollPossibilites().setContent(vBox);
            }
        }
    }
}
