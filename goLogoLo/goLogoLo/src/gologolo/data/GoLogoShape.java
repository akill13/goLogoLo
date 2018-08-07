/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import static gologolo.data.GoLogoDataPrototype.DEFAULT_COLOR;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_HEIGHT;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_WIDTH;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author akillhalimi
 */
public class GoLogoShape {
MouseLocation location = new MouseLocation();   
Shape shape;
public Rectangle buildRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(DEFAULT_HEIGHT);
        rectangle.setWidth(DEFAULT_WIDTH);
        rectangle.setX(90);
        rectangle.setY(200);
        rectangle.setFill(DEFAULT_COLOR);
        rectangle.setStroke(Color.BLACK);
    shape = rectangle;
    rectangle.setOnMousePressed(e->{
            MouseLocation.x=e.getX();
            MouseLocation.y=e.getY();
        });
    rectangle.setOnMouseDragged(e->{
        rectangle.setX(rectangle.getX() + e.getX() - MouseLocation.x);
        rectangle.setY(rectangle.getY() + e.getY() - MouseLocation.y);
        MouseLocation.x = e.getX();
        MouseLocation.y = e.getY();
    });
    return (Rectangle) shape;
}
public Polygon buildTriangle() {
    Polygon triangle = new Polygon();
    triangle.getPoints().setAll(new Double[] {
        285.0, 150.0,
        390.0, 300.0,
        180.0, 300.0
    });
    triangle.setFill(DEFAULT_COLOR);
    triangle.setStroke(Color.BLACK);
    shape = triangle;
    triangle.setOnMouseDragged(e->{
       triangle.setLayoutX(e.getX());
       triangle.setLayoutY(e.getY());
    });
    return (Polygon) shape;
}

public Shape getShape(){
    return shape;
}

}
