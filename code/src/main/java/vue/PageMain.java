package vue;

import Controleur.ControleurMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PageMain extends Application {

    private static StageScenarioConnu currentStage;

    private static ControleurMenu controleurMenu;
    @Override
    public void start(Stage stage) throws IOException {
        currentStage = new StageScenarioConnu();
        stage = currentStage;
        controleurMenu = new ControleurMenu(currentStage.getvBoxMenu());
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void setScene(Scene parScene){

    }

    public static StageScenarioConnu getCurrentStage() {
        return currentStage;
    }
}