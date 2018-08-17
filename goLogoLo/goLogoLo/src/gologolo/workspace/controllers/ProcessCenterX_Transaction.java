/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
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
    
    public ProcessCenterX_Transaction(GoLogoDataPrototype change, double newValue, double oldValue) {
        this.change=change;
        this.newValue=newValue;
        this.oldValue=oldValue;
    }
    @Override
    public void doTransaction() {
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setCenterX(newValue);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), newValue, rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }

    @Override
    public void undoTransaction() {
       LogoRectangle rect = (LogoRectangle) change.getNode();
       rect.setCenterX(oldValue);
       RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), oldValue, rect.getCenterY(), rect.getRadius(),rect.isProportional(), rect.getCycleMethod() ,rect.getStop0(), rect.getStop1());
       rect.setFill(rad);
    }
    
}
