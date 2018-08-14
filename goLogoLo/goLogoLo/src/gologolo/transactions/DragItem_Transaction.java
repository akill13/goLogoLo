/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class DragItem_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    Node originalItem;
    double originalx, orgianly;
    double newx, newy;
    
    public DragItem_Transaction(double newx, double newy, double originalx, double originaly, GoLogoLoApp app, Node originalItem) {
        this.newx=newx;
        this.newy=newy;
        this.originalx=originalx;
        this.orgianly=originaly;
        this.app=app;
        this.originalItem=originalItem;
    }
    
    @Override
    public void doTransaction() {
       originalItem.setTranslateX(newx);
       originalItem.setTranslateY(newy);
    }

    @Override
    public void undoTransaction() {
      originalItem.setTranslateX(originalx);
      originalItem.setTranslateY(orgianly);
    }
}
