package vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

public class VBoxInfoMembres extends VBox {
    TextField textField;
    Villes villes;
    String currentInput;
    ListView<String> listViewChoixMembre;
    ArrayList<String> currentListeMembre;
    String membreChoisie;
    ListView<String> listViewAffichageVille;


    public VBoxInfoMembres() throws IOException {
        setSpacing(10);
        listViewAffichageVille = new ListView<>();
        currentListeMembre = new ArrayList<>();
        textField = new TextField();
        villes = new Villes();
        ArrayList<String> liste = villes.getListeMembre();
        Collections.sort(liste);
        listViewChoixMembre = new ListView<>(FXCollections.observableArrayList(liste));
        this.setId("opaque");
        textField.setPromptText("Veuillez rentrer le nom d'un membre");

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
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
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            listViewChoixMembre.setItems(FXCollections.observableList(currentListeMembre));
        });

        listViewChoixMembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (listViewChoixMembre.getSelectionModel().getSelectedItem() != null){
                    membreChoisie = listViewChoixMembre.getSelectionModel().getSelectedItem();
                    try {
                        setContent(listViewAffichageVille, membreChoisie);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

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

    private void setContent(ListView<String> listViewMembre, String membreChoisie) throws FileNotFoundException {
        ArrayList<String> listeMembre = new ArrayList<>();
        listeMembre.add(villes.getMembreToVilles().get(membreChoisie));
        listViewMembre.setItems(FXCollections.observableArrayList(listeMembre));

    }

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
