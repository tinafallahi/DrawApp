package drawapp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DrawApp extends Application {
    public static void main(String[] args) { 
        
    }
    @Override
        public void start(Stage primaryStage) throws IOException {
        MainWindow main = new MainWindow(primaryStage);
        ImagePanel imagePanel = main.getImagePanel();
        Reader reader = new InputStreamReader(System.in);
        Parser parser = new Parser(reader,imagePanel,main);
        //parser.parse();
        Button next = new Button();
        next = main.nextButton();
        parser.parseButton(next);
        primaryStage.show();
    }
    
}