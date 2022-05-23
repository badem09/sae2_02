package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Scenario;

import java.io.FileInputStream;
import java.io.IOException;

public class ScenarioApplicationAcceuil extends Application {
    private static Scene currentScene;
    private Stage chStage;
    //private String strScene;
    @Override
    public void start(Stage stage) throws IOException {

            VBox root = new VBox();
            Scenario s1 = new Scenario();
            currentScene = new Scene(root, 500, 360);
            root.getChildren().addAll(new VBoxAcceuil());
            stage.setTitle("APLI");
            stage.setScene(currentScene);
            stage.getIcons().add(new Image(new FileInputStream(
                    "src/main/resources/pokeman.png")));
            stage.show();
            chStage = stage;
            currentScene.setUserData("acceuil");
          //  this.currentScene = sceneAcceuil;

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Scene getCurrentScene(){
        return currentScene;
    }
}

