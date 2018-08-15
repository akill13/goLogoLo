/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class BoldFont_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype changedata;
    Font oldfont;
    Font newfont;
    
    public BoldFont_Transaction(GoLogoDataPrototype changedata, Font oldfont, Font newfont) {
        this.changedata=changedata;
        this.oldfont=oldfont;
        this.newfont=newfont;
    }
    @Override
    public void doTransaction() {
        changedata.getText().setTextFont(newfont);
        System.out.print(changedata.getText().getFont().getSize());
    }

    @Override
    public void undoTransaction() {
        changedata.getText().setFont(oldfont);
    }
    
}
