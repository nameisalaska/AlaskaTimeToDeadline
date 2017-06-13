package sample;
/**
 * Created by Alaska on 24.01.2017.
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ClockWidget   {

    static UpdatingThread threadforUpdate;
    public ArrayList<TimeZone> zones = new ArrayList<>();      // Array of selected Time Zones;
    public ArrayList<Label> clocks = new ArrayList<>();       // Array of clocks;
    @FXML
    public Pane pane;
    public Stage stage;
    public int clockX = 7 ;
    public Date deadlineDate;
    private final int NUMBEROFLABEL = 7; // number of deadlineLabel;
    private final int INDENTATION = 200; // Indentation between labels;

    //Coordinates for clocks and labels for names of the clocks;
    public int labelX;

    private ArrayList<Label> labels = new ArrayList<>();      // Array of names of clocks;
    private ArrayList<String> labelsName = new ArrayList<>(); // Array of names for labels;
    private ArrayList<Label> deadlineLabels = new ArrayList<>();
    public void start() {
        stage.setScene(new Scene(pane,clocks.size() * 200,160));
        stage.getIcons().add( new Image( getResource( "mainIcon.png" ).toExternalForm() ) );
        stage.setResizable(false);
        stage.setTitle( "Deadline Time" );
        stage.show();
        updateTheClock();
    }
    private URL getResource(String name) {
        return getClass().getResource( name );
    }

    public void againStart() {
        stage.setWidth( clocks.size() * 200 );
        stage.show();
        stage.toFront();
    }

    public void updateTheClock() {
        threadforUpdate = new UpdatingThread();
        threadforUpdate.start();
    }

    public void addTimeZone(String value, String nameOfZone) {
        zones.add( TimeZone.getTimeZone(value) );
        Label clocklabel = new Label(); Label label = new Label();
        Tooltip.install(label, new Tooltip(nameOfZone));
        clocks.add( new MyLabel( 284, 112, clockX, 15, 42 ).createLabel( clocklabel, value ) );
        labels.add( new MyLabel( 284, 25, clockX + 2, 14, 13 ).createLabel( label, value ) );
        if(deadlineLabels.size() <= NUMBEROFLABEL){
            Label deadlineLabel = new Label();
            deadlineLabels.add( new MyLabel( 160, 25, labelX - 75, 92, 13 ).createLabel(deadlineLabel, "" ));
            pane.getChildren().addAll( deadlineLabel );
        }
        labelsName.add(value);
        pane.getChildren().addAll(  clocklabel, label);
        showDeadLineTime();
        clockX += INDENTATION; labelX += INDENTATION;
        stage.setWidth( clocks.size() * INDENTATION);
    }
    public void cleanTimeToDeadline() {
       for (int i = 0; i <  deadlineLabels.size(); i++) {
            deadlineLabels.get( i ).setText("");
        }
    }

    public void showDeadLineTime(){
        if(deadlineDate != null){
            for(int i = 0; i <  deadlineLabels.size(); i++) {
                DateFormat dfm = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
                dfm.setTimeZone( TimeZone.getTimeZone( labelsName.get( i ) ) );
                deadlineLabels.get( i ).setText( dfm.format( deadlineDate ) );
            }
        }
    }

    public void deleteTimeZone(String value){
        pane.getChildren().removeAll(clocks.get(clocks.size()-1), labels.get(labels.size()-1));
        zones.remove( TimeZone.getTimeZone(value));
        clocks.remove(clocks.size() - 1);
        labels.remove(labels.size() - 1);
        deadlineLabels.remove(deadlineLabels.size() - 1).setText( "" );
        labelsName.remove(value);
        renameLabels();
        showDeadLineTime();
        clockX -= INDENTATION; labelX -= INDENTATION; //use in method 'addTimeZone'
        stage.setWidth( clocks.size() * INDENTATION );
    }
    public void renameLabels(){
        for(int i = 0; i < labels.size(); i++){
            labels.get(i).setText( labelsName.get(i));
        }
    }
    public void refreshTime(TimeZone value, Label label) {
        Calendar calendar  = Calendar.getInstance(value);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        label.setText(String.format("%02d", hour) + ":" +String.format("%02d", min) + ":" + String.format("%02d", sec));
    }

    // Thread for update the clocks;
    class UpdatingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Platform.runLater( () -> {
                    for (int i = 0; i < zones.size(); i++) {
                        refreshTime( zones.get( i ), clocks.get( i ) );
                    }
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
