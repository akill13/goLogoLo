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
public class EditText_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype itemToEdit;
    String oldName, oldText;
    String newName, newText;
    
    public EditText_Transaction(String oldName, String oldText, String newName, String newText, GoLogoDataPrototype itemToEdit) {
        this.oldName=oldName;
        this.oldText=oldText;
        this.newName=newName;
        this.newText=newText;
        this.itemToEdit=itemToEdit;
    }
    @Override
    public void doTransaction() {
        itemToEdit.setName(newName);
        itemToEdit.getText().setText(newText);
    }

    @Override
    public void undoTransaction() {
        itemToEdit.setName(oldName);
        itemToEdit.getText().setText(oldText);
    }
    
}
