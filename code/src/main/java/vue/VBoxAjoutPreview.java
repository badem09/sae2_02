package vue;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import modele.Scenario;
import modele.SuiviScenario;

import java.io.IOException;

public class VBoxAjoutPreview extends VBox {

    private VBoxMenu vBoxMenu;
    private GridPaneOrg gridPaneOrg;
    private ComboBox<String> comboBoxScenario;
    private Scenario scenario;

    public VBoxAjoutPreview(VBoxRoot root) throws IOException {
        this.setId("opaque");
        gridPaneOrg = new GridPaneOrg();
        this.getChildren().addAll(gridPaneOrg);
    }


    public void setScenario(Scenario scenario) throws IOException {
        gridPaneOrg.setScenario(scenario);
    }
}
