package sample;/**
 * Created by Alaska on 04.05.2017.
 */

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Tooltip;

import javafx.scene.layout.Pane;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class TimeZoneMapController implements Initializable {
    @FXML
    Pane paneUnGMT9;
    @FXML
    Pane pane;

    Stage stage = new Stage();

    public void start() throws Exception {

        Parent root = FXMLLoader.load( getClass().getResource( "TimeZoneMap.fxml" ) );

        stage.setScene( new Scene( root, 681, 468 ) );
        stage.show();

    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        launch();
    }

    private void launch() {
        Tooltip.install( paneUnGMT9, new Tooltip( "This is a Pane" ) );
    }

    @FXML
    private void test() {

    }

    private void onGMT() {

    }
}
