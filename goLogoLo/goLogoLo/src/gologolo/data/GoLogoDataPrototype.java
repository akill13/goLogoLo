/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


/**
 *
 * @author akillhalimi
 */
public class GoLogoDataPrototype implements Cloneable {
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final int DEFAULT_THICKNESS = 10;
    public static final int DEFAULT_BORDER_RADIUS = 10;
    public static final int DEFAULT_FOCUS_ANGLE = 0;
    public static final int DEFAULT_FOCUS_DISTANCE = 0;
    public static final int DEFAULT_CENTER_X = 0;
    public static final int DEFAULT_CENTER_Y = 0;
    public static final int DEFAULT_GRADIENT_RADIUS = 0;
    public static final String DEFAULT_CYCLE_METHOD = "NO_CYCLE";
    public static final String DEFAULT_TEXT = "";
    
    final IntegerProperty width;
    final IntegerProperty height;
    final StringProperty color;
    final IntegerProperty thickness;
    final IntegerProperty borderRadius;
    final IntegerProperty angle;
    final IntegerProperty distance;
    final IntegerProperty centerX;
    final IntegerProperty centerY;
    final IntegerProperty gradientRadius;
    final StringProperty cycle;
    public Node node;
    public GoLogoShape shapeBuilder = new GoLogoShape();
    GoLogoDataText text;
    
    
    public GoLogoDataPrototype() {
        width = new SimpleIntegerProperty(DEFAULT_WIDTH);
        height = new SimpleIntegerProperty(DEFAULT_HEIGHT);
        color  = new SimpleStringProperty(DEFAULT_COLOR.toString());
        thickness = new SimpleIntegerProperty(DEFAULT_THICKNESS);
        borderRadius = new SimpleIntegerProperty(DEFAULT_THICKNESS);
        angle = new SimpleIntegerProperty(DEFAULT_FOCUS_ANGLE);
        distance = new SimpleIntegerProperty(DEFAULT_FOCUS_DISTANCE);
        centerX = new SimpleIntegerProperty(DEFAULT_CENTER_Y);
        centerY = new SimpleIntegerProperty(DEFAULT_CENTER_Y);
        gradientRadius = new SimpleIntegerProperty(DEFAULT_GRADIENT_RADIUS);
        cycle = new SimpleStringProperty(DEFAULT_CYCLE_METHOD);
        node = null;
        text = new GoLogoDataText(DEFAULT_TEXT);
    }
    public GoLogoDataPrototype(int width, int height, String color, int thickness, int borderRadius, int angle, int distance, int centerX, int centerY, int gradientRadius, String cycle) {
        this();
        this.width.set(width);
        this.height.set(height);
        this.color.set(color);
        this.thickness.set(thickness);
        this.borderRadius.set(borderRadius);
        this.angle.set(angle);
        this.distance.set(distance);
        this.centerX.set(centerX);
        this.centerY.set(centerY);
        this.gradientRadius.set(gradientRadius);
        this.cycle.set(cycle);
    }
    
    public int getWidth() {
        return width.get();
    }
    
    public int getHeight() {
        return height.get();
    }
    
    public String getColor() {
        return color.get();
    }
    
    public int getThickness() {
        return thickness.get();
    }
    
    public int getBorderRadius() {
        return borderRadius.get();
    }
    
    public int getAngle() {
        return angle.get();
    }
    
    public int getDistance() {
        return distance.get();
    }
    
    public int getCenterX() {
        return centerX.get();
    }
    
    public int getCenterY() {
        return centerY.get();
    }
    
    public int getGradientRadius() {
        return gradientRadius.get();
    }
    
    public String getCycle() {
        return cycle.get();
    }
    
    public GoLogoDataPrototype buildDefaultRectangle() {    
        this.node = shapeBuilder.buildRectangle();
        return this;
    }
    
    public GoLogoDataPrototype buildDefaultTriangle() {
        this.node = shapeBuilder.buildTriangle();
        return this;
    }
    public Node getNode() {
        return node;
    }
    public void setText(GoLogoDataText text){
        this.text = text;
    }
    public Label getText() {
        return text;
    }
    public void setText(String text) {
        this.text.setText(text);
    }
    
}
