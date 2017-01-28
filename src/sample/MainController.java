package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by Alaska on 28.01.2017.
 */
public class MainController implements Initializable {
    @FXML
    private Label labelTime;        // clock of Default Time Zone;
    @FXML
    private ComboBox combobox;      // with value of Time Zones for select ;
    @FXML
    private ComboBox comboboxdelete; // with value of Time Zones for delete;
    @FXML
    private Button buttonOpenClock;
    @FXML
    private Pane pane;
    /*
    labels clock1 - clock2 for writing selected Time Zones;
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
    /*  Var 'control' and 'g' use for cheking in the next methods
      */
    int control = 0;        
    private int count = 0; // count of Time Zones

    public ArrayList<Label> labelclocks = new ArrayList<Label>();
    private ArrayList<String> deleteItems = new ArrayList<String>();
    private ArrayList<String> labelsName = new ArrayList<String>();

    static private ClockWidget clock = new ClockWidget();

    static UpdatingThread threadforUpdate;

    @FXML
    public void clickOk()  {
        try {
            boolean var = false;
            if (count > 4) {
                showErrMessage( "Sorry. You can select only 5 Time Zones" );
            } else {
                for (int j = 0; j < count; j++) {
                    if (labelclocks.get( j ).getText().equals( (String) combobox.getValue() )) {
                        showInfMessage( "You have already added this Time Zone" );
                        var = true;
                    }
                }
                if (!var) {
                    labelsName.add((String) combobox.getValue());
                    deleteItems.add((String) combobox.getValue());
                    setTextlabel();
                    loadDeleteItems();
                    clock.addTimeZone( (String) combobox.getValue() );
                    buttonOpenClock.setDisable( false );
                    count++;
                }
            }
        } catch (NullPointerException e) {
            showErrMessage( "First select" );
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
    @FXML
    public void setTextlabel( ) {
        for (int i = 0; i < labelclocks.size(); i++) {
            labelclocks.get( i ).setText("");
        }
        for (int i = 0; i < labelsName.size(); i++) {
            labelclocks.get( i ).setText(labelsName.get( i ) );
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
    public void deleteTimeZone() {
        if(comboboxdelete.getValue() == null) {
            showErrMessage( "First Select" );
        } else {
            deleteItems.remove( (String) comboboxdelete.getValue() );
            labelsName.remove( (String) comboboxdelete.getValue() );
            setTextlabel();
            clock.deleteTimeZone( (String) comboboxdelete.getValue() );
            loadDeleteItems();
            count -= 1;             //use in method 'clickOk';
        }
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) { launch(); }

    public void createArrayofLable() {
        labelclocks.add( clock1 );
        labelclocks.add( clock2 );
        labelclocks.add( clock3 );
        labelclocks.add( clock4 );
        labelclocks.add( clock5 );
    }

    public void createClockWidjet() {
        clock.pane = new Pane();
        clock.stage = new Stage();
        clock.labelX = 80;
        clock.addTimeZone( TimeZone.getDefault().getID() );
    }

    public void launch() {
        pane = new Pane();
        createArrayofLable();
        loadItems();
        threadforUpdate = new UpdatingThread();
        threadforUpdate.start();
        createClockWidjet();
    }

    public void loadItems() {
        combobox.getItems().addAll( "GMT-12:00", "GMT-11:00", "GMT-10:00", "GMT-09:30", "GMT-09:00", "GMT-08:00", "GMT-07:00", "GMT-06:00", "GMT-05:00", "GMT-04:00", "GMT-03:30", "GMT-03:00", "GMT-02:00", "GMT-01:00", "GMT+00:00", "GMT+01:00", "GMT+02:00", "GMT+03:00", "GMT+03:30", "GMT+04:00", "GMT+04:30", "GMT+05:00", "GMT+05:30", "GMT+05:45", "GMT+06:00", "GMT+06:30", "GMT+07:00", "GMT+08:00", "GMT+08:30", "GMT+08:45", "GMT+09:00", "GMT+09:30", "GMT+10:00", "GMT+10:30", "GMT+11:00", "GMT+12:00", "GMT+13:00", "GMT+14:00" );
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
                Platform.runLater( new Runnable() {
                    @Override
                    public void run() {
                        clock.refreshTime( TimeZone.getDefault(), labelTime );
                    }
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
