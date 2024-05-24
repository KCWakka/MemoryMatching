import javax.swing.*;

public class MainFrame implements Runnable{
    private GraphicsPanel panel;
    public MainFrame(String p1Name, String p2Name, String theme) {
        JFrame frame = new JFrame("Memory Matching");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 1000); // 540 height of image + 40 for window menu bar
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        panel = new GraphicsPanel(p1Name, p2Name, theme);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);

        // start thread, required for animation
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while (true) {
            panel.repaint();
        }
    }
}
