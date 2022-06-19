package vue;

import Controleur.ControleurMenu;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class VBoxRoot extends VBox {

    private StackPane stackPane;
    private  VBoxAjoutScenario vBoxAjout ;
    private  VBoxScenarioConnu vBoxScenario;
    private  VBoxMenu vBoxMenu;
    private  VBoxAllItineraire vBoxAllItineraire;
    private  VBoxAjoutPreview vBoxAjoutPreview;
    private VBoxItinerairePerso vBoxItinerairePerso;
    private VBoxAide vBoxAide;
    private ComboBoxScenario comboBoxScenario;
    private HBoxInfo hBoxInfo;


    public VBoxRoot(Stage stage) throws IOException {
        vBoxMenu = new VBoxMenu(this);
        vBoxScenario = new VBoxScenarioConnu(this);
        vBoxAjout = new VBoxAjoutScenario(stage,this);
        vBoxAllItineraire = new VBoxAllItineraire(this);
        vBoxAjoutPreview = new VBoxAjoutPreview(this);
        comboBoxScenario = new ComboBoxScenario(this);
        vBoxItinerairePerso = new VBoxItinerairePerso(this);
        vBoxAide = new VBoxAide();
        hBoxInfo = new HBoxInfo(vBoxMenu);


        Node [] components = new Node[7];
        components[0] = vBoxScenario;
        components[1] = vBoxAjout;
        components[2] = vBoxAllItineraire;
        components[3] = vBoxAjoutPreview;
        components[4] = vBoxItinerairePerso;
        components[5] = vBoxAide;
        components[6] = hBoxInfo;

        stackPane = new StackPane(components);
        stackPane.getChildren().get(0).toFront();
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.getChildren().addAll(vBoxMenu,stackPane);
    }

    public StackPane getStackPane(){
        return stackPane;
    }

    public VBoxScenarioConnu getVboxScenario() {
        return vBoxScenario;
    }

    public VBoxMenu getVBoxMenu() {
        return vBoxScenario.getvBoxMenu();
    }

    public  VBoxAjoutPreview getvBoxAjoutPreview() {
        return vBoxAjoutPreview;
    }

    public  VBoxAllItineraire getvBoxAllItineraire() {
        return vBoxAllItineraire;
    }

    public VBoxScenarioConnu getvBoxScenario() {
        return vBoxScenario;
    }
}
