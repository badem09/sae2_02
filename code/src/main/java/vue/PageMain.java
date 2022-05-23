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

        MenuBar menuBar = new MenuBar();
        VBoxMenu root = new VBoxMenu();
        Scenario s1 = new Scenario();
        stage.setTitle("APLI");
        Scene scene = new Scene(root, 700, 560);
        stage.setScene(scene);
        stage.getIcons().add(new Image(new FileInputStream(
                "src/main/resources/pokeman.png")));
        stage.show();


    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}