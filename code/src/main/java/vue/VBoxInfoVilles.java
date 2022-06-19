package vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Villes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VBoxInfoVilles extends VBox {
    private final TextField textField;
    private final Villes villes;
    private String currentInput;
    private final ListView<String> listViewChoixVille;
    private ArrayList<String> currentListeVille;
    private  String villeChoisie;
    private final ListView<CelluleListe> listViewAffichageMembre;


    public VBoxInfoVilles() throws IOException {
        setId("opaque");
        setSpacing(10);

        listViewAffichageMembre = new ListView<>();
        currentListeVille = new ArrayList<>();
        textField = new TextField();
        villes = new Villes();
        ArrayList<String> liste = villes.getTabVilles();
        Collections.sort(liste);
        listViewChoixVille = new ListView<>(FXCollections.observableArrayList(liste));
        textField.setPromptText("Veuillez rentrer une ville");

        // À l'écoute des saisies dans le textField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            // si aucune Saisie
            if (currentListeVille.size() == 0 || textField.getText() == ""){
                currentListeVille = (ArrayList<String>) liste.clone();
                listViewChoixVille.setItems(FXCollections.observableArrayList(liste));
            }
            currentInput = newValue;
            currentListeVille = (ArrayList<String>) liste.clone();
            for (String ville : (ArrayList<String>) currentListeVille.clone()){
                if  (! equalsIgnoreCase(ville, currentInput)){
                    currentListeVille.remove(ville);
                }
            }
            listViewChoixVille.setItems(FXCollections.observableList(currentListeVille));
        });

        //Recupère la ville selectionnée et affiche les membres associés
        listViewChoixVille.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (listViewChoixVille.getSelectionModel().getSelectedItem() != null){
                    villeChoisie = listViewChoixVille.getSelectionModel().getSelectedItem();
                    try {
                        setContent(listViewAffichageMembre,villeChoisie);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // Efface le contenu de la listeView d'affichage des villes
        Button buttonClear = new Button("Clear");
        buttonClear.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentListeVille.removeAll(liste);
                currentListeVille.addAll(liste);
                listViewChoixVille.setItems(FXCollections.observableArrayList(liste));
                textField.clear();
                listViewAffichageMembre.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            }
        });

        //Rend le click impossible sur les membres.
        listViewAffichageMembre.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {event.consume();}});

        Label titre = new Label("Recherche de Villes");
        titre.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(titre,textField,
                new HBox(listViewChoixVille, listViewAffichageMembre), buttonClear);
    }

    /**
     * Peuple la listeViewMembre (ListeView affichant les membres de la ville séléctionnée).
     * @param listViewMembre (ListeView)
     * @param villeChoisie (String)
     * @throws FileNotFoundException
     */
    private void setContent(ListView<CelluleListe> listViewMembre, String villeChoisie) throws FileNotFoundException {
        ArrayList<String> listeMembre = villes.getVillesToMembre().get(villeChoisie);
        Collections.sort(listeMembre);
        ObservableList<CelluleListe> content = FXCollections.observableArrayList();
        for (int i = 0 ; i< listeMembre.size() ; i++){
            content.add(new CelluleListe(listeMembre.get(i),String.valueOf(i)));
        }
        listViewMembre.setItems(content);
    }

    /**
     * Sert à comparer deux chaînes de caractère en ignorant la case.
     * @param membre (String).
     * @param input (String) entrée de l'utilisateur.
     * @return boolean
     */
    private boolean equalsIgnoreCase(String membre, String input){
        membre = membre.toLowerCase();
        input = input.toLowerCase();
        for(int i = 0; i < input.length() ; i++){
            if ( membre.charAt(i) != input.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
