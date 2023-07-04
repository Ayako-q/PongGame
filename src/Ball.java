import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball implements Runnable
{
    // default properties
    int x, y, xDirection, yDirection;

    // Variables to store players score
    int p1Score, p2Score;

    Paddle p1 = new Paddle(10, 25, 1);
    Paddle p2 = new Paddle(485, 25, 2);

    Rectangle ball;

    public Ball(int x, int y)
    {
        p1Score = p2Score = 0;
        this.x = x;
        this.y = y;

        // Seting ball to move randomly
        // For X direction:
        Random rand = new Random();
        int rXDirection = rand.nextInt(2);
        if (rXDirection == 0)
            rXDirection--;
        setXDirection(rXDirection);

        // For Y direction:
        int rYDirection = rand.nextInt(2);
        if (rYDirection == 0)
            rYDirection--;
        setYDirection(rYDirection);

        ball = new Rectangle(this.x, this.y, 15, 15);
    }

    public void setXDirection(int Dir) {xDirection = Dir;}

    public void setYDirection(int Dir) {yDirection = Dir;}

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(ball.x, ball.y, ball.width, ball.height);
    }

    public void collision() // method to check if ball hits the paddle and then change its direction
    {
        if(ball.intersects(p1.paddle))
            setXDirection(+1); // change to the right

        if(ball.intersects(p2.paddle))
            setXDirection(-1); // change to the left
    }

    public void move()
    {
        collision();
        ball.x += xDirection;
        ball.y += yDirection;

        //bounce the ball when it hits the edge of the screen
        if (ball.x <= 0)
        {
            setXDirection(1);
            p2Score++;
            JOptionPane.showMessageDialog(null, "P1 Score: " + p1Score + "\nP2 Score: " + p2Score);
        }
        else if (ball.x >= 485) {
            setXDirection(-1);
            p1Score++;
            JOptionPane.showMessageDialog(null, "P1 Score: " + p1Score + "\nP2 Score: " + p2Score);
        }

        else if (ball.y <= 15) {
            setYDirection(+1);
        }

        else if (ball.y >= 385) {
            setYDirection(-1);
        }
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                move();
                Thread.sleep(8);
            }
        }catch(Exception e) { System.err.println(e.getMessage()); }

    }
}
