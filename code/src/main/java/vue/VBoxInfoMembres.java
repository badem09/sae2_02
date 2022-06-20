package vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Villes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class VBoxInfoMembres extends VBox {

    private final TextField textField;
    private final Villes villes;
    private String currentInput;
    private final ListView<String> listViewChoixMembre;
    private ArrayList<String> currentListeMembre;
    private String membreChoisi;
    private final ListView<String> listViewAffichageVille;


    public VBoxInfoMembres() throws IOException {
        setSpacing(10);
        this.setId("opaque");

        villes = new Villes();
        listViewAffichageVille = new ListView<>();
        currentListeMembre = new ArrayList<>();
        textField = new TextField();

        textField.setPromptText("Veuillez rentrer le nom d'un membre");
        ArrayList<String> liste = villes.getListeMembre();
        Collections.sort(liste);

        listViewChoixMembre = new ListView<>(FXCollections.observableArrayList(liste));

        // A l'écoute des saisies dans le textField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // si aucune Saisie
            if (currentListeMembre.size() == 0 || textField.getText() == ""){
                currentListeMembre = (ArrayList<String>) liste.clone();
                listViewChoixMembre.setItems(FXCollections.observableArrayList(liste));
            }
            currentInput = newValue;
            currentListeMembre = (ArrayList<String>) liste.clone();
            for (String membre : (ArrayList<String>) currentListeMembre.clone()){
                if  (!  equalsIgnoreCase(membre, currentInput)){
                    currentListeMembre.remove(membre);
                }
            }
            if (currentListeMembre.size() == 0){
                currentListeMembre.add("Aucun résultat.");
            }
            listViewChoixMembre.setItems(FXCollections.observableArrayList(currentListeMembre));
        });

        //Recupère la membre selectionné et affiche la ville associée.
        listViewChoixMembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (listViewChoixMembre.getSelectionModel().getSelectedItem() != null){
                    membreChoisi = listViewChoixMembre.getSelectionModel().getSelectedItem();
                    try {
                        setContent(listViewAffichageVille, membreChoisi);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // Efface le contenu de la listeView d'affichage des membres
        Button buttonClear = new Button("Clear");
        buttonClear.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentListeMembre.removeAll(liste);
                currentListeMembre.addAll(liste);
                listViewChoixMembre.setItems(FXCollections.observableArrayList(liste));
                textField.clear();
                listViewAffichageVille.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            }
        });
        listViewAffichageVille.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {event.consume();}});

        Label titre = new Label("Recherche de Membres");
        titre.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(titre,textField,
                new HBox(listViewChoixMembre, listViewAffichageVille), buttonClear);
    }

    /**
     * Peuple la listeViewVille (ListeView affichant la ville du membre séléctionné).
     * @param listViewMembre (ListeView)
     * @param membreChoisie (String)
     * @throws FileNotFoundException
     */
    private void setContent(ListView<String> listViewMembre, String membreChoisie) throws FileNotFoundException {
        ArrayList<String> listeMembre = new ArrayList<>();
        listeMembre.add(villes.getMembreToVilles().get(membreChoisie));
        listViewMembre.setItems(FXCollections.observableArrayList(listeMembre));

    }

    /**
     * Sert à comparer deux chaînes de caractère en ignorant la case.
     * @param ville (String).
     * @param input (String) entrée de l'utilisateur.
     * @return boolean
     */
    private boolean equalsIgnoreCase(String ville, String input){
        ville = ville.toLowerCase();
        input = input.toLowerCase();
        for(int i = 0; i < input.length() ; i++){
            if ( ville.charAt(i) != input.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
