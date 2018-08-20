/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import gologolo.transactions.ProcessCenterX_Transaction;
import gologolo.transactions.ChangeCycle_Transaction;
import djf.AppPropertyType;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_ANGLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_RADIUS_BOX;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_X_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_Y_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_BOX;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_0_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_1_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_CYCLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_DIST_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_FONT_CHOICE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import static gologolo.GoLogoPropertyType.GOLO_SIZE_CHOICE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import gologolo.data.GoLogoImageData;
import gologolo.data.GoLogoShape;
import gologolo.data.LogoCircle;
import gologolo.data.LogoRectangle;
import gologolo.data.MouseLocation;
import gologolo.data.SliderInformation;
import gologolo.transactions.AddImage_Transaction;
import gologolo.transactions.AddShape_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.transactions.AddColor_Transaction;
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
import gologolo.transactions.MoveItem_Transaction;
import gologolo.transactions.ProcessBorderChange_Transaction;
import gologolo.transactions.ProcessCenterY_Transaction;
import gologolo.transactions.ProcessColorChange_Transaction;
import gologolo.transactions.ProcessDecreaseFont_Transaction;
import gologolo.transactions.ProcessIncreaseFont_Transaction;
import gologolo.transactions.ProcessRadi_Transaction;
import gologolo.transactions.ProcessRadius_Transaction;
import gologolo.workspace.dialogs.GoLogoLoDialogs;
import gologolo.workspace.dialogs.GoLogoLoEditDialog;
import gologolo.workspace.dialogs.ResizeDialog;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
    ResizeDialog resizeDialog;
    ObservableList<GoLogoDataPrototype> newDataList;
    Pane pane;
    MouseLocation location  = new MouseLocation();
    GoLogoLoDialogs shape;
    private static LogoController singleton = null;
    boolean selected;
    GoLogoLoEditDialog shapeEdit;
    private LogoController(GoLogoLoApp app) {
        this.app = app;
        this.dialogs = new GoLogoLoDialogs(app);
        pane =  (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        this.shape = new GoLogoLoDialogs(app, true);
        this.resizeDialog = new ResizeDialog(app);
        shapeEdit = new GoLogoLoEditDialog(app);
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
        if(!GoLogoLoDialogs.cancelDispatched){
            GoLogoDataPrototype text = dialogs.getNewItem();
            if(!text.shape){
                text.setType("Label");
            }
            GoLogoData data = (GoLogoData) app.getDataComponent();
            AddText_Transaction trans = new AddText_Transaction(data, text);
            app.processTransaction(trans);
        }
        GoLogoLoDialogs.cancelDispatched=false;
        app.getFoolproofModule().updateAll();
    }
    
    public void processEdit(){
        GoLogoData data = (GoLogoData) app.getDataComponent();
        if(data.isItemSelected()){
            GoLogoDataPrototype itemToEdit = data.getSelectedItem();
            if(!itemToEdit.shape){ 
                dialogs.showEditText(itemToEdit);
                GoLogoDataPrototype edit = dialogs.getEditingItem();
                String newName = dialogs.getNewName();
                String newText = dialogs.getNewText();
                if(edit != null){
                    EditText_Transaction trans = new EditText_Transaction(itemToEdit.getName(), itemToEdit.getText().getText(), newName, newText, itemToEdit);
                    app.processTransaction(trans);
                }
            }else{
                shapeEdit.showEditShape(itemToEdit);
                GoLogoDataPrototype edit = shapeEdit.getEditingItem();
            if(edit != null){
                EditShape_Transaction trans = new EditShape_Transaction(itemToEdit.getName(), edit.getName(), edit);
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
        String path = file.getAbsolutePath();
            FileInputStream imageLocation;
        try {
            imageLocation = new FileInputStream(file);
            Image image = new Image(imageLocation);
            GoLogoImageData node = new GoLogoImageData(image,app);
            node.setPath(path);
            GoLogoDataPrototype data = new GoLogoDataPrototype(node);
            data.imageNode=image;
            data.setType("Image");
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
            ChangeSize_Transaction trans = new ChangeSize_Transaction(newfont, oldfont, changedata, app);
            app.processTransaction(trans);
           
        }
    }
    
    public void processMouseIntersection(Node node) {
        TableView table = (TableView) app.getGUIModule().getGUINode(GOLO_DATA_PANE);
        Pane pane = (Pane)app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        TableSelectionModel selection = table.getSelectionModel();
        ObservableList<GoLogoDataPrototype> list = table.getItems();
        GoLogoData data = (GoLogoData) app.getDataComponent();
        int i;
        for(i = 0; i<pane.getChildren().size(); i++) {
            if(pane.getChildren().get(i).equals(node))
                break;
        }
        selection.clearSelection();
        selection.select(i);
        if(node instanceof LogoCircle) {
            LogoCircle cy = (LogoCircle)node;
            Slider thickness = (Slider) app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
            ColorPicker color = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
            Slider angle = (Slider) app.getGUIModule().getGUINode(GOLO_ANGLE_BOX);
            Slider focus = (Slider) app.getGUIModule().getGUINode(GOLO_FOCUS_DIST_SLIDER);
            Slider centerXS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_X_SLIDER);
            Slider centerYS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_Y_SLIDER);
            Slider radiGrad = (Slider) app.getGUIModule().getGUINode(GOLO_COLOR_RADIUS_SLIDER);
            ComboBox cycleMethodC = (ComboBox) app.getGUIModule().getGUINode(GOLO_CYCLE_BOX);
            ColorPicker stop0C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_0_PICKER);
            ColorPicker stop1C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_1_PICKER);
            
            thickness.setValue(cy.strokeWidthProperty().doubleValue());
            color.setValue(cy.getStrokeColor());
            angle.setValue(cy.getFocusAngle());
            focus.setValue(cy.getFocusDistance());
            centerXS.setValue(cy.getCenterX());
            centerYS.setValue(cy.getCenterY());
            radiGrad.setValue(cy.getRadiusGrad());
            cycleMethodC.setValue(cy.getCycleMethod().toString());
            stop0C.setValue(cy.getColor0());
            stop1C.setValue(cy.getColor1());
        }else if(node instanceof LogoRectangle) {
            LogoRectangle cy = (LogoRectangle)node;
            Slider thickness = (Slider) app.getGUIModule().getGUINode(BORDER_THICKNESS_SLIDER);
            Slider radius = (Slider) app.getGUIModule().getGUINode(GOLO_BORDER_RADIUS_BOX);
            ColorPicker color = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
            Slider angle = (Slider) app.getGUIModule().getGUINode(GOLO_ANGLE_BOX);
            Slider focus = (Slider) app.getGUIModule().getGUINode(GOLO_FOCUS_DIST_SLIDER);
            Slider centerXS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_X_SLIDER);
            Slider centerYS = (Slider) app.getGUIModule().getGUINode(GOLO_CENTER_Y_SLIDER);
            Slider radiGrad = (Slider) app.getGUIModule().getGUINode(GOLO_COLOR_RADIUS_SLIDER);
            ComboBox cycleMethodC = (ComboBox) app.getGUIModule().getGUINode(GOLO_CYCLE_BOX);
            ColorPicker stop0C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_0_PICKER);
            ColorPicker stop1C = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_STOP_1_PICKER);
            
            thickness.setValue(cy.strokeWidthProperty().doubleValue());
            color.setValue(cy.getStrokeColor());
            angle.setValue(cy.getFocusAngle());
            focus.setValue(cy.getFocusDistance());
            centerXS.setValue(cy.getCenterX());
            centerYS.setValue(cy.getCenterY());
            radiGrad.setValue(cy.getRadius());
            cycleMethodC.setValue(cy.getCycleMethod().toString());
            stop0C.setValue(cy.getColor0());
            stop1C.setValue(cy.getColor1());
            radius.setValue(cy.getRadi());
        }else if(node instanceof GoLogoDataText) {
            GoLogoDataText cy =(GoLogoDataText)node;
            ComboBox size = (ComboBox) app.getGUIModule().getGUINode(GOLO_SIZE_CHOICE);
            ComboBox font = (ComboBox) app.getGUIModule().getGUINode(GOLO_FONT_CHOICE);
            ColorPicker clor = (ColorPicker) app.getGUIModule().getGUINode(GOLO_COLOR_BOX);
            clor.setValue(cy.getFillColor());
            size.setValue(Double.toString(cy.getFont().getSize()));
            font.setValue((cy.getFont().getName()));
        }
        app.getFoolproofModule().updateAll();
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
    
    public void processUnderLineChange(Color newColor) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.getSelectedItem().getType().equals("Label")) {
            GoLogoDataPrototype changedata = data.getSelectedItem();
            AddColor_Transaction trans = new AddColor_Transaction(changedata,app,newColor);
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
         ChangeAngle_Transaction trans = new ChangeAngle_Transaction(change, newAngle, app);
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
            }else if(change.getType().equals("Circle")) {
                LogoCircle circle = (LogoCircle) change.getNode();
                RadialGradient rad = new RadialGradient(newval.doubleValue(), circle.getFocusDistance(), circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
                circle.setRad(rad);
            }
        }
    }
    
    public void processFocusTran(Number oldval, Number newval, SliderInformation ov) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newValue = newval.doubleValue();
         FocusDistance_Transaction trans = new FocusDistance_Transaction(change, newValue, app);
         app.processTransaction(trans);
        }
    }
    
    public void processFocus(Number oldval, Number newval, SliderInformation ov) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         if(change.getType().equals("Rectangle")) {
             LogoRectangle rect = (LogoRectangle) change.getNode();
             RadialGradient rad = new RadialGradient(rect.getFocusDistance(), newAngle, rect.getCenterX(), rect.getCenterY(), rect.getRadius(), rect.isProportional(), rect.getCycleMethod(), rect.getStop0(), rect.getStop1());
             rect.setFill(rad);
         }else if(change.getType().equals("Circle")) {
             LogoCircle circle = (LogoCircle) change.getNode();
             RadialGradient rad = new RadialGradient(circle.getFocusDistance(), newAngle, circle.getCenterX(), circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
             circle.setFill(rad);
         }
         
        }
    }
    
    public void processCenterXTran(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         ProcessCenterX_Transaction trans = new ProcessCenterX_Transaction(change, newAngle, app);
         app.processTransaction(trans);
        }
    }
    
    public void processCenterX(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
            if(change.getType().equals("Rectangle")) {
             LogoRectangle rect = (LogoRectangle) change.getNode();
             RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), newAngle, rect.getCenterY(), rect.getRadius(), rect.isProportional(), rect.getCycleMethod(), rect.getStop0(), rect.getStop1());
             rect.setFill(rad);
            }else if(change.getType().equals("Circle")) {
                LogoCircle circle = (LogoCircle)change.getNode();
                RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), newAngle, circle.getCenterY(), circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
                circle.setFill(rad);
            }
        }
    }
    public void processCenterYTran(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         ProcessCenterY_Transaction trans = new ProcessCenterY_Transaction(change, newAngle, app);
         app.processTransaction(trans);
        }
    }
    
    public void processCenterY(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         if(change.getType().equals("Rectangle")) {
             LogoRectangle rect = (LogoRectangle) change.getNode();
             RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(),newAngle, rect.getRadius(), rect.isProportional(), rect.getCycleMethod(), rect.getStop0(), rect.getStop1());
             rect.setFill(rad);
         }else if(change.getType().equals("Circle")) {
              LogoCircle circle = (LogoCircle)change.getNode();
              RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), circle.getCenterX(), newAngle, circle.getRadiusGrad(), circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
              circle.setFill(rad);
         }
        }
    }
    
    public void processRadiusTrans(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
         double newAngle = newval.doubleValue();
         double oldAngle = oldval.doubleValue();
         
         ProcessRadius_Transaction trans = new ProcessRadius_Transaction(change, newAngle, app);
         app.processTransaction(trans);
        }
    }
    
    public void processRadius(Number oldval, Number newval) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
            double newradius = newval.doubleValue();
            if(change.getType().equals("Circle")) {
                LogoCircle circle = (LogoCircle)change.getNode();
                RadialGradient rad = new RadialGradient(circle.getFocusAngle(), circle.getFocusDistance(), circle.getCenterX(), circle.getCenterY(), newradius, circle.isProportional(), circle.getCycleMethod(), circle.getStop0(), circle.getStop1());
                circle.setRad(rad);
            }else if(change.getType().equals("Rectangle")) {
                LogoRectangle rect = (LogoRectangle) change.getNode();
                RadialGradient rad = new RadialGradient(rect.getFocusAngle(), rect.getFocusDistance(), rect.getCenterX(), rect.getCenterY(), newradius, rect.isProportional(), rect.getCycleMethod(), rect.getStop0(), rect.getStop1());
                rect.setRad(rad);
            }
        }
    }

    public void processCycle(String cycle) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        GoLogoDataPrototype change = data.getSelectedItem();
        if(change.shape) {
            ChangeCycle_Transaction trans; 
            if(cycle.equals(CycleMethod.NO_CYCLE)){
              trans = new ChangeCycle_Transaction(change,CycleMethod.NO_CYCLE, app);
            }else if(cycle.equals(CycleMethod.REFLECT.toString())){
                trans = new ChangeCycle_Transaction(change,CycleMethod.REFLECT, app);
            }else
                trans = new ChangeCycle_Transaction(change,CycleMethod.REPEAT, app);
            app.processTransaction(trans);
        }
    }

    public void processColor0Change(Color value) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
            ChangeStop0_Transaction trans = new ChangeStop0_Transaction(change, value, app);
            app.processTransaction(trans);
      }
    }

    public void processColor1Change(Color value) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          ChangeStop1_Transaction trans = new ChangeStop1_Transaction(change, value, app);
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
            }else if(change.getType().equals("Circle")) {
            ProcessBorderChange_Transaction trans = new ProcessBorderChange_Transaction(change, newVal.doubleValue(), app);
            app.processTransaction(trans);
            }
        }
    }
    
    public void processBorderThickness(Number newVal) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
          if(change.getType().equals("Rectangle")) {
              LogoRectangle rect = (LogoRectangle) change.getNode();
              rect.setStrokeWidth(newVal.doubleValue());
          }else{
              LogoCircle circle = (LogoCircle)change.getNode();
              circle.setStrokeWidth(newVal.doubleValue());
          }
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
              LogoRectangle rect = (LogoRectangle) change.getNode();
              rect.setArcHeight(newval.doubleValue());
              rect.setArcWidth(newval.doubleValue());
            }
        }
    }

    public void processRadiusChangeTran(Number oldval, Number newval) {
      GoLogoData data = (GoLogoData)app.getDataComponent();
      GoLogoDataPrototype change = data.getSelectedItem();
      if(change.shape) {
            ProcessRadi_Transaction trans = new ProcessRadi_Transaction(change, newval.doubleValue(), app);
            app.processTransaction(trans);
            
            app.getFoolproofModule().updateAll();
      }
    }

    public void openResizeDialog() {
       resizeDialog.showResize();
    }

    public void processAddCircle() {
        dialogs.createCircle(app);
        GoLogoDataPrototype circle = dialogs.getNewItem();
        if(circle.shape)
            circle.setType("Circle");
        GoLogoData data = (GoLogoData)app.getDataComponent();
        AddShape_Transaction transaction = new AddShape_Transaction(data, circle);
        app.processTransaction(transaction);
        
        app.getFoolproofModule().updateAll();
    }

    public void processMoveUp() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.isItemSelected()){
            GoLogoDataPrototype change = data.getSelectedItem();
            int oldIndex = data.getItemIndex(change);
            if(oldIndex>0){
               MoveItem_Transaction trans = new MoveItem_Transaction(data, oldIndex, oldIndex-1);
               app.processTransaction(trans);
               
               data.selectItem(change);
               
               app.getFoolproofModule().updateAll();
            }
        }      
    }

    public void processMoveDown() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.isItemSelected()){
            GoLogoDataPrototype change = data.getSelectedItem();
            int oldIndex = data.getItemIndex(change);
            if(oldIndex < (data.getNumItems()-1)){
               MoveItem_Transaction trans = new MoveItem_Transaction(data, oldIndex, oldIndex+1);
               app.processTransaction(trans);
               
               data.selectItem(change);
               
               app.getFoolproofModule().updateAll();
            }
        }
    }
    
    public GoLogoLoApp getApp() {
        return app;
    }
}
