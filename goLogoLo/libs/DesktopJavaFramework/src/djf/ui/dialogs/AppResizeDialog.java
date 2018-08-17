/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djf.ui.dialogs;

import static djf.AppPropertyType.CANCEL_BUTTON_TEXT;
import static djf.AppPropertyType.OK_BUTTON_TEXT;
import static djf.AppPropertyType.RESIZE_DIALOG_HEADER_TEXT;
import static djf.AppPropertyType.RESIZE_DIALOG_TITLE_TEXT;
import static djf.AppPropertyType.RESIZE_HEIGHT_TEXT;
import static djf.AppPropertyType.RESIZE_WIDTH_TEXT;
import djf.AppTemplate;
import djf.modules.AppLanguageModule;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_PANE;
import static djf.ui.style.DJFStyle.CLASS_DJF_WELCOME_RECENT_PANE;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author akillhalimi
 */
public class AppResizeDialog extends Stage {
    BorderPane pane = new BorderPane();
    HBox topLine = new HBox();
    HBox centerLine = new HBox();
    VBox bottomArea = new VBox();
    Label toptext;
    Label bottomtext;
    Label width;
    HBox heightH;
    HBox widthH;
    HBox buttonHolder = new HBox();
    Button ok = new Button();
    Button cancel = new Button();
   
    public AppResizeDialog(AppTemplate app) {
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String dialogText = props.getProperty(RESIZE_DIALOG_HEADER_TEXT);
        String topString = props.getProperty(RESIZE_DIALOG_TITLE_TEXT);
        String heightString = props.getProperty(RESIZE_HEIGHT_TEXT);
        String widthString = props.getProperty(RESIZE_WIDTH_TEXT);
        
        this.setTitle(dialogText);
        toptext = new Label(topString);
        
        bottomtext = new Label(heightString);
        width = new Label(widthString);
        
        
        
        TextField heightUser = new TextField();
        TextField widthUser = new TextField();
        centerLine.getChildren().add(toptext);
        centerLine.setAlignment(Pos.CENTER);
        heightH = new HBox();
        
        heightH.getChildren().add(bottomtext);
        heightH.getChildren().add(heightUser);
        heightH.setAlignment(Pos.CENTER);
        widthH = new HBox();
        widthH.getChildren().add(width);
        widthH.getChildren().add(widthUser);
        widthH.setAlignment(Pos.CENTER);
        bottomArea.getChildren().add(heightH);
        bottomArea.getChildren().add(widthH);
        String okString = props.getProperty(OK_BUTTON_TEXT);
        ok.setText(okString);
        String cancelString = props.getProperty(CANCEL_BUTTON_TEXT);
        cancel.setText(cancelString);
        
        ok.setOnAction(e->{
            double width = Double.parseDouble(widthUser.getText());
            double height = Double.parseDouble(heightUser.getText());
        //    Pane pane = (Pane)app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
            this.hide();
        });
        cancel.setOnAction(e->{
            this.hide();
        });
        buttonHolder.getChildren().addAll(ok, cancel);
        buttonHolder.setAlignment(Pos.CENTER);
        bottomArea.getChildren().add(buttonHolder);
        pane.setTop(topLine);
        pane.setCenter(centerLine);
        pane.setBottom(bottomArea);
        Scene scene = new Scene(pane);
        this.setScene(scene);
       
    }
}
