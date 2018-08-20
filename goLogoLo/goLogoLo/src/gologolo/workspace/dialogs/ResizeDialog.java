/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialogs;

import djf.modules.AppLanguageModule;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.BUTTON_HBOX;
import static gologolo.GoLogoPropertyType.CANCEL_BUTTON_RESIZE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import static gologolo.GoLogoPropertyType.HEIGHT_RESIZE_TEXT_FIELD;
import static gologolo.GoLogoPropertyType.OK_BUTTON_RESIZE;
import static gologolo.GoLogoPropertyType.RESIZE_HEIGHT;
import static gologolo.GoLogoPropertyType.RESIZE_WIDTH;
import static gologolo.GoLogoPropertyType.WIDTH_RESIZE_TEXT_FIELD;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_DIALOG_PROMPT;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author akillhalimi
 */
public class ResizeDialog extends Stage {
    GoLogoLoApp app;
    GridPane resizePane;
    Scene resize;
    Label height = new Label();
    Label width = new Label();
    TextField heightInput = new TextField();
    TextField widthInput = new TextField();
    Button resizeOk = new Button();
    Button resizeCancel = new Button();
    HBox buttonBox = new HBox();
    public ResizeDialog(GoLogoLoApp app) {
        
        
        resizePane = new GridPane();
        this.app=app;
        createDialogs();
        resize = new Scene(resizePane);
        this.setScene(resize);
        
        
        app.getGUIModule().initStylesheet(this);
        resizePane.getStylesheets().add(CLASS_GOLO_PANE);
        resize.getStylesheets().add(CLASS_GOLO_PANE);
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
    }

    private void createDialogs() {
       createGridNodes(height, RESIZE_HEIGHT, CLASS_GOLO_DIALOG_PROMPT, 0, 1, 1, 1, true);
       createGridNodes(heightInput, HEIGHT_RESIZE_TEXT_FIELD, CLASS_GOLO_DIALOG_PROMPT, 1, 1, 1, 1, false);
       createGridNodes(width, RESIZE_WIDTH, CLASS_GOLO_DIALOG_PROMPT, 0, 2, 1, 1, true);
       createGridNodes(widthInput, WIDTH_RESIZE_TEXT_FIELD, CLASS_GOLO_DIALOG_PROMPT, 1, 2, 1, 1, false);
       createGridNodes(buttonBox, BUTTON_HBOX, CLASS_GOLO_DIALOG_PROMPT, 1, 3, 1, 1, false);
       app.getGUIModule().addGUINode(OK_BUTTON_RESIZE, resizeOk);
       app.getGUIModule().addGUINode(CANCEL_BUTTON_RESIZE, resizeCancel);
       buttonBox.getChildren().add(resizeOk);
       buttonBox.getChildren().add(resizeCancel);
       buttonBox.setAlignment(Pos.CENTER);
       buttonBox.setSpacing(5.0);
        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(OK_BUTTON_RESIZE + "_TEXT",    resizeOk.textProperty());
        languageSettings.addLabeledControlProperty(CANCEL_BUTTON_RESIZE + "_TEXT",    resizeCancel.textProperty());
        resizeOk.setOnAction(e->{
            processComplete();
        });
        resizeCancel.setOnAction(e->{
            this.hide();
        });
        
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
            resizePane.add(node, col, row, colSpan, rowSpan);
            resizePane.setHgap(10.0);
        }
        // SETUP IT'S STYLE SHEET
        node.getStyleClass().add(styleClass);
    }
    
    public void showResize() {
        showAndWait();
    }
    
    public void processComplete() {
        double height = Double.parseDouble(heightInput.getText());
        double width = Double.parseDouble(widthInput.getText());
        heightInput.clear();
        widthInput.clear();
        Pane pane = (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
        pane.setMinSize(width, height);
        this.hide();
    }
}
