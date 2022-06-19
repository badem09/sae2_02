package Controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import modele.Chemin;
import modele.Scenario;
import modele.Itineraire;
import modele.Villes;
import vue.CelluleListe;
import vue.VBoxItinerairePerso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ControleurItinerairePerso implements EventHandler{

    private final Map<String , Itineraire> mapItineraire;
    private final VBoxItinerairePerso root ;
    private String currentSource ;
    private ArrayList<String> currentPath;
    private Chemin currentChemin;
    private ArrayList<String> possibilitesCourantes;
    private final Villes villes ;
    private Itineraire curentItineraire; // r
    private ArrayList<String> distanceCourantes;


    public ControleurItinerairePerso(VBoxItinerairePerso root , Map<String , Itineraire> parMap) throws IOException {
        this.root = root;
        this.mapItineraire = parMap;
        villes = new Villes();
    }

    @Override
    public void handle(Event event) {

        if (event.getSource() instanceof ComboBox<?>) {
            String fileName = (String) ((ComboBox<?>) event.getSource())
                    .getSelectionModel().getSelectedItem();
            root.getTextMembres().clear();
            root.getTextItineraire().clear();
            root.getTextMembres().setText("President (vous) : Vélizy\n");
            root.getTextItineraire().setText("Chemin : [Président] \n" + "Distance : 0");

            try {
                Scenario scenario = Scenario.lectureScenario("src/main/resources/data/" + fileName, false);
                if (mapItineraire.containsKey(fileName)) {
                    currentChemin = mapItineraire.get(fileName).getChemin();
                    curentItineraire = mapItineraire.get(fileName);
                } else {
                    currentChemin = new Chemin(scenario);
                    curentItineraire = new Itineraire(currentChemin);
                    mapItineraire.put(fileName, curentItineraire);
                }
                currentSource = "President";
                currentPath = new ArrayList<>();
                currentPath.add("President");
                possibilitesCourantes = currentChemin.parcoursProgressif(currentSource, currentPath, possibilitesCourantes);
                distanceCourantes = currentChemin.getCurrentDistance(currentSource, possibilitesCourantes);
                ObservableList<CelluleListe> listePossibilites = FXCollections.observableArrayList();

                for (int i = 0; i < possibilitesCourantes.size(); i++) {
                    String infos = " (" + villes.getMembreToVilles().get(possibilitesCourantes.get(i)) + ")" + " : " +
                            distanceCourantes.get(i);
                    CelluleListe cell = new CelluleListe(possibilitesCourantes.get(i), infos);
                    listePossibilites.add(cell);
                }
                root.getListView().setItems(listePossibilites);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (event.getSource() instanceof Button) {
            System.out.println("currentPath : " + currentPath + "source : " + currentSource);
            if (((Button) event.getSource()).getText() == "_Valider" ){ //&& ! currentPath.contains(currentSource)) {
                System.out.println("valider ok ");
                if (currentPath.contains(currentSource) || currentSource == "No selection" ) {
                    Alert mauvaisformat = new Alert(Alert.AlertType.ERROR);
                    mauvaisformat.setHeaderText("Aucune Selection");
                    mauvaisformat.setContentText("Oups. Il semblerait que vous n'avez selectionné aucun membre." +
                            "\n" + "Veuillez en selectionner un parmis ceux proposés.");
                    mauvaisformat.showAndWait();
                } else {
                    currentPath.add(currentSource);
                    root.getTextItineraire().setText(curentItineraire.getCurrentDistance(currentPath));
                    root.getTextMembres().appendText(currentSource + " : " +
                            villes.getMembreToVilles().get(currentSource) + "\n");
                    possibilitesCourantes = currentChemin.parcoursProgressif(currentSource,
                            currentPath, possibilitesCourantes);
                    try {
                        distanceCourantes = currentChemin.getCurrentDistance(currentSource, possibilitesCourantes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    currentSource = "No selection";
                    ObservableList<CelluleListe> listePossibilites = FXCollections.observableArrayList();
                    if (possibilitesCourantes.size() > 0) {
                        for (int i = 0; i < possibilitesCourantes.size(); i++) {
                            String infos = " (" + villes.getMembreToVilles().get(possibilitesCourantes.get(i)) +
                                    ")" + " : " + distanceCourantes.get(i);
                            CelluleListe cell;
                            try {
                                cell = new CelluleListe(possibilitesCourantes.get(i), infos);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            listePossibilites.add(cell);
                        }
                    } else {
                        currentPath.add("President");
                        root.getTextItineraire().setText(curentItineraire.getCurrentDistance(currentPath));
                        try {
                            listePossibilites.add(new CelluleListe("Vous êtes arrivés !", ""));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    root.getListView().setItems(listePossibilites);
                }
            }
        }
    }

    public void setCurrentSource(String currentSource) {
        this.currentSource = currentSource;
    }
}
