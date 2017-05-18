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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Alaska on 28.01.2017.
 */

public class MainController implements Initializable {
    private static final double SCALE = 1.3; // size for increase;
    private static final double DURATION = 300; // time of animation;
    private static final double QUANTITYOFCLOCK = 7;

    static UpdatingThread threadForUpdate;
    static private ClockWidget clockWidget = new ClockWidget();

    /*  Var 'control' and 'count' and ' controlGeo' use for cheking in the next methods
      */
    int control = 0;
    int  controlGeo = 0;
    private int count = 0; // count of Time Zones

    @FXML
    private Label labelTime;        // clock of Default Time Zone;
    @FXML
    private ComboBox SelectComboBox;      // with value of Time Zones for select ;
    @FXML
    private ComboBox comboboxDelete; // with value of Time Zones for delete;
    @FXML
    public ComboBox hourComboBox;
    @FXML
    public ComboBox minuteComboBox;
    @FXML
    private Button buttonOpenClock;
    @FXML
    private ImageView mapIcon;
    @FXML
    private DatePicker deadlineDatePicker;
    @FXML
    private Label labelTimeToDeadline;
    @FXML
    private VBox layoutClock;

    /*
    labels clock1 - clock7 for writing selected Time Zones;
     */
    @FXML
    private Label clock1;
    @FXML
    private Label clock2;
    @FXML
    private Label clock3;
    @FXML
    private Label clock4;
    @FXML
    private Label clock5;
    @FXML
    private Label clock6;
    @FXML
    private Label clock7;
    @FXML
    private Pane pane;


    public Date  deadlineDate;

