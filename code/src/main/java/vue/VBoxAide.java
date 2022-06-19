package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VBoxAide extends VBox {

    public VBoxAide() throws FileNotFoundException {
        this.setId("opaque");
        Label label = new Label("Cette page est consacrée a l'assistance, vous pourrez y trouver des explications" +
                "sur les différentes pages.\n\n"+
                "     1. Les menus\n\n" +
                "          1.1 Scenarios enregistrés. \n\n" +
                "               En cliquant sur ce menu, vous aurez le choix du scenario. Après avoir fait votre choix" +
                "vous aurez à gauche le détail du scénario, à droite les membres impliqués et enfin en " +
                "dessous l'itinéraire le plus rapide.\n"+

                "Exemple : Le numéro de transaction : le vendeur A vend a l'acheteur B : " +
                "Ville de départ ----> Ville d'arrivé avec le nombre de km parcourus.\n\n" +

                "          1.2 Nouveau scenario. \n\n" +
                "               En cliquant sur ce menu vous avez la possibilité de créer votre propre scenario à partir" +
                "d'un fichier au format txt avec la forme Vendeur -> Acheteur qui sera converti en scenario.\n\n" +

                "          1.3 Tous les itinéraires. \n\n" +
                "               Ce menu permet de visualiser tous les itinéraires possibles pour un scénario." +
                "C'est-à-dire qu'après avoir choisi votre scénario vous aurez en visuel toutes"+
                "les possibilités de parcours.\n\n" +

                "          1.4 Itinéraire personnalisé. \n\n" +
                "               Ce menu permet de créer votre propre itinéraire. Tout d'abord vous choisissez un " +
                "scénario, ensuite vous cliquez le prochain membre que vous souhaitez puis vous " +
                "validez avec le bouton en bas a gauche de votre écran. \n" +
                "Quand vous avez fini votre parcours vous avez accès aux membre rentrés ainsi " +
                "que le chemin et la distance.\n\n"+

                "          1.5 Membres.\n\n"+
                "               Cette rubrique vous permet soit a partir d'une ville de trouver ses membres, soit à" +
                "partir d'un membre de trouver sa ville."
        );
        label.setId("inline");
        label.setWrapText(true);
        this.getChildren().add(label);
    }

}
