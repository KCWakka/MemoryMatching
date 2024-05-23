import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements MouseListener, ActionListener {
    private BufferedImage background;
    private Player players;
    private ArrayList<Card> cards;
    public GraphicsPanel(String p1Name, String p2Name, String theme) {
        try {
            background = ImageIO.read(new File("src/image.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        players = new Player(p1Name, p2Name);
        cards = new ArrayList<>();
        if (theme.equals("pokemon")) {
           for (int i = 10; i < 18; i++) {
               String str = "src/";
               str += i + ".png";
               Card card = new Card(str, "src/back2.png", 0, 0);
               cards.add(card);
           }
        } else if (theme.equals("tarot")){
            for (int i = 0; i < 8; i++) {
                String str = "src/";
                str += i + ".png";
                Card card = new Card(str, "src/back.png", 0, 0);
                cards.add(card);
            }
        }
        addMouseListener(this);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Times New Roman", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString(players.getPlayer1Name() + "'s scores: " + players.getP1score(), 0, 0);
        g.drawString(players.getPlayer2Name() + "'s scores: " + players.getP2score(), 1000, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mouseClickLocation = e.getPoint();
        boolean onCard = false;
        int index = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).cardRect().contains(mouseClickLocation)) {
                onCard = true;
                index = i;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
