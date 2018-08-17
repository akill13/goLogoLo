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
public class ProcessRadius_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    double newRadius;
    double oldRadius;
    public ProcessRadius_Transaction(GoLogoDataPrototype change, double newRadius, double oldRadius) {
        this.change=change;
        this.newRadius=newRadius;
        this.oldRadius=oldRadius;
        
    }
    @Override
    public void doTransaction() {
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setRadius(newRadius);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), newRadius,rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }

    @Override
    public void undoTransaction() {
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setRadius(oldRadius);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), oldRadius,rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }
    
}
