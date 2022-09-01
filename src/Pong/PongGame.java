package Pong;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends GameHandler {
    static boolean paddle1Up = false;
    static boolean paddle1Down = false;
    static boolean paddle2Up = false;
    static boolean paddle2Down = false;
    static boolean otherMode = false;
    private PlayGround playGround;
    private float lastDelta = 0;
    private int frameRate = 0;
    private int frameCount = 0;
    private int fpsUpdateTime = 1000;
    private long nextFrameTime = 0;
    static int leftScore = 0, rightScore = 0;

    public PongGame(Window window) {
        super(window);
        this.destinatedFramerate = 144;
        window.getContentPane().add(this);
        window.addKeyListener(keyListener);
        window.setSize(new Dimension(1920, 1080));
        window.setLocationRelativeTo(null);
        playGround = new PlayGround(40, 40, 1920 - 80, 1080 - 100);
        playGround.color = Color.lightGray;
        playGround.withSimpleBot(true, true);
        super.start();
    }

    @Override
    public void render(Graphics g) {
        calcFPS();
        g.setColor(Color.black);
        playGround.paint(g);
        renderUI(g);
    }

    public void renderUI(Graphics g) {
        int deltaTextWidth = g.getFontMetrics().stringWidth(lastDelta + "");
        g.drawString(lastDelta + "", 1900 - deltaTextWidth, 13);
        g.drawString(frameRate + "", 2, 13);

        g.drawString(String.format("Player1: %b, %b", paddle1Up, paddle1Down), 35, 30);
        String player2Text = String.format("Player2: %b, %b", paddle2Up, paddle2Down);
        int player2TextWidth = g.getFontMetrics().stringWidth(player2Text);
        g.drawString(player2Text, 1885 - player2TextWidth, 30);
        String scoreText = String.format("%d - %d", leftScore, rightScore);
        int scoreTextWidth = g.getFontMetrics().stringWidth(scoreText);
        g.drawString(scoreText, window.getWidth()/2 - scoreTextWidth/2, 30);
    }


    @Override
    public void tick(float delta) {
        lastDelta = delta;
        playGround.update(delta);
    }

    private void calcFPS() {
        frameCount++;
        if (System.currentTimeMillis() > nextFrameTime) {
            frameRate = frameCount;
            frameCount = 0;
            nextFrameTime = System.currentTimeMillis() + fpsUpdateTime;
        }
    }

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                paddle1Up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                paddle1Down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                paddle2Up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                paddle2Down = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                otherMode = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                paddle1Up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                paddle1Down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                paddle2Up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                paddle2Down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                otherMode = false;
            }
        }
    };

}
