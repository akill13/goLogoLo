/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_HEIGHT;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_WIDTH;
import gologolo.transactions.DragItem_Transaction;
import gologolo.workspace.controllers.LogoController;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author akillhalimi
 */
public class LogoRectangle extends Rectangle {
    LogoController controls = LogoController.getController();
    GoLogoLoApp app;
    private double focusAngle = 0.0;
    private double focusDistance = 0.0;
    private double centerX = 0.0;
    private double centerY = 0.0;
    private double radius = 0.0;
    private boolean proportional = true;
    private CycleMethod cycleMethod = CycleMethod.NO_CYCLE;
    Color color0 = Color.WHITE;
    Color color1 = Color.WHITE;
    private Stop stop0 = new Stop(0, color0);
    private Stop stop1 = new Stop(1, color1);
    private RadialGradient rad;
    Color strokeColor = Color.BLACK;
    public double oldStrokeValue = 1.0;
    double oldRadi = 0.0;
    public LogoRectangle() {
        
        rad = new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional, cycleMethod, stop0, stop1);
        this.setFill(rad);
        this.setStroke(strokeColor);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
        this.setX(90);
        this.setY(200);
        this.setArcHeight(oldRadi);
        this.setArcWidth(oldRadi);
        
    }
    public LogoRectangle(GoLogoLoApp app) {
        this();
        this.app=app;
        this.setOnMousePressed(e->{
            MouseLocation.x = e.getSceneX();
            MouseLocation.y = e.getSceneY();
            MouseLocation.origianlx = this.getTranslateX();
            MouseLocation.originaly = this.getTranslateY();
        });
//        rectangle.setOnMouseClicked(e->{
//            this.clearSelected();
//        });
        
        this.setOnMouseDragged(e->{
            
            double offsetX = e.getSceneX() - MouseLocation.x;
            double offsetY = e.getSceneY() - MouseLocation.y;
            double newTranslateX = MouseLocation.origianlx + offsetX;
            double newTranslateY = MouseLocation.originaly + offsetY;

            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
            this.setOnMouseReleased(x->{
                
                DragItem_Transaction trans = new DragItem_Transaction(newTranslateX, newTranslateY, MouseLocation.origianlx, MouseLocation.originaly, app, this);
                app.processTransaction(trans);
            });
        });
    }
    public double getFocusAngle() {
        return focusAngle;
    }

    public void setFocusAngle(double focusAngle) {
        this.focusAngle = focusAngle;
    }

    public double getFocusDistance() {
        return focusDistance;
    }

    public void setFocusDistance(double focusDistance) {
        this.focusDistance = focusDistance;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isProportional() {
        return proportional;
    }

    public void setProportional(boolean proportional) {
        this.proportional = proportional;
    }

    public CycleMethod getCycleMethod() {
        return cycleMethod;
    }

    public void setCycleMethod(CycleMethod cycleMethod) {
        this.cycleMethod = cycleMethod;
    }

    public Stop getStop0() {
        return stop0;
    }

    public void setStop0(Color stop0) {
        this.stop0 = new Stop(0, stop0);
    }

    public Stop getStop1() {
        return stop1;
    }

    public void setStop1(Color stop1) {
        this.stop1 = new Stop(1, stop1);
    }

    public RadialGradient getRad() {
        return rad;
    }

    public void setRad(RadialGradient rad) {
        this.rad = rad;
    }
    public Color getColor0() {
        return color0;
    }
    public Color getColor1() {
        return color1;
    }
    public Color getStrokeColor() {
        return strokeColor;
    }
    public void setStrokeColor(Color toSet) {
        strokeColor = toSet;
    }
    public double getOldRadi() {
        return oldRadi;
    }
    public void setOldRadi(double radi) {
        this.oldRadi=radi;
    }
    
}
