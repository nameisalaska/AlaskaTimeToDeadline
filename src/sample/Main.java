package sample;/**
 * Created by Alaska on 24.01.2017.
 */

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource( "Main.fxml" ) );
        primaryStage.setScene( new Scene( root, 600, 200 ) );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch( args );
    }
}