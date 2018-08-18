/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace;


import static djf.AppPropertyType.HAS_HOME;

import static djf.AppPropertyType.HOME_BUTTON;

import static djf.AppPropertyType.OK_BUTTON_TEXT;
import static djf.AppPropertyType.RESIZE_BUTTON;

import static djf.AppPropertyType.ZOOM_IN_BUTTON;
import static djf.AppPropertyType.ZOOM_OUT_BUTTON;
import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BORDER_THICKNESS_SLIDER;
import static gologolo.GoLogoPropertyType.CYCLE;
import static gologolo.GoLogoPropertyType.DEFAULT_CYCLE;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_THICKNESS;
import static gologolo.GoLogoPropertyType.DEFAULT_SCRIPT;
import static gologolo.GoLogoPropertyType.DEFAULT_SIZE;
import static gologolo.GoLogoPropertyType.GOLOR_COLOR_LABEL;
import static gologolo.GoLogoPropertyType.GOLO_ADD_CIRCLE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_DELETE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_IMAGE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_SQUARE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_TEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_TRIANGLE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ANGLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_BAR;
import static gologolo.GoLogoPropertyType.GOLO_BOLD_TEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_PANE;
import static gologolo.GoLogoPropertyType.GOLO_BORDER_RADIUS_BOX;
import static gologolo.GoLogoPropertyType.GOLO_BUTTON_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_BUTTON_PANE;
import static gologolo.GoLogoPropertyType.GOLO_B_B_PANE;
import static gologolo.GoLogoPropertyType.GOLO_B_TEXT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_LABEL_X;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_LABEL_Y;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_X_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_CENTER_Y_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_BOX;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_LABEL;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_0_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_COLOR_STOP_1_PICKER;
import static gologolo.GoLogoPropertyType.GOLO_CYCLE_BOX;
import static gologolo.GoLogoPropertyType.GOLO_CYCLE_METHOD;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_DECREASE_FONT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_DOWN_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_EDIT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_EDIT_TEXT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_DIST_SLIDER;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_LABEL;
import static gologolo.GoLogoPropertyType.GOLO_FOCUS_LABEL_DIST;
import static gologolo.GoLogoPropertyType.GOLO_FONT_CHOICE;
import static gologolo.GoLogoPropertyType.GOLO_GRADIENT_LABEL;
import static gologolo.GoLogoPropertyType.GOLO_GRADIENT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_CANVAS;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import static gologolo.GoLogoPropertyType.GOLO_INCREASE_FONT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ITALICIS_TEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_NAME_COLUMN;
import static gologolo.GoLogoPropertyType.GOLO_ORDER_COLUMN;
import static gologolo.GoLogoPropertyType.GOLO_PANE;
import static gologolo.GoLogoPropertyType.GOLO_PARENT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_RADIUS_LABEL;
import static gologolo.GoLogoPropertyType.GOLO_SIZE_CHOICE;
import static gologolo.GoLogoPropertyType.GOLO_STOP_0_COLOR;
import static gologolo.GoLogoPropertyType.GOLO_STOP_1_COLOR;
import static gologolo.GoLogoPropertyType.GOLO_TYPE_COLUMN;
import static gologolo.GoLogoPropertyType.GOLO_UNDERLINE_TEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_UP_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_USER_TOOLS_PANE;
import static gologolo.GoLogoPropertyType.LIST;
import static gologolo.GoLogoPropertyType.SIZE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.SliderInformation;
import gologolo.workspace.controllers.LogoController;
import static gologolo.workspace.style.gologoloStyle.CLASS_CANVAS;
import static gologolo.workspace.style.gologoloStyle.CLASS_CLEAR;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BOX;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BUTTON;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BUTTON_PANE;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_CANVAS;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_COLUMN;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_PANE;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_TABLE;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_TEXT;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import properties_manager.PropertiesManager;

/**
 *
 * @author akillhalimi
 */
public class GologoWorkspace extends AppWorkspaceComponent {
    SliderInformation angleSlider = new SliderInformation();
    SliderInformation focusSlider = new SliderInformation();
    SliderInformation centerxSlider = new SliderInformation();
    SliderInformation centerySlider = new SliderInformation();
    SliderInformation radiusSlider = new SliderInformation();
    
