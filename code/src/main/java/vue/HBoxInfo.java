package vue;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class HBoxInfo extends HBox {

    public HBoxInfo(VBoxMenu vBoxMenu) throws IOException {

        setId("opaque");
        VBoxInfoMembres vBoxInfoMembres = new VBoxInfoMembres();
        VBoxInfoVilles vBoxInfoVilles = new VBoxInfoVilles();
        setSpacing(100);
        setPadding(new Insets(30));
        setPrefWidth(vBoxMenu.getWidth());
        getChildren().addAll(vBoxInfoVilles, vBoxInfoMembres);
    }
}
