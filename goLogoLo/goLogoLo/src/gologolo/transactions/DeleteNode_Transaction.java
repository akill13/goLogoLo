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
public class DeleteNode_Transaction implements jTPS_Transaction {
    GoLogoLoApp app;
    ArrayList<GoLogoDataPrototype> itemsToRemove;
    ArrayList<Integer> removedItemLocations;
    ArrayList<Node> nodesToRemove;
    Pane middle;
    ArrayList<Integer> removedNodesLocations;
    public DeleteNode_Transaction(GoLogoLoApp initApp, ArrayList<GoLogoDataPrototype> itemsToRemove, ArrayList<Node> nodesToRemove, Pane middle) {
        app=initApp;
        this.itemsToRemove = itemsToRemove;
        this.nodesToRemove=nodesToRemove;
        this.middle=middle;
    }
    @Override
    public void doTransaction() {
        GoLogoData data = (GoLogoData) app.getDataComponent();
        removedItemLocations = data.removeAll(itemsToRemove);
        removedNodesLocations = this.getLocations();
        middle.getChildren().removeAll(nodesToRemove);
        data.upDateOrder();
    }

    @Override
    public void undoTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        data.addAll(itemsToRemove, removedItemLocations);
        for(int i = 0; i<removedNodesLocations.size(); i++) {
            middle.getChildren().add(removedNodesLocations.get(i), nodesToRemove.get(i));
        }
        data.upDateOrder();
    }
    
    public ArrayList<Integer> getLocations(){
        ArrayList<Integer> removedArea = new ArrayList<Integer>();
        for(int i = 0; i<nodesToRemove.size(); i++) {
            Node beta = nodesToRemove.get(i);
            int tau = nodesToRemove.indexOf(beta);
            removedArea.add(tau);
        }
        return removedArea;
    }

}
