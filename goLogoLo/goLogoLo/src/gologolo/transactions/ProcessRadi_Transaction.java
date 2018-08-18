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
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ProcessRadi_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    GoLogoLoApp app;
    double radi;
    double oldradi;
    public ProcessRadi_Transaction(GoLogoDataPrototype change, double radi,GoLogoLoApp app) {
        this.change=change;
        this.app=app;
        this.radi=radi;
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle)change.getNode();
            this.oldradi=rect.getRadi();
        }
    }
    @Override
    public void doTransaction() {
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle)change.getNode();
            rect.setRadi(radi);
            rect.setArcHeight(radi);
            rect.setArcWidth(radi);
        }
        Slider borderRadius = (Slider) app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
        borderRadius.setValue(radi);
    }

    @Override
    public void undoTransaction() {
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle)change.getNode();
            rect.setRadi(oldradi);
            rect.setArcHeight(oldradi);
            rect.setArcWidth(oldradi);
        }
        Slider borderRadius = (Slider) app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
        borderRadius.setValue(oldradi);
    }
    
}
