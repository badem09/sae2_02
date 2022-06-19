package vue;

import Controleur.ControleurItinerairePerso;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.SuiviScenario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class VBoxItinerairePerso extends VBox {

    private ListView<CelluleListe> listView;
    private Map<String , Itineraire> mapItineraire;
    private final TextArea textItineraire;
    private final TextArea textMembres;
    private final ControleurItinerairePerso controleur ;

    public VBoxItinerairePerso(VBoxRoot root) throws IOException {
        this.setId("opaque");
        mapItineraire = root.getVboxScenario().getGridPaneOrg().getMapItineraire();
        controleur = new ControleurItinerairePerso(this,mapItineraire);

        listView = new ListView<>();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CelluleListe>() {

            @Override
            public void changed(ObservableValue<? extends CelluleListe> observable, CelluleListe oldValue, CelluleListe newValue) {
                if (listView.getSelectionModel().getSelectedItem() != null){
                CelluleListe choix = listView.getSelectionModel().getSelectedItem();
                controleur.setCurrentSource(choix.getMembre());}
            }
        });

        ArrayList<String> liste = SuiviScenario.getListeScenarioSuivi();
        Collections.sort(liste);
        ComboBox<String> comboBoxScenario = new ComboBox<>(FXCollections.observableArrayList(liste));
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

        StackPane scrollPossibilites = new StackPane();
        textItineraire = new TextArea();
        textMembres = new TextArea();
        textItineraire.setPromptText("[Membre1, Membre2, Membre3, ...]\r" + "Distance : distance");
        textMembres.setPromptText("Membre 1 : Sa ville\rMembre 2 : Sa ville\rMembre 3 : Sa ville\r...");


        scrollPossibilites.setPrefHeight(300);
        scrollPossibilites.setPrefWidth(715);
        scrollPossibilites.setPadding(new Insets(1));


        listView.setMinHeight(scrollPossibilites.getPrefHeight());
        listView.setMaxHeight(scrollPossibilites.getPrefHeight());
        listView.setMinWidth(scrollPossibilites.getPrefWidth());
        listView.setMaxWidth(scrollPossibilites.getPrefWidth());

        int ligne = 0;

        gridPane.add(labelPossibilite,0,ligne,1,1);
        gridPane.add(labelMembre,1,ligne,1,1);
        ligne ++;
        gridPane.add(scrollPossibilites,0,ligne,1,1);
        gridPane.add(textMembres,1,ligne,1,1);
        ligne ++;
        gridPane.add(labelItineraire,0,ligne,1,1);
        gridPane.add(textItineraire,0,ligne+1,2,1);

        Button valider = new Button("_Valider");

        valider.setMnemonicParsing(true);
        valider.setOnAction(controleur);
        scrollPossibilites.getChildren().add(listView);

        setPadding(new Insets(20));
        setSpacing(10);
        this.getChildren().addAll(vBoxTitre, comboBoxScenario, gridPane,valider);
    }

    public TextArea getTextItineraire() {
        return textItineraire;
    }

    public TextArea getTextMembres() {
        return textMembres;
    }

    public ListView<CelluleListe> getListView() {
        return listView;
    }

    public void setListView(ListView<CelluleListe> listView) {
        this.listView = listView;
    }
}
