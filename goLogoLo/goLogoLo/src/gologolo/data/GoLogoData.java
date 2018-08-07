/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import djf.components.AppDataComponent;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Pane;

/**
 *
 * @author akillhalimi
 */
public class GoLogoData implements AppDataComponent{
    GoLogoLoApp app;
    ObservableList<GoLogoDataPrototype> logoTable;
    TableViewSelectionModel itemSelectionModel;
    ObservableList<Node> panNodes;
    public GoLogoData(GoLogoLoApp app){
        this.app=app;
        TableView table  = (TableView) app.getGUIModule().getGUINode(GOLO_DATA_PANE);
        logoTable = table.getItems();
        Pane canvas = (Pane)app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        panNodes = canvas.getChildren();
    }

    @Override
    public void reset() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addItem(GoLogoDataPrototype itemToAdd) {
        logoTable.add(itemToAdd);
    }
    public void removeItem(GoLogoDataPrototype itemToRemove) {
        logoTable.remove(itemToRemove);
    }
    
    public void addNode(Node toAdd) {
        panNodes.add(toAdd);
    }

    public ObservableList<Node> getPaneNodes() {
        return panNodes;
    }

}
