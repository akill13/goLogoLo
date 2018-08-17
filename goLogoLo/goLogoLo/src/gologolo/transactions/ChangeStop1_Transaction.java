/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi beta
 */
public class ChangeStop1_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    Color newColor;
    Color oldColor;
    
    public ChangeStop1_Transaction(GoLogoDataPrototype change, Color newColor, Color oldColor) {
        this.change=change;
        this.newColor=newColor;
        this.oldColor=oldColor;
    }
    @Override
    public void doTransaction() {
        Stop stop1 = new Stop(1, newColor);
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setStop1(newColor);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), stop1);
       rect.setFill(rad);
       
    }

    @Override
    public void undoTransaction() {
       Stop stop1 = new Stop(1, oldColor);
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setStop1(oldColor);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusAngle(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), stop1);
       rect.setFill(rad);
    }
    
}
