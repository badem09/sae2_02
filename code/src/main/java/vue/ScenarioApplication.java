package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Scenario;
import java.io.File;

public class ScenarioApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(10);
        String path = "/Users/badem/Documents/INFI1/SAE/algos2/Documentation/scenario_0.txt";
        Scenario s1 = new Scenario();
        s1 = Scenario.lectureScenario(new File(path));
        System.out.println(s1);
        Label labelScenario = new Label (s1.toString());
        root.getChildren().add(labelScenario);

        //root.getChildren().add(labelHelloBis);
        Scene scene = new Scene(root,300,80);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

