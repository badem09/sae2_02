package vue;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class VBoxAjoutScenario extends VBox {

    public VBoxAjoutScenario(){

        this.getChildren().addAll(new TextArea("A venir ..."));
        this.setId("opaque");

    }
}
