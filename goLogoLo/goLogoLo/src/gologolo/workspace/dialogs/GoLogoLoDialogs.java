/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialogs;


import djf.modules.AppLanguageModule;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_HEADER_ADD_TXT;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_DIALOG_BUTTON_PANE;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_DIALOG_CANCEL_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_DIALOG_EDIT_HEADER_TEXT;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_DIALOG_OK_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_NAME;
import static gologolo.GoLogoPropertyType.GOLO_ITEM_NAME_PANE;
import static gologolo.GoLogoPropertyType.GOLO_NAME_TEXT_FEILD;
import static gologolo.GoLogoPropertyType.GOLO_TEXT_FEILD;
import static gologolo.GoLogoPropertyType.GOLO_TEXT_LABEL;
import static gologolo.GoLogoPropertyType.OK_BUTTON_RESIZE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_BUTTON;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_HEADER;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_PANE;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_PROMPT;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_PANE;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author akillhalimi
 */
public class GoLogoLoDialogs extends Stage {
    GoLogoLoApp app;
    GridPane pane;
    GridPane editShape;
    Scene editScene;
    Label editName = new Label();
    TextField uiEdit = new TextField();
    HBox editButtons = new HBox();
    Button editOk = new Button();
    Button editCancel = new Button();
    
    Scene scene;
    Label header = new Label();
    Label textLabel = new Label();
    Button ok = new Button();
    Button cancel = new Button();
    HBox okCancel = new HBox();
    HBox namelabe = new HBox();
    Label name = new Label();
    TextField nameArea = new TextField();
    TextField textTextField = new TextField();
    GoLogoDataPrototype newData;
    boolean editing;
    GoLogoDataPrototype editingItem;
    GoLogoDataPrototype editItem;
    String newName;
    String newText;
    
    public GoLogoLoDialogs(GoLogoLoApp app) {
        this.app = app;
        pane = new GridPane();
        pane.getStylesheets().add(CLASS_GOLO_PANE);
        editShape = new GridPane();
        createDialogs();
        scene = new Scene(pane);
        this.setScene(scene);
        editScene = new Scene(editShape);
        
        app.getGUIModule().initStylesheet(this);
        scene.getStylesheets().add(CLASS_GOLO_PANE);
        editScene.getStylesheets().add(CLASS_GOLO_PANE);
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
        
    }
    
    public GoLogoLoDialogs(GoLogoLoApp app, boolean shape) {
        this.app = app;
        pane = new GridPane();
        pane.getStylesheets().add(CLASS_GOLO_PANE);
        
        scene = new Scene(pane);
        this.setScene(scene);
        
        app.getGUIModule().initStylesheet(this);
        scene.getStylesheets().add(CLASS_GOLO_PANE);
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
    }
    
    protected void createGridNodes(Node node, Object nodeId, String styleClass, int col, int row, int colSpan, int rowSpan, boolean isLanguageDependent) {
        AppLanguageModule langSettings = app.getLanguageModule();
        if(isLanguageDependent) {
            langSettings.addLabeledControlProperty(nodeId + "_TEXT", ((Labeled)node).textProperty());
            ((Labeled)node).setTooltip(new Tooltip(""));
            langSettings.addLabeledControlProperty(nodeId + "_TOOLTIP", ((Labeled)node).tooltipProperty().get().textProperty());
        }
          // PUT IT IN THE UI
        if (col >= 0){
            pane.add(node, col, row, colSpan, rowSpan);
            pane.setHgap(10.0);
        }
        // SETUP IT'S STYLE SHEET
        node.getStyleClass().add(styleClass);
    }
    
