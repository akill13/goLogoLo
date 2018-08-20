/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_PANE;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_RADIUS_BOX;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_DOWN_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_EDIT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_EDIT_TEXT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_GRADIENT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_UP_BUTTON;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;

/**
 *
 * @author akillhalimi
 */
public class FoolProof implements FoolproofDesign {
    GoLogoLoApp app;
    
    public FoolProof(GoLogoLoApp app) {
        this.app=app;
    }
    @Override
    public void updateControls() {
       AppGUIModule gui = app.getGUIModule();
       GoLogoData data = (GoLogoData) app.getDataComponent();
       boolean isItemSelected = data.isItemSelected();
       if(isItemSelected) {
           GoLogoDataPrototype pro = data.getSelectedItem();
            gui.getGUINode(GOLO_GRADIENT_PANE).setDisable(pro.getType().equals("Label"));
            gui.getGUINode(GOLO_BORDER_PANE).setDisable(pro.getType().equals("Label"));
            gui.getGUINode(GOLO_EDIT_TEXT_PANE).setDisable(pro.getType().equals("Circle") || pro.getType().equals("Rectangle") || pro.getType().equals("Image"));
            gui.getGUINode(GOLO_UP_BUTTON).setDisable(pro.getOrder()-1==0);
            gui.getGUINode(GOLO_DOWN_BUTTON).setDisable(pro.getOrder()  == data.getNumItems());
            gui.getGUINode(GOLO_UP_BUTTON).setDisable(!isItemSelected);
            gui.getGUINode(GOLO_DOWN_BUTTON).setDisable(!isItemSelected);
            gui.getGUINode(GOLO_EDIT_BUTTON).setDisable(!isItemSelected);
            gui.getGUINode(GOLO_BORDER_RADIUS_BOX).setDisable(pro.getType().equals("Circle"));
       }else if(!isItemSelected) {
           gui.getGUINode(GOLO_UP_BUTTON).setDisable(!isItemSelected);
           gui.getGUINode(GOLO_DOWN_BUTTON).setDisable(!isItemSelected);
           gui.getGUINode(GOLO_EDIT_BUTTON).setDisable(!isItemSelected);
       }
    }
    
}
