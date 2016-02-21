import javax.swing.*;

public class Main {



    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new PhotoPrintingUI();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
