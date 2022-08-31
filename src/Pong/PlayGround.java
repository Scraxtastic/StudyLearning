package Pong;

import javax.swing.*;
import java.awt.*;

public class PlayField extends JComponent {
    float x, y, width, height;
    float thickness = 2;
    public PlayField(){

    }
    public PlayField(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}
