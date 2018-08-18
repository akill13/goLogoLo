/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_CYCLE_BOX;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoCircle;
import gologolo.data.LogoRectangle;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ChangeCycle_Transaction implements jTPS_Transaction {
    CycleMethod oldCycle;
    CycleMethod newCycle;
    GoLogoDataPrototype change;
    GoLogoLoApp app;
    boolean isRectangle;
    public ChangeCycle_Transaction(GoLogoDataPrototype change, CycleMethod newCycle, GoLogoLoApp app) {
        this.newCycle = newCycle;
        this.change=change;
        this.app=app;
        if(change.getType().equals("Rectangle")) {
            this.isRectangle=true;
           LogoRectangle rect = (LogoRectangle) change.getNode();
           this.oldCycle=rect.getCycleMethod(); 
        }
        
    }
    @Override
    public void doTransaction() {
        if(isRectangle) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), newCycle ,rect.getStop0(), rect.getStop1());
            rect.setFill(rad);
        }else{
            LogoCircle circle = (LogoCircle) change.getNode();
            RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), newCycle, circle.getStop0(), circle.getStop1());
            circle.setRad(rad);
        }
        ComboBox combo = (ComboBox) app.getGUIModule().getGUINode(GOLO_CYCLE_BOX);
        combo.setValue(newCycle.toString());
    }

    @Override
    public void undoTransaction() {
      if(isRectangle) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), oldCycle ,rect.getStop0(), rect.getStop1());
            rect.setFill(rad);
        }else{
            LogoCircle circle = (LogoCircle) change.getNode();
            RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), oldCycle, circle.getStop0(), circle.getStop1());
            circle.setRad(rad);
        }
        ComboBox combo = (ComboBox) app.getGUIModule().getGUINode(GOLO_CYCLE_BOX);
        combo.setValue(oldCycle.toString());
     }
    
}
