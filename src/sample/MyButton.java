package sample;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by Alaska on 27.01.2017.
 */

public class MyButton {
    public double  width;
    public double  height;
    public double  x;
    public double  y;

    public MyButton(double  width, double height, double  x, double  y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    public Button createButton(Button button){
        button.setOpacity(0.40F);
        button.setLayoutX( x );
        button.setLayoutY( y );
        button.setPrefHeight( height );
        button.setPrefWidth( width );
        button.setFont(new Font(13));
        button.toFront();
        button.setText( "Delete" );
        return button;
    }
}
