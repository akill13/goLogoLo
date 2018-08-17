/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_SIZE_CHOICE;
import gologolo.data.GoLogoDataText;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ProcessDecreaseFont_Transaction implements jTPS_Transaction {
    GoLogoDataText text;
    Font oldFont;
    Font newFont;
    GoLogoLoApp app;
    
    public ProcessDecreaseFont_Transaction(GoLogoDataText text, GoLogoLoApp app) {
        this.text=text;
        oldFont = text.getFont();
        this.app=app;
    }
    @Override
    public void doTransaction() {
        if(text.isBold){
            newFont = Font.font(oldFont.getName(), FontWeight.BOLD, oldFont.getSize()-2);
            
        }else if(text.isItalics){
            newFont = Font.font(oldFont.getName(), FontPosture.ITALIC, oldFont.getSize()-2);
            
        }else
            newFont = Font.font(oldFont.getName(), oldFont.getSize()-2);
        text.setFont(newFont);
//        ComboBox sizeText = (ComboBox) app.getGUIModule().getGUINode(GOLO_SIZE_CHOICE);
//        sizeText.setValue(Double.toString(newFont.getSize()));
    }

    @Override
    public void undoTransaction() {
       text.setFont(oldFont);
       ComboBox sizeText = (ComboBox) app.getGUIModule().getGUINode(GOLO_SIZE_CHOICE);
       String value = Double.toString(oldFont.getSize());
       sizeText.setValue(value);
    }
    
}
