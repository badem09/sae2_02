package vue;

import Controleur.ControleurMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PageMain extends Application {

    private static VBoxRoot root;
    private ControleurMenu  controleurMenu;

    public static VBoxScenarioConnu getvBoxScenario() {
        return root.getVboxScenario();
    }

    @Override
    public void start(Stage stage) throws IOException {
       // currentStage = new StageScenarioConnu();
        ///stage = currentStage;
        root = new VBoxRoot(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        File css = new File("css"+File.separator+"style.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.getIcons().add(new Image(new FileInputStream(
                "src/main/resources/pokeman.png")));        //controleurMenu = new ControleurMenu(root.getVBoxMenu());
        stage.setTitle("APLI");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void setScene(Scene parScene){

    }

    public static VBoxMenu getvBoxMenu() {
        return root.getVBoxMenu();
    }

}