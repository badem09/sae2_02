package vue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Scenario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ScenarioApplication extends Application {
    private Scene currentScene;
    //private String strScene;
    @Override
    public void start(Stage chStage) throws IOException {

            VBox root = new VBox();
            Scenario s1 = new Scenario();
            currentScene = new Scene(root, 500, 370);
            root.getChildren().addAll(new VBoxAcceuil());
            chStage.setTitle("APLI");
            chStage.setScene(currentScene);
            chStage.show();
            currentScene.setUserData("acceuil");
          //  this.currentScene = sceneAcceuil;

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

