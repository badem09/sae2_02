package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxAide extends VBox {

    public VBoxAide(){
        this.setId("opaque");
        this.getChildren().add(new Label( "Cette page est consacrée a l'assistance, si vous ne comprnenez pas comment marche l'application veuillez trouver ci-dessous des explications  "));
        this.getChildren().add(new Label( " 1. Les menus "));
        this.getChildren().add(new Label( " 1.1 Scenario enregistré : "));
        this.getChildren().add(new Label( " En cliquant sur ce menu , vous aurez le choix du scenario. Après avoir fait votre choix vous aurez a gauche le détail du scénario, a droite les membres impliqués et enfin en dessous l'itinéraire le plus rapide "));
        this.getChildren().add(new Label("Exemple : Le numéro de transaction : le vendeur A vend a l'acheteur B : Ville de départ ----> Ville d'arrivé avec le nombre de km parcourus "));
        this.getChildren().add(new Label("1.2 Nouveau scenario "));
        this.getChildren().add(new Label("En cliquant sur ce menu vous avez la possibilité de créer votre propre scenario a partir d'un fichier au format ... qui sera converti en scenario "));
        this.getChildren().add(new Label("1.3 Tous les itinéraires "));
        this.getChildren().add(new Label("Ce menu permet de visualiser tous les itinéraires possibles pour un scénario. C'est a dire qu'après avoir choisi votre scénario vous aurez en visuel toute les possibilités de parcours "));
        this.getChildren().add(new Label("1.4 Itinéraire personnalisé "));
        this.getChildren().add(new Label("Ce menu permet de créer votre propre itinéraire "));
    }

}
