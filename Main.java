import javax.swing.*;

public class Main {

    static int  width = 800 ,height = 600;
    public static void main(String[] args) {

        JFrame window = new JFrame("Floppy bird");
        window.setSize(width , height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new GamePanel());
        window.setVisible(true);

    }
}