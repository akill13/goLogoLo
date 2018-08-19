/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLoApp;
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
public class CutItem_Transcation implements jTPS_Transaction {
    GoLogoLoApp app;
    ArrayList<GoLogoDataPrototype> itemsToCut;
    ArrayList<Integer> cutItemLocations;
    ArrayList<Node> clipboardNodes;
    Pane pane;
    ArrayList<Integer> nodeLocation;
    public CutItem_Transcation(GoLogoLoApp app, ArrayList<GoLogoDataPrototype> itemsToCut, ArrayList<Node> clipboardNodes, Pane pane) {
        this.itemsToCut = itemsToCut;
        this.app=app;
        this.clipboardNodes = clipboardNodes;
        this.pane=pane;
    }
    @Override
    public void doTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        cutItemLocations = data.removeAll(itemsToCut);
        pane.getChildren().removeAll(clipboardNodes);
        data.upDateOrder();
    }

    @Override
    public void undoTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        data.addAll(itemsToCut, cutItemLocations);
        addAll(pane, cutItemLocations, clipboardNodes);
        data.upDateOrder();
        
    }
    public void addAll(Pane pane, ArrayList<Integer> location, ArrayList<Node> clipboardNodes){
        for(int i = 0; i<location.size(); i++) {
            pane.getChildren().add(location.get(i), clipboardNodes.get(i));
        }
    }
}
