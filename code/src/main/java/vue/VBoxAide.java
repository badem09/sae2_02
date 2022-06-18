package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxAide extends VBox {

    public VBoxAide(){
        this.setId("opaque");
        this.getChildren().add(new Label( " VboxAide"));
    }
}
