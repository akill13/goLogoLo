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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    public boolean isBold;
    public boolean isItalics;
    public double xCoordinate;
    public double yCoordinate;
    Color fillColor;
public GoLogoDataText(String text){
    super(text);
    this.text.setValue(text);
    this.setText(text);
    font = Font.font("Times New Roman", FontWeight.THIN, 12.0);
    this.setFont(font);
    this.setX(100);
    this.setY(100);
}
  public GoLogoDataText(String text, GoLogoLoApp app){
        this(text);
        this.text.set(text);
        this.app=app;
        this.setOnMousePressed(e->{
            MouseLocation.x = e.getSceneX();
            MouseLocation.y = e.getSceneY();
            MouseLocation.origianlx = this.getTranslateX();
            MouseLocation.originaly = this.getTranslateY();
        });
        
        this.setOnMouseDragged(e->{
            double offsetX = e.getSceneX() - MouseLocation.x;
            double offsetY = e.getSceneY() - MouseLocation.y;
            double newTranslateX = MouseLocation.origianlx + offsetX;
            double newTranslateY = MouseLocation.originaly + offsetY;

            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
            this.setOnMouseReleased(x->{
            this.xCoordinate=newTranslateX;
            this.yCoordinate=newTranslateY;
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

    public GoLogoDataText(String text, GoLogoLoApp app, Font font, boolean bold, boolean italics, double x, double y) {
        this(text, app);
        this.font=font;
        if(bold){
            this.setFont(Font.font(font.getName(), FontWeight.BOLD, font.getSize()));
        }
        if(italics){
            this.setFont(Font.font(font.getName(), FontPosture.ITALIC, font.getSize()));
        }
        this.xCoordinate=x;
        this.yCoordinate=y;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    
    public GoLogoDataText(String text, GoLogoLoApp app, Font font, boolean bold, boolean italics, double x, double y, Color color) {
        this(text, app);
        this.font=font;
        if(bold){
            this.setFont(Font.font(font.getName(), FontWeight.BOLD, font.getSize()));
        }
        if(italics){
            this.setFont(Font.font(font.getName(), FontPosture.ITALIC, font.getSize()));
        }
        this.xCoordinate=x;
        this.yCoordinate=y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setFill(color);
    }
    
    public GoLogoDataText clone(String text, GoLogoLoApp app, Font font, boolean isBold, boolean isItalics, double xcoordinate, double ycoordinate){
        return new GoLogoDataText(text, app, font, isBold, isItalics, xcoordinate, ycoordinate);
    }
    
    public void setTextFont(Font newfont){
        this.font=newfont;
        this.setFont(newfont);
    }
    public void setFillColor(Color color) {
        this.fillColor=color;
    }
    public Color getFillColor() {
        return fillColor;
    }
}
