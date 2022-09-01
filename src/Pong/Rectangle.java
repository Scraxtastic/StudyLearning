package Pong;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JComponent {
    String name = "";
    float x, y, width, height;
    float xSpeed = 0;
    float ySpeed = 0;
    float speed = 0;
    Color color;
    public Rectangle(){

    }
    public Rectangle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(color != null)
            g.setColor(color);
        g.fillRect((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
    }
    public void paint(Graphics g, float scaling, float baseX, float baseY) {
        super.paint(g);
        if(color != null)
            g.setColor(color);
        float calcedX = baseX + (x-width/2)*scaling;
        float calcedY = baseY + (y-height/2)*scaling;
        g.fillRect((int)calcedX, (int)calcedY, (int)(width*scaling), (int)(height*scaling));
    }

    public boolean collides(Rectangle other){
        if(other.rightX() < this.leftX() || this.rightX() < other.leftX() ||  this.lowerY() < other.upperY() || other.lowerY() < this.upperY()) {
            return false;
        }
        return true;
    }

    public void update(float delta){
        x += xSpeed * delta;
        y += ySpeed * delta;
    }
    public void move(float delta, boolean up, boolean down){
        if(up){
            y -= delta*speed;
        }
        if(down){
            y += delta*speed;
        }
    }

    public float upperY(){
        return y-height/2;
    }

    public void setUpperY(float y){
        this.y = y+height/2;
    }

    public float lowerY(){
        return y+height/2;
    }

    public void setLowerY(float newY){
        y = newY - height/2;
    }

    public float rightX(){
        return x+width/2;
    }

    public void setRightX(float newX){
        x = newX - width/2;
    }
    public float leftX(){
        return x-width/2;
    }
    public void setLeftX(float newX){
        x = newX + width/2;
    }
}
