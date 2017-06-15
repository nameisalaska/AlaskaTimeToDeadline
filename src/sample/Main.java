package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Alaska on 24.01.2017.
 */

public class Main extends Application {
    public static Stage stage;
    private MainController mainController = new MainController();

    public static void main(String[] args) {
        launch( args );
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource( "MainForm.fxml" ));
        Parent root =  loader.load();
        primaryStage.setTitle( "TimeToDeadline" );
        primaryStage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        Scene scene = new Scene( root, 608, 568 );
        primaryStage.setScene( scene );
        primaryStage.setResizable(false);
        primaryStage.show();
        stage = primaryStage;
        mainController = loader.getController();
         try {
             XMLDecoder xmlDecoder = new XMLDecoder( new FileInputStream( "Main.xml" ));
             Date date = (Date) xmlDecoder.readObject();
             if (mainController.deadlineDate!=null && !date.before(new Date( ))) {
                mainController.getDeadlineInfo( date );
                mainController.isDeadline = true;
             }
         } catch (IOException e) {
            e.printStackTrace();
         }
    }

    private URL getResource(String name) {
        return getClass().getResource( name );
    }
}

