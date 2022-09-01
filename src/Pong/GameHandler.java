package Pong;

import javax.swing.*;
import java.awt.*;

public abstract class GameHandler extends JPanel {
    long starttime = System.currentTimeMillis();
    private boolean running = false;
    protected Window window;
    int destinatedFramerate = 144;
    float timescale = 1;

    public GameHandler(Window window){
        this.window = window;
        this.setBackground(Color.gray);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        render(g);
    }

    public abstract void render(Graphics g);

    public abstract void tick(float delta);

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if(running) return;
        new TTick().start();
        running = true;
    }

    public class TTick extends Thread {
        private TTick t = null;
        long lastFrameTime = System.currentTimeMillis();

        public TTick() {

        }

        public void run() {
            while (running) {
                try {
                    float delta = ((float) (System.currentTimeMillis() - lastFrameTime)) / 1000 * timescale;
                    tick(delta);
                    repaint();
                    int millis = 1000/destinatedFramerate;
                    lastFrameTime = System.currentTimeMillis();
                    sleep(millis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
