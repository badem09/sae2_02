package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VBoxRoot extends VBox {

    private StackPane stackPane;
    private static VBoxAjoutScenario vBoxAjout ;
    private static VBoxScenarioConnu vBoxScenario;
    private static VBoxMenu vBoxMenu;
    private static VBoxAllItineraire vBoxAllItineraire;


    public VBoxRoot() throws IOException {
        vBoxMenu = new VBoxMenu(this);
        vBoxScenario = new VBoxScenarioConnu(this);
        vBoxAjout = new VBoxAjoutScenario();
        vBoxAllItineraire = new VBoxAllItineraire();

        Node [] components = new Node[3];
        components[0] = vBoxScenario;
        components[1] = vBoxAjout;
        components[2] = vBoxAllItineraire;

        System.out.println(components);
        stackPane = new StackPane(components);
        stackPane.getChildren().get(0).toFront();
     //   stackPane.getChildren().get(0).setVisible(false);

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

}
