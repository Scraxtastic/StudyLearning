package Pong;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    public Window(){
        this.setTitle("Pong");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setUndecorated(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setPreferredSize(new Dimension(500, 500));

//        this.pack();
    }
}
