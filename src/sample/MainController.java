package sample;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Alaska on 28.01.2017.
 */

public class MainController implements Initializable {
    private static final double SCALE = 1.3; // коэффициент увеличения
    private static final double DURATION = 300; // время анимации в мс
    static UpdatingThread threadForUpdate;
    static private ClockWidget clock = new ClockWidget();
    public ArrayList<Label> labelclocks = new ArrayList<Label>();
    /*  Var 'control' and 'count' use for cheking in the next methods
      */
    int control = 0;
    @FXML
    private Label labelTime;        // clock of Default Time Zone;
    @FXML
    private ComboBox combobox;      // with value of Time Zones for select ;
    @FXML
    private ComboBox comboboxdelete; // with value of Time Zones for delete;
    @FXML
    private Button buttonOpenClock;
    @FXML
    private ImageView mapIcon;
    /*
    labels clock1 - clock5 for writing selected Time Zones;
     */
    @FXML
    private Label clock1;
    @FXML
    private Label clock2;
    @FXML
    private Label clock3;
    @FXML
    private Label clock4;
    //  public List<String>  timeZones = Arrays.asList( "GMT-09:00", "GMT-08:00", "GMT-07:00", "GMT-06:00", "GMT-05:00", "GMT-04:00", "GMT-03:00", "GMT-02:00", "GMT-01:00", "GMT+00:00", "GMT+01:00", "GMT+02:00", "GMT+03:00", "GMT+04:00", "GMT+05:00", "GMT+06:00", "GMT+07:00", "GMT+08:00", "GMT+09:00", "GMT+10:00", "GMT+11:00", "GMT+12:00") ;
    @FXML
    private Label clock5;
    private int count = 0; // count of Time Zones
    private ArrayList<String> deleteItems = new ArrayList<String>();
    private ArrayList<String> labelsName = new ArrayList<String>();

