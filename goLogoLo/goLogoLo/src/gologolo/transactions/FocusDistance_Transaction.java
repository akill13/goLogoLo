/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_DIST_SLIDER;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoCircle;
import gologolo.data.LogoRectangle;
import javafx.scene.control.Slider;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class FocusDistance_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    double newValue; 
    double oldValue; 
    GoLogoLoApp app;
    boolean isRectangle;
    public FocusDistance_Transaction(GoLogoDataPrototype change, double newValue, GoLogoLoApp app) {
        this.change=change;
        this.newValue=newValue;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            this.oldValue=rect.getFocusDistance();
            this.isRectangle=true;
        }else{
            LogoCircle circle = (LogoCircle)change.getNode();
            this.oldValue=circle.getFocusDistance();
        }
    }
    
    @Override
    public void doTransaction() {
        if(isRectangle) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            rect.setFocusDistance(newValue);
            RadialGradient rad = new RadialGradient(rect.getFocusAngle(), newValue, rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
            rect.setFill(rad);  
        }
        else{
            LogoCircle circle = (LogoCircle) change.getNode();
            circle.setFocusDistance(newValue);
            RadialGradient rad = new RadialGradient(circle.getFocusAngle(), newValue, circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
            circle.setFill(rad);  
        }
       Slider focusDistance = (Slider) app.getGUIModule().getGUINode(GOLO_FOCUS_DIST_SLIDER);
       focusDistance.setValue(newValue);
    }

    @Override
    public void undoTransaction() {
       if(isRectangle) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            rect.setFocusDistance(oldValue);
            RadialGradient rad = new RadialGradient(rect.getFocusAngle(), oldValue, rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
            rect.setFill(rad);  
        }
        else{
            LogoCircle circle = (LogoCircle) change.getNode();
            circle.setFocusDistance(newValue);
            RadialGradient rad = new RadialGradient(circle.getFocusAngle(), oldValue, circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
            circle.setFill(rad);  
        }
       Slider focusDistance = (Slider) app.getGUIModule().getGUINode(GOLO_FOCUS_DIST_SLIDER);
       focusDistance.setValue(oldValue);
    }
    
}
