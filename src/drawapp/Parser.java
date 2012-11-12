package drawapp;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Parser
{
  private BufferedReader reader;
  private ImagePanel image;
  private MainWindow frame;
  private String line;
  private int i=0;
  
  public Parser(Reader reader, ImagePanel image, MainWindow frame)
  {
    this.reader = new BufferedReader(reader);
    this.image = image;
    this.frame = frame;
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
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("DI")) { return; }

    throw new ParseException("Unknown drawing command");
  }

  private void drawImage (String args) throws ParseException 
  {
      int x1 = 0;
      int y1 = 0;
      int width=0;
      int height = 0;
      String file="";
      StringTokenizer tokenizer = new StringTokenizer(args);
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
    if (colourName.equals("black")) { image.setColour(Color.black); return;}
    if (colourName.equals("blue")) { image.setColour(Color.blue); return;}
    if (colourName.equals("cyan")) { image.setColour(Color.cyan); return;}
    if (colourName.equals("darkgray")) { image.setColour(Color.darkGray); return;}
    if (colourName.equals("gray")) { image.setColour(Color.gray); return;}
    if (colourName.equals("green")) { image.setColour(Color.green); return;}
    if (colourName.equals("lightgray")) { image.setColour(Color.lightGray); return;}
    if (colourName.equals("magenta")) { image.setColour(Color.magenta); return;}
    if (colourName.equals("orange")) { image.setColour(Color.orange); return;}
    if (colourName.equals("pink")) { image.setColour(Color.pink); return;}
    if (colourName.equals("red")) { image.setColour(Color.red); return;}
    if (colourName.equals("white")) { image.setColour(Color.white); return;}
    if (colourName.equals("yellow")) { image.setColour(Color.yellow); return;}
    throw new ParseException("Invalid colour name");
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
  
  public void parseButton(final Button b) throws IOException
{
    String line = reader.readLine();
    final ArrayList<String> asl=new ArrayList<String>();
    while (line != null)
    { 
        asl.add(line);
        line = reader.readLine();
    }
    b.setOnAction(new EventHandler<ActionEvent>() 
    {
        public void handle(ActionEvent event) 
        {
            try 
            {
                parseLine(asl.get(i));
                i++;
                if(i==asl.size()){
                    b.setDisable(true);
                }
            } catch (ParseException ex) { 
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    });
    }
}
