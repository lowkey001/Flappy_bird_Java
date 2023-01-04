import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;


public class ImagePanel extends JPanel{
    private Image image;
    private int width, height;
    public ImagePanel(Image image, int width, int height) {
        this.image = (Image)image;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height, this);
        Flappy_into.flappy_birdy.repaint(g);
    }
}
