package drawapp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class MainWindow
{
  public static int IMAGEWIDTH = 500;
  public static int IMAGEHEIGHT = 300;

  private int width;
  private int height;

  private ImagePanel imagePanel = new ImagePanel(IMAGEWIDTH,IMAGEHEIGHT+200); 
  private TextArea textarea=new TextArea();
  private HBox pictureRegion = new HBox();
  private Button closeButton=new Button("Close Window");
  private Button nextButton=new Button("Next Step");
  private Button completeButton=new Button("Complete Drawing");
  private Button saveButton=new Button("Save As Image");
 
  public MainWindow(Stage stage)
  {
      this(stage,IMAGEWIDTH, IMAGEHEIGHT+200);
  }

  public MainWindow (Stage primaryStage, int width, int height) 
  {
      primaryStage.setTitle("DrawApp"); 
      Group root = new Group(); 
      Scene scene = new Scene(root, IMAGEWIDTH, IMAGEHEIGHT+200, Color.WHITE);
      GridPane gridpane = buildGUI();
      root.getChildren().add(gridpane);         
      primaryStage.setScene(scene); 
  }

  private GridPane buildGUI()
  {
      GridPane gridpane = new GridPane(); 
      gridpane.setHgap(10); 
      gridpane.setVgap(0);  
      // Text area for CSS editor  
      textarea.setWrapText(true); 
      textarea.setPrefWidth(IMAGEWIDTH); 
      textarea.setPrefHeight(150);
      GridPane.setHalignment(textarea, HPos.CENTER); 
      gridpane.add(textarea, 0, 1); 
      String cssDefault = "-fx-border-color: black;\n" 
      + "-fx-border-insets: 5;\n" 
      + "-fx-border-width: 0;\n";
      textarea.setText("Scene Ready!"); 
      // Border decorate the picture 
      imagePanel.setStyle(cssDefault); 
      imagePanel.setPrefWidth(IMAGEWIDTH);
      imagePanel.setPrefHeight(IMAGEHEIGHT);
      gridpane.add(imagePanel, 0, 0);
         
      pictureRegion.setPrefHeight(50);
      pictureRegion.setPrefWidth(IMAGEWIDTH);
      pictureRegion.setAlignment(Pos.CENTER);
      pictureRegion.setStyle("-fx-background-color: #E8E8E8");
        
      closeButton.setOnAction(new EventHandler<ActionEvent>() { 
        @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
      });
      
      saveButton.setOnAction(new EventHandler<ActionEvent>() { 
        @Override
            public void handle(ActionEvent event) {
              try {
                  ImageIO.write(
                  SwingFXUtils.fromFXImage(
                  imagePanel.snapshot(null, null), null
                  ),
                  "png",
                  new File("DrawApp.png")
                  );
              } catch (IOException ex) {
                  Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
      });
        
      pictureRegion.getChildren().add(closeButton);
      pictureRegion.getChildren().add(nextButton);
      pictureRegion.getChildren().add(completeButton);
      pictureRegion.getChildren().add(saveButton);
      gridpane.add(pictureRegion, 0, 2);
        
      return gridpane;
  }

  public ImagePanel getImagePanel()
  {
      return imagePanel;
  }
  
  public Button nextButton()
  {
      return nextButton;
  }
  
  public Button completeButton()
  {
      return completeButton;
  }

  public void postMessage(final String s)
  {
      textarea.setText(s);
  }
  public void changeSize(int width,int height){
      imagePanel.setPrefHeight(height-200);
      imagePanel.setPrefWidth(width);
      textarea.setPrefWidth(width);
      pictureRegion.setPrefWidth(width);
  }
}