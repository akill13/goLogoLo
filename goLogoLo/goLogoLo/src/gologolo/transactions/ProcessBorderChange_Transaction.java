/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoRectangle;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import jtps.jTPS_Transaction;

/**
 *
 * @author akillhalimi
 */
public class ProcessBorderChange_Transaction implements jTPS_Transaction {
    GoLogoDataPrototype node;
    double newWidth;
    double oldWidth;
    GoLogoLoApp app;
    public ProcessBorderChange_Transaction(GoLogoDataPrototype node, double newWidth, GoLogoLoApp app) {
        this.node=node;
        this.newWidth=newWidth;
        this.app=app;
    }
    @Override
    public void doTransaction() {
        if(node.shape) {
           if(node.getType().equals("Rectangle")) {
               LogoRectangle rect = (LogoRectangle) node.getNode();
               this.oldWidth = rect.oldStrokeValue;
               rect.setStrokeWidth(newWidth);
               rect.oldStrokeValue = newWidth;
               Slider borderThickness = (Slider)app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
               borderThickness.setValue(newWidth);
           }
        }
    }

    @Override
    public void undoTransaction() {
        LogoRectangle rect = (LogoRectangle) node.getNode();
        rect.setStrokeWidth(oldWidth);
        Slider borderThickness = (Slider)app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
        borderThickness.setValue(oldWidth);
    }
    
}
