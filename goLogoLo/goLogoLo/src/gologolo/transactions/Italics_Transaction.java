/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import javafx.scene.text.Font;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class Italics_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype changedata;
    Font oldfont;
    Font newfont;
    public Italics_Transaction(GoLogoDataPrototype changedata, Font oldfont, Font newfont) {
        this.changedata=changedata;
        this.oldfont=oldfont;
        this.newfont=newfont;
    }
    @Override
    public void doTransaction() {
      
    }

    @Override
    public void undoTransaction() {
    
    }
    
}
