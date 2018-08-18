/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialogs;

import djf.modules.AppLanguageModule;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.CANCEL_EDIT_BUTTON;
import static gologolo.GoLogoPropertyType.EDIT_NAME_LABEL;
import static gologolo.GoLogoPropertyType.EDIT_NAME_USER_INPUT;
import static gologolo.GoLogoPropertyType.HBOX_BUTTONS_EDIT;
import static gologolo.GoLogoPropertyType.OK_EDIT_BUTTON;
import gologolo.data.GoLogoDataPrototype;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_BUTTON;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_PANE;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author akillhalimi
 */
public class GoLogoLoEditDialog extends Stage {
    GoLogoLoApp app;
    GridPane editShape;
    Scene editScene;
    Label editText = new Label();
    TextField userInput = new TextField();
    HBox buttons = new HBox();
    Button ok = new Button();
    Button cancel = new Button();
    GoLogoDataPrototype itemToEdit;
    public GoLogoLoEditDialog(GoLogoLoApp app) {
        this.app=app;
        editShape = new GridPane();
        createDialogs();
        editScene = new Scene(editShape);
        this.setScene(editScene);
        app.getGUIModule().initStylesheet(this);
        editScene.getStylesheets().add(CLASS_GOLO_PANE);
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
            editShape.add(node, col, row, colSpan, rowSpan);
            editShape.setHgap(10.0);
        }
        // SETUP IT'S STYLE SHEET
        node.getStyleClass().add(styleClass);
    }
    
    private void createDialogs() {
        createGridNodes(editText, EDIT_NAME_LABEL, null, 0, 1, 1, 1, true);
        createGridNodes(userInput, EDIT_NAME_USER_INPUT, null, 1, 1, 1, 1, false);
        createGridNodes(buttons, HBOX_BUTTONS_EDIT, null, 0, 2, 1, 1, false);
        app.getGUIModule().addGUINode(OK_EDIT_BUTTON, ok);
        app.getGUIModule().addGUINode(CANCEL_EDIT_BUTTON, cancel);
        buttons.getChildren().add(ok);
        buttons.getChildren().add(cancel);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5.0);
        buttons.getStylesheets().add(CLASS_GOLO_DIALOG_BUTTON);
        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(OK_EDIT_BUTTON+"_TEXT", ok.textProperty());
        languageSettings.addLabeledControlProperty(CANCEL_EDIT_BUTTON+"_TEXT", cancel.textProperty());
        
        ok.setOnAction(e->{
           processComplete(); 
        });
        cancel.setOnAction(e->{
            itemToEdit=null;
            userInput.clear();
            this.hide();
        });
    }

    public void showEditShape(GoLogoDataPrototype itemToEdit) {
       this.itemToEdit=itemToEdit;
       userInput.setText(itemToEdit.getName());
       showAndWait();
    }

    public GoLogoDataPrototype getEditingItem() {
        return itemToEdit;
    }

    private void processComplete() {
        String newName = userInput.getText();
        itemToEdit.setName(newName);
        userInput.clear();
        this.hide();
    }
    
}
