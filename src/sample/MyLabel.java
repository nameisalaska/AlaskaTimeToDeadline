package sample;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by  Alaska on 26.01.2017.
 */

public class MyLabel {

    private double width;
    private double height;
    private double x;
    private double y;
    private int value;

    public MyLabel(double width, double height, double x, double y, int value) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Label createLabel(Label label, String s) {
        label.setText(s);
        label.setLayoutX( x );
        label.setLayoutY( y );
        label.setPrefHeight( height );
        label.setPrefWidth( width );
        label.setFont( new Font( value ) );
        return label;
    }
}