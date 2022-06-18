package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class HBoxInfo extends HBox {

    private VBoxInfoVilles vBoxInfoVilles;
    private VBoxInfoMembres vBoxInfoMembres;

    public HBoxInfo(VBoxMenu vBoxMenu) throws IOException {
        setId("opaque");
        vBoxInfoMembres = new VBoxInfoMembres();
        vBoxInfoVilles = new VBoxInfoVilles();
        setSpacing(100);
        setPadding(new Insets(30));
        setPrefWidth(vBoxMenu.getWidth());
        getChildren().addAll(vBoxInfoVilles, vBoxInfoMembres);
    }
}
