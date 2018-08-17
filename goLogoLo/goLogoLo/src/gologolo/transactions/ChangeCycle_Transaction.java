/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
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
    public ChangeCycle_Transaction(GoLogoDataPrototype change, CycleMethod newCycle) {
        this.newCycle = newCycle;
        this.change=change;
        LogoRectangle rect = (LogoRectangle) change.getNode();
        this.oldCycle=rect.getCycleMethod();
    }
    @Override
    public void doTransaction() {
        LogoRectangle rect = (LogoRectangle) change.getNode();
        RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), newCycle ,rect.getStop0(), rect.getStop1());
        rect.setFill(rad);
    }

    @Override
    public void undoTransaction() {
      LogoRectangle rect = (LogoRectangle) change.getNode();
      RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), oldCycle ,rect.getStop0(), rect.getStop1());
      rect.setFill(rad);
     }
    
}
