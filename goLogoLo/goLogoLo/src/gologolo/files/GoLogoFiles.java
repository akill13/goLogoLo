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
import gologolo.data.GoLogoImageData;
import gologolo.data.GoLogoShape;
import gologolo.data.LogoCircle;
import gologolo.data.LogoRectangle;
import java.io.File;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import static javafx.scene.paint.Paint.valueOf;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;
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
    GoLogoLoApp app;
    public GoLogoFiles(GoLogoLoApp app){
        this.app=app;
    }
    GoLogoShape builder = new GoLogoShape();
    static final String JSON_NAME = "name";
    static final String JSON_ORDER  = "order";    
    static final String JSON_TYPE = "type";
    static final String JSON_TABLE = "table";
    
    static final String JSON_TEXT = "Label";
    static final String JSON_LABEL = "text_label";
    static final String JSON_FONT = "text_font";
    static final String JSON_IS_BOLD = "isBold";
    static final String JSON_IS_ITALIC = "isItalic";
    static final String JSON_TEXT_X = "label_x_translate";
    static final String JSON_TEXT_Y = "label_y_translatae";
    static final String JSON_COLOR_FILL = "label_color_fill";
    static final String JSON_FONT_NAME = "font_name";
    static final String JSON_FONT_SIZE = "font_size";
    
    static final String JSON_REC = "Rectangle";
    static final String JSON_FOCUS_ANGLE = "rectangle_focus_angle";
    static final String JSON_FOCUS_DISTANCE = "rectangle_focus_distance";
    static final String JSON_CENTER_X = "rectangle_center_x";
    static final String JSON_CENTER_Y = "rectangle_center_y";
    static final String JSON_RADIUS = "rectangle_radius";
    static final String JSON_PROPORTIONAL = "rectangle_proportional";
    static final String JSON_CYCLE_METHOD = "rectangle_cycle_method";
    static final String JSON_STOP_0 = "rectangle_stop_0_color";
    static final String JSON_STOP_1 = "rectangle_stop_1_color";
    static final String JSON_RADI = "rectangle_radi";
    static final String JSON_RECTANGLE_X_TRANSLATE = "rectangle_x_translate";
    static final String JSON_RECTANGLE_Y_TRANSLATE = "rectangle_y_translate";
    static final String JSON_RECTANGLE_BORDER_THICKNESS = "rectangle_border_thickness";
    static final String JSON_RECTANGLE_ARC_CURVE = "rectangle_arc_radi";
    static final String JSON_RECTANGLE_STROKE_COLOR = "rectangle_stroke_color";
    
    static final String JSON_CIRCLE = "Circle";
    static final String JSON_C_FOCUS_ANGLE = "circle_focus_angle";
    static final String JSON_C_FOCUS_DISTANCE = "circle_focus_distance";
    static final String JSON_C_CENTER_X = "circle_center_x";
    static final String JSON_C_CENTER_Y =  "circle_center_y";
    static final String JSON_C_RADIUS = "circle_radius";
    static final String JSON_C_PROPORTIONAL = "circle_proportional";
    static final String JSON_C_CYCLE_METHOD = "circle_cycle_method";
    static final String JSON_C_STOP_0 = "circle_stop_0_color";
    static final String JSON_C_STOP_1 = "circle_stop_1_color";
    static final String JSON_C_X_TRANSLATE = "circle_x_translate";
    static final String JSON_C_Y_TRANSLATE = "cicle_y_translate";
    static final String JSON_C_BORDER_THICKNESS = "circle_border_thickness";
    static final String JSON_C_CIRCLE_RADIUS = "circle_radius";
    static final String JSON_C_STROKE_COLOR = "circle_stroke_color";
    
    static final String JSON_IMAGE_NODE = "Image";
    static final String JSON_IMAGE_PATH = "image_path";
    static final String JSON_IMAGE_X_TRANSLATE = "image_x_translate";
    static final String JSON_IMAGE_Y_TRANSLATE = "image_y_translate";
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
      GoLogoData logoData = (GoLogoData) data;
      
      JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
      Iterator<GoLogoDataPrototype> itemsIt = logoData.tableIterator();
      
      while (itemsIt.hasNext()) {	
            GoLogoDataPrototype item = itemsIt.next();
            if(item.getType().equals(JSON_REC)){
                LogoRectangle rect = (LogoRectangle)item.getNode();
                JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_ORDER, Integer.toString(item.getOrder()))
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
                    .add(JSON_FOCUS_ANGLE, Double.toString(rect.getFocusAngle()))
                    .add(JSON_FOCUS_DISTANCE, Double.toString(rect.getFocusDistance()))
                    .add(JSON_CENTER_X, Double.toString(rect.getCenterX()))
                    .add(JSON_CENTER_Y, Double.toString(rect.getCenterY()))
                    .add(JSON_RADIUS, Double.toString(rect.getRadius()))
                    .add(JSON_PROPORTIONAL, Boolean.toString(rect.isProportional()))
                    .add(JSON_CYCLE_METHOD, rect.getCycleMethod().toString())
                    .add(JSON_STOP_0, rect.getStop0().getColor().toString())
                    .add(JSON_STOP_1, rect.getStop1().getColor().toString())
                    .add(JSON_RADI, Double.toString(rect.getRadi()))
                    .add(JSON_RECTANGLE_X_TRANSLATE, Double.toString(rect.xCoordinate))
                    .add(JSON_RECTANGLE_Y_TRANSLATE, Double.toString(rect.yCoordinate))
                    .add(JSON_RECTANGLE_BORDER_THICKNESS, Double.toString(rect.oldStrokeWidth))
                    .add(JSON_RECTANGLE_ARC_CURVE, Double.toString(rect.getRadi()))
                    .add(JSON_RECTANGLE_STROKE_COLOR, rect.getStrokeColor().toString()).build();
	    arrayBuilder.add(itemJson);
            }else if(item.getType().equals(JSON_TEXT)) {
                GoLogoDataText text = (GoLogoDataText) item.getText();
                JsonObject itemJson = Json.createObjectBuilder()
                    .add(JSON_ORDER, Integer.toString(item.getOrder()))
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
                    .add(JSON_TEXT, text.getText())
                    .add(JSON_FONT_NAME, text.getFont().getName())
                    .add(JSON_FONT_SIZE, Double.toString(text.getFont().getSize()))
                    .add(JSON_IS_BOLD, Boolean.toString(text.isBold))
                    .add(JSON_IS_ITALIC, Boolean.toString(text.isItalics))
                    .add(JSON_TEXT_X, text.xCoordinate)
                    .add(JSON_TEXT_Y, text.yCoordinate)
                    .add(JSON_COLOR_FILL, text.getFill().toString()).build();
            arrayBuilder.add(itemJson);
            }else if(item.getType().equals(JSON_CIRCLE)) {
                LogoCircle circle = (LogoCircle) item.getNode();
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_ORDER, Integer.toString(item.getOrder()))
                        .add(JSON_NAME, item.getName())
                        .add(JSON_TYPE, item.getType())
                        .add(JSON_C_FOCUS_ANGLE,Double.toString(circle.getFocusAngle()))
                        .add(JSON_C_FOCUS_DISTANCE, Double.toString(circle.getFocusDistance()))
                        .add(JSON_C_CENTER_X, Double.toString(circle.getCenterX()))
                        .add(JSON_C_CENTER_Y, Double.toString(circle.getCenterY()))
                        .add(JSON_C_RADIUS, Double.toString(circle.getRadiusGrad()))
                        .add(JSON_C_PROPORTIONAL, Boolean.toString(circle.isProportional()))
                        .add(JSON_C_CYCLE_METHOD, circle.getCycleMethod().toString())
                        .add(JSON_C_STOP_0, circle.getStop0().getColor().toString())
                        .add(JSON_C_STOP_1, circle.getStop1().getColor().toString())
                        .add(JSON_C_X_TRANSLATE, Double.toString(circle.getTranslateX()))
                        .add(JSON_C_Y_TRANSLATE, Double.toString(circle.getTranslateY()))
                        .add(JSON_C_BORDER_THICKNESS, Double.toString(circle.getStrokeWidth()))
                        .add(JSON_C_CIRCLE_RADIUS, Double.toString(circle.getRadius()))
                        .add(JSON_C_STROKE_COLOR, circle.getStrokeColor().toString()).build();
                arrayBuilder.add(itemJson);
            }else if(item.getType().equals(JSON_IMAGE_NODE)){
                GoLogoImageData img = (GoLogoImageData)item.getImage();
                JsonObject itemJson = Json.createObjectBuilder()
                        .add(JSON_ORDER, Integer.toString(item.getOrder()))
                        .add(JSON_NAME, item.getName())
                        .add(JSON_TYPE, item.getType())
                        .add(JSON_IMAGE_PATH, img.getPath())
                        .add(JSON_IMAGE_X_TRANSLATE, Double.toString(img.getTranslateX()))
                        .add(JSON_IMAGE_Y_TRANSLATE, Double.toString(img.getTranslateY())).build();
                arrayBuilder.add(itemJson);
            }
	    
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
            if(item.getType().equals("Rectangle") || item.getType().equals("Circle")) {
                pane.getChildren().add(item.node);
            }else if(item.getType().equals("Label")) {
                pane.getChildren().add(item.getText());
            }
        }
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
      GoLogoData logodata = (GoLogoData)data;
      Pane pane = (Pane)logodata.getapp().getGUIModule().getGUINode(GOLO_IMAGE_PANE);
      WritableImage image = pane.snapshot(new SnapshotParameters(), null);
      File file = new File(filePath);
      try{
          ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
      }catch(IOException e) {
          e.printStackTrace();
      }
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
        
	int order = Integer.parseInt(jsonItem.getString(JSON_ORDER))-1;
	String name = jsonItem.getString(JSON_NAME);
        String type = jsonItem.getString(JSON_TYPE);
        GoLogoDataPrototype item = new GoLogoDataPrototype(order, name, type);
        
        if(type.equals("Rectangle")) {
            double focusAngle = Double.parseDouble(jsonItem.getString(JSON_FOCUS_ANGLE));
            double focusDistance = Double.parseDouble(jsonItem.getString(JSON_FOCUS_DISTANCE));
            double centerX = Double.parseDouble(jsonItem.getString(JSON_CENTER_X));
            double centerY = Double.parseDouble(jsonItem.getString(JSON_CENTER_Y));
            double radius = Double.parseDouble(jsonItem.getString(JSON_RADIUS));
            boolean proportional = Boolean.parseBoolean(jsonItem.getString(JSON_PROPORTIONAL));
            CycleMethod cycleMethod;
            if(jsonItem.getString(JSON_CYCLE_METHOD).equals("NO_CYCLE")) {
                cycleMethod = CycleMethod.NO_CYCLE;
            }else if(jsonItem.getString(JSON_CYCLE_METHOD).equals("REFLECT")) {
                cycleMethod = CycleMethod.REFLECT;
            }else
                cycleMethod = CycleMethod.REPEAT;
            String beta = jsonItem.getString(JSON_STOP_0);
            String alpha = jsonItem.getString(JSON_STOP_1);
            Color c0 = (Color) valueOf(beta);
            Color c1 = (Color) valueOf(alpha);
            Stop color0 = new Stop(0, c0);
            Stop color1 = new Stop(1, c1);
            Color strokeColor = (Color) valueOf(jsonItem.getString(JSON_RECTANGLE_STROKE_COLOR));
            double radi = Double.parseDouble(jsonItem.getString(JSON_RADI));
            double xCoordinate = Double.parseDouble(jsonItem.getString(JSON_RECTANGLE_X_TRANSLATE));
            double yCoordinate = Double.parseDouble(jsonItem.getString(JSON_RECTANGLE_Y_TRANSLATE));
            double borderThickness = Double.parseDouble(jsonItem.getString(JSON_RECTANGLE_BORDER_THICKNESS));
            LogoRectangle rect = new LogoRectangle(app,focusAngle,focusDistance, centerX, centerY, radius, proportional, cycleMethod, color0, color1, strokeColor, radi, xCoordinate, yCoordinate, borderThickness);
            item.node=rect;
        }else if(type.equals("Circle")) {
            double focusAngle = Double.parseDouble(jsonItem.getString(JSON_C_FOCUS_ANGLE));
            double focusDistance = Double.parseDouble(jsonItem.getString(JSON_C_FOCUS_DISTANCE));
            double centerX = Double.parseDouble(jsonItem.getString(JSON_C_CENTER_X));
            double centerY = Double.parseDouble(jsonItem.getString(JSON_C_CENTER_Y));
            double radius = Double.parseDouble(jsonItem.getString(JSON_C_RADIUS));
            boolean proportional = Boolean.parseBoolean(jsonItem.getString(JSON_C_PROPORTIONAL));
            CycleMethod cycleMethod;
            if(jsonItem.getString(JSON_C_CYCLE_METHOD).equals("NO_CYCLE")) {
                cycleMethod = CycleMethod.NO_CYCLE;
            }else if(jsonItem.getString(JSON_CYCLE_METHOD).equals("REFLECT")) {
                cycleMethod = CycleMethod.REFLECT;
            }else
                cycleMethod = CycleMethod.REPEAT;
            String beta = jsonItem.getString(JSON_C_STOP_0);
            String alpha = jsonItem.getString(JSON_C_STOP_1);
            Color c0 = (Color) valueOf(beta);
            Color c1 = (Color) valueOf(alpha);
            Stop color0 = new Stop(0, c0);
            Stop color1 = new Stop(1, c1);
            Color strokeColor = (Color) valueOf(jsonItem.getString(JSON_C_STROKE_COLOR));
            double radi = Double.parseDouble(jsonItem.getString(JSON_C_CIRCLE_RADIUS));
            double xCoordinate = Double.parseDouble(jsonItem.getString(JSON_C_X_TRANSLATE));
            double yCoordinate = Double.parseDouble(jsonItem.getString(JSON_C_Y_TRANSLATE));
            double borderThickness = Double.parseDouble(jsonItem.getString(JSON_C_BORDER_THICKNESS));
            LogoCircle rect = new LogoCircle(app,focusAngle,focusDistance, centerX, centerY, radius, proportional, cycleMethod, color0, color1, strokeColor, xCoordinate, yCoordinate, borderThickness);
            item.node=rect;
        }else if(type.equals("Label")){
            String nodeText = jsonItem.getString(JSON_TEXT);
            String fontName = jsonItem.getString(JSON_FONT_NAME);
            double fontSize = Double.parseDouble(jsonItem.getString(JSON_FONT_SIZE));
            
            boolean isBold = Boolean.valueOf(jsonItem.getString(JSON_IS_BOLD));
            boolean isItalic = Boolean.valueOf(jsonItem.getString(JSON_IS_ITALIC));
            
            double textx = jsonItem.getInt(JSON_TEXT_X);
            double texty = jsonItem.getInt(JSON_TEXT_Y);
            Color fillColor = (Color) valueOf(jsonItem.getString(JSON_COLOR_FILL));
            
            Font font = Font.font(fontName, fontSize);
            GoLogoDataText text = new GoLogoDataText(nodeText, app, font,isBold, isItalic, textx, texty, fillColor);
            item.setText(text);
        }
	// THEN USE THE DATA TO BUILD AN ITEM
        
	// ALL DONE, RETURN IT
	return item;
    }
    
    public Node createChild(GoLogoDataPrototype child, GoLogoLoApp app) {
        if(child.getType().equals("Rectangle")){
             LogoRectangle rect = (LogoRectangle)child.getNode();
        }
        else if(child.getType().equals("Circle")){
            Node create = new GoLogoDataText(child.getName(), app);
        }else if(child.getType().equals("Label")){
           Node create = new GoLogoDataText(child.getName(), app);
        }
       return null;
    }
}
