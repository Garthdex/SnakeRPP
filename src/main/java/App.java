import java.awt.EventQueue;

public class App {
    public static GUI gui;

    public App() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App.gui = new GUI();
                    App.gui.createGameWindow();
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });
    }
}
