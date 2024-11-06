/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_project;

/**
 *
 * @author victus
 */

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Obstacle {
    private Image image; 
    private double x; 
    private int y; 
    private int width; 
    private int height; 
    private double speed; 
    private double maxSpeed = 40; 
    private double acceleration = 0.010; 

    public Obstacle(int screenHeight, int desiredWidth, int desiredHeight,double speed) {
        // Load image
        this.image = new ImageIcon(getClass().getResource("/img/obstacle.png")).getImage();

        // Verify that the image has loaded successfully.
        if (image != null) {
            // Set the size of the obstacle as desired.
            this.width = desiredWidth;  
            this.height = desiredHeight; 
            
            // Set the initial x position to be off-screen (right).
            int n = (int)(Math.random() * 2500) + 1000+(int)(150*this.speed);
            this.x = n; // Default off-screen position value
            // Position y is randomly selected from the ground.
            this.y = screenHeight - height; // Use the y position to be on the ground.
        } else {
            System.out.println("Failed to load image.");
        }

        // Resize the image to match the specified size.
        this.image = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        
        // Set default speed
        this.speed = speed; 
    }

    public void update() {
        // Gradually increase speed until reaching maximum speed.
        if (speed < maxSpeed) {
            speed += acceleration; // Increase speed gradually
        }

        // Move the obstacle to the left
        this.x -= speed;
        
        // If the obstacle falls off the screen, return to off-screen.
        if (this.x + this.width < 0) {
            int n = (int)(Math.random() * 1100) + 1000+(int)(150*this.speed);
            this.x = n;
        }
    }

    public void draw(Graphics g) {
        // Draw obstacles on the screen
        if (image != null) {
            g.drawImage(image, (int)x, y, null); // Draw obstacles with specified dimensions.
        } else {
            System.out.println("Image is null, cannot draw obstacle.");
        }
    }

    public boolean checkCollision(int characterX, int characterY, int characterWidth, int characterHeight) {
        // Check for collisions between obstacles and characters.
        return x < characterX + characterWidth &&
               x + width > characterX &&
               y < characterY + characterHeight &&
               y + height > characterY;
    }

    public int getX() {
        return (int)x;
    }

    public int getWidth() {
        return width;
    }

    public double getSpeed() {
        return speed;
    }
}