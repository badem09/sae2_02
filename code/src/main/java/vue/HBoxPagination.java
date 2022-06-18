package vue;

import Controleur.ControleurPagination;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxPagination extends HBox {

    private Label labelCurrentPage;

    private Label labelMaxPage;
    private Button buttonAvancer ;
    private Button buttonReculer;
    private Button buttonDebut ;
    private Button buttonFin;

    private ControleurPagination controleurPage;
    private VBoxAllItineraire vBoxItineraire;

    public HBoxPagination(VBoxAllItineraire parVboxIt){
        vBoxItineraire = parVboxIt;

        controleurPage = new ControleurPagination(vBoxItineraire,this);
        labelMaxPage = new Label("1");
        labelCurrentPage = new Label("1");
        buttonAvancer = new Button(">");
        buttonReculer = new Button("<");
        buttonDebut = new Button("<<");
        buttonFin = new Button(">>");

        buttonDebut.setAccessibleText("debut");
        buttonReculer.setAccessibleText("reculer");
        buttonAvancer.setAccessibleText("avancer");
        buttonFin.setAccessibleText("fin");

        buttonFin.setOnAction(controleurPage);
        buttonDebut.setOnAction(controleurPage);
        buttonReculer.setOnAction(controleurPage);
        buttonAvancer.setOnAction(controleurPage);


        HBox hBoxNumPage = new HBox(labelCurrentPage,new Label("sur"), labelMaxPage, new Label("pages"));
        hBoxNumPage.setSpacing(3);
        this.setSpacing(3);
        this.getChildren().addAll(buttonDebut,buttonReculer,hBoxNumPage, buttonAvancer,buttonFin);
    }

    public void setLabelCurrentPage(String strMin) {
        this.labelCurrentPage.setText(strMin);
    }

    public void setLabelMaxPage(String strMax) {
        this.labelMaxPage.setText(strMax);
    }
    public Label getLabelMaxPage() {
        return labelMaxPage;
    }

    public Label getLabelCurrentPage() {
        return labelCurrentPage;
    }
    public Integer getCurrentPage(){
        return Integer.valueOf(labelCurrentPage.getText());
    }
    public Integer getMaxPage(){
        return Integer.valueOf(labelMaxPage.getText());
    }

}
