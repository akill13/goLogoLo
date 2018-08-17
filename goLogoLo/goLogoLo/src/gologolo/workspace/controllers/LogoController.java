/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import gologolo.transactions.ChangeCycle_Transaction;
import djf.AppPropertyType;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_FONT_CHOICE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import static gologolo.GoLogoPropertyType.GOLO_SIZE_CHOICE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import gologolo.data.GoLogoShape;
import gologolo.data.LogoRectangle;
import gologolo.data.MouseLocation;
import gologolo.data.SliderInformation;
import gologolo.transactions.AddImage_Transaction;
import gologolo.transactions.AddShape_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.transactions.AddUnderline_Transaction;
import gologolo.transactions.BoldFont_Transaction;
import gologolo.transactions.ChangeAngle_Transaction;
import gologolo.transactions.ChangeFont_Transaction;
import gologolo.transactions.ChangeSize_Transaction;
import gologolo.transactions.ChangeStop0_Transaction;
import gologolo.transactions.ChangeStop1_Transaction;
import gologolo.transactions.DeleteNode_Transaction;
import gologolo.transactions.DragItem_Transaction;
import gologolo.transactions.EditShape_Transaction;
import gologolo.transactions.EditText_Transaction;
import gologolo.transactions.FocusDistance_Transaction;
import gologolo.transactions.Italics_Transaction;
import gologolo.transactions.ProcessBorderChange_Transaction;
import gologolo.transactions.ProcessCenterY_Transaction;
import gologolo.transactions.ProcessColorChange_Transaction;
import gologolo.transactions.ProcessDecreaseFont_Transaction;
import gologolo.transactions.ProcessIncreaseFont_Transaction;
import gologolo.transactions.ProcessRadi_Transaction;
import gologolo.transactions.ProcessRadius_Transaction;
import gologolo.workspace.dialogs.GoLogoLoDialogs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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
            rect.setType("Rectangle");
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
            GoLogoDataPrototype changedata = data.getSelectedItem();
            Font oldfont = changedata.getText().getFont();
            Font newfont;
            if(changedata.getText().isBold){
                newfont = Font.font(selectedFont, FontWeight.BOLD ,oldfont.getSize());
            }else if(changedata.getText().isItalics){
                newfont = Font.font(selectedFont, FontPosture.ITALIC, oldfont.getSize());
            }else
                newfont = Font.font(selectedFont, oldfont.getSize());
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
             Font newfont;
            if(changedata.getText().isBold) {
                newfont = Font.font(oldfont.getName(), FontWeight.BOLD, size);
            }else
                newfont = Font.font(oldfont.getName(), size);
            System.out.println("Font before: " + oldfont.toString());
            ChangeSize_Transaction trans = new ChangeSize_Transaction(newfont, oldfont, changedata);
            app.processTransaction(trans);
            System.out.println("Font after: suppose to be" + newfont.toString());
            System.out.println("Actual " + changedata.getText().getFont().toString());
           
        }
    }
    
    public void processMouseIntersection(Node node) {
        TableView table = (TableView) app.getGUIModule().getGUINode(GOLO_DATA_PANE);
        Pane pane = (Pane)app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        TableSelectionModel selection = table.getSelectionModel();
        ObservableList<GoLogoDataPrototype> list = table.getItems();
        int i;
        for(i = 0; i<pane.getChildren().size(); i++) {
            if(pane.getChildren().get(i).equals(node))
                break;
        }
        selection.clearSelection();
        selection.select(i);
        
    }
    
    public void processBoldChange() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
           GoLogoDataPrototype changedata = data.getSelectedItem();
           Font oldFont = changedata.getText().getFont();
           Font newFont = Font.font(oldFont.getName(), FontWeight.BOLD, oldFont.getSize());
           changedata.getText().isBold=true;
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
            Font oldfont = changedata.getText().getFont();
            Font newfont = Font.font(oldfont.getName(), FontPosture.ITALIC, oldfont.getSize());
            Italics_Transaction trans = new Italics_Transaction(changedata, oldfont, newfont);
            app.processTransaction(trans);
        }
    }
    
    public void processAngleTrans(Number oldval, Number newval, SliderInformation ov) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         double og = ov.origianl;
         ChangeAngle_Transaction trans = new ChangeAngle_Transaction(change, newAngle, oldAngle, og);
         app.processTransaction(trans);
        }
    }
    
    public void processAngle(Number oldval, Number newval, SliderInformation ov) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
            if(change.getType().equals("Rectangle")) {
                LogoRectangle rect = (LogoRectangle) change.getNode();
                RadialGradient rad = new RadialGradient(newval.doubleValue(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), rect.getRadius(), rect.isProportional(), rect.getCycleMethod(), rect.getStop0(), rect.getStop1());
                rect.setFill(rad);
            }
        }
    }
    
    public void processFocusTran(Number oldval, Number newval, SliderInformation ov) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         double og = ov.origianl;
         FocusDistance_Transaction trans = new FocusDistance_Transaction(change, newAngle, oldAngle, og);
         app.processTransaction(trans);
        }
    }
    
    public void processFocus(Number oldval, Number newval, SliderInformation ov) {
        
    }
    
    public void processCenterX(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         
         ProcessCenterX_Transaction trans = new ProcessCenterX_Transaction(change, newAngle, oldAngle);
         app.processTransaction(trans);
        }
    }
    public void processCenterY(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         
         ProcessCenterY_Transaction trans = new ProcessCenterY_Transaction(change, newAngle, oldAngle);
         app.processTransaction(trans);
        }
    }
    public void processRadius(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         
         ProcessRadius_Transaction trans = new ProcessRadius_Transaction(change, newAngle, oldAngle);
         app.processTransaction(trans);
        }
    }

    public void processCycle(String cycle) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
            ChangeCycle_Transaction trans; 
            if(cycle.equals(CycleMethod.NO_CYCLE)){
              trans = new ChangeCycle_Transaction(change,CycleMethod.NO_CYCLE);
            }else if(cycle.equals(CycleMethod.REFLECT.toString())){
                trans = new ChangeCycle_Transaction(change,CycleMethod.REFLECT);
            }else
                trans = new ChangeCycle_Transaction(change,CycleMethod.REPEAT);
            
            app.processTransaction(trans);
        }
    }

    public void processColor0Change(Color value) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      LogoRectangle rect = (LogoRectangle) change.getNode();
      Color oldColor = rect.getColor0();
      if(change.shape) {
          ChangeStop0_Transaction trans = new ChangeStop0_Transaction(change, value,oldColor);
          app.processTransaction(trans);
      }
    }

    public void processColor1Change(Color value) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          LogoRectangle rect = (LogoRectangle) change.getNode();
          Color oldColor = rect.getColor1();
          ChangeStop1_Transaction trans = new ChangeStop1_Transaction(change, value,oldColor);
          app.processTransaction(trans);
      } 
    }
    
    public void processIncreaseFontSize(){
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(!change.shape) {
          GoLogoDataText text = change.getText();
          ProcessIncreaseFont_Transaction trans = new ProcessIncreaseFont_Transaction(text, app);
          app.processTransaction(trans);
      } 
    }
    
    public void processDecreaseFontSize(){
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(!change.shape) {
          GoLogoDataText text = change.getText();
          ProcessDecreaseFont_Transaction trans = new ProcessDecreaseFont_Transaction(text, app);
          app.processTransaction(trans);
      } 
    }
    
    public void processBorderThicknessTrans(Number newVal) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          if(change.getType().equals("Rectangle")){
              ProcessBorderChange_Transaction trans = new ProcessBorderChange_Transaction(change, newVal.doubleValue(), app);
              app.processTransaction(trans);
            }
        }
    }
    
    public void processBorderThickness(Number newVal) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
         LogoRectangle rect = (LogoRectangle) change.getNode();
         rect.setStrokeWidth(newVal.doubleValue());
      }
    }
    
    public void processColorChange(Color color) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          ProcessColorChange_Transaction trans = new ProcessColorChange_Transaction(change, color,app);
          app.processTransaction(trans);
      }
    }

    public void processRadiusChange(Number oldval, Number newval) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          if(change.getType().equals("Rectangle")) {
              
          }
      }
    }

    public void processRadiusChangeTran(Number oldval, Number newval) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          if(change.getType().equals("Rectangle")) {
            LogoRectangle rect = (LogoRectangle) change.getNode();
            ProcessRadi_Transaction trans = new ProcessRadi_Transaction(change, newval.doubleValue(), app);
            app.processTransaction(trans);
          }
      }
    }
}
