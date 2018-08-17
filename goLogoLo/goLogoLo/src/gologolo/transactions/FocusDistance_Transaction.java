/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
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
    double og;
    
    public FocusDistance_Transaction(GoLogoDataPrototype change, double newValue, double oldValue, double og) {
        this.change=change;
        this.newValue=newValue;
        this.oldValue=oldValue;
        this.og=og;
    }
    
    @Override
    public void doTransaction() {
      LogoRectangle rect = (LogoRectangle) change.getNode();
      rect.setFocusDistance(newValue);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), newValue, rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }

    @Override
    public void undoTransaction() {
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setFocusDistance(oldValue);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), og, rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }
    
}
