/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djf.ui.controllers;

import djf.AppTemplate;
import djf.ui.AppNodesBuilder;
import djf.ui.dialogs.AppDialogsFacade;

/**
 *
 * @author akillhalimi
 */
public class AppImageController {
    private AppTemplate app;
    private AppNodesBuilder builder;
    public AppImageController(AppTemplate app, AppNodesBuilder builder) {
        this.app = app;
        this.builder=builder;
    }
    public void processResizeRequest(){
       AppDialogsFacade.showResizeDialog(app, builder);
      
    }
}
