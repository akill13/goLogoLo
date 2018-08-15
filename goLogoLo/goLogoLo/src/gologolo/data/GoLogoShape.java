/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_COLOR;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_HEIGHT;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_WIDTH;
import gologolo.transactions.DragItem_Transaction;
import gologolo.workspace.controllers.LogoController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.*;

/**
 *
 * @author akillhalimi
 */
public class GoLogoShape {
Shape shape;
LogoController controls = LogoController.getController();
GoLogoLoApp app;

public Rectangle buildRectangle(GoLogoLoApp app) {
    this.app = app;
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(DEFAULT_HEIGHT);
        rectangle.setWidth(DEFAULT_WIDTH);
        rectangle.setX(90);
        rectangle.setY(200);
        rectangle.setFill(DEFAULT_COLOR);
        rectangle.setStroke(Color.BLACK);
    shape = rectangle;
    double resizeZone = rectangle.getTranslateX();
    rectangle.setOnMousePressed(e->{
//            this.clearSelected();
//            rec.getStyleClass().add(CLASS_LOGO_RECTANGLES);
//            this.selectItem(items.get(nodes.indexOf(rec)));
            
            MouseLocation.x = e.getSceneX();
            MouseLocation.y = e.getSceneY();
            MouseLocation.origianlx = rectangle.getTranslateX();
            MouseLocation.originaly = rectangle.getTranslateY();
        });
//        rectangle.setOnMouseClicked(e->{
//            this.clearSelected();
//        });
        
        rectangle.setOnMouseDragged(e->{
            
            if(MouseLocation.x==resizeZone){
                System.out.print("corner area");
            }
            double offsetX = e.getSceneX() - MouseLocation.x;
            double offsetY = e.getSceneY() - MouseLocation.y;
            double newTranslateX = MouseLocation.origianlx + offsetX;
            double newTranslateY = MouseLocation.originaly + offsetY;

            rectangle.setTranslateX(newTranslateX);
            rectangle.setTranslateY(newTranslateY);
            rectangle.setOnMouseReleased(x->{
                
                DragItem_Transaction trans = new DragItem_Transaction(newTranslateX, newTranslateY, MouseLocation.origianlx, MouseLocation.originaly, app, rectangle);
                app.processTransaction(trans);
            });
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
