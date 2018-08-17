/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_BOX;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ProcessColorChange_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype change;
    Color newcolor;
    Color previous;
    GoLogoLoApp app;
    public ProcessColorChange_Transaction(GoLogoDataPrototype change, Color newcolor, GoLogoLoApp app) {
        this.change=change;
        this.newcolor=newcolor;
        this.app=app;
    }
    
    @Override
    public void doTransaction() {
      if(change.shape) {
          if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            this.previous = rect.getStrokeColor();
            rect.setStroke(newcolor);
            rect.setStrokeColor(newcolor);
          }
          
          ColorPicker picker = (ColorPicker)app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
          picker.setValue(newcolor);
      }
    }

    @Override
    public void undoTransaction() {
        if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            rect.setStroke(previous);
        }
     ColorPicker picker = (ColorPicker)app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
     picker.setValue(previous);
    }
}
