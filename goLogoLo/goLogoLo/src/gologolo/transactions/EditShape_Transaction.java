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
public class EditShape_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype itemToEdit;
    String oldName;
    String newName;
    
    public EditShape_Transaction(String oldName, String newName){
        this.oldName=oldName;
        this.newName=newName;
    }
    @Override
    public void doTransaction() {
       itemToEdit.setName(newName);
    }

    @Override
    public void undoTransaction() {
       itemToEdit.setName(oldName);
    }
    
}
