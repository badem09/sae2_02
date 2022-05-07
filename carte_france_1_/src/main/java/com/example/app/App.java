// lien : https://developers.arcgis.com/java/route-and-directions/add-streetmap-premium-data/

package com.example.app;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.view.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private MapView mapView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        // set the title and size of the stage and show it
        stage.setTitle("Add a point line and polygon tutorial");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        // create a JavaFX scene with a stack pane as the root node
        // and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // Note: it is not best practice to store API keys in source code.
        // The API key is referenced here for the convenience of this tutorial.
        String yourApiKey = "AAPKabf6535cb8604314b6fa982a907bcea2iJpYuajwH6N65Q8U8TQGzhDrdrvsHrGX_h6SrbzJbwuxqmSVAICjwrQ9NNcI-G-N";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // create a map view to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);

        // set the map on the map view
        mapView.setMap(map);
        mapView.setViewpoint(new Viewpoint(46.2276, 2.5, 9845900.819286));
        // create a graphics overlay and add it to the map view
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // create a point geometry with a location and spatial reference
        Point point = new Point(45.764043, 4.835659, SpatialReferences.getWgs84());
        // create an opaque orange (0xFFFF5733) point symbol with a blue (0xFF0063FF) outline symbol
        SimpleMarkerSymbol simpleMarkerSymbol =
                new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF5733, 100);
        SimpleLineSymbol blueOutlineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 2);

        simpleMarkerSymbol.setOutline(blueOutlineSymbol);

        // create a graphic with the point geometry and symbol
        Graphic pointGraphic = new Graphic(point, simpleMarkerSymbol);

        // add the point graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(pointGraphic);

        // create a point collection with a spatial reference, and add three points to it
        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
        polylinePoints.add(new Point(44.764043, 5));
        polylinePoints.add(new Point(43, 5));
        polylinePoints.add(new Point(46, 5));
        // create a polyline geometry from the point collection
        Polyline polyline = new Polyline(polylinePoints);

        // create a blue line symbol for the polyline
        SimpleLineSymbol polylineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0063FF, 3);

        // create a polyline graphic with the polyline geometry and symbol
        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);

        // add the polyline graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polylineGraphic);

        // create a point collection with a spatial reference, and add five points to it
        PointCollection polygonPoints = new PointCollection(SpatialReferences.getWgs84());
        polygonPoints.add(new Point(49, 5));
        polygonPoints.add(new Point(50.806796597377, 5));
        polygonPoints.add(new Point(53.791432890735, 5));
        polygonPoints.add(new Point(52.795966865355, 5));
        polygonPoints.add(new Point(51.808558110679, 5));
        // create a polygon geometry from the point collection
        Polygon polygon = new Polygon(polygonPoints);

        // create an orange fill symbol with 20% transparency and the blue simple line symbol
        SimpleFillSymbol polygonFillSymbol =
                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x80FF5733, blueOutlineSymbol);

        // create a polygon graphic from the polygon geometry and symbol
        Graphic polygonGraphic = new Graphic(polygon, polygonFillSymbol);
        // add the polygon graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polygonGraphic);

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