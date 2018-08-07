/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 *
 * @author akillhalimi
 */
public class GoLogoDataText extends Label implements Cloneable {
    public static final String DEFAULT_TEXT = "";
    
    final StringProperty text = new SimpleStringProperty(DEFAULT_TEXT);
//    GoLogoDataText() {
//        text = new SimpleStringProperty(DEFAULT_TEXT);
//    }
    GoLogoDataText(String text){
        super(text);
        this.setOnMouseDragged(e->{
            this.setTranslateX(e.getX());
            this.setTranslateY(e.getY());
        });
    }
}