    private void modifyButton(final Runnable action) {


        // создаём анимацию увеличения картинки
        final ScaleTransition animationGrow = new ScaleTransition( Duration.millis( DURATION ), mapIcon );
        animationGrow.setToX( SCALE );
        animationGrow.setToY( SCALE );


        // и уменьшения
        final ScaleTransition animationShrink = new ScaleTransition( Duration.millis( DURATION ), mapIcon );
        animationShrink.setToX( 1 );
        animationShrink.setToY( 1 );

      /*  // добавляем эффект отраженичя
        final Reflection effect = new Reflection();
        mapIcon.setEffect(effect);
      */
        // обработчик нажатия мыши

        // при наведении курсора мы запускаем анимацию увеличения кнопки
        mapIcon.setOnMouseEntered( new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                mapIcon.toFront();
                animationShrink.stop();
                animationGrow.playFromStart();
            }
        } );
        // когда курсор сдвигается -- запускаем анимацию уменьшения
        mapIcon.setOnMouseExited( new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                animationGrow.stop();
                animationShrink.playFromStart();
            }
        } );
    }
    @FXML
    public void addSelectedTimeZone() {
        try {
            boolean var = false;
            if (count > 4) {
                showErrMessage( "Sorry. You can select only 5 Time Zones" );
            } else {
                for (int j = 0; j < count; j++) {
                    if (labelclocks.get( j ).getText().equals( combobox.getValue() )) {
                        showInfMessage( "You have already added this Time Zone" );
                        var = true;
                    }
                }
                if (!var) {
                    labelsName.add( (String) combobox.getValue() );
                    deleteItems.add((String) combobox.getValue());
                    setTextlabel();
                    loadDeleteItems();
                    clock.addTimeZone( ((String) combobox.getValue()).substring( 0, 9 ) );
                    buttonOpenClock.setDisable( false );
                    count++;
                }
            }
        } catch (NullPointerException e) {
            showErrMessage( "First select" );
    }
    }

    public void clickOnGeoButton() throws Exception {

        new TimeZoneMapController().start();
    }

    public void showErrMessage(String message) {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        alert.setTitle( "Error" );
        alert.setHeaderText( "Oooops" );
        alert.setContentText( message );
        alert.showAndWait();
    }

    public void showInfMessage(String message) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle( "Error" );
        alert.setHeaderText( "Oooops" );
        alert.setContentText( message );
        alert.showAndWait();
    }
    @FXML
    public void setTextlabel( ) {
        for (int i = 0; i < labelclocks.size(); i++) {
            labelclocks.get( i ).setText("");
        }
        for (int i = 0; i < labelsName.size(); i++) {
            labelclocks.get( i ).setText(labelsName.get( i ) );
            Tooltip.install( labelclocks.get( i ), new Tooltip( labelsName.get( i ) ) );
        }
    }
    @FXML
    public void openClock() {
        control += 1;
        if (control == 1) {
            clock.start();
        } else {
            clock.Exit();
            clock.AgainStart();
        }
    }
    @FXML
    public void deleteSelectedTimeZone() {
        if(comboboxdelete.getValue() == null) {
            showErrMessage( "First Select" );
        } else {
            deleteItems.remove( comboboxdelete.getValue() );
            labelsName.remove( comboboxdelete.getValue() );
            setTextlabel();
            clock.deleteTimeZone( ((String) comboboxdelete.getValue()).substring( 0, 9 ) );
            loadDeleteItems();
            count -= 1;             //use in method 'clickOk';

        }
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) { launch(); }

    public void createArrayOfLabels() {
        labelclocks.add( clock1 );
        labelclocks.add( clock2 );
        labelclocks.add( clock3 );
        labelclocks.add( clock4 );
        labelclocks.add( clock5 );
    }

    public void createClockWidget() {
        clock.pane = new Pane();
        clock.stage = new Stage();
        clock.labelX = 80;
        clock.addTimeZone( TimeZone.getDefault().getID() );
    }

    private void launch() {
        createArrayOfLabels();
        loadItems();
        threadForUpdate = new UpdatingThread();
        threadForUpdate.start();
        createClockWidget();
        modifyButton( new Runnable() {

            public void run() {
            }
        } );
    }
    public void loadItems() {
        combobox.getItems().addAll( "GMT-09:00 , Anchorage, Alaska",
                "GMT-08:00, Los Angeles, Vancouver, Tijuana",
                "GMT-07:00, Phoenix, Calgary, Ciudad Juarez",
                "GMT-06:00, Chicago, Guatemala City, Mexico City, San Jose, San Salvador, Winnipeg",
                "GMT-05:00, New York, Lima, Toronto, Bogota, Havana, Kingston",
                "GMT-04:00, Caracas, Santiago, Manaus, Halifax, Santo Domingo",
                "GMT-03:00, Buenos Aires, Montevideo, Sao Paulo",
                "GMT-02:00, Brazil, United Kingdom",
                "GMT-01:00, Cape Verde, Denmark, Greenland, Portugal",
                "GMT-00:00, Accra, Casablanca, Dakar, Dublin, Lisbon, London",
                "GMT+01:00, Berlin, Lagos, Madrid, Paris, Rome, Tunis, Vienna, Warsaw",
                "GMT+02:00, Athens, Bucharest, Cairo, Helsinki, Jerusalem, Johannesburg, Kiev",
                "GMT+03:00, Istanbul, Moscow, Nairobi, Baghdad, Doha, Khartoum, Minsk, Riyadh",
                "GMT+04:00, Baku, Dubai, Samara, Muscat",
                "GMT+05:00, Karachi, Tashkent, Yekaterinburg",
                "GMT+06:00, Almaty, Dhaka, Omsk",
                "GMT+07:00, Jakarta, Bangkok, Novosibirsk, Hanoi",
                "GMT+08:00, Beijing, Taipei, Perth, Manila, Denpasar, Irkutsk",
                "GMT+09:00, Seoul,Tokyo, Ambon, Yakutsk",
                "GMT+10:00, Port Moresby, Sydney, Vladivostok",
                "GMT+11:00, Noumea",
                "GMT+12:00, Auckland, Suva" );
    }

    public void loadDeleteItems(){
        comboboxdelete.getItems().clear();
        comboboxdelete.getItems().addAll( deleteItems );
    }

    class UpdatingThread extends Thread {
        @Override
        public void run() {
            //  System.out.println( "Привет из первого побочного потока!" );
            while (true) {
                Platform.runLater( () -> {
                    clock.refreshTime( TimeZone.getDefault(), labelTime );
                } );
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
