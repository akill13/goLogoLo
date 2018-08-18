/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;


import gologolo.GoLogoLoApp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.*;
import javafx.scene.text.Text;


/**
 *
 * @author akillhalimi
 */
public class GoLogoDataPrototype implements Cloneable {
    public static int i;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final int DEFAULT_THICKNESS = 10;
    public static final int DEFAULT_BORDER_RADIUS = 10;
    public static final double DEFAULT_FOCUS_ANGLE = 0;
    public static final double DEFAULT_FOCUS_DISTANCE = 0;
    public static final double DEFAULT_CENTER_X = 0;
    public static final double DEFAULT_CENTER_Y = 0;
    public static final double DEFAULT_GRADIENT_RADIUS = 0;
    public static final String DEFAULT_CYCLE_METHOD = "NO_CYCLE";
    public static final String DEFAULT_TEXT = "";
    public static final String DEFAULT_NAME = "LOGO_DATA";
    
    final IntegerProperty width;
    final IntegerProperty height;
    final StringProperty color;
    final DoubleProperty thickness;
    final DoubleProperty borderRadius;
    final DoubleProperty angle;
    final DoubleProperty distance;
    final DoubleProperty centerX;
    final DoubleProperty centerY;
    final DoubleProperty gradientRadius;
    final StringProperty cycle;
    final IntegerProperty order;
    final StringProperty name;
    final StringProperty type;
    public Node node;
    public GoLogoShape shapeBuilder = new GoLogoShape();
    GoLogoDataText text;
   
    public boolean shape;
    GoLogoLoApp app;
    
    public GoLogoDataPrototype() {
        width = new SimpleIntegerProperty(DEFAULT_WIDTH);
        height = new SimpleIntegerProperty(DEFAULT_HEIGHT);
        color  = new SimpleStringProperty(DEFAULT_COLOR.toString());
        thickness = new SimpleDoubleProperty(DEFAULT_THICKNESS);
        borderRadius = new SimpleDoubleProperty(DEFAULT_THICKNESS);
        angle = new SimpleDoubleProperty(DEFAULT_FOCUS_ANGLE);
        distance = new SimpleDoubleProperty(DEFAULT_FOCUS_DISTANCE);
        centerX = new SimpleDoubleProperty(DEFAULT_CENTER_Y);
        centerY = new SimpleDoubleProperty(DEFAULT_CENTER_Y);
        gradientRadius = new SimpleDoubleProperty(DEFAULT_GRADIENT_RADIUS);
        cycle = new SimpleStringProperty(DEFAULT_CYCLE_METHOD);
        node = null;
        text = null;
        name = new SimpleStringProperty(DEFAULT_NAME);
        order = new SimpleIntegerProperty();
        type = new SimpleStringProperty();
        shape = false;
    }
    public GoLogoDataPrototype(int width, int height, String color, int thickness, int borderRadius, int angle, int distance, int centerX, int centerY, int gradientRadius, String cycle, String name) {
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
        this.name.set(name);
    }

    public GoLogoDataPrototype(int order, String name, String type) {
        this();
        this.order.set(order);
        this.name.set(name);
        this.type.set(type);
    }
    public GoLogoDataPrototype(String name, String type) {
        this();
        this.name.set(name);
        this.type.set(type);
    }
    public GoLogoDataPrototype(int order, String name, String type, String text){
        this();
        this.name.set(name);
        this.type.set(type);
        this.text.setText(name);
    }
    public GoLogoDataPrototype(Node node){
        this();
        this.node=node;
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
    
    public double getThickness() {
        return thickness.get();
    }
    
    public double getBorderRadius() {
        return borderRadius.get();
    }
    
    public double getAngle() {
        return angle.get();
    }
    
    public double getDistance() {
        return distance.get();
    }
    
    public double getCenterX() {
        return centerX.get();
    }
    
    public double getCenterY() {
        return centerY.get();
    }
    
    public double getGradientRadius() {
        return gradientRadius.get();
    }
    
    public String getCycle() {
        return cycle.get();
    }
    
    public GoLogoDataPrototype buildDefaultRectangle(GoLogoLoApp app) {    
        this.node = new LogoRectangle(app);
        this.shape=true;
        return this;
    }
    
    public GoLogoDataPrototype buildDefaultTriangle() {
        this.node = shapeBuilder.buildTriangle();
        return this;
    }
    
    public GoLogoDataPrototype buildDefaultCircle(GoLogoLoApp app) {
        this.node = new LogoCircle(app);
        this.shape=true;
        return this;
    }
    public Node getNode() {
        return node;
    }
    public void setText(GoLogoDataText text){
        this.text = text;
    }
    public GoLogoDataText getText() {
        return text;
    }
    public void setText(String text) {
        this.text.setText(text);
    }
    public void setName(String name){
        this.name.set(name);
    }
    public String getName() {
        return this.name.get();
    }
    public void setOrder(int order){
        this.order.set(order);
    }
    public int getOrder(){
        return order.get();
    }
    public void setType(String type){
        this.type.set(type);
    }
    public String getType(){
        return type.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public IntegerProperty orderProperty(){
        return order;
    }
    public StringProperty typeProperty(){
        return type;
    }
    public GoLogoDataPrototype buildDefaultText(String text, GoLogoLoApp app) {
        this.text = new GoLogoDataText(text, app);
        return this;
    }
    public void setNode(Node node) {
        this.node=node;
    }

    public GoLogoDataPrototype clone() {
     GoLogoDataPrototype clone = new GoLogoDataPrototype(name.getValue(),type.getValue());
        if(this.text!=null){
            clone.setText(this.text);
        }
        if(this.node!=null){
            clone.setNode(node);
        }
     return clone;
    }
    
    public boolean contains(Node node) {
        if(this.getNode() != null)
            return true;
        else if (this.getText() != null)
            return true;
        return false;
    }
}
