import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel implements ActionListener {
    private JTextField name1;
    private JTextField name2;
    private JTextField theme;
    private JButton submitButton;
    private JButton clearButton;
    private JFrame enclosingFrame;
    private BufferedImage rick;
    private BufferedImage image;
    public WelcomePanel (JFrame frame) {
        enclosingFrame = frame;
        name1 = new JTextField(10);
        name2 = new JTextField(10);
        theme = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        try {
            rick = ImageIO.read(new File("src/Rick.png"));
            image = ImageIO.read(new File("src/Game.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        add(name1);
        add(name2);
        add(theme);
        add(submitButton);
        add(clearButton);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("Enter Player 1 and Player 2's Name", 20, 20);
        g.setFont(new Font("Times New Roman", Font.BOLD, 16));
        g.drawString("P1's Name", 20, 65);
        g.drawString("P2's Name", 20, 90);
        name1.setLocation(100, 50);
        name2.setLocation(100, 75);
        theme.setLocation(100, 125);
        submitButton.setLocation(75, 150);
        clearButton.setLocation(175, 150);
        g.drawImage(rick, 250, 150, null);
        g.drawImage(image, 250, 80, null);
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
