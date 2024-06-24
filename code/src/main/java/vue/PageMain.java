package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PageMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        VBoxRoot root = new VBoxRoot(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        File css = new File("css"+File.separator+"style.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.getIcons().add(new Image(new FileInputStream(
                "code/src/main/resources/images/pokeman.png")));
        stage.setTitle("APLI");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}