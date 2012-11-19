package drawapp;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Turtle 
{
  Path path= new Path();
  double startx=0;
  double starty=0;
  double rot=0;  
  ImagePanel image;
  boolean p = true;
  
  public Turtle(ImagePanel image,int x, int y, int r) 
  {
      this.image = image;
      MoveTo moveto = new MoveTo(x,y);
      path.getElements().addAll(moveto);
      path.setTranslateX(x);
      path.setTranslateY(y);
      image.getChildren().add(path);
      startx= x;
      starty= y;
      rot=r;
  }

public void startTurtle(int x,int y, int r)
{
   new Turtle(image, x,y,r);
}

public void forward(int dist)
{
     rot = (rot * Math.PI) / 180;
        double sine = Math.sin(rot);
        double cosine = Math.cos(rot);
        double deltaX = cosine * dist;
        double deltaY = sine * dist;
        if (p) {
            LineTo line = new LineTo((startx + deltaX), (starty + deltaY));
            path.getElements().add(line);
        } else {
            MoveTo line = new MoveTo((startx + deltaX), (starty + deltaY));
            path.getElements().add(line);
        }
        startx = startx + deltaX;
        starty = starty + deltaY;

        rot = (rot * 180) / Math.PI;
}

public void turnLeft(int r)
{
    rot = rot-r;
}

public void turnRight(int r)
{
    rot = rot+r;
}

public void penUp()
{
    this.p=false;
}
public void penDown()
{
      this.p=true;
}
    
}