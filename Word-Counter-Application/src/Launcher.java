import javax.swing.SwingUtilities;

import Gui.Window;

public class Launcher {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new Window().setVisible(true);
            }
        });
    }
}
