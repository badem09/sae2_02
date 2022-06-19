package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import modele.Chemin;
import modele.Itineraire;
import modele.Scenario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GridPaneOrg extends GridPane {
    private Scenario chScenario;

    private final TextArea textBestChemin;
    private final TextArea textMembres;
    private final TextArea textScenario;
    private final Map<String , Itineraire> mapItineraire;
    private final VBoxRoot root;




    public GridPaneOrg(VBoxRoot parRoot){
      //  this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        mapItineraire = new HashMap<>();
        root = parRoot;

        textMembres = new TextArea();
        textBestChemin = new TextArea();
        textBestChemin.setWrapText(true);

        textScenario = new TextArea();
        textScenario.setPrefHeight(300);
        textScenario.setPrefWidth(700);

        textScenario.setEditable(false);
        textBestChemin.setEditable(false);
        textMembres.setEditable(false);

        textBestChemin.setPromptText("[Membre1, Membre2, Membre3, ...]\r" + "Distance : distance");
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
        this.add(textBestChemin,0,ligne,2,1);
    }

    private void setContent(){
        Itineraire ti = mapItineraire.get(chScenario.getFileName());
        String bestIt = ti.getBestItineraire();

        textBestChemin.clear();
        textBestChemin.setText(bestIt);

        textMembres.clear();
        textMembres.setText(chScenario.getMembreToString());

        textScenario.clear();
        textScenario.setText(chScenario.toString());

    }

    public TextArea getTextMembres() {
        return textMembres;
    }
    public TextArea getTextScenario() {
        return textScenario;
    }
    public TextArea getTextBestChemin() {
        return textBestChemin;
    }

    public void setScenario(Scenario parScenario) throws IOException {
        String fileName = parScenario.getFileName();
        if ( ! mapItineraire.containsKey(fileName)){
            Itineraire ti = new Itineraire(new Chemin(parScenario));
            mapItineraire.put(fileName,ti);
            root.getvBoxAllItineraire().updateMapItineraire(fileName, ti);
        }
        chScenario = parScenario;
        setContent();
    }

    public Map<String, Itineraire> getMapItineraire() {
        return mapItineraire;
    }

    public void updateMapItineraire(String fileName, Itineraire tempsItineraire) {
        mapItineraire.put(fileName,tempsItineraire);
    }
}
