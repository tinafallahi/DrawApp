package drawapp;

import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.SwingUtilities;

public class DrawApp
{
  public static void main(String[] args)
  {
    final MainWindow main = new MainWindow();

    SwingUtilities.invokeLater(
    new Runnable()
    {
        @Override
      public void run()
      {
        ImagePanel imagePanel = main.getImagePanel();
        Reader reader = new InputStreamReader(System.in);
        Parser parser = new Parser(reader,imagePanel,main);
        parser.parse();
        imagePanel.repaint();
      }
    }
  );

  }
}

