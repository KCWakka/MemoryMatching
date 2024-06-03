import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements MouseListener, ActionListener {
    private BufferedImage background;
    private JButton resetButton;
    private Player players;
    private ArrayList<Card> cards;
    private boolean isTarot;
    private int card1;
    private int card2;
    private Timer timer;
    private int time;
    private boolean gameOver;
    private boolean wrong;
    private Clip music;
    private boolean once;
    public GraphicsPanel(String p1Name, String p2Name, String theme) {
        int x = 10;
        int y = 60;
        if (theme.equals("pokemon")) {
           isTarot = false;
        } else if (theme.equals("tarot")) {
            isTarot = true;
        }
        try {
            if (!isTarot) {
                background = ImageIO.read(new File("src/image/pokemon.jpg"));
            } else {
                background = ImageIO.read(new File("src/image/tarot.jpeg"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        players = new Player(p1Name, p2Name);
        cards = new ArrayList<>();
        ArrayList<Card> temp = new ArrayList<>();
        card1 = -1;
        card2 = -1;
        gameOver = false;
        resetButton = new JButton("RESET");
        resetButton.setVisible(false);
        add(resetButton);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);
        Dimension dimension = new Dimension(200, 150);
        resetButton.setPreferredSize(dimension);
        timer = new Timer(1000, this);
        once = true;
        timer.start();
        time = 0;
        wrong = false;
        if (!isTarot) {
           for (int i = 10; i < 18; i++) {
               String str = "src/image/";
               str += i + ".png";
               Card card = new Card(str, "src/image/back2.png", 0, 0);
               Card card2 = new Card(str, "src/image/back2.png", 0, 0);
               temp.add(card);
               temp.add(card2);
           }
        } else {
            for (int i = 0; i < 8; i++) {
                String str = "src/image/";
                str += i + ".png";
                Card card = new Card(str, "src/image/back.png", 0, 0);
                Card card2 = new Card(str, "src/image/back.png", 0, 0);
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
        playMusic();
    }
    @Override
    public void paintComponent(Graphics g) {
        if (gameOver) {
            try {
                background = ImageIO.read(new File("src/image/duolingo.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            g.drawImage(background, 0, 0, null);
            g.setFont(new Font("Times New Roman", Font.BOLD, 55));
            g.setColor(Color.white);
            if (players.getP1score() > players.getP2score()) {
                g.drawString("Game Over! ", 300, 130);
                g.drawString(players.getPlayer1Name() + " win!", 250, 180);
            } else if (players.getP1score() < players.getP2score()) {
                g.drawString("Game Over! ",300, 130);
                g.drawString(players.getPlayer2Name() + " win!", 250, 180);
            } else {
                g.drawString("Game Over! It was a tie!", 170, 130);
            }
            resetButton.setVisible(true);
            resetButton.setLocation(350,500);
            if (once) {
                music.stop();
                music.close();
                playGunShot();
                playMusic();
                once = false;
            }
        } else {
            once = true;
            resetButton.setVisible(false);
            gameOver = true;
            super.paintComponent(g);
            g.drawImage(background, 0, 0, null);
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
                if (cards.get(i).isFront()) {
                    g.drawImage(cards.get(i).getImage(), cards.get(i).getXCoord(), cards.get(i).getYCoord(), null);
                } else {
                    g.drawImage(cards.get(i).getBack(), cards.get(i).getXCoord(), cards.get(i).getYCoord(), null);
                    gameOver = false;
                }
            }
            if (time >= 2) {
                cards.get(card1).setIsFront();
                cards.get(card2).setIsFront();
                playCardFlip();
                players.setIsPlayer1();
                card1 = -1;
                card2 = -1;
                wrong = false;
                time = 0;
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
        if (!wrong) {
            Point mouseClickLocation = e.getPoint();
            boolean onCard = false;
            int index = -1;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).cardRect().contains(mouseClickLocation) && !cards.get(i).isFront()) {
                    onCard = true;
                    if (i != card1) {
                        index = i;
                    }
                }
            }
            if (onCard) {
                if (card1 == -1) {
                    card1 = index;
                    cards.get(index).setIsFront();
                    playCardFlip();
                } else {
                    if (index != -1) {
                        card2 = index;
                        cards.get(index).setIsFront();
                        playCardFlip();
                        if (cards.get(card1).getName().equals(cards.get(card2).getName())) {
                            players.addScore();
                            card1 = -1;
                            card2 = -1;
                        } else {
                            wrong = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == resetButton) {
                gameOver = false;
                ArrayList<Card> temp = new ArrayList<>();
                for (int i = 0; i < cards.size(); i++) {
                    cards.get(i).setIsFront();
                    temp.add(cards.remove(i));
                    i--;
                }
                int x = 10;
                int y = 60;
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
                try {
                    if (!isTarot) {
                        background = ImageIO.read(new File("src/image/pokemon.jpg"));
                    } else {
                        background = ImageIO.read(new File("src/image/tarot.jpeg"));
                    }
                } catch (IOException f) {
                    System.out.println(f.getMessage());
                }
                players.resetScore();
                music.stop();
                music.close();
                playMusic();
            }
        } else if (e.getSource() instanceof Timer) {
            if (wrong) {
                time++;
            }
        }
    }

    private void playCardFlip() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sound/CardFlip.wav").getAbsoluteFile());
            Clip cardFlipClip = AudioSystem.getClip();
            cardFlipClip.open(audioInputStream);
            cardFlipClip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void playGunShot() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sound/GunShot.wav").getAbsoluteFile());
            Clip gunShot = AudioSystem.getClip();
            gunShot.open(audioInputStream);
            gunShot.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void playMusic() {
        try {
            AudioInputStream audioInputStream;
            if (isTarot && !gameOver) {
                audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sound/StarrySky.wav").getAbsoluteFile());
            } else if (gameOver) {
                audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sound/spooky.wav").getAbsoluteFile());
            } else{
                audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sound/Pokemon.wav").getAbsoluteFile());
            }
            music = AudioSystem.getClip();
            music.open(audioInputStream);
            music.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
