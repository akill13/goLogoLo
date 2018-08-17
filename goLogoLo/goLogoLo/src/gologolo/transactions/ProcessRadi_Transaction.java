/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
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
            this.oldradi=rect.getOldRadi();
        }
    }
    @Override
    public void doTransaction() {
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle)change.getNode();
            rect.setOldRadi(radi);
            rect.setArcHeight(radi);
            rect.setArcWidth(radi);
        }
    }

    @Override
    public void undoTransaction() {
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle)change.getNode();
            rect.setOldRadi(oldradi);
            rect.setArcHeight(oldradi);
            rect.setArcWidth(oldradi);
        }
    }
    
}
