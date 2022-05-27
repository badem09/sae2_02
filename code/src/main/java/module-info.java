module com.example.scenario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.scenario to javafx.fxml;
    exports com.example.scenario;
    exports vue;
    exports modele;
    exports Controleur;

}