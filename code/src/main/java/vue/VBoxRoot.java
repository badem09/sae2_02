package vue;

import Controleur.ControleurMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.font.TextHitInfo;
import java.io.IOException;

public class VBoxRoot extends VBox {

    private StackPane stackPane;
    private static VBoxAjoutScenario vBoxAjout ;
    private static VBoxScenarioConnu vBoxScenario;
    private static VBoxMenu vBoxMenu;
    private static VBoxAllItineraire vBoxAllItineraire;
    private static VBoxAjoutPreview vBoxAjoutPreview;
    private static ControleurMenu controleurMenu;


    public VBoxRoot(Stage stage) throws IOException {
        vBoxMenu = new VBoxMenu(this);
        vBoxScenario = new VBoxScenarioConnu(this);
        vBoxAjout = new VBoxAjoutScenario(stage,this);
        vBoxAllItineraire = new VBoxAllItineraire();
        vBoxAjoutPreview = new VBoxAjoutPreview(this);

        Node [] components = new Node[4];
        components[0] = vBoxScenario;
        components[1] = vBoxAjout;
        components[2] = vBoxAllItineraire;
        components[3] = vBoxAjoutPreview;

        System.out.println(components);
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

    public static VBoxAjoutPreview getvBoxAjoutPreview() {
        return vBoxAjoutPreview;
    }
}
