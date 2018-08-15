/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import djf.AppPropertyType;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import gologolo.data.GoLogoShape;
import gologolo.data.MouseLocation;
import gologolo.transactions.AddImage_Transaction;
import gologolo.transactions.AddShape_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.transactions.AddUnderline_Transaction;
import gologolo.transactions.BoldFont_Transaction;
import gologolo.transactions.ChangeFont_Transaction;
import gologolo.transactions.ChangeSize_Transaction;
import gologolo.transactions.DeleteNode_Transaction;
import gologolo.transactions.DragItem_Transaction;
import gologolo.transactions.EditShape_Transaction;
import gologolo.transactions.EditText_Transaction;
import gologolo.workspace.dialogs.GoLogoLoDialogs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author akillhalimi
 */
public class LogoController {
    GoLogoLoApp app;
    GoLogoLoDialogs dialogs;
    ObservableList<GoLogoDataPrototype> newDataList;
    Pane pane;
    MouseLocation location  = new MouseLocation();
    GoLogoLoDialogs shape;
    private static LogoController singleton = null;
    boolean selected;
    private LogoController(GoLogoLoApp app) {
        this.app = app;
        this.dialogs = new GoLogoLoDialogs(app);
        pane =  (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        this.shape = new GoLogoLoDialogs(app, true);
    }
    
    public void processAddRectangle() {
        dialogs.createRectangle(app);
        GoLogoDataPrototype rect = dialogs.getNewItem();
        if(rect.shape)
            rect.setType("Shape");
        GoLogoData data = (GoLogoData)app.getDataComponent();
        AddShape_Transaction transaction = new AddShape_Transaction(data, rect);
        app.processTransaction(transaction);
    }
    
    public void processTriangle() {
        dialogs.createTriangle();
        GoLogoDataPrototype tri = dialogs.getNewItem();
        if(tri.shape)
            tri.setType("Shape");
        GoLogoData data = (GoLogoData)app.getDataComponent();
        AddShape_Transaction transaction = new AddShape_Transaction(data, tri);
        app.processTransaction(transaction);
    }
    
    public void processAddText() {
        dialogs.showAddText();
        GoLogoDataPrototype text = dialogs.getNewItem();
        if(!text.shape)
            text.setType("Label");
        GoLogoData data = (GoLogoData) app.getDataComponent();
        AddText_Transaction trans = new AddText_Transaction(data, text);
        app.processTransaction(trans);
    }
    
    public void processEdit(){
        GoLogoData data = (GoLogoData) app.getDataComponent();
        if(data.isItemSelected()){
            GoLogoDataPrototype itemToEdit = data.getSelectedItem();
            if(!itemToEdit.shape){ 
                dialogs.showEditText(itemToEdit);
            GoLogoDataPrototype edit = dialogs.getEditingItem();
            if(edit != null){
                EditText_Transaction trans = new EditText_Transaction(itemToEdit.getName(), itemToEdit.getText().getText(), edit.getName(), edit.getText().getText(), itemToEdit);
                app.processTransaction(trans);
            }
        }else{
                dialogs.showEditShape(itemToEdit);
                GoLogoDataPrototype edit = dialogs.getEditingItem();
            if(edit != null){
                EditShape_Transaction trans = new EditShape_Transaction(itemToEdit.getName(), edit.getName());
                app.processTransaction(trans);
            }     
        }
      }
    }
    
    public void processEdit(GoLogoDataText text) {
        selected=true;
        processEdit();
    }
    
    public void processRemove(Pane pane){
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<GoLogoDataPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            ArrayList<Node> nodesToRemove = data.getSelectedNodes();
            DeleteNode_Transaction transaction = new DeleteNode_Transaction(app, itemsToRemove, nodesToRemove, pane);
            app.processTransaction(transaction);
        }
    }
    public static LogoController getController(GoLogoLoApp app) {
        if(singleton == null){
            singleton = new LogoController(app);
        }
        return singleton;
    }
    
    public static LogoController getController() {
        return singleton;
    }
    
    public void processAddImage(){
        File file = djf.ui.dialogs.AppDialogsFacade.showOpenDialog(dialogs, AppPropertyType.APP_TITLE);
        System.out.println(file.getPath());
        String path = file.getAbsolutePath();
            FileInputStream imageLocation;
        try {
            imageLocation = new FileInputStream(file);
            Image image = new Image(imageLocation);
            ImageView node = new ImageView(image);
            GoLogoDataPrototype data = new GoLogoDataPrototype(node);
            GoLogoData logodata = (GoLogoData)app.getDataComponent();
            AddImage_Transaction trans = new AddImage_Transaction(logodata, data);
            app.processTransaction(trans);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }     
    }
    
    public void processChangeFont(String selectedFont){
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
            Font newfont = new Font(selectedFont, 13.0);
            GoLogoDataPrototype changedata = data.getSelectedItem();
            Font oldfont = changedata.getText().getFont();
            ChangeFont_Transaction trans = new ChangeFont_Transaction(newfont, oldfont, changedata);
            app.processTransaction(trans);
        }
    }
    public void processChangeSize(String selectedSize) {
        GoLogoData data = (GoLogoData) app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
            double size = Double.parseDouble(selectedSize);
            GoLogoDataPrototype changedata = data.getSelectedItem();
            Font oldfont = data.getSelectedItem().getText().getFont();
            Font newfont = new Font(size);
            ChangeSize_Transaction trans = new ChangeSize_Transaction(newfont, oldfont, changedata);
            app.processTransaction(trans);
        }
    }
    
    public void processMouseIntersection(Node node) {
        TableView table = (TableView) app.getGUIModule().getGUINode(GOLO_DATA_PANE);
        TableSelectionModel selection = table.getSelectionModel();
        ObservableList<GoLogoDataPrototype> list = table.getItems();
        for(int i = 0; i<list.size(); i++) {
            if(list.get(i).contains(node)){
                selection.select(i);
            }
        }
        
    }
    
    public void processBoldChange() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
           GoLogoDataPrototype changedata = data.getSelectedItem();
           Font oldFont = changedata.getText().getFont();
           Font newFont = Font.font("Times New Roman", FontWeight.BOLD, oldFont.getSize());
           BoldFont_Transaction trans = new BoldFont_Transaction(changedata, oldFont, newFont);
           app.processTransaction(trans);
        }
    }
    
    public void processUnderLineChange() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
            GoLogoDataPrototype changedata = data.getSelectedItem();
            AddUnderline_Transaction trans = new AddUnderline_Transaction(changedata);
            app.processTransaction(trans);
        }
    }
    
    public void processItalicsChange() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
            GoLogoDataPrototype changedata = data.getSelectedItem();
            AddUnderline_Transaction trans = new AddUnderline_Transaction(changedata);
            app.processTransaction(trans);
        }
    }
    
    public void processAngle(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
          
        }
       
    }
    public void processFocus(Number oldval, Number newval) {
        
    }
    public void processCenterX(Number oldval, Number newval) {
        
    }
    public void processCenterY(Number oldval, Number newval) {
        
    }
    public void processRadius(Number oldval, Number newval) {
        
    }

    public void processCycle(String cycle) {
        
    }

    public void processColor0Change(Color value) {
      
    }

    public void processColor1Change(Color value) {
    }
}
