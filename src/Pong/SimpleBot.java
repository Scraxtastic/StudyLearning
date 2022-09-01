package Pong;


public class SimpleBot {

    private Rectangle paddle;

    public SimpleBot(Rectangle paddle) {
        this.paddle = paddle;
    }

    public void update(float delta, Rectangle ball, Rectangle other) {
        if (paddle.y > ball.y) {
            paddle.move(delta, true, false);
            if (paddle.y < ball.y)
                paddle.y = ball.y;
        }
        if(paddle.y < ball.y  ) {
            paddle.move(delta, false, true);
            if (paddle.y > ball.y)
                paddle.y = ball.y;
        }
    }
}
