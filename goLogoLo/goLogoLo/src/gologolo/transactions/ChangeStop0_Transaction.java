/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_0_PICKER;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoCircle;
import gologolo.data.LogoRectangle;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ChangeStop0_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change; 
    Color newColor; 
    Color oldColor;
    GoLogoLoApp app;
    boolean isRectangle;
    
    public ChangeStop0_Transaction(GoLogoDataPrototype change, Color newColor, GoLogoLoApp app) {
        this.change=change;
        this.newColor=newColor;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            Color old;
            LogoRectangle rect = (LogoRectangle) change.getNode();
            old = rect.getColor0();
            this.oldColor = old;
            this.isRectangle=true;
        }else if(change.getType().equals("Circle")) {
            Color old;
            LogoCircle circle = (LogoCircle) change.getNode();
            old = circle.getColor0();
            this.oldColor = old;
        }
    }
    @Override
    public void doTransaction() {
       Stop stop0 = new Stop(0, newColor);
       if(isRectangle){
        LogoRectangle rect = (LogoRectangle) change.getNode();
        rect.setStop0(newColor);
        RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,stop0, rect.getStop1());
        rect.setFill(rad); 
       }else {
           LogoCircle circle = (LogoCircle) change.getNode();
           circle.setStop0(newColor);
           RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusAngle(), circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(),circle.isProportional(), circle.getCycleMethod() ,stop0, circle.getStop1());
           circle.setFill(rad);
       }
       ColorPicker stop = (ColorPicker)app.getGUIModule().getGUINode(GOLO_COLOR_STOP_0_PICKER);
       stop.setValue(newColor);
    }

    @Override
    public void undoTransaction() {
       Stop stop0 = new Stop(0, oldColor);
       if(isRectangle) {
        LogoRectangle rect = (LogoRectangle) change.getNode();
        rect.setStop0(oldColor);
        RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,stop0, rect.getStop1());
        rect.setFill(rad);  
       }else{
           LogoCircle circle = (LogoCircle) change.getNode();
           circle.setStop0(oldColor);
           RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusAngle(), circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(),circle.isProportional(), circle.getCycleMethod() ,stop0, circle.getStop1());
           circle.setFill(rad);
       }
       ColorPicker stop = (ColorPicker)app.getGUIModule().getGUINode(GOLO_COLOR_STOP_0_PICKER);
       stop.setValue(oldColor);
    }
    
}
