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


    public ControleurItinerairePerso(VBoxItinerairePerso root , Map<String , Itineraire> parMap) throws IOException {
        this.root = root;
        this.mapItineraire = parMap;
        villes = new Villes();
    }

    @Override
    public void handle(Event event) {

        ArrayList<String> distanceCourantes;

        // Itinéraire pas encore entamé:
        if (event.getSource() instanceof ComboBox<?>) {
            String fileName = (String) ((ComboBox<?>) event.getSource())
                    .getSelectionModel().getSelectedItem();
            root.getTextMembres().clear();
            root.getTextItineraire().clear();
            root.getTextMembres().setText("President (vous) : Vélizy\n");
            root.getTextItineraire().setText("Chemin : [Président] \n" + "Distance : 0");

            //Stockage des itinéraire dans une map
            try {
                Scenario scenario = Scenario.lectureScenario("code/src/main/resources/data/" + fileName, false);
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
                e.printStackTrace();
            }
        }

        // Si au milieu d'un itinéraire.
        if (event.getSource() instanceof Button) {
            if (((Button) event.getSource()).getText() == "_Valider" ){

                // si pas de sélection
                if (currentPath.contains(currentSource) || currentSource == "No selection" ) {
                    Alert mauvaisformat = new Alert(Alert.AlertType.ERROR);
                    mauvaisformat.setHeaderText("Aucune Selection");
                    mauvaisformat.setContentText("Oups. Il semblerait que vous n'avez selectionné aucun membre." +
                            "\n" + "Veuillez en selectionner un parmis ceux proposés.");
                    mauvaisformat.showAndWait();

                } else if (currentSource == "Vous êtes arrivés !") {
                    Alert mauvaisformat = new Alert(Alert.AlertType.ERROR);
                    mauvaisformat.setHeaderText("Fin de l'itineraire");
                    mauvaisformat.setContentText("Vous êtes arrivés !");
                    mauvaisformat.showAndWait();

                } else {
                    System.out.println(currentSource);
                    currentPath.add(currentSource);
                    root.getTextItineraire().setText(curentItineraire.getCurrentDistance(currentPath));
                    root.getTextMembres().appendText(currentSource + " : " +
                            villes.getMembreToVilles().get(currentSource) + "\n");
                    possibilitesCourantes = currentChemin.parcoursProgressif(currentSource,
                            currentPath, possibilitesCourantes);
                    distanceCourantes = currentChemin.getCurrentDistance(currentSource, possibilitesCourantes);
                    currentSource = "No selection";
                    ObservableList<CelluleListe> listePossibilites = FXCollections.observableArrayList();

                    // s'il reste des possibilités
                    if (possibilitesCourantes.size() > 0) {
                        for (int i = 0; i < possibilitesCourantes.size(); i++) {
                            String infos = " (" + villes.getMembreToVilles().get(possibilitesCourantes.get(i)) +
                                    ")" + " : " + distanceCourantes.get(i);
                            CelluleListe cell = null;
                            try {
                                cell = new CelluleListe(possibilitesCourantes.get(i), infos);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            listePossibilites.add(cell);
                        }

                     // Sinon on est arrivé à la fin.
                    } else {
                        currentPath.add("President");
                        root.getTextItineraire().setText(curentItineraire.getCurrentDistance(currentPath));
                        try {
                            listePossibilites.add(new CelluleListe("Vous êtes arrivés !", ""));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
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
