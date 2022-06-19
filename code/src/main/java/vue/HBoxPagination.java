package vue;

import Controleur.ControleurPagination;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HBoxPagination extends HBox {

    private final Label labelCurrentPage;
    private final Label labelMaxPage;

    public HBoxPagination(VBoxAllItineraire parVboxIt){

        labelMaxPage = new Label("1");
        labelCurrentPage = new Label("1");
        ControleurPagination controleurPage = new ControleurPagination(parVboxIt, this);
        Button buttonAvancer = new Button(">");
        Button buttonReculer = new Button("<");
        Button buttonDebut = new Button("<<");
        Button buttonFin = new Button(">>");

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
        this.getChildren().addAll(buttonDebut, buttonReculer,hBoxNumPage, buttonAvancer, buttonFin);
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
