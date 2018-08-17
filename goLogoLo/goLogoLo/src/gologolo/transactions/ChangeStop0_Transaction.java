/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
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
    
    public ChangeStop0_Transaction(GoLogoDataPrototype change, Color newColor, Color oldColor) {
        this.change=change;
        this.newColor=newColor;
        this.oldColor=oldColor;
    }
    @Override
    public void doTransaction() {
       Stop stop0 = new Stop(0, newColor);
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setStop0(newColor);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,stop0, rect.getStop1());
       rect.setFill(rad);
    }

    @Override
    public void undoTransaction() {
       Stop stop0 = new Stop(0, oldColor);
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setStop0(oldColor);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,stop0, rect.getStop1());
       rect.setFill(rad);
    }
    
}
