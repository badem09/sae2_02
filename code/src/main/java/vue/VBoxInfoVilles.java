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
    TextField textField;
    Villes villes;
    String currentInput;
    ListView<String> listViewChoixVille;
    ArrayList<String> currentListeVille;
    String villeChoisie;
    ListView<CelluleListe> listViewAffichageMembre;


    public VBoxInfoVilles() throws IOException {
        setSpacing(10);
        listViewAffichageMembre = new ListView<>();
        currentListeVille = new ArrayList<>();
        textField = new TextField();
        villes = new Villes();
        ArrayList<String> liste = villes.getTabVilles();
        Collections.sort(liste);
        listViewChoixVille = new ListView<>(FXCollections.observableArrayList(liste));
        this.setId("opaque");
        textField.setPromptText("Veuillez rentrer une ville");

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (currentListeVille.size() == 0 || textField.getText() == ""){
                currentListeVille = (ArrayList<String>) liste.clone();
                listViewChoixVille.setItems(FXCollections.observableArrayList(liste));
            }
            currentInput = newValue;
            currentListeVille = (ArrayList<String>) liste.clone();
            for (String ville : (ArrayList<String>) currentListeVille.clone()){
                if  (!  equalsIgnoreCase(ville, currentInput)){
                    currentListeVille.remove(ville);
                }
            }
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            listViewChoixVille.setItems(FXCollections.observableList(currentListeVille));
        });

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

        Button buttonClear = new Button("Clear");
        //buttonClear.setAlignment(Pos.BOTTOM_RIGHT);
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
        listViewAffichageMembre.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {event.consume();}});
        Label titre = new Label("Recherche de Villes");
        titre.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(titre,textField,
                new HBox(listViewChoixVille, listViewAffichageMembre), buttonClear);
    }

    private void setContent(ListView<CelluleListe> listViewMembre, String villeChoisie) throws FileNotFoundException {
        ArrayList<String> listeMembre = villes.getVillesToMembre().get(villeChoisie);
        Collections.sort(listeMembre);
        ObservableList<CelluleListe> content = FXCollections.observableArrayList();
        for (int i = 0 ; i< listeMembre.size() ; i++){
            content.add(new CelluleListe(i + ". ",listeMembre.get(i)));
        }
        listViewMembre.setItems(content);

    }

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
