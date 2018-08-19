/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import gologolo.data.GoLogoData;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class MoveItem_Transaction implements jTPS_Transaction {
    GoLogoData data;
    int oldIndex;
    int newIndex;
    public MoveItem_Transaction(GoLogoData data, int oldIndex, int newIndex){
        this.data=data;
        this.oldIndex=oldIndex;
        this.newIndex=newIndex;
    }
    @Override
    public void doTransaction() {
        
        data.moveItem(oldIndex, newIndex);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(newIndex, oldIndex);
    }
    
}
