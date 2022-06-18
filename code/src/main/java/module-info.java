module com.example.scenario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.scenario to javafx.fxml;

    exports vue;
    exports modele;
    exports Controleur;

}