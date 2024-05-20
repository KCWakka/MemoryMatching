import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel implements ActionListener {
    private JTextField name1;
    private JTextField name2;
    private JTextField theme;
    private JButton submitButton;
    private JButton clearButton;
    private JFrame enclosingFrame;
    public WelcomePanel (JFrame frame) {
        enclosingFrame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == submitButton) {
                if (name1.getText().equals("") || name2.getText().equals("") || theme.getText().equals("") || name1.getText().equals("Please enter all the box to enter the game!")) {
                    name1.setText("Please enter all the box to enter the game!");
                } else {
                    String p1Name = name1.getText();
                    String p2Name = name2.getText();
                    String themes = theme.getText().toLowerCase();
                    enclosingFrame.setVisible(false);
                }
            } else if (button == clearButton) {
                name1.setText("");
                name2.setText("");
                theme.setText("");
            }
        }
    }
}
