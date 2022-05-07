package com.example.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class carteFrance1 extends Application {

    private MapView mapView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // set the title and size of the stage and show it
        stage.setTitle("France");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        // create a JavaFX scene with a stack pane as the root node, and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // Note: it is not best practice to store API keys in source code.
        // The API key is referenced here for the convenience of this tutorial.
        String yourApiKey = "AAPKabf6535cb8604314b6fa982a907bcea2iJpY" +
                "uajwH6N65Q8U8TQGzhDrdrvsHrGX_h6SrbzJbwuxqmSVAICjwrQ9NNcI-G-N";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // create a map view to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);

        // set the map on the map view
        mapView.setMap(map);

        mapView.setViewpoint(new Viewpoint(46.9276, 2.5, 8045900.819286));

    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose();
        }
    }

}