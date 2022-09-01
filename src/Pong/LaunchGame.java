package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LaunchGame {
    public static void main(String[] args) throws InterruptedException {
        new LaunchGame().start();
    }

    public void start() throws InterruptedException {
        Window window = new Window();
        new PongGame(window);
    }
}
