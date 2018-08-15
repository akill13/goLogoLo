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
import java.util.HashMap;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
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
    HashMap<Object, Node> children;
    public GoLogoData(GoLogoLoApp app){
        this.app=app;
        TableView table  = (TableView) app.getGUIModule().getGUINode(GOLO_DATA_PANE);
        logoTable = table.getItems();
        itemSelectionModel = table.getSelectionModel();
        itemSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        Pane canvas = (Pane)app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        panNodes = canvas.getChildren();
        
    }

    @Override
    public void reset() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<GoLogoDataPrototype> getLogoTable(){
        return logoTable;
    }
    
    public void addItem(GoLogoDataPrototype itemToAdd) {
        logoTable.add(itemToAdd);
        this.upDateOrder();
    }
    public void removeItem(GoLogoDataPrototype itemToRemove) {
        logoTable.remove(itemToRemove);
        this.upDateOrder();
    }
    
    public void addNode(Node toAdd) {
        panNodes.add(toAdd);
    }

    public ObservableList<Node> getPaneNodes() {
        return panNodes;
    }
    
    public boolean isItemSelected() {
        ObservableList<GoLogoDataPrototype> selectedItems = this.getSelectedItems();
        ObservableList<Node> selectedNode = this.getPaneNodes();
        return ((selectedItems != null) && (selectedItems.size() == 1)) || ((selectedNode != null ) && (selectedNode.size()==1));
    }
    public boolean areItemsSelected() {
        ObservableList<GoLogoDataPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);        
    }
    public ObservableList<GoLogoDataPrototype> getSelectedItems() {
        return (ObservableList<GoLogoDataPrototype>)this.itemSelectionModel.getSelectedItems();
    }
    
    public HashMap<Object, Node> getChildrenHash() {
        return children;
    }
    
    public void fill() {
       for(int i = 0; i<panNodes.size(); i++) {
           children.put(panNodes.get(i).getId(), panNodes.get(i));
       }
    }
    public GoLogoDataPrototype getSelectedItem(){
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }
    
    public ArrayList<Integer> removeAll(ArrayList<GoLogoDataPrototype> itemsToRemove) {
        ArrayList<Integer> itemIndices = new ArrayList();
        for (GoLogoDataPrototype item: itemsToRemove) {
            itemIndices.add(logoTable.indexOf(item));
        }
        for (GoLogoDataPrototype item: itemsToRemove) {
            logoTable.remove(item);
        }
        this.upDateOrder();
        return itemIndices;
    }
    
        public void addAll(ArrayList<GoLogoDataPrototype> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            GoLogoDataPrototype itemToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            logoTable.add(location, itemToAdd);
        }
    }

    public ArrayList<GoLogoDataPrototype> getCurrentItemsOrder() {
        ArrayList<GoLogoDataPrototype> orderedItems = new ArrayList();
        for (GoLogoDataPrototype item : logoTable) {
            orderedItems.add(item);
        }
        return orderedItems;
    }
    
    public ArrayList<Node> getSelectedNodes() {
        ArrayList<Node> markedForRemoval = new ArrayList<Node>();
        ArrayList<GoLogoDataPrototype> data = new ArrayList<>(this.getSelectedItems());
        
        for(int i = 0; i<data.size(); i++){
            if(data.get(i).getNode()!=null){
                markedForRemoval.add(data.get(i).getNode());
            }
            if(data.get(i).getText()!=null){
               markedForRemoval.add(data.get(i).getText());
            }
        }
        return markedForRemoval;
    }
    
    public void upDateOrder(){
        for(int i = 0; i <logoTable.size(); i++){
            logoTable.get(i).setOrder(i+1);
        }
    }
    
    public Iterator<GoLogoDataPrototype> tableIterator() {
        return this.logoTable.iterator();
    }
    
    public Iterator<Node> paneIterator(){
        return this.panNodes.iterator();
    }
    
    public int getItemIndex(GoLogoDataPrototype item) {
        return logoTable.indexOf(item);
    }
    
    public void addItemAt(GoLogoDataPrototype item, int itemIndex) {
        logoTable.add(itemIndex, item);
    }
    
    public ObservableList<GoLogoDataPrototype> getlogoTable() {
        return logoTable;
    }
    
    public GoLogoLoApp getapp(){
        return app;
    }

}
