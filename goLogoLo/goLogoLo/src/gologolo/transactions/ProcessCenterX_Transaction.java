/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_RADIUS_BOX;
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
public class ProcessCenterX_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    double newValue; 
    double oldValue; 
    GoLogoLoApp app;
    boolean isRectangle;
    public ProcessCenterX_Transaction(GoLogoDataPrototype change, double newValue, GoLogoLoApp app) {
        this.change=change;
        this.newValue=newValue;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            this.oldValue=rect.getCenterX();
            this.isRectangle = true;
        }else if(change.getType().equals("Circle")) {
            LogoCircle cirlce = (LogoCircle) change.getNode();
            this.oldValue=cirlce.getCenterX();
        }
    }
    @Override
    public void doTransaction() {
       if(isRectangle){
          LogoRectangle rect = (LogoRectangle) change.getNode();
          rect.setCenterX(newValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), newValue, rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }else{
           LogoCircle circle = (LogoCircle) change.getNode();
           circle.setCenterX(newValue);
           RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), newValue, circle.getCenterY(), circle.getRadius(), circle.isProportional(), circle.getCycleMethod() ,circle.getStop0(), circle.getStop1());
           circle.setFill(rad);
       }
       Slider borderRadi = (Slider) app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
       borderRadi.setValue(newValue);
    }

    @Override
    public void undoTransaction() {
       if(isRectangle){
          LogoRectangle rect = (LogoRectangle) change.getNode();
          rect.setCenterX(oldValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), oldValue, rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }else{
           LogoCircle circle = (LogoCircle) change.getNode();
           circle.setCenterX(oldValue);
           RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), oldValue, circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod() ,circle.getStop0(), circle.getStop1());
           circle.setFill(rad);
       }
       Slider borderRadi = (Slider) app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
       borderRadi.setValue(oldValue);
    }
    
}
