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


public class Main extends Application {

    public static void main(String[] args) {
        launch( args );
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource( "Main.fxml" ) );
        primaryStage.setTitle( "TimeToDeadline" );
        primaryStage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        Scene scene = new Scene( root, 661, 543 );
        primaryStage.setScene( scene );
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private URL getResource(String name) {
        return getClass().getResource( name );
    }

}
