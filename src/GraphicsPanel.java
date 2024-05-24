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
    private boolean isTarot;
    public GraphicsPanel(String p1Name, String p2Name, String theme) {
        int x = 10;
        int y = 60;
        if (theme.equals("pokemon")) {
           isTarot = false;
        } else if (theme.equals("tarot")) {
            isTarot = true;
        }
        try {
            if (theme.equals("pokemon")) {
                background = ImageIO.read(new File("src/pokemon.jpg"));
            } else if (theme.equals("tarot")) {
                background = ImageIO.read(new File("src/tarot2.jpg"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        players = new Player(p1Name, p2Name);
        cards = new ArrayList<>();
        ArrayList<Card> temp = new ArrayList<>();
        if (!isTarot) {
           for (int i = 10; i < 18; i++) {
               String str = "src/";
               str += i + ".png";
               Card card = new Card(str, "src/back2.png", 0, 0);
               Card card2 = new Card(str, "src/back2.png", 0, 0);
               temp.add(card);
               temp.add(card2);
           }
        } else {
            for (int i = 0; i < 8; i++) {
                String str = "src/";
                str += i + ".png";
                Card card = new Card(str, "src/back.png", 0, 0);
                Card card2 = new Card(str, "src/back.png", 0, 0);
                temp.add(card);
                temp.add(card2);
            }
        }
        while (temp.size() > 0) {
            if (x >= 900) {
                x = 10;
                y += 240;
            }
            int random = (int) (Math.random() * temp.size());
            temp.get(random).setxCoord(x);
            temp.get(random).setyCoord(y);
            cards.add(temp.remove(random));
            x+= 250;
        }
        addMouseListener(this);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0 , null);
        g.setFont(new Font("Times New Roman", Font.BOLD, 35));
        if (isTarot) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.RED);
        }
        g.drawString(players.getPlayer1Name() + ": " + players.getP1score(), 1, 30);
        g.drawString(players.getPlayer2Name() + ": " + players.getP2score(), 700, 30);
        if (players.isPlayer1()) {
            g.drawString(players.getPlayer1Name() + "'s turn! ", 350, 30);
        } else {
            g.drawString(players.getPlayer2Name() + "'s turn! ", 350, 30);
        }
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).isFront()) {
                g.drawImage(cards.get(i).getImage(), cards.get(i).getXCoord(), cards.get(i).getYCoord(), null);
            } else {
                g.drawImage(cards.get(i).getBack(), cards.get(i).getXCoord(), cards.get(i).getYCoord(), null);
            }
        }
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
