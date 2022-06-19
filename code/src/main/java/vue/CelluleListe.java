package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CelluleListe extends HBox {

    private final String membre;
    private final String infos;

    public CelluleListe(String membre, String infos) throws FileNotFoundException {
        this.infos = infos;
        this.membre = membre;
        ImageView imageView;
        try {
             imageView = new ImageView(new Image(new FileInputStream(
                    "src/main/resources/images/" + membre + ".jpg")));
        }
        catch (FileNotFoundException fnfe){
            imageView = new ImageView(new Image(new FileInputStream(
                    "src/main/resources/images/unknown.jpg")));
        }

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        HBox vBoxImage = new HBox(imageView);
        try {
            Integer.parseInt(infos);
            getChildren().addAll(vBoxImage,new Text(infos + ". " + membre));
        } catch (NumberFormatException e) {
            getChildren().addAll(vBoxImage,new Text(membre + " " + infos));
        }
        setSpacing(10);
     //   vBoxImage.setAlignment(Pos.CENTER_RIGHT); ne marche pas
    }

    public String getMembre() {
        return membre;
    }
}
