package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxAide extends VBox {

    public VBoxAide(){
        setId("opaque");
        setPadding(new Insets(10));
        Label label = new Label("""
                 
                 Cette page est consacrée a l'assistance, vous pourrez y trouver des explicationssur les différentes pages.


                     1. Les menus
                     
                          1.1 Scenarios enregistrés.\s

                               En cliquant sur ce menu, vous aurez le choix du scenario. Après avoir fait votre choixvous aurez à gauche le détail du scénario, à droite les membres impliqués et enfin en dessous l'itinéraire le plus rapide.
                               Exemple : Le numéro de transaction : le vendeur A vend a l'acheteur B : Ville de départ ----> Ville d'arrivé avec le nombre de km parcourus.

                          1.2 Nouveau scenario.\s

                               En cliquant sur ce menu vous avez la possibilité de créer votre propre scenario à partird'un fichier au format txt avec la forme Vendeur -> Acheteur qui sera converti en scenario.

                          1.3 Tous les itinéraires.\s

                               Ce menu permet de visualiser tous les itinéraires possibles pour un scénario.C'est-à-dire qu'après avoir choisi votre scénario vous aurez en visuel toutesles possibilités de parcours.

                          1.4 Itinéraire personnalisé.\s

                               Ce menu permet de créer votre propre itinéraire. Tout d'abord vous choisissez un scénario, ensuite vous cliquez le prochain membre que vous souhaitez puis vous validez avec le bouton en bas à gauche de votre écran.\s
                               Quand vous avez fini votre parcours vous avez accès aux membre rentrés ainsi que le chemin et la distance.

                          1.5 Membres.

                               Cette rubrique vous permet soit a partir d'une ville de trouver ses membres, soit à partir d'un membre de trouver sa ville."""
        );
        label.setId("inline");
        label.setWrapText(true);
        this.getChildren().add(label);
    }

}
