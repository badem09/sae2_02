package Controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import vue.HBoxPagination;
import vue.VBoxAllItineraire;

public class ControleurPagination implements EventHandler {
    private final VBoxAllItineraire vBoxItineraire;
    private final HBoxPagination pagination;

    public ControleurPagination(VBoxAllItineraire parVboxIt, HBoxPagination pagination){
        vBoxItineraire = parVboxIt;
        this.pagination = pagination;
    }
    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getAccessibleText() == "avancer") {
                if (pagination.getMaxPage() > pagination.getCurrentPage()) {
                    int anciennePage = Integer.parseInt(pagination.getLabelCurrentPage().getText()) +1;
                    // +1 car la pagination commence à 1.
                    pagination.setLabelCurrentPage(String.valueOf(anciennePage));
                    String currentPage = vBoxItineraire.getItineraire().toString(8 * (anciennePage - 1), 8 * anciennePage);
                    vBoxItineraire.getTextItineraire().setText(currentPage);
                }
            }
        }
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getAccessibleText() == "reculer") {
                if (pagination.getCurrentPage() > 1) {
                    int anciennePage = Integer.parseInt(pagination.getLabelCurrentPage().getText()) -1;
                    pagination.setLabelCurrentPage(String.valueOf(anciennePage));
                    String currentPage = vBoxItineraire.getItineraire().toString(8 * (anciennePage - 1), 8 * anciennePage);
                    vBoxItineraire.getTextItineraire().setText(currentPage);

                }
            }
        }
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getAccessibleText() == "fin") {
                if (pagination.getCurrentPage() < pagination.getMaxPage()) {
                    pagination.setLabelCurrentPage(pagination.getLabelMaxPage().getText());
                    String currentPage = vBoxItineraire.getItineraire().toString(
                            (pagination.getMaxPage() - 1) * 8, (pagination.getMaxPage() + 1) * 8);
                    vBoxItineraire.getTextItineraire().setText(currentPage);
                }
            }
        }
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getAccessibleText() == "debut") {
                if (pagination.getCurrentPage() > 1) {
                    pagination.setLabelCurrentPage("1");
                    String currentPage = vBoxItineraire.getItineraire().toString(0, 8);
                    vBoxItineraire.getTextItineraire().setText(currentPage);
                }
            }
        }
    }
}
