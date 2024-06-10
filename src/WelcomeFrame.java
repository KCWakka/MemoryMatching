import javax.swing.*;

public class WelcomeFrame {
    private WelcomePanel panel;
    public WelcomeFrame() {
        JFrame frame = new JFrame("Welcome to MM Mystery!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        panel = new WelcomePanel(frame);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}
