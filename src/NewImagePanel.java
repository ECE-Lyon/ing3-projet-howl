import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NewImagePanel extends JPanel {

    private BufferedImage img;

    public NewImagePanel(String movie) {
        try {
            img = ImageIO.read(RemoveMovie.class.getResource("/resources/"+movie));
        } catch (IOException ex) {
            System.out.println("Could not load image");
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 160);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}