    private ArrayList<Label> labelСlocks = new ArrayList<>() ;
    private ArrayList<String> deleteItems = new ArrayList<>();
    private ArrayList<String> labelsName = new ArrayList<>();
    private List<String>  timeZones = Arrays.asList("GMT-09:00 , Anchorage, Alaska", "GMT-08:00, Los Angeles, Vancouver, Tijuana", "GMT-07:00, Phoenix, Calgary, Ciudad Juarez", "GMT-06:00, Chicago, Guatemala City, Mexico City, San Jose, San Salvador, Winnipeg", "GMT-05:00, New York, Lima, Toronto, Bogota, Havana, Kingston", "GMT-04:00, Caracas, Santiago, Manaus, Halifax, Santo Domingo", "GMT-03:00, Buenos Aires, Montevideo, Sao Paulo", "GMT-02:00, Brazil, United Kingdom", "GMT-01:00, Cape Verde, Denmark, Greenland, Portugal", "GMT-00:00, Accra, Casablanca, Dakar, Dublin, Lisbon, London", "GMT+01:00, Berlin, Lagos, Madrid, Paris, Rome, Tunis, Vienna, Warsaw", "GMT+02:00, Athens, Bucharest, Cairo, Helsinki, Jerusalem, Johannesburg, Kiev", "GMT+03:00, Istanbul, Moscow, Nairobi, Baghdad, Doha, Khartoum, Minsk, Riyadh", "GMT+04:00, Baku, Dubai, Samara, Muscat", "GMT+05:00, Karachi, Tashkent, Yekaterinburg", "GMT+06:00, Almaty, Dhaka, Omsk", "GMT+07:00, Jakarta, Bangkok, Novosibirsk, Hanoi", "GMT+08:00, Beijing, Taipei, Perth, Manila, Denpasar, Irkutsk", "GMT+09:00, Seoul,Tokyo, Ambon, Yakutsk", "GMT+10:00, Port Moresby, Sydney, Vladivostok", "GMT+11:00, Noumea", "GMT+12:00, Auckland, Suva" );
    TimeZoneMapController timeZoneMapController = new TimeZoneMapController();
    private void setAnimation() {

        //Increase animation
        final ScaleTransition animationGrow = new ScaleTransition( Duration.millis( DURATION ), mapIcon );
        animationGrow.setToX( SCALE );
        animationGrow.setToY( SCALE );

        //Decrease animation
        final ScaleTransition animationShrink = new ScaleTransition( Duration.millis( DURATION ), mapIcon );
        animationShrink.setToX( 1 );
        animationShrink.setToY( 1 );

        mapIcon.setOnMouseEntered( new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mapIcon.toFront();
                animationShrink.stop();
                animationGrow.playFromStart();
            }
        } );

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
            if (count == QUANTITYOFCLOCK ) {
                showErrMessage( "Sorry. You can select only 7 Time Zones" );
            } else {
                for (int j = 0; j < count; j++) {
                    if (labelСlocks.get( j ).getText().equals( SelectComboBox.getValue() )) {
                        showInfMessage( "You have already added this Time Zone" );
                        var = true;
                    }
                }
                if (!var) {
                    labelsName.add( (String) SelectComboBox.getValue() );
                    deleteItems.add((String) SelectComboBox.getValue());
                    setTextlabel();
                    loadDeleteItems();
                    clockWidget.addTimeZone( ((String) SelectComboBox.getValue()).substring( 0, 9 ), (String) SelectComboBox.getValue() );
                    buttonOpenClock.setDisable( false );
                    count++;
                }
            }
        } catch (NullPointerException e) {
            showErrMessage( "First select" );
    }
    }
    public void addSelectedTimeZone(String value) {
        boolean var = false;
        if (count == QUANTITYOFCLOCK ) {
            showErrMessage( "Sorry. You can select only 7 Time Zones" );
        } else {
            for (int j = 0; j < count; j++) {
                if (labelСlocks.get( j ).getText().equals( value )) {
                    showInfMessage( "You have already added this Time Zone" );
                    var = true;
                }
            }
            if (!var) {
                labelsName.add(value);
                deleteItems.add(value);
                System.out.println(labelsName.size());
                setTextlabel();
                loadDeleteItems();
                clockWidget.addTimeZone( value.substring( 0, 9 ), value );
                buttonOpenClock.setDisable( false );
                count++;
            }
        }
    }

    public void getDeadlineInfo() {
        try {
            LocalDate localDate = deadlineDatePicker.getValue();
            if (localDate.isBefore( LocalDate.now() )) {
                showErrMessage( "This date is before the current date." + "\n" + "Please, try again" );
            } else {
                try {
                    Instant instant = Instant.from( localDate.atStartOfDay( ZoneId.systemDefault() ) );
                    Date date = Date.from( instant );
                    SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
                    String dateString = dateFormat.format( date );
                    dateString = dateString.substring( 0, 10 ) + " " + hourComboBox.getValue() + ":" + minuteComboBox.getValue() + ":" + "00";
                    deadlineDate = dateFormat.parse( dateString );
                    clockWidget.deadlineDate = deadlineDate;
                    clockWidget.showDeadLineTime();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (NullPointerException ex) {
            showErrMessage( "Please,check the correctness of the data and try again " );
        }
    }

   public void findTime( Date toTime){
       Date fromTime = new Date();
       Calendar diff = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
       diff.setTimeInMillis(toTime.getTime() - fromTime.getTime());
       labelTimeToDeadline.setText(getCoolTime(diff));
    }

    public String getCoolTime(Calendar time) {
        return (String.format("%02d",time.get(Calendar.DAY_OF_YEAR) - 1)) + " days" + "  |  "
                + String.format("%02d", time.get(Calendar.HOUR_OF_DAY) )+ " : "
                + String.format("%02d",time.get(Calendar.MINUTE)) + " : " + String.format("%02d", time.get(Calendar.SECOND));
    }
    public void clickOnGeoButton() throws Exception {
        controlGeo++;
        if (controlGeo == 1) {
            timeZoneMapController = timeZoneMapController.startTheForm();
            timeZoneMapController.setMainController( this );
        } else {
            timeZoneMapController.againStart();

        }

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

    public void setTextlabel( ) {
        for (int i = 0; i < labelСlocks.size(); i++) {
            labelСlocks.get( i ).setText("");
        }
        for (int i = 0; i < labelsName.size(); i++) {
            labelСlocks.get(i).setText(labelsName.get( i ) );
            Tooltip.install( labelСlocks.get( i ), new Tooltip( labelsName.get( i ) ) );
        }
    }

    @FXML
    public void OpenClockWidjet() {
        control += 1;
        if (control == 1) {
            clockWidget.start();
        } else {
            clockWidget.againStart();
        }
    }

    @FXML
    public void deleteSelectedTimeZone() {
        if(comboboxDelete.getValue() == null) {
            showErrMessage( "First Select" );
        } else {
            deleteItems.remove( comboboxDelete.getValue() );
            labelsName.remove( comboboxDelete.getValue() );
            setTextlabel();
            clockWidget.deleteTimeZone( ((String) comboboxDelete.getValue()).substring( 0, 9 ) );
            loadDeleteItems();
            count -= 1;             //use in method 'addSelectedTimeZone' for active Button "Open..";

        }
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) { launch(); }

    public void createArrayOfLabels() {
        labelСlocks.add( clock1 );
        labelСlocks.add( clock2 );
        labelСlocks.add( clock3 );
        labelСlocks.add( clock4 );
        labelСlocks.add( clock5 );
        labelСlocks.add( clock6 );
        labelСlocks.add( clock7 );
    }

    public void createClockWidget() {
        clockWidget.pane = new Pane();
        clockWidget.stage = new Stage();
        clockWidget.labelX = 80;
        // time in your location
        //clock.addTimeZone( TimeZone.getDefault().getID(), TimeZone.getDefault().getDisplayName());
    }

    private void launch() {
        createArrayOfLabels();
        loadItemsForSelectComboBox();
        loadItemsForHourComboBox();
        loadItemsForMinuteComboBox();
        threadForUpdate = new UpdatingThread();
        threadForUpdate.start();
        createClockWidget();
        setAnimation();
        layoutClock.getChildren().add( new AnalogueClock("", 100));

    }

    public void loadItemsForSelectComboBox() {
        SelectComboBox.getItems().addAll(timeZones);
    }
    public void loadItemsForHourComboBox(){
        hourComboBox.getItems().addAll(Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"));
    }
    public void loadItemsForMinuteComboBox(){
        minuteComboBox.getItems().addAll(Arrays.asList( "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"));
    }

    public void loadDeleteItems(){
        comboboxDelete.getItems().clear();
        comboboxDelete.getItems().addAll( deleteItems );
    }

    class UpdatingThread extends Thread {
        @Override
        public void run() {
            //  System.out.println( "Привет из первого побочного потока!" );
            while (true) {
                Platform.runLater( () -> {
                    if(deadlineDate!= null){
                        findTime( deadlineDate );
                    }
                    clockWidget.refreshTime( TimeZone.getDefault(), labelTime );
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
