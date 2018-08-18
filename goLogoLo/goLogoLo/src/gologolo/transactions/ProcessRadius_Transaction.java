/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_SLIDER;
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
public class ProcessRadius_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    double newRadius;
    double oldRadius;
    GoLogoLoApp app;
    boolean isRectangle;
    public ProcessRadius_Transaction(GoLogoDataPrototype change, double newRadius, GoLogoLoApp app) {
        this.change=change;
        this.newRadius=newRadius;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            this.isRectangle=true;
            LogoRectangle rect = (LogoRectangle)change.getNode();
            this.oldRadius=rect.getRadius();
        }else{
            LogoCircle circle = (LogoCircle)change.getNode();
            this.oldRadius=circle.getRadiusGrad();
        }
        
    }
    @Override
    public void doTransaction() {
       if(isRectangle) {
           LogoRectangle rect = (LogoRectangle) change.getNode();
           rect.setRadius(newRadius);
           RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), newRadius,rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
           rect.setFill(rad);
       }else{
           LogoCircle rect = (LogoCircle) change.getNode();
           rect.setRadiusGrad(newRadius);
           RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), newRadius, rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
           rect.setFill(rad);
       }
       Slider radiusGrad = (Slider) app.getGUIModule().getGUINode(GOLO_COLOR_RADIUS_SLIDER);
       radiusGrad.setValue(newRadius);
       
    }

    @Override
    public void undoTransaction() {
       if(isRectangle) {
           LogoRectangle rect = (LogoRectangle) change.getNode();
           rect.setRadius(oldRadius);
           RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), oldRadius,rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
           rect.setFill(rad);
       }else{
           LogoCircle rect = (LogoCircle) change.getNode();
           rect.setRadiusGrad(oldRadius);
           RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), oldRadius, rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
           rect.setFill(rad);
       }
       Slider radiusGrad = (Slider) app.getGUIModule().getGUINode(GOLO_COLOR_RADIUS_SLIDER);
       radiusGrad.setValue(oldRadius);
    }
    
}
