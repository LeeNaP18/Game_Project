/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_project;

/**
 *
 * @author victus
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {

    private int x, y;
    private boolean jumping;
    private int jumpHeight;
    private Image playerImage;
    private int gravity = 1;
    private int velocityY = 0;
    private final int groundY = 240;
    private PlayerHealth health;
    private boolean hit;
    private long hitTime; // Variable that stores the time of the collision

    public Player() {
        x = 50;
        y = groundY;
        jumpHeight = -18;
        playerImage = new ImageIcon(getClass().getResource("/img/chihiro1-1.gif")).getImage();
        health = new PlayerHealth(100); // Use PlayerHealth with the default health set to 100.
        hit = false;
    }

    public void jump() {
        if (!jumping) {
            jumping = true;
            velocityY = jumpHeight;
        }
    }

    public void update() {
        if (jumping) {
            y += velocityY;
            velocityY += gravity;

            if (y >= groundY) {
                y = groundY;
                velocityY = 0;
                jumping = false;
            }
        }

        // Reset the collision state after 200 milliseconds.
        if (hit && System.currentTimeMillis() - hitTime > 200) {
            hit = false;
        }
    }

    public void draw(Graphics g) {
        if (hit) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, 1000, 600);
        }

        g.drawImage(playerImage, x - 100, y - 47, 350, 260, null);
        health.draw(g, 20, 20); // Draw health over players
    }

    public void decreaseHealth() {
        health.reduceHealth(10); // Call a function from PlayerHealth
        hit = true;
        hitTime = System.currentTimeMillis(); // Capture the time of the collision
    }

    public int getHealth() {
        return health.getHealth(); // Restores health from PlayerHealth
    }

    public void resetHealth() {
        health.resetHealth(); // Reset health back to 100
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return 50;
    }

    public int getHeight() {
        return 150;
    }
}
