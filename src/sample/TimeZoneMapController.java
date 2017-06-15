package sample;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alaska on 04.05.2017.
 */

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
    /*
    Arrays for comboboxs for checking Time Zone, using for detailed selection;
     */
    private String[] unGMT9 = {"(GMT-9:00) America/Anchorage", "(GMT-9:00) America/Nome", "(GMT-9:00) America/Sitka", "(GMT-9:00) America/Yakutat", "(GMT-9:00) Pacific/Gambier", "(GMT-9:00) US/Alaska"};
    private String[] unGMT8 = {"(GMT-8:00) America/Dawson", "(GMT-8:00) America/Ensenada", "(GMT-8:00) America/Metlakatla", "(GMT-8:00) Canada/Yukon", "(GMT-8:00) Mexico/BajaNorte", "(GMT-8:00) Pacific/Pitcairn"};
    private String[] unGMT7 = {"(GMT-7:00) America/Boise", "(GMT-7:00) America/Cambridge_Bay", "(GMT-7:00) America/Phoenix", "(GMT-7:00) America/Shiprock", "(GMT-7:00) America/Yellowknife", "(GMT-7:00) US/Mountain"};
    private String[] unGMT6 = {"(GMT-6:00) America/Bahia_Banderas", "(GMT-6:00) America/Belize", "(GMT-6:00) America/Guatemala", "(GMT-6:00) America/Monterrey", "(GMT-6:00) America/Tegucigalpa", "(GMT-6:00) Chile/EasterIsland", "(GMT-6:00) Mexico/General", "(GMT-6:00) US/Indiana-Starke"};
    private String[] unGMT5 = {"(GMT-5:00) America/Atikokan", "(GMT-5:00) America/Bogota", "(GMT-5:00) America/Cayman", "(GMT-5:00) America/Coral_Harbour", "(GMT-5:00) Brazil/Acre", "(GMT-5:00) Canada/Eastern", "(GMT-5:00) Cuba", "(GMT-5:00) IET", "(GMT-5:00) Jamaica", "(GMT-5:00) US/East-Indiana", "(GMT-5:00) US/Eastern", "(GMT-5:00) US/Michigan"};
    private String[] unGMT4 = { "(GMT-4:00) America/Anguilla", "(GMT-4:00) America/Antigua", "(GMT-4:00) America/Aruba", "(GMT-4:00) America/Curacao", "(GMT-4:00) America/Montserrat", "(GMT-4:00) Antarctica/Palmer", "(GMT-4:00) Atlantic/Bermuda", "(GMT-4:00) Brazil/West", "(GMT-4:00) Canada/Atlantic", "(GMT-4:00) Chile/Continental"};
    private String[] unGMT3 = {"(GMT-3:00) America/Araguaina", "(GMT-3:00) America/Argentina/Buenos_Aires", "(GMT-3:00) America/Miquelon", "(GMT-3:00) America/Montevideo", "(GMT-3:00) America/Rosario", "(GMT-3:00) Antarctica/Rothera", "(GMT-3:00) Brazil/East"};
    private String[] unGMT2 = {"(GMT-2:00) America/Noronha", "(GMT-2:00) Atlantic/South_Georgia", "(GMT-2:00) Brazil/DeNoronha"};
    private String[] unGMT1 = {"(GMT-1:00) America/Scoresbysund", "(GMT-1:00) Atlantic/Azores", "(GMT-1:00) Atlantic/Cape_Verde"};
    private String[] GMT0 = {"(GMT 0:00) Africa/Bamako", "(GMT 0:00) Africa/Banjul", "(GMT 0:00) Africa/Bissau", "(GMT 0:00) Europe/Lisbon", "(GMT 0:00) Europe/London", "(GMT 0:00) Portugal"};
    private String[] GMT1 = {"(GMT+1:00) Africa/Algiers", "(GMT+1:00) Africa/Windhoek", "(GMT+1:00) Atlantic/Jan_Mayen", "(GMT+1:00) Europe/Amsterdam", "(GMT+1:00) Europe/Andorra", "(GMT+1:00) Europe/Belgrade", "(GMT+1:00) Europe/Berlin", "(GMT+1:00) Europe/Vienna", "(GMT+1:00) Europe/Zurich", "(GMT+1:00) Poland"};
    private String[] GMT2 = {"(GMT+2:00) Europe/Athens", "(GMT+2:00) Europe/Kiev", "(GMT+2:00) Europe/Mariehamn", "(GMT+2:00) Europe/Nicosia", "(GMT+2:00) Israel", "(GMT+2:00) Libya", "(GMT+2:00) Turkey"};
    private String[] GMT3 = {"(GMT+3:00) Africa/Addis_Ababa", "(GMT+3:00) Africa/Asmara", "(GMT+3:00) Africa/Nairobi", "(GMT+3:00) Antarctica/Syowa", "(GMT+3:00) Asia/Aden", "(GMT+3:00) Asia/Qatar", "(GMT+3:00) Asia/Riyadh", "(GMT+3:00) Europe/Kaliningrad", "(GMT+3:00) Europe/Minsk", "(GMT+3:00) Indian/Antananarivo", "(GMT+3:00) Indian/Comoro", "(GMT+3:00) Indian/Mayotte"};
    private String[] GMT4 = {"(GMT+4:00) Asia/Baku", "(GMT+4:00) Asia/Dubai", "(GMT+4:00) Europe/Moscow", "(GMT+4:00) Europe/Samara", "(GMT+4:00) Indian/Mahe"};
    private String[] GMT5 = {"(GMT+5:00) Antarctica/Mawson", "(GMT+5:00) Asia/Aqtau", "(GMT+5:00) Asia/Aqtobe", "(GMT+5:00) Asia/Ashgabat", "(GMT+5:00) Asia/Ashkhabad", "(GMT+5:00) Asia/Dushanbe", "(GMT+5:00) Asia/Karachi", "(GMT+5:00) Asia/Oral", "(GMT+5:00) Asia/Samarkand", "(GMT+5:00) Asia/Tashkent", "(GMT+5:00) Indian/Kerguelen"};
    private String[] GMT6 = {"(GMT+6:00) Antarctica/Vostok", "(GMT+6:00) Asia/Almaty", "(GMT+6:00) Asia/Qyzylorda", "(GMT+6:00) Asia/Yekaterinburg", "(GMT+6:00) Indian/Chagos"};
    private String[] GMT7 = {"(GMT+7:00) Antarctica/Davis", "(GMT+7:00) Asia/Bangkok", "(GMT+7:00) Asia/Ho_Chi_Minh", "(GMT+7:00) Indian/Christmas"};
    private String[] GMT8 = {"(GMT+8:00) Antarctica/Casey", "(GMT+8:00) Asia/Brunei", "(GMT+8:00) Singapore"};
    private String[] GMT9 = {"(GMT+9:00) Asia/Dili", "(GMT+9:00) Asia/Pyongyang", "(GMT+9:00) Asia/Seoul", "(GMT+9:00) Asia/Tokyo", "(GMT+9:00) Japan", "(GMT+9:00) Pacific/Palau"};
    private String[] GMT10 = {"(GMT+10:00) Antarctica/DumontDUrville", "(GMT+10:00) Asia/Khandyga", "(GMT+10:00) Asia/Yakutsk"};
    private String[] GMT11 = {"(GMT+11:00) Antarctica/Macquarie", "(GMT+11:00) Asia/Sakhalin", "(GMT+11:00) Asia/Ust-Nera", "(GMT+11:00) Asia/Vladivostok"};
    private String[] GMT12 = {"(GMT+12:00) Antarctica/McMurdo", "(GMT+12:00) Antarctica/South_Pole", "(GMT+12:00) Kwajalein", "(GMT+12:00) Pacific/Auckland", "(GMT+12:00) Pacific/Wallis"};
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
    @FXML
    private void openQuestionDialog(String value, String[] array){
        Stage stage = new Stage( );
        Pane paneForCombobox = new Pane();
        stage.setScene( new Scene(paneForCombobox, 260, 150 ) );
        stage.setTitle( "Select Time Zone" );
        Label zoneLabel = new Label(  );
        zoneLabel.setLayoutX( 5 );
        zoneLabel.setText(value);
        Label label = new Label(  );
        label.setText("Please specify your choice :");
        label.setLayoutX( 5 );
        label.setLayoutY( 10 );
        label.setFont( new Font( 18 ) );
        label.autosize();
        ComboBox comboBox = new ComboBox(  );
        comboBox.setPrefWidth( 150 );
        comboBox.setPrefHeight( 25 );
        comboBox.setLayoutY( 70 ); comboBox.setLayoutX( 25 );
        comboBox.getItems().addAll(array);
        Button buttonOk = new Button(  );
        buttonOk.setText( "OK" );
        buttonOk.addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainController.addSelectedTimeZone((String) comboBox.getValue() );
            }
        } );
        buttonOk.setLayoutX( 175);
        buttonOk.setLayoutY( 70 );
        paneForCombobox.getChildren().addAll( comboBox , buttonOk,label);
        stage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        stage.setResizable( false );
        stage.show();
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

    private void createMouseEvents(){
        timeZoneOnMap.get(0).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    openQuestionDialog(timeZones.get(0),unGMT9);
                }
            } );
        timeZoneOnMap.get(1).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(1),unGMT8);
            }
        } );
        timeZoneOnMap.get(2).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(2), unGMT7);
            }
        } );
        timeZoneOnMap.get(3).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(3),unGMT6);
            }
        } );
        timeZoneOnMap.get(4).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(4),unGMT5);
            }
        } );
        timeZoneOnMap.get(5).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(5),unGMT4);
            }
        } );
        timeZoneOnMap.get(6).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(6),unGMT3);
            }
        } ); timeZoneOnMap.get(7).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {openQuestionDialog(timeZones.get(7),unGMT2);}
        } ); timeZoneOnMap.get(8).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(8), unGMT1);
            }
        } ); timeZoneOnMap.get(9).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(9), GMT0);
            }
        } ); timeZoneOnMap.get(10).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(10), GMT1);
            }
        } ); timeZoneOnMap.get(11).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(11), GMT2);
            }
        } ); timeZoneOnMap.get(12).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(12),GMT3);
            }
        } );
        timeZoneOnMap.get(13).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(13),GMT4);
            }
        } ); timeZoneOnMap.get(14).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(14),GMT5);
            }
        } ); timeZoneOnMap.get(15).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(15),GMT6);
            }
        } );
        timeZoneOnMap.get(16).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(16),GMT7);
            }
        } );
        timeZoneOnMap.get(17).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(17), GMT8);
            }
        } );
        timeZoneOnMap.get(18).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(18),GMT9);
            }
        } );
        timeZoneOnMap.get(19).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(19),GMT10);
            }
        } );
        timeZoneOnMap.get(20).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(20),GMT11);
            }
        } ); timeZoneOnMap.get(21).addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openQuestionDialog(timeZones.get(21),GMT12);
            }
        } );
    }
}
