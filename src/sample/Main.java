package sample;/**
 * Created by Alaska on 24.01.2017.
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

public class Main extends Application {

    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource( "Main.fxml" ) );
        primaryStage.setTitle( "TimeToDeadline" );
        //Image icon = new Image("mainIcon.png");
        primaryStage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        primaryStage.setScene( new Scene( root, 661, 404 ) );


        primaryStage.show();
    }

    private URL getResource(String name) {
        return getClass().getResource( name );
    }
}