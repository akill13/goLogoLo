/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_ANGLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_X_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_Y_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_BOX;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_0_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_1_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_CYCLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_DIST_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_LABEL_DIST;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_HEIGHT;
import static gologolo.data.GoLogoDataPrototype.DEFAULT_WIDTH;
import gologolo.transactions.DragItem_Transaction;
import gologolo.workspace.controllers.LogoController;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author akillhalimi
 */
public class LogoCircle extends Circle{
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
    double circleRadius = 50.0;
    
    public LogoCircle() {
        rad = new RadialGradient(focusAngle, focusDistance, centerX, centerY, radius, proportional, cycleMethod, stop0, stop1);
        this.setFill(rad);
        this.setStroke(strokeColor);
        this.setRadius(circleRadius);
        this.setTranslateX(50);
        this.setTranslateY(50);
    }
    
    public LogoCircle(GoLogoLoApp app) {
        this();
        
        this.app=app;
        this.setOnMousePressed(e->{
            MouseLocation.x = e.getSceneX();
            MouseLocation.y = e.getSceneY();
            MouseLocation.origianlx = this.getTranslateX();
            MouseLocation.originaly = this.getTranslateY();
            
        });
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
        
        this.setOnMouseReleased(e->{
            Slider thickness = (Slider) app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
            ColorPicker color = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
            Slider angle = (Slider) app.getGUIModule().getGUINode(GOLO_ANGLE_BOX);
            Slider focus = (Slider) app.getGUIModule().getGUINode(GOLO_FOCUS_DIST_SLIDER);
            Slider centerXS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_X_SLIDER);
            Slider centerYS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_Y_SLIDER);
            Slider radiGrad = (Slider) app.getGUIModule().getGUINode(GOLO_COLOR_RADIUS_SLIDER);
            ComboBox cycleMethodC = (ComboBox) app.getGUIModule().getGUINode(GOLO_CYCLE_BOX);
            ColorPicker stop0C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_0_PICKER);
            ColorPicker stop1C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_1_PICKER);
            
            thickness.setValue(this.strokeWidthProperty().doubleValue());
            color.setValue(this.getStrokeColor());
            angle.setValue(this.getFocusAngle());
            focus.setValue(this.getFocusDistance());
            centerXS.setValue(this.getCenterX());
            centerYS.setValue(this.getCenterY());
            radiGrad.setValue(this.getRadiusGrad());
            cycleMethodC.setValue(this.getCycleMethod().toString());
            stop0C.setValue(this.getColor0());
            stop1C.setValue(this.getColor1());
        });
    }

    public LogoController getControls() {
        return controls;
    }

    public void setControls(LogoController controls) {
        this.controls = controls;
    }

    public GoLogoLoApp getApp() {
        return app;
    }

    public void setApp(GoLogoLoApp app) {
        this.app = app;
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
    
    public double getRadiusGrad() {
        return radius;
    }

    public void setRadiusGrad(double radius) {
        this.radius = radius;
    }
    
    public double getCircleRadius() {
        return circleRadius;
    }
    public void setCircleRadius(double circleRadius) {
        this.circleRadius=circleRadius;
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

    public Color getColor0() {
        return color0;
    }

    public void setColor0(Color color0) {
        this.color0 = color0;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Stop getStop0() {
        return stop0;
    }

    public void setStop0(Stop stop0) {
        this.stop0 = stop0;
    }
    
    public void setStop0(Color color) {
        Stop stop0 = new Stop(0, color);
        this.stop0=stop0;
    }

    public Stop getStop1() {
        return stop1;
    }
    
    public void setStop1(Color color) {
        Stop stop1 = new Stop(1, color);
        this.stop1 = stop1;
    }

    public void setStop1(Stop stop1) {
        this.stop1 = stop1;
    }

    public RadialGradient getRad() {
        return rad;
    }

    public void setRad(RadialGradient rad) {
        this.rad = rad;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getOldStrokeValue() {
        return oldStrokeValue;
    }

    public void setOldStrokeValue(double oldStrokeValue) {
        this.oldStrokeValue = oldStrokeValue;
    }

    public double getOldRadi() {
        return oldRadi;
    }

    public void setOldRadi(double oldRadi) {
        this.oldRadi = oldRadi;
    }
    
}