    private void createDialogs() {
        createGridNodes(header, GOLO_HEADER_ADD_TXT,  CLASS_GOLO_DIALOG_HEADER,   0,  0,  3,  1,  true);
        createGridNodes(textLabel, GOLO_TEXT_LABEL, CLASS_GOLO_DIALOG_PROMPT,      0,  1,  1,  1,  true);
        createGridNodes(textTextField, GOLO_TEXT_FEILD, CLASS_GOLO_DIALOG_PROMPT,  1,  1,  1,  1,  false);
        createGridNodes(namelabe,GOLO_ITEM_NAME_PANE, CLASS_GOLO_DIALOG_PROMPT, 0, 2, 3, 1, false);
        createGridNodes(okCancel, GOLO_ITEM_DIALOG_BUTTON_PANE, CLASS_GOLO_DIALOG_PROMPT, 0, 3, 3, 1, false);
        app.getGUIModule().addGUINode(GOLO_ITEM_DIALOG_OK_BUTTON, ok);
        app.getGUIModule().addGUINode(GOLO_ITEM_DIALOG_CANCEL_BUTTON, cancel);
        app.getGUIModule().addGUINode(GOLO_ITEM_NAME, name);
        app.getGUIModule().addGUINode(GOLO_NAME_TEXT_FEILD, nameArea);
        ok.getStyleClass().add(CLASS_GOLO_DIALOG_BUTTON);
        pane.getStyleClass().add(CLASS_GOLO_PANE);
        cancel.getStyleClass().add(CLASS_GOLO_DIALOG_BUTTON);
        okCancel.getChildren().add(ok);
        okCancel.getChildren().add(cancel);
        okCancel.setAlignment(Pos.CENTER);
        okCancel.setSpacing(5.0);
        namelabe.getChildren().addAll(name, nameArea);
        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(GOLO_ITEM_DIALOG_OK_BUTTON + "_TEXT",    ok.textProperty());
        languageSettings.addLabeledControlProperty(GOLO_ITEM_DIALOG_CANCEL_BUTTON + "_TEXT",    cancel.textProperty());
        languageSettings.addLabeledControlProperty(GOLO_ITEM_NAME + "_TEXT", name.textProperty());
        textTextField.setOnAction(e->{
            processComplete();
        });

        ok.setOnAction(e->{
            processComplete();
        });
        cancel.setOnAction(e->{
            editItem = null;
            newData = null;
            this.hide();
        });
    }
    
    public Rectangle createRectangle(GoLogoLoApp app) {
        newData = new GoLogoDataPrototype();
        newData = newData.buildDefaultRectangle(app);
       return (Rectangle) newData.getNode();
    }
    
    public Polygon createTriangle() {
        newData = new GoLogoDataPrototype();
        newData = newData.buildDefaultTriangle();
        return (Polygon) newData.getNode();
    }
    
    public GoLogoDataPrototype getNewItem() {
        return newData;
    }
    
    public void showAddText() {
        PropertiesManager pros = PropertiesManager.getPropertiesManager();
        String headerText = pros.getProperty(GOLO_HEADER_ADD_TXT);
        header.setText(headerText);
        setTitle(headerText);
        textTextField.setText("");
        nameArea.setText("");
        editing = false;
        editingItem = null;
        showAndWait();
    }
    public void showEditText(GoLogoDataPrototype editingItem){
        this.setEditingItem(editingItem);
        PropertiesManager pros = PropertiesManager.getPropertiesManager();
        String headerText = pros.getProperty(GOLO_ITEM_DIALOG_EDIT_HEADER_TEXT);
        header.setText(headerText);
        
        editing = true;
        editItem =null;
        
        nameArea.setText(editingItem.getName());
        textTextField.setText(editingItem.getText().getText());
        
        showAndWait();
        
        newName = nameArea.getText();
        newText = textTextField.getText();
    }
    
    public void processComplete() {
        String text = textTextField.getText();
        String name = nameArea.getText();
        if(editing){
            editingItem.setText(text);
            editingItem.setName(name);
        }else{
            this.makeNewItem();
        }
        this.hide();
    }
    
    private void makeNewItem() {
        String text = textTextField.getText();
        String name = nameArea.getText();
        newData = new GoLogoDataPrototype();
        newData = newData.buildDefaultText(text, app);
        newData.setName(name);
        this.hide();
    }
    
    public void setEditingItem(GoLogoDataPrototype editingItem) {
        this.editingItem=editingItem;
    }
    public GoLogoDataPrototype getEditingItem(){
        return editingItem;
    }

    public String getNewName() {
       return newName;
    }

    public String getNewText() {
        return newText;
    }

    public Circle createCircle(GoLogoLoApp app) {
        newData = new GoLogoDataPrototype();
        newData = newData.buildDefaultCircle(app);
        return (Circle) newData.getNode();
    }
}
