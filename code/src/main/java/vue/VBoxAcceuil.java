package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VBoxAcceuil extends VBox {

    public VBoxAcceuil() throws FileNotFoundException {
        VBox vBoxPokeman = new VBox();
        vBoxPokeman.setAlignment(Pos.TOP_RIGHT);
        Image image = new Image(new FileInputStream(
                "src/main/resources/pokeman.png"));
        ImageView imageView = new ImageView(image);

        imageView.setX(700);
        imageView.setY(500);

        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        imageView.setPreserveRatio(true);

        vBoxPokeman.setPadding(new Insets(30,30,0,0));
        vBoxPokeman.getChildren().add(imageView);

        Label bienvenue = new Label("Bienvenue dans votre application APLI !");
        VBox vBoxBienvenue = new VBox(bienvenue);
        vBoxBienvenue.setAlignment(Pos.CENTER);

        Button commencer = new Button("Commencer");
        VBox vBoxButtonCommencer = new VBox(commencer);
        vBoxButtonCommencer.setAlignment(Pos.CENTER);
        vBoxBienvenue.setPadding(new Insets(30,30,30,30));
        vBoxButtonCommencer.setPadding(new Insets(30,30,30,30));

        Label labelCopyright = new Label("2022 \nBA Demba" +
                "\nBOUNGAB Hassan\nZIHOUNE Bilal");

        VBox vBoxCopyright = new VBox(labelCopyright);
        vBoxCopyright.setAlignment(Pos.BOTTOM_RIGHT);

        Button buttonAide = new Button("Aide");
        HBox vBoxButtonAide = new HBox(buttonAide);
        vBoxButtonAide.setAlignment(Pos.BOTTOM_LEFT);

        vBoxButtonAide.getChildren().add(vBoxCopyright);
        vBoxButtonAide.setSpacing(320);

        this.getChildren().addAll(vBoxPokeman,vBoxBienvenue,
                vBoxButtonCommencer,vBoxButtonAide);
    }
}
