package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import static javax.swing.SwingConstants.CENTER;

/**
 * Created by Alaska on 26.01.2017.
 */

public class MyLabel {

    public double width;
    public double height;
    public double x;
    public double y;
    public int value;

    public MyLabel(double width, double height, double x, double y, int value) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Label createLable(Label label, String s) {
        label.setText(s);
        label.setLayoutX( x );
        label.setLayoutY( y );
        label.setPrefHeight( height );
        label.setPrefWidth( width );
        label.setFont( new Font( value ) );
        return label;
    }

}