import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable
{
    int x, y, id, yDirection;

    Rectangle paddle;

    // Constructor
    public Paddle(int x, int y, int id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        paddle = new Rectangle(x, y, 10, 50);
    }

    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            default:
                System.out.println("\u001B[31mNot valid ID in paddle constructor\u001B[0m");
                break;

            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {setYDirection(-1);}
                else if (e.getKeyCode() == KeyEvent.VK_S) {setYDirection(1);}

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {setYDirection(-1);}
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {setYDirection(1);}
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            default:
                System.out.println("\u001B[31mNot valid ID in paddle constructor\u001B[0m");
                break;

            case 1:
                if (e.getKeyCode() == e.VK_W) {setYDirection(0);}
                else if (e.getKeyCode() == e.VK_S) {setYDirection(0);}

            case 2:
                if (e.getKeyCode() == e.VK_UP) {setYDirection(0);}
                else if (e.getKeyCode() == e.VK_DOWN) {setYDirection(0);}
                break;
        }
    }

    public void move()
    {
        paddle.y += yDirection;
        if (paddle.y <= 15)
        {
            paddle.y = 15;
        }
        else if (paddle.y >= 340)
        {
            paddle.y = 340;
        }
    }

    public void draw(Graphics g)
    {
        switch (id)
        {
            default:
                System.out.println("\u001B[31mNot valid ID in paddle constructor\u001B[0m");
                break;

            case 1:
                g.setColor(Color.CYAN);
                g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
                break;

            case 2:
                g.setColor(Color.PINK);
                g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
                break;
        }
    }

    // method to set yDirection
    public void setYDirection(int yDir) {yDirection = yDir;}

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(7);
            }
        } catch(Exception e) { System.err.println(e.getMessage()); }
    }
}

