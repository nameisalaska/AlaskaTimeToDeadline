package sample;/**
 * Created by Alaska on 04.05.2017.
 */

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private static Stage stage = new Stage();

    private ArrayList<Pane> timeZoneOnMap = new ArrayList<>( );
    private List<String> timeZones = Arrays.asList("GMT-09:00 , Anchorage, Alaska", "GMT-08:00, Los Angeles, Vancouver, Tijuana", "GMT-07:00, Phoenix, Calgary, Ciudad Juarez", "GMT-06:00, Chicago, Guatemala City, Mexico City, San Jose, San Salvador, Winnipeg", "GMT-05:00, New York, Lima, Toronto, Bogota, Havana, Kingston", "GMT-04:00, Caracas, Santiago, Manaus, Halifax, Santo Domingo", "GMT-03:00, Buenos Aires, Montevideo, Sao Paulo", "GMT-02:00, Brazil, United Kingdom", "GMT-01:00, Cape Verde, Denmark, Greenland, Portugal", "GMT-00:00, Accra, Casablanca, Dakar, Dublin, Lisbon, London", "GMT+01:00, Berlin, Lagos, Madrid, Paris, Rome, Tunis, Vienna, Warsaw", "GMT+02:00, Athens, Bucharest, Cairo, Helsinki, Jerusalem, Johannesburg, Kiev", "GMT+03:00, Istanbul, Moscow, Nairobi, Baghdad, Doha, Khartoum, Minsk, Riyadh", "GMT+04:00, Baku, Dubai, Samara, Muscat", "GMT+05:00, Karachi, Tashkent, Yekaterinburg", "GMT+06:00, Almaty, Dhaka, Omsk", "GMT+07:00, Jakarta, Bangkok, Novosibirsk, Hanoi", "GMT+08:00, Beijing, Taipei, Perth, Manila, Denpasar, Irkutsk", "GMT+09:00, Seoul,Tokyo, Ambon, Yakutsk", "GMT+10:00, Port Moresby, Sydney, Vladivostok", "GMT+11:00, Noumea", "GMT+12:00, Auckland, Suva" );
    private MainController mainController;

    public void setMainController (MainController mainController){
        this.mainController = mainController;
    }
    public TimeZoneMapController startTheForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TimeZoneMap.fxml"));
        Parent root =  loader.load();
        stage.setScene( new Scene( root, 685, 456 ) );
        stage.setTitle( "Selecting Time Zone" );
        stage.getIcons().add( new Image( getResource( "mapIcon.png" ).toExternalForm() ) );
        stage.setResizable( false );
        stage.show();
        return loader.getController();
    }
    private URL getResource(String name) {
        return getClass().getResource( name );
    }
    public void againStart() {
        stage.show();
        stage.toFront();
    }

    private void addTimeZoneInArray(){
        timeZoneOnMap.add(paneUnGMT9); timeZoneOnMap.add(paneUnGMT8); timeZoneOnMap.add(paneUnGMT7); timeZoneOnMap.add(paneUnGMT6); timeZoneOnMap.add(paneUnGMT5); timeZoneOnMap.add(paneUnGMT4); timeZoneOnMap.add(paneUnGMT3); timeZoneOnMap.add(paneUnGMT2); timeZoneOnMap.add(paneUnGMT1);
        timeZoneOnMap.add(paneGMT0);   timeZoneOnMap.add(paneGMT1);  timeZoneOnMap.add(paneGMT2);  timeZoneOnMap.add(paneGMT3);  timeZoneOnMap.add(paneGMT4); timeZoneOnMap.add(paneGMT5);  timeZoneOnMap.add(paneGMT6);  timeZoneOnMap.add(paneGMT7);  timeZoneOnMap.add(paneGMT8);  timeZoneOnMap.add(paneGMT9);  timeZoneOnMap.add(paneGMT10);  timeZoneOnMap.add(paneGMT11);  timeZoneOnMap.add(paneGMT12);
    }
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        addTimeZoneInArray();
        createArrayTimeZoneOnMap();
        createMouseEvents();
    }

    private void createArrayTimeZoneOnMap() {
        for(int i = 0; i < timeZoneOnMap.size(); i++ ){
            Tooltip.install(timeZoneOnMap.get(i), new Tooltip(timeZones.get(i)) );
        }

    }

    public void openQuestionDialog(String value){
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Add selected time zone :" +"\n" + value);
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           mainController.addSelectedTimeZone( value );

        } else {
            alert.close();
        }
    }

    /*int j = 0; // for handle();
    public void createMouseEvents(){
        j = 0;
        for( int i = 0; i < timeZoneOnMap.size(); i++) {
            j=i;
            timeZoneOnMap.get(i).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    openQuestionDialog(timeZones.get(j));
                }
            } );
        }
    }
    */
    public void createMouseEvents(){
        timeZoneOnMap.get(0).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    openQuestionDialog(timeZones.get(0));
                }
            } );
        timeZoneOnMap.get(1).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(1));
            }
        } );
        timeZoneOnMap.get(2).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(2));
            }
        } );
        timeZoneOnMap.get(3).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(3));
            }
        } );
        timeZoneOnMap.get(4).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(4));
            }
        } );
        timeZoneOnMap.get(5).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(5));
            }
        } );
        timeZoneOnMap.get(6).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(6));
            }
        } ); timeZoneOnMap.get(7).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(7));
            }
        } ); timeZoneOnMap.get(8).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(8));
            }
        } ); timeZoneOnMap.get(9).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(9));
            }
        } ); timeZoneOnMap.get(10).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(10));
            }
        } ); timeZoneOnMap.get(11).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(11));
            }
        } ); timeZoneOnMap.get(12).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(12));
            }
        } );
        timeZoneOnMap.get(13).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(13));
            }
        } ); timeZoneOnMap.get(14).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(14));
            }
        } ); timeZoneOnMap.get(15).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(15));
            }
        } );
        timeZoneOnMap.get(16).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(16));
            }
        } );
        timeZoneOnMap.get(17).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(17));
            }
        } );
        timeZoneOnMap.get(18).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(18));
            }
        } );
        timeZoneOnMap.get(19).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(19));
            }
        } );
        timeZoneOnMap.get(20).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(20));
            }
        } ); timeZoneOnMap.get(21).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(21));
            }
        } );
    }
}
