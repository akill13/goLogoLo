/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class PasteItem_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    ArrayList<GoLogoDataPrototype> itemsToPaste;
    Node nodeToPaste;
    int pasteIndex;
    Pane pane;
    public PasteItem_Transaction(GoLogoLoApp app, 
                                    ArrayList<GoLogoDataPrototype> itemsToPaste,
                                    int pasteIndex, Node nodeToPaste){
        this.app = app;
        this.itemsToPaste=itemsToPaste;
        this.pasteIndex=pasteIndex;
        this.nodeToPaste = nodeToPaste;
        pane = (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
    }
    @Override
    public void doTransaction() {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      int index = pasteIndex+1;
        for (GoLogoDataPrototype itemToPaste : itemsToPaste) {
            data.addItemAt(itemToPaste, index);
            index++;
        }
        pane.getChildren().add(nodeToPaste);
      data.upDateOrder();
    }

    @Override
    public void undoTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        for (GoLogoDataPrototype itemToPaste : itemsToPaste) {
            data.removeItem(itemToPaste);
        }
        pane.getChildren().remove(nodeToPaste);
        data.upDateOrder();
    }
    
}
