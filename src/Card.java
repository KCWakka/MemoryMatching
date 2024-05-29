import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {
    private BufferedImage image;
    private BufferedImage back;
    private int xCoord;
    private int yCoord;
    private boolean isFront;
    private String name;
    public Card(String image, String back, int x, int y) {
        try {
            this.image = ImageIO.read(new File(image));
            this.back = ImageIO.read(new File(back));
        } catch (IOException e){
            System.out.println(e);
        }
        xCoord = x;
        yCoord = y;
        isFront = false;
        name = image;
    }
    public int getXCoord() {
        return xCoord;
    }
    public int getYCoord() {
        return yCoord;
    }
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage getBack() {
        return back;
    }

    public String getName() {
        return name;
    }

    public boolean isFront() {
        return isFront;
    }
    public void setIsFront() {
        isFront = !isFront;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Rectangle cardRect() {
        int imageHeight = getImage().getHeight();
        int imageWidth = getImage().getWidth();
        Rectangle rect = new Rectangle(xCoord, yCoord, imageWidth, imageHeight);
        return rect;
    }
}