    public GologoWorkspace(GoLogoLoApp initApp) {
        super(initApp);
        
        createLayout();
    }
    
    private void createLayout() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AppNodesBuilder gologoMaker = app.getGUIModule().getNodesBuilder();
        
        BorderPane gologoPane =  gologoMaker.buildBorderPane(GOLO_PANE,  null, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        VBox left       =  gologoMaker.buildVBox(GOLO_DATA_PANE,         null, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Pane parent     =  gologoMaker.buildPane(GOLO_PARENT_PANE,       null, null, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Pane middle     =  gologoMaker.buildPane(GOLO_IMAGE_PANE,        parent, null, CLASS_CANVAS, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        VBox right      =  gologoMaker.buildVBox(GOLO_USER_TOOLS_PANE,   null, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        middle.setMinSize(600, 700);
        parent.layoutBoundsProperty().addListener((ObservableValue<? extends Bounds> observable, Bounds oldBounds, Bounds bounds) -> {
            parent.setClip(new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight()));
        });
        
        middle.layoutBoundsProperty().addListener((ObservableValue<? extends Bounds> observable, Bounds oldBounds, Bounds bounds) -> {
            middle.setClip(new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight()));
        });
        gologoPane.setLeft(left);
        gologoPane.setRight(right);
        gologoPane.setCenter(parent);
        
         
        TableView<GoLogoDataPrototype> itemsTable  = gologoMaker.buildTableView(GOLO_DATA_PANE,       left,          null,   CLASS_GOLO_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderCategory                  = gologoMaker.buildTableColumn(  GOLO_ORDER_COLUMN,    itemsTable,         CLASS_GOLO_COLUMN);
        TableColumn nameCategory                   = gologoMaker.buildTableColumn(GOLO_NAME_COLUMN, itemsTable, CLASS_GOLO_COLUMN);
        TableColumn typeCategory                   = gologoMaker.buildTableColumn(GOLO_TYPE_COLUMN, itemsTable, CLASS_GOLO_COLUMN);
        
        orderCategory.setCellValueFactory(new PropertyValueFactory<Integer,String>("order"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<String,String>("name"));
        typeCategory.setCellValueFactory(new PropertyValueFactory<String,String>("type"));
       
        HBox tableControls = gologoMaker.buildHBox(    GOLO_BUTTON_DATA_PANE,       left,          null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button up       =  gologoMaker.buildIconButton(GOLO_UP_BUTTON,              tableControls, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button down     =  gologoMaker.buildIconButton(GOLO_DOWN_BUTTON,            tableControls, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button edit     =  gologoMaker.buildIconButton(GOLO_EDIT_BUTTON,            tableControls, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        HBox   buttonPane =  gologoMaker.buildHBox(    GOLO_BUTTON_PANE,            right,      null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addText  =  gologoMaker.buildIconButton(GOLO_ADD_TEXT_BUTTON,        buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addImage =  gologoMaker.buildIconButton(GOLO_ADD_IMAGE_BUTTON,       buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addSquare=  gologoMaker.buildIconButton(GOLO_ADD_SQUARE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addCirlce=  gologoMaker.buildIconButton(GOLO_ADD_CIRCLE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addTri   =  gologoMaker.buildIconButton(GOLO_ADD_TRIANGLE_BUTTON,    buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE,ENABLED);
        Button discard  =  gologoMaker.buildIconButton(GOLO_ADD_DELETE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        HBox textCombo    = gologoMaker.buildHBox(GOLO_B_TEXT_PANE,                 null,   null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        HBox textBCombo   = gologoMaker.buildHBox(GOLO_B_B_PANE,                    null,   null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);

        BorderPane   textPane   = gologoMaker.buildBorderPane(GOLO_EDIT_TEXT_PANE,               right,      null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox listText       = gologoMaker.buildComboBox(GOLO_FONT_CHOICE,             LIST,   DEFAULT_SCRIPT, textCombo, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox fontSize       = gologoMaker.buildComboBox(GOLO_SIZE_CHOICE,             SIZE,   DEFAULT_SIZE, textCombo, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button   boldText       = gologoMaker.buildIconButton(GOLO_BOLD_TEXT_BUTTON,      textBCombo,   null,  CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button   italic         = gologoMaker.buildIconButton(GOLO_ITALICIS_TEXT_BUTTON,  textBCombo,   null, CLASS_GOLO_BUTTON,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button   sanscript      = gologoMaker.buildIconButton(GOLO_DECREASE_FONT_BUTTON,  textBCombo,   null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button   superscript    = gologoMaker.buildIconButton(GOLO_INCREASE_FONT_BUTTON,textBCombo,   null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker underLine      = gologoMaker.buildColorPicker(GOLO_UNDERLINE_TEXT_BUTTON, textBCombo, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        VBox     border          = gologoMaker.buildVBox(GOLO_BORDER_PANE, right, null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label    thicknesslabel  = gologoMaker.buildLabel(GOLO_BORDER_THICKNESS, border, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider   thickness       = gologoMaker.buildSlider(BORDER_THICKNESS_SLIDER, border, null, CLASS_CLEAR, 1, 15, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label    colorlabel      = gologoMaker.buildLabel(GOLOR_COLOR_LABEL, border, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker color        = gologoMaker.buildColorPicker(GOLO_COLOR_BOX, border, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label  radiusLabels      = gologoMaker.buildLabel(GOLO_RADIUS_LABEL, border, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider   radi            = gologoMaker.buildSlider(GOLO_BORDER_RADIUS_BOX, border, null, CLASS_CLEAR, 0, 100, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        VBox colorGrad          = gologoMaker.buildVBox(GOLO_GRADIENT_PANE, right, null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label colorGradLabel    = gologoMaker.buildLabel(GOLO_GRADIENT_LABEL, colorGrad, null, CLASS_GOLO_TEXT, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label colorangelLabel   = gologoMaker.buildLabel(GOLO_FOCUS_LABEL, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider angle            = gologoMaker.buildSlider(GOLO_ANGLE_BOX, colorGrad, null, CLASS_CLEAR, 0, 5, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusdistlabel    = gologoMaker.buildLabel(GOLO_FOCUS_LABEL_DIST, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider focusdist        = gologoMaker.buildSlider(GOLO_FOCUS_DIST_SLIDER, colorGrad, null, CLASS_CLEAR, 0, 5, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerXLabel      = gologoMaker.buildLabel(GOLO_CENTER_LABEL_X, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerX          = gologoMaker.buildSlider(GOLO_CENTER_X_SLIDER, colorGrad, null, CLASS_CLEAR, 0, 5, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerYlabel      = gologoMaker.buildLabel(GOLO_CENTER_LABEL_Y, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider centerY          = gologoMaker.buildSlider(GOLO_CENTER_Y_SLIDER, colorGrad, null, CLASS_CLEAR, 0, 5, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label radiusLabel       = gologoMaker.buildLabel(GOLO_COLOR_RADIUS_LABEL, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider radius           = gologoMaker.buildSlider(GOLO_COLOR_RADIUS_SLIDER, colorGrad, null, CLASS_CLEAR, 0, 5, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label cycleMethod       = gologoMaker.buildLabel(GOLO_CYCLE_METHOD, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox cycleColor     = gologoMaker.buildComboBox(GOLO_CYCLE_BOX, CYCLE, DEFAULT_CYCLE, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label stop0             = gologoMaker.buildLabel(GOLO_STOP_0_COLOR, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop0color  = gologoMaker.buildColorPicker(GOLO_COLOR_STOP_0_PICKER, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label stop1             = gologoMaker.buildLabel(GOLO_STOP_1_COLOR, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop1color  = gologoMaker.buildColorPicker(GOLO_COLOR_STOP_1_PICKER, colorGrad, null, CLASS_CLEAR, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);

        
        textPane.setTop(textCombo);
        textPane.setBottom(textBCombo);
        right.setSpacing(3);
        left.setSpacing(3);
        workspace = new BorderPane();
	((BorderPane)workspace).setCenter(gologoPane);
        
        
        LogoController itemsController = LogoController.getController((GoLogoLoApp) app);
        
        addSquare.setOnAction(e->{
            itemsController.processAddRectangle();
        });
        addText.setOnAction(e->{
            itemsController.processAddText();
        });
        edit.setOnAction(e->{
            itemsController.processEdit();
        });
        discard.setOnAction(e->{
            itemsController.processRemove(middle);
        });
        addImage.setOnAction(e->{
            itemsController.processAddImage();
        });
        listText.setOnAction(e->{
            itemsController.processChangeFont((String) listText.getValue());
        });
        fontSize.setOnAction(e->{
            itemsController.processChangeSize((String) fontSize.getValue());
        });
        middle.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
            if(e.getClickCount()==1 || e.getClickCount()==2){
              Node source = (Node) e.getPickResult().getIntersectedNode();
              itemsController.processMouseIntersection(source);
            }
        });
        boldText.setOnAction(e->{
            itemsController.processBoldChange();
        });
        underLine.setOnAction(e->{
            itemsController.processUnderLineChange((Color)underLine.getValue());
        });
        italic.setOnAction(e->{
            itemsController.processItalicsChange();
        });
        angle.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Number original = ov.getValue();
            angle.setOnMouseDragged(e->{
                itemsController.processAngle(old_val, new_val, angleSlider);
            });
            angle.setOnMouseReleased(e->{
                angleSlider.add(old_val.doubleValue());
                angleSlider.setAdded(true);
                itemsController.processAngleTrans(old_val, new_val, angleSlider);
            });
        });
        focusdist.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) ->{
            focusdist.setOnMouseDragged(e->{
                itemsController.processFocus(old_val, new_val, focusSlider);
            });
            focusdist.setOnMouseReleased(e->{
                itemsController.processFocusTran(old_val, new_val, focusSlider);
            });
        });
        centerX.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            centerX.setOnMouseDragged(e->{
                itemsController.processCenterX(old_val, new_val);
            });
            
            centerX.setOnMouseReleased(e->{
                itemsController.processCenterXTran(old_val, new_val);
            });
        });
        centerY.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            centerY.setOnMouseDragged(e->{
                itemsController.processCenterY(old_val, new_val);
            });
            centerY.setOnMouseReleased(e->{
                itemsController.processCenterY(old_val, new_val);
            });
        });
        radius.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            radius.setOnMouseDragged(e->{
                itemsController.processRadius(old_val, new_val);
            });
            
            radius.setOnMouseReleased(e->{
                itemsController.processRadiusTrans(old_val, new_val);
            });
            
        });
        cycleColor.setOnAction(e->{
            itemsController.processCycle((String) cycleColor.getValue()); 
        });
        stop0color.setOnAction(e->{
            itemsController.processColor0Change(stop0color.getValue());
        });
        stop1color.setOnAction(e->{
            itemsController.processColor1Change(stop1color.getValue());
        });
        superscript.setOnAction(e->{
            itemsController.processIncreaseFontSize();
        });
        sanscript.setOnAction(e->{
            itemsController.processDecreaseFontSize();
        });
        
        thickness.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            thickness.setOnMouseDragged(e->{
                itemsController.processBorderThickness(thickness.getValue());
            });
            thickness.setOnMouseReleased(e->{
                itemsController.processBorderThicknessTrans(thickness.getValue());
            });
        });
        color.setOnAction(e->{
            itemsController.processColorChange((Color)color.getValue());
        });
        radi.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            radi.setOnMouseDragged(e->{
                itemsController.processRadiusChange(old_val, new_val);
            });
            
            radi.setOnMouseReleased(e->{
                itemsController.processRadiusChangeTran(old_val, new_val);
            });
        });
        Button zoomIn = (Button) app.getGUIModule().getGUINode(ZOOM_IN_BUTTON);
        Button zoomOut = (Button) app.getGUIModule().getGUINode(ZOOM_OUT_BUTTON);
        Button reset = (Button) app.getGUIModule().getGUINode(HOME_BUTTON);
        zoomIn.setOnAction(e->{
           middle.setScaleX(middle.getScaleX()*2.0);
           middle.setScaleY(middle.getScaleY()*2.0);
        });
        zoomOut.setOnAction(e->{
            middle.setScaleX(middle.getScaleX()*0.5);
            middle.setScaleY(middle.getScaleY()*0.5);
        });
        reset.setOnAction(e->{
            middle.setScaleX(1.0);
            middle.setScaleY(1.0);
        });
        
        Button resizeButton = (Button) app.getGUIModule().getGUINode(RESIZE_BUTTON);
        resizeButton.setOnAction(e->{
            itemsController.openResizeDialog();
        });
        addCirlce.setOnAction(e->{
            itemsController.processAddCircle();
        });
     }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {

    }
    
}
