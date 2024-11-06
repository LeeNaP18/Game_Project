/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_project;

/**
 *
 * @author victus
 */
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ground {

    private Image groundImage;
    private double x1, x2;
    private double speed = 1.0;
    private double maxSpeed = 40.0;
    private double acceleration = 0.010;
    private int screenWidth;
    private int groundHeight;
    private int groundWidth;

    public Ground(int screenWidth) {
        // background image 
        groundImage = new ImageIcon(getClass().getResource("/img/ground-1 (2).png")).getImage();

        this.screenWidth = screenWidth;
        this.groundHeight = groundImage.getHeight(null); // Store the height of the floor image
        this.groundWidth = groundImage.getWidth(null); // Store the width of the background image
        x1 = 0;
        x2 = groundWidth; // Set x2 next to x1 to create a loop.
    }

    public void update() {
        // Gradually increase speed until reaching maximum speed.
        if (speed < maxSpeed) {
            speed += acceleration; // Increase speed gradually
        }

        System.out.println("Current speed: " + speed);

        // Move x1 and x2 to the left according to the velocity.
        x1 -= speed;
        x2 -= speed;

        // Check if the first image exits the left edge of the screen. If it does, reset the position. 
        if (x1 + groundWidth <= 0) {
            x1 = x2 + groundWidth;
        }

        // Check out the second picture as well. 
        if (x2 + groundWidth <= 0) {
            x2 = x1 + groundWidth;
        }
    }

    public void draw(Graphics g) {
        // Draw two background images in a row to fill the entire screen.
        g.drawImage(groundImage, (int) x1, 410, screenWidth, groundHeight, null);
        g.drawImage(groundImage, (int) x2, 410, screenWidth, groundHeight, null);
    }

    // Function for returning the height of the floor
    public int getHeight() {
        return groundHeight;
    }

    // Function for restoring ground speed
    public double getSpeed() {
        return speed;
    }

    // Reset function to default value
    public void reset() {
        speed = 1.0;
        x1 = 0;
        x2 = groundWidth;
    }

}
