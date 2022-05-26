package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Itineraire;
import modele.Scenario;
import modele.TempsItineraire;

import java.io.File;
import java.io.IOException;

public class GridPaneOrg extends GridPane {
    String chMembres;
    Scenario chScenario;

    TextArea textBestIt ;
    TextArea textMembres;
    TextArea textScenario;



    public GridPaneOrg() throws IOException {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        textMembres = new TextArea();

     //   TempsItineraire ti =new TempsItineraire(new Itineraire(chScenario));
      //  String bestIt = ti.getBestItineraire();

        textBestIt = new TextArea();
        textBestIt.setWrapText(true);

        textScenario = new TextArea();
        textScenario.setPrefHeight(300);
        textScenario.setPrefWidth(700);

        textScenario.setEditable(false);
        textBestIt.setEditable(false);
        textMembres.setEditable(false);

        Label labelScenario = new Label("Détail du scénario.");
        Label labelMembre = new Label("Les membres impliqués");
        Label labelBestit = new Label("Le meilleur itinéraire");

        int ligne = 0;

        this.add(labelScenario,0,ligne,1,1);
        this.add(labelMembre,1,ligne,1,1);
        ligne ++;
        this.add(textScenario,0,ligne,1,1);
        this.add(textMembres,1,ligne,1,1);
        ligne +=1;
        this.add(labelBestit,0,ligne,1,1);
        ligne +=1;
        this.add(textBestIt,0,ligne,2,1);
    }

    private void setContent() throws IOException {
        TempsItineraire ti =new TempsItineraire(new Itineraire(chScenario));
        String bestIt = ti.getBestItineraire();

        textBestIt.clear();
        textBestIt.setText(bestIt);

        textMembres.clear();
        textMembres.setText(chScenario.getMembreToString());

        textScenario.clear();
        textScenario.setText(chScenario.toString());

    }
    public void setChMembres(String chMembres) {
        this.chMembres = chMembres;
    }

    public TextArea getTextMembres() {
        return textMembres;
    }
    public TextArea getTextScenario() {
        return textScenario;
    }
    public TextArea getTextBestIt() {
        return textBestIt;
    }

    public void setScenario(Scenario scenario) throws IOException {
        chScenario = scenario;
        setContent();
    }
}
