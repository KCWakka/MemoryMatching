import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {
    private BufferedImage image;
    private BufferedImage back;
    private double xcord;
    private double ycord;
    private boolean isFront;
    public Card(String image, String back, double x, double y) {
        try {
            this.image = ImageIO.read(new File(image));
            this.back = ImageIO.read(new File(back));
        } catch (IOException e){
            System.out.println(e);
        }
        xcord = x;
        ycord = y;
        isFront = false;
    }
    public double getXcord() {
        return xcord;
    }
    public double getYcord() {
        return ycord;
    }
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage getBack() {
        return back;
    }
    public boolean isFront() {
        return isFront;
    }
    public void setIsFront() {
        isFront = !isFront;
    }
}
