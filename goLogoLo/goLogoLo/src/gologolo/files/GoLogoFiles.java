/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.files;

import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import gologolo.GoLogoLoApp;
import static gologolo.GoLogoPropertyType.GOLO_IMAGE_PANE;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoDataPrototype;
import gologolo.data.GoLogoDataText;
import gologolo.data.GoLogoShape;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author akillhalimi
 */
public class GoLogoFiles implements AppFileComponent{
    GoLogoShape builder = new GoLogoShape();
    static final String JSON_ORDER  = "order";
    static final String JSON_NAME = "name";
    static final String JSON_TYPE = "type";
    static final String JSON_TABLE = "table";
    static final String JSON_TEXT = "text";
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
      GoLogoData logoData = (GoLogoData) data;
      
      JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
      Iterator<GoLogoDataPrototype> itemsIt = logoData.tableIterator();
      
      while (itemsIt.hasNext()) {	
            GoLogoDataPrototype item = itemsIt.next();
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_ORDER, item.getOrder())
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
                    .add(JSON_TEXT, item.getText().getText()).build();
	    arrayBuilder.add(itemJson);
	}
	JsonArray itemsArray = arrayBuilder.build();
        JsonObject toDoDataJSO = Json.createObjectBuilder()
		.add(JSON_TABLE, itemsArray)
		.build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(toDoDataJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(toDoDataJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        GoLogoData logodata = (GoLogoData)data;
	logodata.reset();
        Pane pane = (Pane)logodata.getapp().getGUIModule().getGUINode(GOLO_IMAGE_PANE);
	
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
	
	// LOAD LIST NAME AND OWNER
        
	
	// AND NOW LOAD ALL THE ITEMS
	JsonArray jsonItemArray = json.getJsonArray(JSON_TABLE);
	for (int i = 0; i < jsonItemArray.size(); i++) {
	    JsonObject jsonItem = jsonItemArray.getJsonObject(i);
	    GoLogoDataPrototype item = loadItem(jsonItem);
	    logodata.addItem(item);
        }
        for(int i = 0; i<logodata.getlogoTable().size(); i++){
            Node child = createChild(logodata.getlogoTable().get(i), logodata.getapp());
            pane.getChildren().add(child);
        }
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    public GoLogoDataPrototype loadItem(JsonObject jsonItem) {
	// GET THE DATA
	int order = jsonItem.getInt(JSON_ORDER);
	String name = jsonItem.getString(JSON_NAME);
        String type = jsonItem.getString(JSON_TYPE);
        String text = jsonItem.getString(JSON_TEXT);
	// THEN USE THE DATA TO BUILD AN ITEM
        GoLogoDataPrototype item = new GoLogoDataPrototype(order, name, type);
        
	// ALL DONE, RETURN IT
	return item;
    }
    
    public Node createChild(GoLogoDataPrototype child, GoLogoLoApp app) {
        Node create;
        if(child.getType().equals("Shape")){
             create = builder.buildRectangle(app);
        }
        else{
            create = new GoLogoDataText(child.getName(), app);
        }
       return create;
    }
}
