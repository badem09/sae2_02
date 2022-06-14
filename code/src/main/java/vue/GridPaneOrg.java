package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import modele.Chemin;
import modele.Scenario;
import modele.TempsItineraire;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GridPaneOrg extends GridPane {
    String chMembres;
    Scenario chScenario;

    TextArea textBestIt ;
    TextArea textMembres;
    TextArea textScenario;
    private Map<String , TempsItineraire> mapItineraire;
    private VBoxRoot root;




    public GridPaneOrg(VBoxRoot parRoot) throws IOException {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        mapItineraire = new HashMap<>();
        root = parRoot;

        textMembres = new TextArea();

     //   TempsItineraire ti =new TempsItineraire(new Chemin(chScenario));
      //  String bestIt = ti.getBestItineraire();

        textBestIt = new TextArea();
        textBestIt.setWrapText(true);

        textScenario = new TextArea();
        textScenario.setPrefHeight(300);
        textScenario.setPrefWidth(700);

        textScenario.setEditable(false);
        textBestIt.setEditable(false);
        textMembres.setEditable(false);

        textBestIt.setPromptText("[Membre1, Membre2, Membre3, ...]\r" + "Distance : distance");
        textMembres.setPromptText("Membre 1 : Sa ville\rMembre 2 : Sa ville\rMembre 3 : Sa ville\r...");
        textScenario.setPromptText("Membre 1 vends à Membre 2"+"\r"+"Membre 3 vends à Membre 2\rMembre 4 vends à Membre 1\r");

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
        TempsItineraire ti = mapItineraire.get(chScenario.getFileName());
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

    public void setScenario(Scenario parScenario) throws IOException {
        String fileName = parScenario.getFileName();
        if ( ! mapItineraire.containsKey(fileName)){
            TempsItineraire ti = new TempsItineraire(new Chemin(parScenario));
            mapItineraire.put(fileName,ti);
            root.getvBoxAllItineraire().updateMapItineraire(fileName, ti);
        }
        chScenario = parScenario;
        setContent();
    }

    public Map<String, TempsItineraire> getMapItineraire() {
        return mapItineraire;
    }

    public void updateMapItineraire(String fileName, TempsItineraire tempsItineraire) {
        mapItineraire.put(fileName,tempsItineraire);
    }
}
