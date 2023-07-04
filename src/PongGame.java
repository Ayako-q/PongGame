import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PongGame extends JFrame
{
    //screen size variables.
    int gWidth = 500;
    int gHeight = 400;
    Dimension screenSize = new Dimension(gWidth, gHeight);

    Image dbImage;
    Graphics dbGraphics;

    //ball object
    static Ball b = new Ball(250, 200);

    // Window constructor
    public PongGame()
    {
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setSize(screenSize);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(new AL());
    }

    public static void main(String[] args)
    {
        PongGame pg = new PongGame();

        Thread ball = new Thread(b);
        ball.start();
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);

        p2.start();
        p1.start();
    }

    @Override
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        draw(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void draw(Graphics g)
    {
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);

        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 15, 20);
        g.drawString(""+b.p2Score, 385, 20);

        repaint();
    }

    public class AL extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }

    }
}