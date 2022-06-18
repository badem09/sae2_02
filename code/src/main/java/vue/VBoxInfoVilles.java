package vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class VBoxMembres extends VBox {
    TextField textField;
    Villes villes;
    String currentInput;
    ListView<String> listViewChoixVille;
    ArrayList<String> currentListeVille;
    String villeChoisie;
    ListView<CelluleListe> listViewAffichageMembre;


    public VBoxMembres() throws IOException {
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
            for (String ville : (ArrayList<String>) currentListeVille.clone()){
                if  (! ( containsIgnoreCase(ville,currentInput)  && equalsIgnoreCase(currentInput.charAt(currentInput.length() - 1), ville.charAt(currentInput.length() -1)))){
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

        Button buttonRecommencer = new Button("Recommencer");
        buttonRecommencer.setOnAction(new EventHandler<>() {
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
        this.getChildren().addAll(new VBox(new Label("Recherche de membres"),textField,
                new HBox(listViewChoixVille, listViewAffichageMembre), buttonRecommencer));
    }

    private void setContent(ListView<CelluleListe> listViewMembre, String villeChoisie) throws FileNotFoundException {
        ArrayList<String> listeMembre = villes.getVillesToMembre().get(villeChoisie);
        Collections.sort(listeMembre);
        ObservableList<CelluleListe> content = FXCollections.observableArrayList();
        for (String membre : listeMembre){
            content.add(new CelluleListe(membre,""));
        }
        listViewMembre.setItems(content);

    }

    private boolean containsIgnoreCase(String ville, String input){
        for ( char lettre : input.toCharArray()){
            if (  ville.contains(String.valueOf(lettre).toLowerCase()) ||  ville.contains(String.valueOf(lettre).toUpperCase())){
                return true;
            }
        }
        return false;
    }

    private boolean equalsIgnoreCase(char c1, char c2){
        return String.valueOf(c1).equalsIgnoreCase(String.valueOf(c2));
    }
}
