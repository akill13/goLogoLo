/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoDataPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class AddUnderline_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype changedata;

    public AddUnderline_Transaction(GoLogoDataPrototype changedata) {
        this.changedata=changedata;
    }
    @Override
    public void doTransaction() {
       changedata.getText().setUnderline(true);
    }

    @Override
    public void undoTransaction() {
      changedata.getText().setUnderline(false);
    }
    
}
