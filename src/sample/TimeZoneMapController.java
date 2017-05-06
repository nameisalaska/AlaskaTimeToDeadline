package sample;/**
 * Created by Alaska on 04.05.2017.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TimeZoneMapController implements Initializable {
    @FXML
    Pane paneUnGMT9;
    @FXML
    Pane paneUnGMT8;
    @FXML
    Pane paneUnGMT7;
    @FXML
    Pane paneUnGMT6;
    @FXML
    Pane paneUnGMT5;
    @FXML
    Pane paneUnGMT4;
    @FXML
    Pane paneUnGMT3;
    @FXML
    Pane paneUnGMT2;
    @FXML
    Pane paneUnGMT1;
    @FXML
    Pane paneGMT0;
    @FXML
    Pane paneGMT1;
    @FXML
    Pane paneGMT2;
    @FXML
    Pane paneGMT3;
    @FXML
    Pane paneGMT4;
    @FXML
    Pane paneGMT5;
    @FXML
    Pane paneGMT6;
    @FXML
    Pane paneGMT7;
    @FXML
    Pane paneGMT8;
    @FXML
    Pane paneGMT9;
    @FXML
    Pane paneGMT10;
    @FXML
    Pane paneGMT11;
    @FXML
    Pane paneGMT12;
    @FXML
    Pane pane;

    public ArrayList<Pane> timeZoneOnMap = new ArrayList<>( );

    Stage stage = new Stage();

    public void start() throws Exception {
        Parent root = FXMLLoader.load( getClass().getResource( "TimeZoneMap.fxml" ) );
        stage.setScene( new Scene( root, 681, 468 ) );
        stage.show();
    }
    private void addTimeZoneInArray(){
        timeZoneOnMap.add(paneUnGMT9); timeZoneOnMap.add(paneUnGMT8); timeZoneOnMap.add(paneUnGMT7); timeZoneOnMap.add(paneUnGMT6); timeZoneOnMap.add(paneUnGMT5); timeZoneOnMap.add(paneUnGMT4); timeZoneOnMap.add(paneUnGMT3); timeZoneOnMap.add(paneUnGMT2); timeZoneOnMap.add(paneUnGMT1);
        timeZoneOnMap.add(paneGMT0);   timeZoneOnMap.add(paneGMT1);  timeZoneOnMap.add(paneGMT2);  timeZoneOnMap.add(paneGMT3);  timeZoneOnMap.add(paneGMT4); timeZoneOnMap.add(paneGMT5);  timeZoneOnMap.add(paneGMT6);  timeZoneOnMap.add(paneGMT7);  timeZoneOnMap.add(paneGMT8);  timeZoneOnMap.add(paneGMT9);  timeZoneOnMap.add(paneGMT10);  timeZoneOnMap.add(paneGMT11);  timeZoneOnMap.add(paneGMT12);
    }
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        addTimeZoneInArray();
        launch();
    }

    private void launch() {
        MainController mainController = new MainController();
        for(int i = 0; i < timeZoneOnMap.size(); i++ ){
            Tooltip.install(timeZoneOnMap.get(i), new Tooltip(mainController.timeZones.get(i)) );
        }
    }

}
