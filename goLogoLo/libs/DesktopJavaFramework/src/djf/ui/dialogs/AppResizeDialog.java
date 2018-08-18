/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djf.ui.dialogs;


//import static djf.AppPropertyType.CANCEL_BUTTON_RESIZE;
//import static djf.AppPropertyType.CANCEL_BUTTON_TEXT;
//import static djf.AppPropertyType.OK_BUTTON_RESIZE;
//import static djf.AppPropertyType.OK_BUTTON_RESIZE_TEXT;
//import static djf.AppPropertyType.CANCEL_BUTTON_RESIZE_TEXT;
//import static djf.AppPropertyType.HEIGHT_RESIZE_TEXT_FIELD;
//import static djf.AppPropertyType.OK_BUTTON_TEXT;
//import static djf.AppPropertyType.RESIZE_DIALOG_HEADER_TEXT;
//import static djf.AppPropertyType.RESIZE_DIALOG_TITLE_TEXT;
//import static djf.AppPropertyType.RESIZE_HEIGHT_TEXT;
//import static djf.AppPropertyType.RESIZE_WIDTH_TEXT;
//import static djf.AppPropertyType.WIDTH_RESIZE_TEXT_FIELD;
import djf.AppTemplate;
import djf.modules.AppLanguageModule;
import djf.ui.AppNodesBuilder;
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
    Button ok;
    Button cancel;
   
    public AppResizeDialog(AppTemplate app, AppNodesBuilder builder) {
        
//        PropertiesManager props = PropertiesManager.getPropertiesManager();
//        String dialogText = props.getProperty(RESIZE_DIALOG_HEADER_TEXT);
//        String topString = props.getProperty(RESIZE_DIALOG_TITLE_TEXT);
//        String heightString = props.getProperty(RESIZE_HEIGHT_TEXT);
//        String widthString = props.getProperty(RESIZE_WIDTH_TEXT);
        
//        this.setTitle(dialogText);
//        toptext = new Label(topString);
//        
//        bottomtext = new Label(heightString);
//        width = new Label(widthString);
//        centerLine.getChildren().add(toptext);
//        centerLine.setAlignment(Pos.CENTER);
//        heightH = new HBox();
//        heightH.getChildren().add(bottomtext);
//        heightH.setAlignment(Pos.CENTER);
//        widthH = new HBox();
//        widthH.getChildren().add(width);
//        widthH.setAlignment(Pos.CENTER);
//        
//        TextField heightUser = builder.buildTextField(HEIGHT_RESIZE_TEXT_FIELD, heightH, null, null, true, true, true);
//        TextField widthUser = builder.buildTextField(WIDTH_RESIZE_TEXT_FIELD, widthH, null, null, true, true, true);
//        
//        ok = builder.buildTextButton(OK_BUTTON_RESIZE, buttonHolder, null, null, true, true, true);
//        cancel = builder.buildTextButton(CANCEL_BUTTON_RESIZE, buttonHolder, null, null, true, true, true);
//        String okString = props.getProperty(OK_BUTTON_RESIZE_TEXT);
//        ok.setText(okString);
//        String cancelString = props.getProperty(CANCEL_BUTTON_RESIZE_TEXT);
//        cancel.setText(cancelString);
//        cancel.setOnAction(e->{
//            this.hide();
//        });
//        buttonHolder.setAlignment(Pos.CENTER);
//        bottomArea.getChildren().add(buttonHolder);
//        pane.setTop(topLine);
//        pane.setCenter(centerLine);
//        pane.setBottom(bottomArea);
//        
//        Scene scene = new Scene(pane);
//        this.setScene(scene);
//       
    }
}
