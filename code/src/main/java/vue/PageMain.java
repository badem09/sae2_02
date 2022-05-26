package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Scenario;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Member;

public class PageMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage = new StageScenarioConnu();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}