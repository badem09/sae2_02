package Controleur;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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
    private ArrayList<String> distanceCourantes;


    public ControleurItinerairePerso(VBoxItinerairePerso root , Map<String , TempsItineraire> parMap) throws IOException {
        this.root = root;
        this.mapItineraire = parMap;
        prochaineSource = "";
        villes = new Villes();

    }

    // bug quand appuie 2 fois sur valider sans radioButton



    @Override
    public void handle(Event event) {

        if (event.getSource() instanceof ComboBox<?>) {
            String fileName = (String) ((ComboBox<?>) event.getSource())
                    .getSelectionModel().getSelectedItem();
            root.getTextMembres().clear();
            root.getTextItineraire().clear();


            try {
                Scenario scenario = Scenario.lectureScenario("src/main/resources/" + fileName, false);
                if (mapItineraire.containsKey(fileName)) {
                    currentItineraire = mapItineraire.get(fileName).getItineraire();
                    curentTempIt = mapItineraire.get(fileName);
                    currentSource = "";
                    currentPath = new ArrayList<>();
                    currentPath.add("PresidentDebut");
                } else {
                    currentItineraire = new Itineraire(scenario);
                    curentTempIt = new TempsItineraire(currentItineraire);
                    mapItineraire.put(fileName,curentTempIt);
                    currentSource = "";
                    currentPath = new ArrayList<>();
                    currentPath.add("PresidentDebut");
                }
                possibilitesCourantes = currentItineraire.parcoursProgressif(currentSource,currentPath);
                distanceCourantes = currentItineraire.getCurrentDistance(currentSource,possibilitesCourantes);
                    VBox vBox = new VBox();
                    vBox.setSpacing(5);
                    ToggleGroup toggleGroup = new ToggleGroup();
                    for (int i = 0; i < possibilitesCourantes.size() ; i++) {
                        String prochain = possibilitesCourantes.get(i);
                        RadioButton radioButton = new RadioButton(prochain);
                        radioButton.setOnAction(this);
                        radioButton.setToggleGroup(toggleGroup);
                        vBox.getChildren().add(new HBox(radioButton,new Label( " (" +
                                villes.getMembreToVilles().get(prochain) + ")" + " : " +
                                distanceCourantes.get(i) + " km")));
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
                try {
                    distanceCourantes = currentItineraire.getCurrentDistance(currentSource,possibilitesCourantes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // pour merge sans duplicates
                sautes.removeAll(possibilitesCourantes);
                possibilitesCourantes.addAll(sautes);
                VBox vBox = new VBox();
                vBox.setSpacing(5);
                System.out.println(distanceCourantes);
                if (possibilitesCourantes.size() > 0) {
                    ToggleGroup toggleGroup = new ToggleGroup();
                    for (int i = 0; i<possibilitesCourantes.size() ; i++) {
                        RadioButton radioButton = new RadioButton(possibilitesCourantes.get(i));
                        radioButton.setOnAction(this);
                        radioButton.setToggleGroup(toggleGroup);
                        vBox.getChildren().add(new HBox(radioButton,new Label( " : "
                                + distanceCourantes.get(i) + " km")));
                    }
                }
                else {
                    vBox.getChildren().add(new Label("Vous êtes arrivés !"));
                    currentPath.add("PresidentFin");
                    root.getTextItineraire().setText(curentTempIt.getCurrentDistance(currentPath));
                }
                root.getScrollPossibilites().setContent(vBox);
            }
        }
    }
}
