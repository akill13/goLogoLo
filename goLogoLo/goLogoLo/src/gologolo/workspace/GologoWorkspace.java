/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace;

import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_ADD_CIRCLE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_DELETE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_IMAGE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_SQUARE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_TEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_ADD_TRIANGLE_BUTTON;
import static gologolo.GoLogoPropertyType.GOLO_BUTTON_PANE;
import static gologolo.GoLogoPropertyType.GOLO_DATA_PANE;
import static gologolo.GoLogoPropertyType.GOLO_EDIT_TEXT_PANE;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_CANVAS;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import static gologolo.GoLogoPropertyType.GOLO_PANE;
import static gologolo.GoLogoPropertyType.GOLO_USER_TOOLS_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BOX;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BUTTON;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_BUTTON_PANE;
import static gologolo.workspace.style.gologoloStyle.CLASS_GOLO_CANVAS;
import static gologolo.workspace.style.gologoloStyle.CLASS_TDLM_TABLE;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author akillhalimi
 */
public class GologoWorkspace extends AppWorkspaceComponent {

    public GologoWorkspace(GoLogoLoApp initApp) {
        super(initApp);
        
        createLayout();
    }
    
    private void createLayout() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AppNodesBuilder gologoMaker = app.getGUIModule().getNodesBuilder();
        
        HBox gologoPane =  gologoMaker.buildHBox(GOLO_PANE,              null,       null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        VBox left       =  gologoMaker.buildVBox(GOLO_DATA_PANE,         gologoPane, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        VBox middle     =  gologoMaker.buildVBox(GOLO_IMAGE_PANE,        gologoPane, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        VBox right      =  gologoMaker.buildVBox(GOLO_USER_TOOLS_PANE,   gologoPane, null, CLASS_GOLO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        TableView<GoLogoData> itemsTable  = gologoMaker.buildTableView(GOLO_DATA_PANE,       left,          null,   CLASS_TDLM_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        
        BorderPane canvas  = gologoMaker.buildBorderPane(GOLO_IMAGE_CANVAS,         middle,     null, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        canvas.setCenter(middle);
        HBox buttonPane =  gologoMaker.buildHBox(      GOLO_BUTTON_PANE,            right,      null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addText  =  gologoMaker.buildIconButton(GOLO_ADD_TEXT_BUTTON,        buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addImage =  gologoMaker.buildIconButton(GOLO_ADD_IMAGE_BUTTON,       buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addSquare=  gologoMaker.buildIconButton(GOLO_ADD_SQUARE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addCirlce=  gologoMaker.buildIconButton(GOLO_ADD_CIRCLE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button addTri   =  gologoMaker.buildIconButton(GOLO_ADD_TRIANGLE_BUTTON,    buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE,ENABLED);
        Button discard  =  gologoMaker.buildIconButton(GOLO_ADD_DELETE_BUTTON,      buttonPane, null, CLASS_GOLO_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, DISABLED);
        
        HBox   textPane =  gologoMaker.buildHBox(GOLO_EDIT_TEXT_PANE,               right,      null, CLASS_GOLO_BUTTON_PANE, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        right.setSpacing(15);
        left.setSpacing(15);
        workspace = new BorderPane();
	((BorderPane)workspace).setCenter(gologoPane);
    }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
