package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        Button bouttonCommencer = new Button("Commencer");
        bouttonCommencer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("stage2");
                Scene secondScene = new Scene(new VBox(),100,100);
                Stage secondStage = new Stage();

                
            }
        });
        VBox vBoxButtonCommencer = new VBox(bouttonCommencer);
        vBoxButtonCommencer.setAlignment(Pos.CENTER);
        vBoxBienvenue.setPadding(new Insets(30,30,30,30));
        vBoxButtonCommencer.setPadding(new Insets(30,30,30,30));

        Label labelCopyright = new Label("2022 \nBA Demba" +
                "\nBOUNGAB Hassan\nZIHOUNE Bilal");

        VBox vBoxCopyright = new VBox(labelCopyright);
        vBoxCopyright.setAlignment(Pos.BOTTOM_RIGHT);

        Button buttonAide = new Button("Aide");
        HBox hBoxAideCopyright = new HBox(buttonAide);
        hBoxAideCopyright.setAlignment(Pos.BOTTOM_LEFT);

        hBoxAideCopyright.getChildren().add(vBoxCopyright);
        hBoxAideCopyright.setSpacing(320);

        this.getChildren().addAll(vBoxPokeman,vBoxBienvenue,
                vBoxButtonCommencer,hBoxAideCopyright);
    }
}
