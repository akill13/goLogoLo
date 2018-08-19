/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_UNDERLINE_TEXT_BUTTON;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class AddColor_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype changedata;
    GoLogoLoApp app;
    Color newcolor;
    Color oldcolor;
    public AddColor_Transaction(GoLogoDataPrototype changedata, GoLogoLoApp app, Color newcolor) {
        this.changedata=changedata;
        this.app=app;
        this.newcolor=newcolor;
        GoLogoDataText text = (GoLogoDataText)changedata.getText();
        this.oldcolor=(Color) text.getFill();
    }
    @Override
    public void doTransaction() {
        GoLogoDataText text = (GoLogoDataText)changedata.getText();
        text.setFill(newcolor);
        text.setFillColor(newcolor);
        ColorPicker picker = (ColorPicker) app.getGUIModule().getGUINode(GOLO_UNDERLINE_TEXT_BUTTON);
        picker.setValue(newcolor);
    }

    @Override
    public void undoTransaction() {
      GoLogoDataText text = (GoLogoDataText)changedata.getText();
      text.setFill(oldcolor);
      text.setFillColor(oldcolor);
      ColorPicker picker = (ColorPicker) app.getGUIModule().getGUINode(GOLO_UNDERLINE_TEXT_BUTTON);
      picker.setValue(oldcolor);
    }
    
}
