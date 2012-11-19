package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Parser
{
  private BufferedReader reader;
  private ImagePanel image;
  private MainWindow frame;
  private Stage primaryStage = new Stage();
  private String line;
  private Turtle turtle;
  private int i=0;
  
  public Parser(Reader reader, ImagePanel image, MainWindow frame,Stage primaryStage)
  {
    this.reader = new BufferedReader(reader);
    this.image = image;
    this.frame = frame;
    this.primaryStage = primaryStage;
  }

  public void parse()
  {
    try
    {
      String line = reader.readLine();
      while (line != null)
      {
        parseLine(line);
        line = reader.readLine();
      }
    }
    catch (IOException e)
    {
      frame.postMessage("Parse failed.");
      return;
    }
    catch (ParseException e)
    {
      frame.postMessage("Parse Exception: " + e.getMessage());
      return;
    }
    frame.postMessage("Drawing completed");
  }
  
  private void parseLine(String line) throws ParseException
  {
    
    if (line.length() < 2) {
          return;
      }
    String command = line.substring(0, 2);
    if (command.equals("SD")) { setDimension(line.substring(2,line.length())); return;}
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("DI")) { drawImage(line.substring(3, line.length()));return; }
    if (command.equals("SG")) { setGradient(line.substring(2,line.length())); return; }
    if (command.equals("SB")) { setBlur(); return;}
    if (command.equals("SR")) { setReflection(); return;}
    if (command.equals("SS")) { setDropShadow(); return;}
    if (command.equals("ST")) { startTurtle(line.substring(2, line.length())); return; }
    if (command.equals("FT")) { forward(line.substring(2, line.length())); return; }
    if (command.equals("TL")) { turnLeft(line.substring(2, line.length())); return; }
    if (command.equals("TR")) { turnRight(line.substring(2, line.length())); return; }
    if (command.equals("PU")) { penUp(); return; }
    if (command.equals("PD")) { penDown(); return; }

    throw new ParseException("Unknown drawing command");
  }
  
  private void penUp() throws ParseException{
      turtle.penUp();
  }
  
  private void penDown() throws ParseException{
      turtle.penDown();
  }
  private void turnRight(String args) throws ParseException
  {
    int r = -1;
    StringTokenizer tokenizer = new StringTokenizer(args);
    r = getInteger(tokenizer);
    if (r < 0) throw new ParseException("Invalid turning values");
    turtle.turnRight(r);
  }
  
   private void turnLeft(String args) throws ParseException
  {
    int r = -1;
    StringTokenizer tokenizer = new StringTokenizer(args);
    r = getInteger(tokenizer);
    if (r < 0) throw new ParseException("Invalid turning values");
    turtle.turnLeft(r);
  }
  
  private void forward(String args) throws ParseException
  {
    int d = -1;
    StringTokenizer tokenizer = new StringTokenizer(args);
    d = getInteger(tokenizer);
    if (d < 0) throw new ParseException("Invalid values for Line command");
    turtle.forward(d);
  }
  
  private void startTurtle(String args) throws ParseException
  {
    int x1 = -1;
    int y1 = -1;
    int r = -1;
    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    r = getInteger(tokenizer);
    if ((x1 < 0)||(y1 < 0)||(r < 0)) throw new ParseException("Invalid values for Line command");
    turtle = new Turtle(image,x1,y1,r);
  }
  
  private void setDimension (String args) throws ParseException
  {
      int width=-1;
      int height = -1;
      StringTokenizer tokenizer = new StringTokenizer(args);
      width = getInteger(tokenizer);
      height = getInteger(tokenizer);
      if((width<0)||(height<0)) throw new ParseException ("Invalid values for the scene dimension command.");
      
      primaryStage.setWidth(width);
      primaryStage.setHeight(height);
      frame.changeSize(width,height);
  }
  
  private void drawImage (String args) throws ParseException 
  {
      int x = -1;
      int y = -1;
      int width=-1;
      int height = -1;
      String file="";
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      x = getInteger(tokenizer);
      y = getInteger(tokenizer);
      width = getInteger(tokenizer);
      height = getInteger(tokenizer);
      int position = args.indexOf("@");
      if (position == -1) {
          throw new ParseException("DrawString string is missing");
      }
      file = args.substring(position+1,args.length());
      
      if((x<0)||(y<0)||(width<0)||(height<0)) throw new ParseException ("Invalid values for the draw image command.");
      
      image.drawImage(x, y, width, height, file);
  }
  
  private void drawLine(String args) throws ParseException
  {
    int x1 = -1;
    int y1 = -1;
    int x2 = -1;
    int y2 = -1;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    
    if((x1<0)||(x2<0)||(y1<0)||(y2<0)) throw new ParseException ("Invalid values for the draw line command.");
    
    image.drawLine(x1,y1,x2,y2);
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = -1;
    int y1 = -1;
    int x2 = -1;
    int y2 = -1;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    
    if((x1<0)||(x2<0)||(y1<0)||(y2<0)) throw new ParseException ("Invalid values for the draw rectangles command."); 
    
    image.drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = -1;
    int y1 = -1;
    int x2 = -1;
    int y2 = -1;
    
    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    
    if((x1<0)||(x2<0)||(y1<0)||(y2<0)) throw new ParseException ("Invalid values for the draw fill rectangle command.");
    
    image.fillRect(x1, y1, x2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = -1;
    int y = -1;
    int width = -1;
    int height = -1;
    int startAngle = -1;
    int arcAngle = -1;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    startAngle = getInteger(tokenizer);
    arcAngle = getInteger(tokenizer);
    
    if((x<0)||(y<0)||(width<0)||(height<0)||(startAngle<0)||(arcAngle<0)) throw new ParseException ("Invalid values for the draw arc command.");
    
    image.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = -1;
    int y1 = -1;
    int width = -1;
    int height = -1;
    
    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    
    if((x1<0)||(y1<0)||(width<0)||(height<0)) throw new ParseException ("Invalid values for the draw oval command.");
    
    image.drawOval(x1, y1, width, height);
  }

  private void drawString(String args) throws ParseException
  {
    int x = -1;
    int y = -1;
    String s = "";
    
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    int position = args.indexOf("@");
    if (position == -1) {
          throw new ParseException("DrawString string is missing");
      }
    s = args.substring(position+1,args.length());
    
    if((x<0)||(y<0)) throw new ParseException ("Invalid values for the draw string command.");
    
    image.drawString(x,y,s);
  }

  private void setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { image.setColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { image.setColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { image.setColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { image.setColour(Color.DARKGREY); return;}
    if (colourName.equals("gray")) { image.setColour(Color.GRAY); return;}
    if (colourName.equals("green")) { image.setColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { image.setColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { image.setColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { image.setColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { image.setColour(Color.PINK); return;}
    if (colourName.equals("red")) { image.setColour(Color.RED); return;}
    if (colourName.equals("white")) { image.setColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { image.setColour(Color.YELLOW); return;}
    throw new ParseException("Invalid colour name");
  }
  
  private void setBlur(){
      image.setBlur();
  }
  
  private void setReflection(){
      image.setReflection();
  }
  
  private void setDropShadow(){
      image.setDropShadow();
  }
  
  private Color getColour(String colourName) throws ParseException 
  {
    if (colourName.equals("black")) { return Color.BLACK;}
    if (colourName.equals("blue")) { return Color.BLUE;}
    if (colourName.equals("cyan")) { return Color.CYAN;}
    if (colourName.equals("darkgray")) { return Color.DARKGREY;}
    if (colourName.equals("gray")) { return Color.GRAY;}
    if (colourName.equals("green")) { return Color.GREEN;}
    if (colourName.equals("lightgray")) { return Color.LIGHTGRAY;}
    if (colourName.equals("magenta")) { return Color.MAGENTA;}
    if (colourName.equals("orange")) { return Color.ORANGE;}
    if (colourName.equals("pink")) { return Color.PINK;}
    if (colourName.equals("red")) { return Color.RED;}
    if (colourName.equals("white")) { return Color.WHITE;}
    if (colourName.equals("yellow")) { return Color.YELLOW;}
    throw new ParseException("Invalid colour name");
  }
  
  private void setGradient(String args) throws ParseException
  {
    String colourStart="";
    String colourEnd="";
    int position1 = args.indexOf("@");
    int position2 = args.indexOf("!");
    if ((position1 == -1)||(position2 == -1)) {
          throw new ParseException("DrawString string is missing");
      }
    colourStart = args.substring(position1+1,position2-1);
    colourEnd = args.substring(position2+1,args.length());
    image.setGradient(getColour(colourStart),getColour(colourEnd));
  }

  private int getInteger(StringTokenizer tokenizer) throws ParseException
  {
    if (tokenizer.hasMoreTokens()) {
          return Integer.parseInt(tokenizer.nextToken());
      }
    else {
          throw new ParseException("Missing Integer value");
      }
  }
  
  public void parseButton(final Button next,final Button complete) throws IOException
{
    String line = reader.readLine();
    final ArrayList<String> asl=new ArrayList<String>();
    while (line != null)
    { 
        asl.add(line);
        line = reader.readLine();
    }
    next.setOnAction(new EventHandler<ActionEvent>() 
    {
        public void handle(ActionEvent event) 
        {
            try 
            {
                parseLine(asl.get(i));
                i++;
                frame.postMessage("Next Step Completed!!");
                if(i==asl.size()){
                    next.setDisable(true);
                    complete.setDisable(true);
                    frame.postMessage("Drawing Completed!!");
                }
            } catch (ParseException e) { 
                frame.postMessage("Parse Exception: " + e.getMessage());
            } 
        }
    });
    complete.setOnAction(new EventHandler<ActionEvent>() 
    {
        public void handle(ActionEvent event) 
        {
            try
    {
      while (i<asl.size())
      {
        parseLine(asl.get(i));
        i++;
        if(i==asl.size()){
                    complete.setDisable(true);
                    next.setDisable(true);
                    frame.postMessage("Drawing Completed!!");
                }
      }
    }
    catch (ParseException e)
    {
      frame.postMessage("Parse Exception: " + e.getMessage());
    }
        }
    });
    }
}
