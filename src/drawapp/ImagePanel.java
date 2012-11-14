package drawapp;

import java.io.File;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class ImagePanel extends HBox
{
    private Paint colourG=Color.web("000000");
    private Group graphic=new Group();
    private HBox iView;
    private Boolean dropShadow=false;
    private Boolean gaussianBlur=false;
    private Boolean reflection=false;
    
    public ImagePanel(int width, int height)
    {
        setImageSize(width, height);
    }
    private void setImageSize(int width, int height)
    { 
        this.setMaxSize(width, height);
        this.getChildren().add(graphic);
        clear(Color.WHITE);
    }
    public void setBackgroundColour(Color colour)
    {
        String hex = colour.toString();
        this.setStyle("-fx-fill:#"+hex+";");
    }
    public void clear(Color colour)
    {
        setBackgroundColour(colour);
    }
    public void setColour(Color colour)
    {
       colourG=colour;
    }
    public void setBlur(){
        gaussianBlur=true;
    }
    public void setReflection(){
        reflection=true;
    }
    public void setDropShadow(){
        dropShadow=true;
    }
    public void setGradient(Color colourStart,Color colourEnd){
        Stop[] stops = new Stop[] { new Stop(0, colourStart), new Stop(1, colourEnd)};
        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        colourG = lg;
    }
    public void drawLine(int x1, int y1, int x2, int y2)
    {
        Line line = new Line(x1,y1,x2,y2);
        line.setStroke(colourG);
        colourG=Color.web("000000");
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            line.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            line.setEffect(reflectionE);
        }
                graphic.getChildren().add(line);

    }
    public void drawRect(int x1, int y1, int x2, int y2)
    {
        Rectangle rect = new Rectangle(x1,y1,x2,y2);
        rect.setStroke(Paint.valueOf("000000"));
        rect.setFill(Paint.valueOf("00000000"));
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            rect.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            rect.setEffect(reflectionE);
        }
                graphic.getChildren().add(rect);

    }
    public void fillRect(int x1, int y1, int x2, int y2)
    {
        Rectangle rectFill = new Rectangle(x1,y1,x2,y2);
        rectFill.setFill(colourG);
        colourG=Color.web("000000");
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            rectFill.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            rectFill.setEffect(reflectionE);
        }
                graphic.getChildren().add(rectFill);

    }
    public void drawString(int x, int y, String s)
    {
        Text t = new Text(x,y,s);
        if(dropShadow==true){
            final DropShadow dropShadowE = new DropShadow();
            t.setEffect(dropShadowE);
        }
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            t.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            t.setEffect(reflectionE);
        }
                graphic.getChildren().add(t);

    }
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        Arc arc = new Arc(x,y,width/2,height/2,startAngle,arcAngle);
        arc.setStroke(colourG);
        arc.setFill(Paint.valueOf("00000000"));     
        colourG=Color.web("000000");
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            arc.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            arc.setEffect(reflectionE);
        }
        graphic.getChildren().add(arc);
    }
    public void drawOval(int x, int y, int width, int height)
    {
        Ellipse oval = new Ellipse(x,y,width,height);
        oval.setStroke(colourG);
        oval.setFill(Paint.valueOf("00000000"));
        colourG=Color.web("000000");
        if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            oval.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            oval.setEffect(reflectionE);
        }
        graphic.getChildren().add(oval);
    }
    public void drawImage(int x, int y, int width, int height, String path) {
         File file = new File(path);
         Image image = new Image(file.toURI().toString());
         ImageView iv = new ImageView(image);
         iv.setFitWidth(width);
         iv.setFitHeight(height);
         iv.setPreserveRatio(true);
         iv.setSmooth(true);
         iv.setCache(true);
         if(gaussianBlur==true){
            final GaussianBlur gaussianBlurE = new GaussianBlur();
            iv.setEffect(gaussianBlurE);
        }
        if(reflection==true){
            final Reflection reflectionE = new Reflection();
            iv.setEffect(reflectionE);
        }
         graphic.getChildren().add(iv);
    }
}
