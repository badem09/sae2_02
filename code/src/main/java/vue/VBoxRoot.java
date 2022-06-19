package vue;

import Controleur.ControleurMenu;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class VBoxRoot extends VBox {

    private final StackPane stackPane;
    private final VBoxScenarioConnu vBoxScenario;
    private final VBoxAllItineraire vBoxAllItineraire;
    private final VBoxAjoutPreview vBoxAjoutPreview;


    public VBoxRoot(Stage stage) throws IOException {

        VBoxMenu vBoxMenu = new VBoxMenu(this);
        vBoxScenario = new VBoxScenarioConnu(this);
        VBoxAjoutScenario vBoxAjout = new VBoxAjoutScenario(stage, this);
        vBoxAllItineraire = new VBoxAllItineraire(this);
        vBoxAjoutPreview = new VBoxAjoutPreview(this);
        ComboBoxScenario comboBoxScenario = new ComboBoxScenario(this);
        VBoxItinerairePerso vBoxItinerairePerso = new VBoxItinerairePerso(this);

        VBoxAide vBoxAide = new VBoxAide();
        HBoxInfo hBoxInfo = new HBoxInfo(vBoxMenu);


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
