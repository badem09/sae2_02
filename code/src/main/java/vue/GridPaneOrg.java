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


    public GridPaneOrg() throws IOException {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        chScenario = Scenario.lectureScenario(new File(
                "src/main/resources/scenario_0.txt"));

        chMembres = chScenario.getMembreToString();
        TextArea textMembres = new TextArea(chMembres);
        Scenario scenario = Scenario.lectureScenario(
                new File("src/main/resources/scenario_2_1.txt"));
        TempsItineraire ti =new TempsItineraire(new Itineraire(scenario));
        String bestIt = ti.getBestItineraire();

        TextArea textBestIt = new TextArea(bestIt);
        textBestIt.setWrapText(true);

        VBox vBoxBestItineraire = new VBox (textBestIt);
        VBox vBoxSenario =  new VBox(new TextArea(scenario.toString()));

        Label labelScenario = new Label("Détail du scénario.");
        Label labelMembre = new Label("Les membres impliqués");
        Label labelBestit = new Label("Le meilleur itinéraire");

        int ligne = 0;

        this.add(labelScenario,0,ligne,1,1);
        this.add(labelMembre,1,ligne,1,1);
        ligne ++;
        this.add(vBoxSenario,0,ligne,1,1);
        this.add(textMembres,1,ligne,2,1);
        ligne +=1;
        this.add(labelBestit,0,ligne,1,1);
        ligne +=1;
        this.add(vBoxBestItineraire,0,ligne,3,1);
    }
}
