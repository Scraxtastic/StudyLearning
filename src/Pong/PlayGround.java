package Pong;

import javax.swing.*;
import java.awt.*;

public class PlayGround extends JComponent {
    float x, y, width, height;
    float scaledWidth, scaledHeight, scaledThickness;
    float thickness = 6;

    int leftScore = 0, rightScore = 0;
    boolean player1 = true, player2 = true;
    Color color;
    private Rectangle paddle1, paddle2, ball;
    private SimpleBot bot1, bot2;
    float defaultPlatfromspeed = 500;
    float scaling = 1;

    public PlayGround() {

    }

    public PlayGround(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        paddle1 = new Rectangle(20, height / 2, 20, 100);
        paddle1.speed = defaultPlatfromspeed;
        paddle1.color = Color.CYAN;
        paddle1.name = "Paddle1";
        paddle2 = new Rectangle(width - 20, height / 2, 20, 100);
        paddle2.speed = defaultPlatfromspeed;
        paddle2.color = Color.blue;
        paddle2.name = "Paddle2";
        ball = new Rectangle(width / 2, height / 2, 30, 30);
        ball.xSpeed = -1000;
        ball.color = Color.yellow;
        ball.name = "Ball";
        ball.speed = 1000;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (color != null)
            g.setColor(color);
        if (PongGame.otherMode) {
            if (ball.y != paddle1.y) {
                return;
            }
        }
        scaledWidth = width * scaling;
        scaledHeight = height * scaling;
        scaledThickness = thickness * scaling;
        g.fillRect((int) (x - scaledThickness), (int) (y - scaledThickness), (int) (scaledWidth + scaledThickness * 2), (int) scaledThickness);
        g.fillRect((int) (x - scaledThickness), (int) y, (int) scaledThickness, (int) (scaledHeight + scaledThickness));
        g.fillRect((int) (x + scaledWidth), (int) y, (int) scaledThickness, (int) scaledHeight);
        g.fillRect((int) x, (int) (y + scaledHeight), (int) (scaledWidth + scaledThickness), (int) scaledThickness);

        paddle1.paint(g, scaling, x, y);
        paddle2.paint(g, scaling, x, y);
        ball.paint(g, scaling, x, y);
        renderUI(g);
    }

    public void renderUI (Graphics g){
        g.setColor(Color.white);
        g.drawString(String.format("Ball y difs: %f %f", Math.abs(ball.y - paddle1.y), Math.abs(ball.y - paddle2.y)), (int)(x + 20), (int)(y + 20));
    }

    public void update(float delta) {
        paddleMovement(delta);
        ball.update(delta);
        ballCollisions(ball);
        ballPlatformCollisions(paddle1, paddle2, ball);
        keepPlatformInPlayground(paddle1);
        keepPlatformInPlayground(paddle2);
    }

    public void paddleMovement(float delta) {
        if (bot1 == null) player1 = true;
        if (player1)
            paddle1.move(delta, PongGame.paddle1Up, PongGame.paddle1Down);
        else {
            bot1.update(delta, ball, paddle1);
        }
        if (bot2 == null) player2 = true;
        if (player2)
            paddle2.move(delta, PongGame.paddle2Up, PongGame.paddle2Down);
        else {
            bot2.update(delta, ball, paddle2);
        }
    }

    public void keepPlatformInPlayground(Rectangle paddle) {
        if (paddle.upperY() < 0) {
            paddle.setUpperY(0);
        }
        if (paddle.lowerY() > height) {
            paddle.setLowerY(height);
        }
    }

    public void ballPlatformCollisions(Rectangle paddle1, Rectangle paddle2, Rectangle ball) {
        if (ball.collides(paddle1)) {
            ballPlatformCollision(ball, paddle1, true);
        }
        if (ball.collides(paddle2)) {
            ballPlatformCollision(ball, paddle2, false);
        }
    }

    private void ballPlatformCollision(Rectangle ball, Rectangle other, boolean left) {
        if (ball.collides(other)) {
            if (left) {
                ball.xSpeed = Math.abs(ball.xSpeed);
                ball.setLeftX(other.rightX());
            } else {
                ball.xSpeed = -Math.abs(ball.xSpeed);
                ball.setRightX(other.leftX());
            }
            if (ball.y == other.y) {
                ball.ySpeed = ball.speed * Random.randomFloat(-1, 1);
            } else {
                ball.ySpeed = (ball.speed * (ball.y - other.y) / (other.height / 2));
            }
        }
    }

    public void ballCollisions(Rectangle ball) {
        if (ball.upperY() < 0) {
            ball.setUpperY(0);
            ball.ySpeed = Math.abs(ball.ySpeed);
        }
        if (ball.lowerY() > this.height) {
            ball.setLowerY(this.height);
            ball.ySpeed = -Math.abs(ball.ySpeed);
        }
        if (ball.rightX() > this.width) {
            ball.x = this.width / 2;
            ball.ySpeed = 0;
            ball.y = this.height / 2;
            leftScore++;
            PongGame.leftScore++;
        }
        if (ball.leftX() < 0) {
            ball.x = this.width / 2;
            ball.ySpeed = 0;
            ball.y = this.height / 2;
            rightScore++;
            PongGame.rightScore++;
        }
    }

    public PlayGround withPlayers(boolean player1, boolean player2) {
        this.player1 = player1;
        this.player2 = player2;
        return this;
    }

    public PlayGround withSimpleBot(boolean paddle1Bot, boolean paddle2Bot) {
        if (paddle1Bot) {
            player1 = false;
            this.bot1 = new SimpleBot(paddle1);
        }
        if(paddle2Bot){
            player2 = false;
            this.bot2 = new SimpleBot(paddle2);
        }
        return this;
    }
}
