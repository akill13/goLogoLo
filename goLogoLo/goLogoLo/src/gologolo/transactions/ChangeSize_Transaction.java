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
public class ChangeSize_Transaction implements jTPS_Transaction {
    Font newfont;
    Font oldfont;
    GoLogoDataPrototype changedata;
    
    public ChangeSize_Transaction(Font newfont, Font oldfont, GoLogoDataPrototype changedata) {
        this.newfont=newfont;
        this.oldfont=oldfont;
        this.changedata=changedata;
    }
    @Override
    public void doTransaction() {
       changedata.getText().setFont(newfont);
    }

    @Override
    public void undoTransaction() {
        changedata.getText().setFont(oldfont);
    }
    
}
