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
public class ProcessCenterY_Transaction implements jTPS_Transaction{
    GoLogoDataPrototype change;
    double newValue;
    double oldValue;
    GoLogoLoApp app;
    boolean isRectangle;
    public ProcessCenterY_Transaction(GoLogoDataPrototype change, double newValue, GoLogoLoApp app) {
        this.change=change;
        this.newValue=newValue;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            this.isRectangle=true;
            LogoRectangle rect = (LogoRectangle)change.getNode();
            this.oldValue=rect.getCenterY();
        }else{
            LogoCircle circle = (LogoCircle)change.getNode();
            this.oldValue=circle.getCenterY();
        }
    }
    @Override
    public void doTransaction() {
       if(isRectangle) {
          LogoRectangle rect = (LogoRectangle) change.getNode();
          rect.setCenterY(newValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), newValue, rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }else{
          LogoCircle rect = (LogoCircle) change.getNode();
          rect.setCenterY(newValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), newValue, rect.getRadiusGrad(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }
       Slider centerY = (Slider)app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
       centerY.setValue(newValue);
    }

    @Override
    public void undoTransaction() {
       if(isRectangle) {
          LogoRectangle rect = (LogoRectangle) change.getNode();
          rect.setCenterY(oldValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), oldValue, rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }else{
          LogoCircle rect = (LogoCircle) change.getNode();
          rect.setCenterY(newValue);
          RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), oldValue, rect.getRadiusGrad(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
          rect.setFill(rad); 
       }
       Slider centerY = (Slider)app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
       centerY.setValue(oldValue);
        
    }
    
}
