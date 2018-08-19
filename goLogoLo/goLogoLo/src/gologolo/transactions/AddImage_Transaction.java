/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class AddImage_Transaction implements jTPS_Transaction{
    GoLogoData data;
    GoLogoDataPrototype itemToAdd;
    public AddImage_Transaction(GoLogoData data, GoLogoDataPrototype itemToAdd){
        this.data=data;
        this.itemToAdd=itemToAdd;
    }
    @Override
    public void doTransaction() {
      data.getPaneNodes().add(itemToAdd.getImage());
      data.addItem(itemToAdd);
    }

    @Override
    public void undoTransaction() {
        data.getPaneNodes().remove(itemToAdd.getImage());
        data.removeItem(itemToAdd);
    }
    
}
