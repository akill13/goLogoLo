/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.LogoCircle;
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
               this.oldWidth = rect.oldStrokeWidth;
               rect.setStrokeWidth(newWidth);
               rect.oldStrokeWidth = newWidth;
           }else{
               LogoCircle circle = (LogoCircle) node.getNode();
               this.oldWidth=circle.oldStrokeValue;
               System.out.println(oldWidth);
               circle.setStrokeWidth(newWidth);
               System.out.println(newWidth);
               circle.oldStrokeValue=newWidth;
           }
        }
        Slider borderThickness = (Slider)app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
        borderThickness.setValue(newWidth);
    }

    @Override
    public void undoTransaction() {
        if(node.shape) {
           if(node.getType().equals("Rectangle")) {
               LogoRectangle rect = (LogoRectangle) node.getNode();
               rect.setStrokeWidth(oldWidth);
               rect.oldStrokeWidth = oldWidth;
           }else{
               LogoCircle circle = (LogoCircle) node.getNode();
               circle.setStrokeWidth(oldWidth);
               circle.oldStrokeValue=oldWidth;
           }
        }
        Slider borderThickness = (Slider)app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
        borderThickness.setValue(oldWidth);
    }
    
}
