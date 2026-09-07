

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {


    Timer time;
    Player player = new Player();
    boolean jump = false;

    // pipe variables
    int bottomPipeY = 400 + new Random().nextInt(100);
    int topPipeheight = 50 + new Random().nextInt(250);


    Pipe topPipe = new Pipe(Main.width , 0 , 50 , topPipeheight);
    Pipe bottPipe = new Pipe(Main.width , bottomPipeY , 50 , 800);

    boolean pause = false;

    public GamePanel() {
        setBackground(Color.LIGHT_GRAY);
        addKeyListener(this);
        setFocusable(true);
        time = new Timer(16, a -> {
            update();
            repaint();
        });

        time.start();
    }



    void update()
    {
        if (jump) {
            player.y -= 10;
        }else {
            player.y += 18;
        }


        topPipe.x -= 10;
        bottPipe.x -= 10;
        if (topPipe.x  <=  -100)
        {
            topPipe.x = Main.width;
            bottPipe.x = Main.width;
            bottomPipeY = 400 + new Random().nextInt(100);
            topPipeheight =  50 + new Random().nextInt(250);

            topPipe.height = topPipeheight;
            bottPipe.y = bottomPipeY;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        // player
        g.fillRect(player.x,player.y,player.width,player.height);

        // pipes
        g.fillRect(topPipe.x,topPipe.y,topPipe.width,topPipe.height);
        g.fillRect(bottPipe.x,bottPipe.y,bottPipe.width,bottPipe.height);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            jump = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_P){

            if (pause)
               time.start();
            else
                time.stop();

            pause = !pause;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            jump = false;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


}
