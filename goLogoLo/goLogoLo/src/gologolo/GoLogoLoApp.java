/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo;

import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.components.AppWorkspaceComponent;
import gologolo.appclipboard.GoLogoClipboard;
import gologolo.data.GoLogoData;
import gologolo.files.GoLogoFiles;
import gologolo.workspace.GologoWorkspace;
import java.util.Locale;

/**
 *
 * @author akillhalimi
 */
public class GoLogoLoApp extends AppTemplate {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Locale.setDefault(Locale.US);
        launch(args);
    }
    

    @Override
    public AppClipboardComponent buildClipboardComponent(AppTemplate app) {
        return new GoLogoClipboard(this); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppDataComponent buildDataComponent(AppTemplate app) {
        return new GoLogoData(this); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppFileComponent buildFileComponent() {
        return new GoLogoFiles(this); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppWorkspaceComponent buildWorkspaceComponent(AppTemplate app) {
        return new GologoWorkspace(this);
    }
    
}
