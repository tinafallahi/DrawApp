package drawapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class DrawApp extends Application {
    public static void main(String[] args) { 
        launch(args); 
    }

    @Override
    public void start(Stage stage)
    {
        MainWindow mw=new MainWindow(stage);
        stage.show();
    }
    
}