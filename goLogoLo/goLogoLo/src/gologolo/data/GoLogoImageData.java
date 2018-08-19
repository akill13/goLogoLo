/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import gologolo.GoLogoLoApp;
import gologolo.transactions.DragItem_Transaction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author akillhalimi
 */
public class GoLogoImageData extends ImageView {
    double xCoordinates;
    double yCoodinates;
    GoLogoLoApp app;
    String path;
    public GoLogoImageData(Image node, GoLogoLoApp app) {
       super(node);
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
                this.xCoordinates=newTranslateX;
                this.yCoodinates=newTranslateY;
                DragItem_Transaction trans = new DragItem_Transaction(newTranslateX, newTranslateY, MouseLocation.origianlx, MouseLocation.originaly, app, this);
                app.processTransaction(trans);
            });
        });
    }
    public GoLogoImageData(String url) {
        super(url);
    }
    public void setPath(String path) {
        this.path=path;
    }
    public String getPath(){
        return path;
    }
}
