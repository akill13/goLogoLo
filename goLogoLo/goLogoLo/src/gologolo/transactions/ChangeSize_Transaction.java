/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_FONT_CHOICE;
import gologolo.data.GoLogoDataPrototype;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ChangeSize_Transaction implements jTPS_Transaction {
    Font newfont;
    Font oldfont;
    GoLogoDataPrototype changedata;
    GoLogoLoApp app;
    public ChangeSize_Transaction(Font newfont, Font oldfont, GoLogoDataPrototype changedata, GoLogoLoApp app) {
        this.newfont=newfont;
        this.oldfont=oldfont;
        this.changedata=changedata;
        this.app=app;
    }
    
    @Override
    public void doTransaction() {
        changedata.getText().setFont(newfont);
        ComboBox fontSize = (ComboBox) app.getGUIModule().getGUINode(GOLO_FONT_CHOICE);
        fontSize.setValue(Double.toString(newfont.getSize()));
    }

    @Override
    public void undoTransaction() {
        changedata.getText().setFont(oldfont);
        ComboBox fontSize = (ComboBox) app.getGUIModule().getGUINode(GOLO_FONT_CHOICE);
        fontSize.setValue(Double.toString(oldfont.getSize()));
    }
    
}
