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
    GoLogoDataPrototype item;
    GoLogoDataPrototype add;
    public PasteItem_Transaction(GoLogoLoApp app, 
                                    ArrayList<GoLogoDataPrototype> itemsToPaste,
                                    int pasteIndex, Node nodeToPaste){
        this.app = app;
        this.itemsToPaste=itemsToPaste;
        this.pasteIndex=pasteIndex;
        this.nodeToPaste = nodeToPaste;
        pane = (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        this.item=itemsToPaste.get(0);
        this.add=item.clone();
    }
    @Override
    public void doTransaction() {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      if(add.getType().equals("Circle") || add.getType().equals("Rectangle") ) {
        pane.getChildren().add(add.node);
      }else if(add.getType().equals("Label")) {
          pane.getChildren().add(add.getText());
      }else if(add.getType().equals("Image")) {
          pane.getChildren().add(add.getImage());
      }
      data.addItem(add);
      data.upDateOrder();
    }

    @Override
    public void undoTransaction() {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      if(add.getType().equals("Circle") || add.getType().equals("Rectangle") ) {
        pane.getChildren().remove(add.node);
      }else if(add.getType().equals("Label")) {
          pane.getChildren().remove(add.getText());
      }else if(add.getType().equals("Image")) {
          pane.getChildren().remove(add.getImage());
      }
      data.removeItem(add);
      data.upDateOrder();
    }
    
}
