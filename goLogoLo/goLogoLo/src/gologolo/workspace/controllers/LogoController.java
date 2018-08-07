/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.transactions.AddShape_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.workspace.dialogs.GoLogoLoDialogs;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

/**
 *
 * @author akillhalimi
 */
public class LogoController {
    GoLogoLoApp app;
    GoLogoLoDialogs dialogs;
    ObservableList<GoLogoDataPrototype> newDataList;
    Pane pane;
    
    public LogoController(GoLogoLoApp app) {
        this.app = app;
        this.dialogs = new GoLogoLoDialogs(app);
    }
    
    public void processAddRectangle() {
        dialogs.createRectangle();
        GoLogoDataPrototype rect = dialogs.getNewItem();
        GoLogoData data = (GoLogoData)app.getDataComponent();
        AddShape_Transaction transaction = new AddShape_Transaction(data, rect);
        app.processTransaction(transaction);
    }
    
    public void processTriangle() {
        dialogs.createTriangle();
        GoLogoDataPrototype tri = dialogs.getNewItem();
        GoLogoData data = (GoLogoData)app.getDataComponent();
        AddShape_Transaction transaction = new AddShape_Transaction(data, tri);
        app.processTransaction(transaction);
    }
    
    public void processAddText() {
        dialogs.showAddText();
        GoLogoDataPrototype text = dialogs.getNewItem();
        GoLogoData data = (GoLogoData) app.getDataComponent();
        AddText_Transaction trans = new AddText_Transaction(data, text);
        app.processTransaction(trans);
    }
    
}
