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
    Pane pane;
    GoLogoDataPrototype item;
    GoLogoDataPrototype add;
    GoLogoData tableInfo;
    public PasteItem_Transaction(GoLogoLoApp app, ArrayList<GoLogoDataPrototype> itemsToPaste){
        this.app = app;
        this.itemsToPaste=itemsToPaste;
        pane = (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        this.item=itemsToPaste.get(0);
        this.add=item.clone();
        this.tableInfo=(GoLogoData)app.getDataComponent();
    }
    @Override
    public void doTransaction() {
      if(add.getType().equals("Circle") || add.getType().equals("Rectangle") ) {
        pane.getChildren().add(add.node);
      }else if(add.getType().equals("Label")) {
          pane.getChildren().add(add.getText());
      }else if(add.getType().equals("Image")) {
          pane.getChildren().add(add.getImage());  
      }
      tableInfo.addItem(add);
      tableInfo.upDateOrder();
    }

    @Override
    public void undoTransaction() {
      if(add.getType().equals("Circle") || add.getType().equals("Rectangle") ) {
        pane.getChildren().remove(add.node);
      }else if(add.getType().equals("Label")) {
          pane.getChildren().remove(add.getText());
      }else if(add.getType().equals("Image")) {
          pane.getChildren().remove(add.getImage());
      }
      tableInfo.removeItem(add);
      tableInfo.upDateOrder();
    }
    
}
