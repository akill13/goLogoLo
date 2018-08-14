/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.appclipboard;

import djf.components.AppClipboardComponent;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.transactions.CutItem_Transcation;
import gologolo.transactions.PasteItem_Transaction;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author akillhalimi
 */
public class GoLogoClipboard implements AppClipboardComponent {
    GoLogoLoApp app;
    ArrayList<GoLogoDataPrototype> clipboardCutItems;
    ArrayList<Node> clipboardNodes;
    ArrayList<GoLogoDataPrototype> clipboardCopiedItems;
    ArrayList<Node> clipboardPasteNodes;
    Pane pane;
    public GoLogoClipboard(GoLogoLoApp initApp) {
        app = initApp;
        clipboardCutItems = null;
        clipboardCopiedItems = null;
        clipboardNodes = null;
        clipboardPasteNodes = null;
        pane = (Pane) app.getGUIModule().getGUINode(GOLO_IMAGE_PANE);
    }

    @Override
    public void cut() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.isItemSelected()){
            clipboardCutItems = new ArrayList<GoLogoDataPrototype>();
            clipboardCutItems.add(data.getSelectedItem());
            clipboardCopiedItems = null;
            clipboardNodes = data.getSelectedNodes();
            CutItem_Transcation trans = new CutItem_Transcation(app, clipboardCutItems, clipboardNodes, pane);
            app.processTransaction(trans);
        }
        
    }

    @Override
    public void copy() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<GoLogoDataPrototype> tempItems = new ArrayList(data.getSelectedItems());
            copyToCopiedClipboard(tempItems);
            clipboardNodes = data.getSelectedNodes();
        }
    }

    @Override
    public void paste() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            ArrayList<Node> nodesToAdd = data.getSelectedNodes();
            Node node = nodesToAdd.get(0);
            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
            ArrayList<GoLogoDataPrototype> pasteItems = clipboardCutItems;
            if ((clipboardCutItems != null)
                    && (!clipboardCutItems.isEmpty())) {
                PasteItem_Transaction transaction = new PasteItem_Transaction(app, clipboardCutItems, selectedIndex, node);
                app.processTransaction(transaction);
                copyToCutClipboard(clipboardCopiedItems);
            }
            else if ((clipboardCopiedItems != null)
                    && (!clipboardCopiedItems.isEmpty())) {
                PasteItem_Transaction transaction = new PasteItem_Transaction(app, clipboardCopiedItems, selectedIndex, node);
                app.processTransaction(transaction);
                copyToCopiedClipboard(clipboardCopiedItems);
            }
        }
    }

    @Override
    public boolean hasSomethingToCut() {
        //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean hasSomethingToCopy() {
        return ((GoLogoData)app.getDataComponent()).isItemSelected()
                || ((GoLogoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
        if ((clipboardCutItems != null) && (!clipboardCutItems.isEmpty()))
            return true;
        else if ((clipboardCopiedItems != null) && (!clipboardCopiedItems.isEmpty()))
            return true;
        else
            return false;
    }
    private void copyToCopiedClipboard(ArrayList<GoLogoDataPrototype> itemsToCopy) {
        clipboardCutItems = null;
        clipboardCopiedItems = copyItems(itemsToCopy);
    }
    
    private ArrayList<GoLogoDataPrototype> copyItems(ArrayList<GoLogoDataPrototype> itemsToCopy) {
        ArrayList<GoLogoDataPrototype> tempCopy = new ArrayList();         
        for (GoLogoDataPrototype itemToCopy : itemsToCopy) {
            GoLogoDataPrototype copiedItem = (GoLogoDataPrototype)itemToCopy.clone();
            tempCopy.add(copiedItem);
        }        
        return tempCopy;
    }
    private void copyToCutClipboard(ArrayList<GoLogoDataPrototype> itemsToCopy) {
        clipboardCutItems = copyItems(itemsToCopy);
        clipboardCopiedItems = null;    
    }
}
