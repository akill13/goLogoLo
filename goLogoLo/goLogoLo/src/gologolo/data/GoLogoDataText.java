/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import gologolo.transactions.DragItem_Transaction;
import gologolo.workspace.controllers.LogoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author akillhalimi
 */
public class GoLogoDataText extends Text implements Cloneable {
    public static final String DEFAULT_TEXT = "";
    LogoController controlls = LogoController.getController();
    StringProperty text = new SimpleStringProperty(DEFAULT_TEXT);
    Font font;
GoLogoLoApp app;
public GoLogoDataText(String text){
    super(text);
    this.text.setValue(text);
}
  public GoLogoDataText(String text, GoLogoLoApp app){
        this(text);
        this.text.set(text);
        this.app=app;
        this.setOnMousePressed(e->{
//            this.clearSelected();
//            rec.getStyleClass().add(CLASS_LOGO_RECTANGLES);
//            this.selectItem(items.get(nodes.indexOf(rec)));
            MouseLocation.x = e.getSceneX();
            MouseLocation.y = e.getSceneY();
            MouseLocation.origianlx = this.getTranslateX();
            MouseLocation.originaly = this.getTranslateY();
        });
//        rectangle.setOnMouseClicked(e->{
//            this.clearSelected();
//        });
        
        this.setOnMouseDragged(e->{
            double offsetX = e.getSceneX() - MouseLocation.x;
            double offsetY = e.getSceneY() - MouseLocation.y;
            double newTranslateX = MouseLocation.origianlx + offsetX;
            double newTranslateY = MouseLocation.originaly + offsetY;

            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
            this.setOnMouseReleased(x->{
                
                 DragItem_Transaction trans = new DragItem_Transaction(newTranslateX, newTranslateY, MouseLocation.origianlx, MouseLocation.originaly, app, this);
                app.processTransaction(trans);
            });
        });
        this.setOnMouseClicked(e->{
            if(e.getClickCount()==2){
                controlls.processEdit(this);
            }
        });
    }
    
    public GoLogoDataText clone(String text, GoLogoLoApp app){
        return new GoLogoDataText(text, app);
    }
    
    public void setterofFont(Font newfont){
        this.font=newfont;
        this.setFont(font);
    }
}
