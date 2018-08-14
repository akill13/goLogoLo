/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import javafx.scene.text.Font;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ChangeFont_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype changedata;
    GoLogoData data;
    Font font;
    Font oldfont;
    public ChangeFont_Transaction(Font font, Font oldfont, GoLogoDataPrototype changedata){
        this.font=font;
        this.oldfont=oldfont;
        this.changedata=changedata;
    }
    @Override
    public void doTransaction() {
       System.out.println("Before: "+ changedata.getText().getFont());
       changedata.getText().setFont(font);
       System.out.println("After: " + changedata.getText().getFont());
    }

    @Override
    public void undoTransaction() {
        changedata.getText().setFont(oldfont);
    }
    
}
