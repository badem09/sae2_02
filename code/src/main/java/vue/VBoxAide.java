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

public class VBoxAide extends VBox {

    public VBoxAide(){
        this.setId("opaque");
        //Label label1 = new Label("oe");
        //label1.setFont(Font.font("Cambria",30));
        //label1.setTextFill(Color.web("#0076a3"));
        //this.getChildren().add(label1);
        Label label = new Label("Cette page est consacrée a l'assistance, si vous ne comprnenez pas " +
                "comment marche l'application veuillez trouver ci-dessous des explications.\n"+
                " 1. Les menus\n" +
                " 1.1 Scenario enregistré. \n" +
                " En cliquant sur ce menu , vous aurez le choix du scenario. Après avoir fait votre choix vous aurez a " +
                "gauche le détail du scénario, a droite les membres impliqués et enfin en dessous l'itinéraire le plus rapide\n"+
                "Exemple : Le numéro de transaction : le vendeur A vend a l'acheteur B :" +
                " Ville de départ ----> Ville d'arrivé avec le nombre de km parcourus \n" +
                "1.2 Nouveau scenario \n" +
                "En cliquant sur ce menu vous avez la possibilité de créer votre propre scenario a partir d'un fichier " +
                "au format txt avec la forme Vendeur -> Acheteur qui sera converti en scenario\n " +
                "1.3 Tous les itinéraires \n" +
                "Ce menu permet de visualiser tous les itinéraires possibles pour un scénario. C'est a dire qu'après" +
                " avoir choisi votre scénario vous aurez en visuel toute les possibilités de parcours \n"+
                "1.4 Itinéraire personnalisé \n" +
                "Ce menu permet de créer votre propre itinéraire. Tout d'abord vous choisissez un scénario , " +
                "ensuite vous cliquez le prochain membre que vous souhaitez puis vous validez avec le bouton en" +
                " bas a gauche de votre écran. \n" +
                "Quand vous avez fini votre parcours vous avez accès aux membre rentrés ainsi que le chemin et la distance\n"
        );
        label.setWrapText(true);
        this.getChildren().add(label);


    }

}
