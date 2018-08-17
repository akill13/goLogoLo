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
        if(!changedata.getText().isUnderline()) {
            changedata.getText().setUnderline(true);
        }else
            changedata.getText().setUnderline(false);
       
    }

    @Override
    public void undoTransaction() {
      if(changedata.getText().isUnderline()) {
          changedata.getText().setUnderline(false);
      }else
          changedata.getText().setUnderline(true);
    }
    
}
