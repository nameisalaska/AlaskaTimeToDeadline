package sample;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.imageio.ImageIO;
import java.awt.*;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;


/**
 * Created by Alaska on 28.01.2017.
 */

public class MainController implements Initializable {

    private static final double SCALE = 1.3; // size for increase;
    private static final double DURATION = 300; // time of animation;
    private static final double QUANTITYOFCLOCK = 7;
    private final float LEADING = 14.5f; // for Report in PDF

    static private UpdatingThread threadForUpdate;
    static private ClockWidget clockWidget = new ClockWidget();

    /*  Var 'control' and 'count' and ' controlGeo' use for cheking in the next methods
      */

    private int control = 0;
    private int controlGeo = 0;
    private int count = 0; // count of Time Zones
    private int lineY = 700;
    public boolean isDeadline;  // if deadlineDate is set
    public Date deadlineDate;

    @FXML
    private Label labelTime;        // clock of Default Time Zone;
    @FXML
    private ComboBox SelectComboBox;      // with value of Time Zones for select ;
    @FXML
    private ComboBox comboboxDelete; // with value of Time Zones for delete;
    @FXML
    private ComboBox hourComboBox;
    @FXML
    private ComboBox minuteComboBox;
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

    private ArrayList<Label> labelСlocks = new ArrayList<>(); // for digital clock;
    private ArrayList<String> deleteItems = new ArrayList<>(); // items for combobox for deleting;
    private ArrayList<String> labelsName = new ArrayList<>(); // for selected Time Zone;
    private List<String> timeZones = Arrays.asList( "(GMT-9:00) America/Anchorage", "(GMT-9:00) America/Nome", "(GMT-9:00) America/Sitka", "(GMT-9:00) America/Yakutat", "(GMT-9:00) Pacific/Gambier", "(GMT-9:00) US/Alaska", "(GMT-8:00) America/Dawson", "(GMT-8:00) America/Ensenada", "(GMT-8:00) America/Metlakatla", "(GMT-8:00) Canada/Yukon", "(GMT-8:00) Mexico/BajaNorte", "(GMT-8:00) Pacific/Pitcairn", "(GMT-7:00) America/Boise", "(GMT-7:00) America/Cambridge_Bay", "(GMT-7:00) America/Phoenix", "(GMT-7:00) America/Shiprock", "(GMT-7:00) America/Yellowknife", "(GMT-7:00) US/Mountain", "(GMT-6:00) America/Bahia_Banderas", "(GMT-6:00) America/Belize", "(GMT-6:00) America/Guatemala", "(GMT-6:00) America/Monterrey", "(GMT-6:00) America/Tegucigalpa", "(GMT-6:00) Chile/EasterIsland", "(GMT-6:00) Mexico/General", "(GMT-6:00) US/Indiana-Starke", "(GMT-5:00) America/Atikokan", "(GMT-5:00) America/Bogota", "(GMT-5:00) America/Cayman", "(GMT-5:00) America/Coral_Harbour", "(GMT-5:00) Brazil/Acre", "(GMT-5:00) Canada/Eastern", "(GMT-5:00) Cuba", "(GMT-5:00) IET", "(GMT-5:00) Jamaica", "(GMT-5:00) US/East-Indiana", "(GMT-5:00) US/Eastern", "(GMT-5:00) US/Michigan", "(GMT-4:30) America/Caracas", "(GMT-4:00) America/Anguilla", "(GMT-4:00) America/Antigua", "(GMT-4:00) America/Aruba", "(GMT-4:00) America/Curacao", "(GMT-4:00) America/Montserrat", "(GMT-4:00) Antarctica/Palmer", "(GMT-4:00) Atlantic/Bermuda", "(GMT-4:00) Brazil/West", "(GMT-4:00) Canada/Atlantic", "(GMT-4:00) Chile/Continental", "(GMT-3:00) America/Araguaina", "(GMT-3:00) America/Argentina/Buenos_Aires", "(GMT-3:00) America/Miquelon", "(GMT-3:00) America/Montevideo", "(GMT-3:00) America/Rosario", "(GMT-3:00) Antarctica/Rothera", "(GMT-3:00) Brazil/East", "(GMT-2:00) America/Noronha", "(GMT-2:00) Atlantic/South_Georgia", "(GMT-2:00) Brazil/DeNoronha", "(GMT-1:00) America/Scoresbysund", "(GMT-1:00) Atlantic/Azores", "(GMT-1:00) Atlantic/Cape_Verde", "(GMT 0:00) Africa/Bamako", "(GMT 0:00) Africa/Banjul", "(GMT 0:00) Africa/Bissau", "(GMT 0:00) Europe/Lisbon", "(GMT 0:00) Europe/London", "(GMT 0:00) Portugal", "(GMT+1:00) Africa/Algiers", "(GMT+1:00) Africa/Windhoek", "(GMT+1:00) Atlantic/Jan_Mayen", "(GMT+1:00) Europe/Amsterdam", "(GMT+1:00) Europe/Berlin", "(GMT+1:00) Europe/Vienna", "(GMT+1:00) Poland", "(GMT+2:00) Europe/Athens", "(GMT+2:00) Europe/Kiev", "(GMT+2:00) Europe/Mariehamn", "(GMT+2:00) Europe/Nicosia", "(GMT+2:00) Israel", "(GMT+2:00) Libya", "(GMT+2:00) Turkey", "(GMT+3:00) Africa/Addis_Ababa", "(GMT+3:00) Africa/Asmara", "(GMT+3:00) Africa/Nairobi", "(GMT+3:00) Antarctica/Syowa", "(GMT+3:00) Asia/Aden", "(GMT+3:00) Asia/Qatar", "(GMT+3:00) Asia/Riyadh", "(GMT+3:00) Europe/Kaliningrad", "(GMT+3:00) Europe/Minsk", "(GMT+3:00) Indian/Antananarivo", "(GMT+3:00) Indian/Comoro", "(GMT+3:00) Indian/Mayotte", "(GMT+4:00) Asia/Baku", "(GMT+4:00) Asia/Dubai", "(GMT+4:00) Europe/Moscow", "(GMT+4:00) Europe/Samara", "(GMT+4:00) Indian/Mahe", "(GMT+5:00) Antarctica/Mawson", "(GMT+5:00) Asia/Aqtau", "(GMT+5:00) Asia/Aqtobe", "(GMT+5:00) Asia/Ashgabat", "(GMT+5:00) Asia/Ashkhabad", "(GMT+5:00) Asia/Dushanbe", "(GMT+5:00) Asia/Karachi", "(GMT+5:00) Asia/Oral", "(GMT+5:00) Asia/Samarkand", "(GMT+5:00) Asia/Tashkent", "(GMT+5:00) Indian/Kerguelen", "(GMT+6:00) Antarctica/Vostok", "(GMT+6:00) Asia/Almaty", "(GMT+6:00) Asia/Qyzylorda", "(GMT+6:00) Asia/Yekaterinburg", "(GMT+6:00) Indian/Chagos", "(GMT+7:00) Antarctica/Davis", "(GMT+7:00) Asia/Bangkok", "(GMT+7:00) Asia/Ho_Chi_Minh", "(GMT+7:00) Indian/Christmas", "(GMT+8:00) Antarctica/Casey", "(GMT+8:00) Asia/Brunei", "(GMT+8:00) Singapore", "(GMT+9:00) Asia/Dili", "(GMT+9:00) Asia/Pyongyang", "(GMT+9:00) Asia/Seoul", "(GMT+9:00) Asia/Tokyo", "(GMT+9:00) Japan", "(GMT+9:00) Pacific/Palau", "(GMT+10:00) Antarctica/DumontDUrville", "(GMT+10:00) Asia/Khandyga", "(GMT+10:00) Asia/Yakutsk", "(GMT+11:00) Antarctica/Macquarie", "(GMT+11:00) Asia/Sakhalin", "(GMT+11:00) Asia/Ust-Nera", "(GMT+11:00) Asia/Vladivostok", "(GMT+12:00) Antarctica/McMurdo", "(GMT+12:00) Antarctica/South_Pole", "(GMT+12:00) Kwajalein", "(GMT+12:00) Pacific/Auckland", "(GMT+12:00) Pacific/Wallis" );
    private TimeZoneMapController timeZoneMapController = new TimeZoneMapController();

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
    private void addSelectedTimeZone() {
        try {
            boolean var = false;
            if (count == QUANTITYOFCLOCK) {
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
                    deleteItems.add( (String) SelectComboBox.getValue() );
                    setTextlabel();
                    loadDeleteItems();
                    clockWidget.addTimeZone( ((String) SelectComboBox.getValue()).substring( 11 ), (String) SelectComboBox.getValue() );
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
        if (count == QUANTITYOFCLOCK) {
            showErrMessage( "Sorry. You can select only 7 Time Zones" );
        } else {
            for (int j = 0; j < count; j++) {
                if (labelСlocks.get( j ).getText().equals( value )) {
                    showInfMessage( "You have already added this Time Zone" );
                    var = true;
                }
            }
            if (!var) {
                labelsName.add( value );
                deleteItems.add( value );
                System.out.println( labelsName.size() );
                setTextlabel();
                loadDeleteItems();
                clockWidget.addTimeZone( value.substring( 11 ), value );
                buttonOpenClock.setDisable( false );
                count++;
            }
        }
    }
    @FXML
    private void getDeadlineInfo() {
        try {
            LocalDate localDate = deadlineDatePicker.getValue();
            try {
                Instant instant = Instant.from( localDate.atStartOfDay( ZoneId.systemDefault() ) );
                Date date = Date.from( instant );
                SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
                String dateString = dateFormat.format( date );
                dateString = dateString.substring( 0, 10 ) + " " + hourComboBox.getValue() + ":" + minuteComboBox.getValue() + ":" + "00";
                deadlineDate = dateFormat.parse( dateString ) ;
                if (deadlineDate.before(new Date(  )) ) {
                    isDeadline = false;
                    deadlineDate = null;
                }
                else {
                    saveToXML();
                    isDeadline = true;
                    clockWidget.deadlineDate = deadlineDate;
                    clockWidget.showDeadLineTime();
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } catch (NullPointerException ex) {
            showErrMessage( "Please,check the correctness of the data and try again " );
        }
    }

    public void getDeadlineInfo(Date date) {
        try {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.isBefore( LocalDate.now() )) {
                labelTimeToDeadline.setText(" 0 days  |   00 : 00: 00 ");
            } else {
                    deadlineDate =  date ;
                    saveToXML();
                    isDeadline = true;
                    clockWidget.deadlineDate = deadlineDate;
                    clockWidget.showDeadLineTime();
            }
        } catch (NullPointerException ex) {
            showErrMessage( "Please,check the correctness of the data and try again " );
        }
    }

    private void findTime(Date toTime) {
        Date fromTime = new Date();
        Calendar diff = Calendar.getInstance( TimeZone.getTimeZone( "GMT" ) );
        diff.setTimeInMillis( toTime.getTime() - fromTime.getTime() );
        labelTimeToDeadline.setText( getCoolTime( diff ) );
    }

    private String getCoolTime(Calendar time) {
        return ( time.get( Calendar.DAY_OF_YEAR ) - 1  + " days" + "  |  "
                + String.format( "%02d", time.get( Calendar.HOUR_OF_DAY ) ) + " : "
                + String.format( "%02d", time.get( Calendar.MINUTE ) ) + " : " + String.format( "%02d", time.get( Calendar.SECOND ) ));
    }
    @FXML
    private void clickOnGeoButton() throws Exception {
        controlGeo++;
        if (controlGeo == 1) {
            timeZoneMapController = timeZoneMapController.startTheForm();
            timeZoneMapController.setMainController( this );
        } else {
            timeZoneMapController.againStart();

        }

    }

    private void showErrMessage(String message) {
        Alert alert = new Alert( Alert.AlertType.ERROR );
        alert.setTitle( "Error" );
        alert.setHeaderText( "Oooops" );
        alert.setContentText( message );
        alert.showAndWait();
    }

    private void showInfMessage(String message) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle( "Information Window" );
        alert.setHeaderText( "Oooops" );
        alert.setContentText( message );
        alert.showAndWait();
    }

    private void setTextlabel() {
        for (int i = 0; i < labelСlocks.size(); i++) {
            labelСlocks.get( i ).setText( "" );
        }
        for (int i = 0; i < labelsName.size(); i++) {
            labelСlocks.get( i ).setText( labelsName.get( i ) );
            Tooltip.install( labelСlocks.get( i ), new Tooltip( labelsName.get( i ) ) );
        }
    }

    @FXML
    private void OpenClockWidjet() {
        control += 1;
        if (control == 1) {
            clockWidget.start();
        } else {
            clockWidget.againStart();
        }
    }

    @FXML
    private void deleteSelectedTimeZone() {
        if (comboboxDelete.getValue() == null) {
            showErrMessage( "First Select" );
        } else {
            deleteItems.remove( comboboxDelete.getValue() );
            labelsName.remove( comboboxDelete.getValue() );
            setTextlabel();
            clockWidget.deleteTimeZone( ((String) comboboxDelete.getValue()).substring( 11 ) );
            loadDeleteItems();
            count -= 1;             //use in method 'addSelectedTimeZone' for active Button "Open..";

        }
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        launch();
    }

    private void createArrayOfLabels() {
        labelСlocks.add( clock1 );
        labelСlocks.add( clock2 );
        labelСlocks.add( clock3 );
        labelСlocks.add( clock4 );
        labelСlocks.add( clock5 );
        labelСlocks.add( clock6 );
        labelСlocks.add( clock7 );
    }

    private void createClockWidget() {
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
        layoutClock.getChildren().add( new AnalogueClock( "", 100 ) );
        setEffectForClock();

    }

    private void setEffectForClock() {
        // add a glow effect whenever the mouse is positioned over the clock.
        final Glow glow = new Glow();
        layoutClock.setOnMouseEntered( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                layoutClock.setEffect( glow );
            }
        } );
        layoutClock.setOnMouseExited( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                layoutClock.setEffect( null );
            }
        } );
    }

    private void loadItemsForSelectComboBox() {
        SelectComboBox.getItems().addAll( timeZones );
    }

    private void loadItemsForHourComboBox() {
        hourComboBox.getItems().addAll( Arrays.asList( "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" ) );
    }

    private void loadItemsForMinuteComboBox() {
        minuteComboBox.getItems().addAll( Arrays.asList( "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" ) );
    }

    private void loadDeleteItems() {
        comboboxDelete.getItems().clear();
        comboboxDelete.getItems().addAll( deleteItems );
    }

    private void saveToXML() {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder( new FileOutputStream( "Main.xml" ) );
            xmlEncoder.writeObject( deadlineDate );
            xmlEncoder.flush();
            xmlEncoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void createReport() throws Exception {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor( "Kate Varyeva" );
        pdd.setTitle( "Report" );
        pdd.setCreator( "PDF" );
        pdd.setSubject( "Report " );
        PDPageContentStream contentStream = new PDPageContentStream( document, page );
        contentStream.beginText();
        contentStream.newLineAtOffset( 10, lineY );
        contentStream.setFont( PDType1Font.HELVETICA_BOLD, 12 );
        contentStream.showText( "Report of  " );
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        String dateString = dateFormat.format( date );
        contentStream.showText( dateString );
        contentStream.setLeading( LEADING);
        contentStream.newLine();
        lineY -= LEADING;
        contentStream.showText( "Selected Time Zone : " );
        for (int i = 0; i < labelsName.size(); i++) {
            contentStream.newLine();
            lineY -= LEADING;
            contentStream.showText( labelsName.get( i ) );
        }
        contentStream.newLine();
        lineY -= LEADING;
        contentStream.showText( "Time to Deadline : " + deadlineDate );
        contentStream.newLine();
        lineY -= LEADING;
        contentStream.showText( "Main window's snapshot : " );
        lineY -= LEADING;
        contentStream.endText();
        Stage stage = new Main().stage;
        WritableImage snapshot = stage.getScene().snapshot( null );
        File IoFile = new File( "img.png" );
        ImageIO.write(
                SwingFXUtils.fromFXImage( snapshot, null ),
                "png",
                IoFile );
        PDImageXObject pdImage = PDImageXObject.createFromFile( "img.png", document );
        contentStream.drawImage( pdImage, 0, lineY -= LEADING + pdImage.getHeight() );
        contentStream.close();
        document.save( new File( "Report.pdf" ) );
        document.close();
    }
    @FXML
    public void clickOnReset() {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("RESET DEADLINE TIME");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            isDeadline = false;
            labelTimeToDeadline.setText(" 0 days  |   00 : 00: 00 ");
            clockWidget.deadlineDate = null;
            clockWidget.cleanTimeToDeadline();
            deadlineDate = null;
            saveToXML();
        } else {
            alert.close();
        }
    }
    @FXML
    public void showReport()  throws IOException{
        File file = new File( "Report.pdf" );
        Desktop desktop = Desktop.getDesktop();
        desktop.open( file );
    }
    @FXML
    public void clickOnAbout() throws Exception{
        Stage stage = new Stage( );
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("about.fxml"));
        Parent root =  loader.load();
        stage.setScene( new Scene( root, 488, 256 ) );
        stage.setTitle( "Time to deadline" );
        stage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        stage.setResizable( false );
        stage.show();
    }
    @FXML
    public void clickOnHelp(){
        try {
            new ProcessBuilder("cmd", "/c", "start", "instruction\\index.htm").start();
        } catch (IOException ex) {
            showErrMessage("Sorry.It will be fixed soon" );
        }
    }

    private URL getResource(String name) {
        return getClass().getResource( name );
    }

    class UpdatingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Platform.runLater( () -> {
                    if (isDeadline) {
                        if (deadlineDate.before(new Date(  )) ) {
                            isDeadline = false;
                            labelTimeToDeadline.setText(" 0 days  |   00 : 00: 00 ");
                            clockWidget.deadlineDate = null;
                            clockWidget.cleanTimeToDeadline();
                            deadlineDate = null;
                            saveToXML();
                            showInfMessage( "Deadline!" );
                        }else {
                            findTime( deadlineDate );
                        }
                    }
                    clockWidget.refreshTime( TimeZone.getDefault(), labelTime );
                } );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
