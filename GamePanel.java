

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {


    Timer time;
    Player player = new Player();

    Image bird;
    
    boolean jump = false;
    JLabel label = new JLabel("x: " + player.x + "y: " + player.y, SwingConstants.CENTER);  
 


    // pipe variables
    int bottomPipeY = 200 + new Random().nextInt(200);
    int topPipeheight = bottomPipeY - 100 - new Random().nextInt(50);


    Pipe topPipe = new Pipe(Main.width , 0 , 50 , topPipeheight);
    Pipe bottPipe = new Pipe(Main.width , bottomPipeY , 50 , 800);


    JLabel label_pipe = new JLabel("pipe- x: " + topPipe.x + "y: " + topPipe.y, SwingConstants.CENTER); 
    boolean pause = false;

    public GamePanel() {

        try{
           bird = ImageIO.read(new File("bird.png"));
        }catch(Exception e){
            System.out.println(e);
        }

        setBackground(Color.LIGHT_GRAY);
        addKeyListener(this);
        add(label);
        add(label_pipe);
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
            bottomPipeY = 100 + new Random().nextInt(450);
            topPipeheight = bottomPipeY - 100 - new Random().nextInt(100);


            topPipe.height = topPipeheight;
            bottPipe.y = bottomPipeY;
        }


        if (player.collosionTop(topPipe) || player.collosionBott(bottPipe) || player.y >= Main.height-40 || player.y <= 0) {
            pause();
        }

        label.setText("x: " + player.x + " y: " + player.y);
        label_pipe.setText("pipe- x: " + topPipe.x + " y: " + topPipe.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);

       g.drawImage(bird, player.x, player.y, player.width, player.height,this);
        // player
        //g.fillRect(player.x,player.y,player.width,player.height);

    
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

              pause();
        }

        if (e.getKeyCode() == KeyEvent.VK_R){
           resetart();
        }
    }



    void resetart(){
            player = new Player();
            topPipe = new Pipe(Main.width , 0 , 50 , topPipeheight);
            bottPipe = new Pipe(Main.width , bottomPipeY , 50 , 800);
            time.start();
    }

    void pause(){
        if (pause) {
            time.start();
        }else{
            time.stop();
        }

        pause = !pause;
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
