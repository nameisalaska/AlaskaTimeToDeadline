package sample;/**
 * Created by Alaska on 24.01.2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ClockWidget   {

    public ArrayList<TimeZone> zones = new ArrayList<TimeZone>();      // Array of selected Time Zones;
    public ArrayList<Label> clocks = new ArrayList<Label>();       // Array of clocks;
    private ArrayList<Label> labels = new ArrayList<Label>();      // Array of names of clocks;
    private ArrayList<String> labelsName = new ArrayList<String>(); // Array of names for labels;

    @FXML
    public Pane pane;
    public Stage stage;
    /*
    Coordinates for clocks and labels for names of the clocks;
     */
    public int clockX ;
    public int labelX ;

    static AffableThread threadforUpdate;

    public void start() {
        stage.setScene(new Scene(pane,clocks.size() * 200,200));
        stage.show();
        updateTheClock();
    }

    public void AgainStart(){
        stage.show();
    }

    public void Exit(){
        stage.setWidth( clocks.size() * 200 );
        stage.close();
    }

    public void updateTheClock() {
        threadforUpdate = new AffableThread();
        threadforUpdate.start();
    }

    public void addTimeZone(String value) {
        zones.add( TimeZone.getTimeZone(value) );
        Label clocklabel = new Label(); Label label = new Label();
        clocks.add( new MyLabel( 284, 112, clockX, 15, 42 ).createLable(clocklabel, value));
        labels.add(new MyLabel( 80, 25, labelX, 14 , 13).createLable(label,value));
        labelsName.add(value);
        pane.getChildren().addAll( new MyLabel( 284, 112, clockX, 15, 42 ).createLable(clocklabel,value) , new MyLabel( 80, 25, labelX , 14 , 13).createLable(label,value));
        clockX += 200; labelX += 200;
        stage.setWidth( clocks.size() * 200 );
    }

    public void deleteTimeZone(String value){
        pane.getChildren().removeAll(clocks.get(clocks.size()-1), labels.get(labels.size()-1));
        zones.remove( TimeZone.getTimeZone(value));
        clocks.remove(clocks.size() - 1);
        labels.remove(labels.size()-1);
        labelsName.remove(value);
        renameLabels();
        clockX -=200; labelX -=200;                      // use in method 'addTimeZone'
        stage.setWidth( clocks.size() * 200 );
    }
    public void renameLabels(){
        for(int i = 1; i < labels.size(); i++){
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

    class AffableThread extends Thread {
        @Override
        public void run() {
            System.out.println("Привет из побочного потока!");
            while (true) {
                Platform.runLater( new Runnable() {
                    @Override
                    public void run() {
                        for ( int i = 0; i < zones.size(); i++) {
                            refreshTime( zones.get( i ), clocks.get( i ) );
                        }
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
