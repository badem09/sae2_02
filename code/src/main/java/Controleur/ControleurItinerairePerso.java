package Controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import modele.Chemin;
import modele.Scenario;
import modele.TempsItineraire;
import modele.Villes;
import vue.CelluleListe;
import vue.VBoxItinerairePerso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ControleurItinerairePerso implements EventHandler {

    private Map<String , TempsItineraire> mapItineraire;
    private VBoxItinerairePerso root ;

    private String currentSource ;
    private ArrayList<String> currentPath;
    private Chemin currentItineraire;
    private ArrayList<String> possibilitesCourantes;
    private Villes villes ;
    private TempsItineraire curentTempIt ;
    private ArrayList<String> distanceCourantes;


    public ControleurItinerairePerso(VBoxItinerairePerso root , Map<String , TempsItineraire> parMap) throws IOException {
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

            try {
                Scenario scenario = Scenario.lectureScenario("src/main/resources/" + fileName, false);
                if (mapItineraire.containsKey(fileName)) {
                    currentItineraire = mapItineraire.get(fileName).getItineraire();
                    curentTempIt = mapItineraire.get(fileName);
                } else {
                    currentItineraire = new Chemin(scenario);
                   curentTempIt = new TempsItineraire(currentItineraire);
                    mapItineraire.put(fileName,curentTempIt);
                }
                currentSource = "President";
                currentPath = new ArrayList<>();
                currentPath.add("President");
                possibilitesCourantes = currentItineraire.parcoursProgressif(currentSource,currentPath,possibilitesCourantes);
                distanceCourantes = currentItineraire.getCurrentDistance(currentSource,possibilitesCourantes);
                ObservableList<CelluleListe> listePossibilites = FXCollections.observableArrayList();

                for (int i = 0; i < possibilitesCourantes.size() ; i++) {
                    String infos =  " (" + villes.getMembreToVilles().get(possibilitesCourantes.get(i)) + ")" + " : " +
                            distanceCourantes.get(i);
                    CelluleListe cell = new CelluleListe(possibilitesCourantes.get(i),infos);
                    listePossibilites.add(cell);
                    }
                root.getListView().setItems(listePossibilites);
            }
             catch(IOException e){
                    throw new RuntimeException(e);
            }
        }

        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getText() == "_Valider" && currentPath.size()>0
                    && ! currentPath.contains(currentSource) && currentSource != ""){
                currentPath.add(currentSource);
                root.getTextItineraire().setText(curentTempIt.getCurrentDistance(currentPath));
                 root.getTextMembres().appendText(currentSource + " : " +
                        villes.getMembreToVilles().get(currentSource) + "\n");
                possibilitesCourantes = currentItineraire.parcoursProgressif(currentSource,currentPath,possibilitesCourantes);
                try {
                    distanceCourantes = currentItineraire.getCurrentDistance(currentSource,possibilitesCourantes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                currentSource = "";
                ObservableList<CelluleListe> listePossibilites = FXCollections.observableArrayList();
                if (possibilitesCourantes.size() > 0) {
                    for (int i = 0; i < possibilitesCourantes.size() ; i++) {
                        String infos =  " (" + villes.getMembreToVilles().get(possibilitesCourantes.get(i)) + ")" + " : " +
                                distanceCourantes.get(i);
                        CelluleListe cell = new CelluleListe(possibilitesCourantes.get(i),infos);
                        listePossibilites.add(cell);
                    }
                }
                else {
                    currentPath.add("President");
                    root.getTextItineraire().setText(curentTempIt.getCurrentDistance(currentPath));
                    listePossibilites.add(new CelluleListe("Vous êtes arrivés !",""));
                }
                root.getListView().setItems(listePossibilites);
            }
        }
    }

    public void setCurrentSource(String currentSource) {
        this.currentSource = currentSource;
    }
}
