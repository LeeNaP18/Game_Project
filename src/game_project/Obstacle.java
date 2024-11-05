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
    private Image image; // รูปภาพของอุปสรรค
    private double x; // ตำแหน่ง x ของอุปสรรค
    private int y; // ตำแหน่ง y ของอุปสรรค
    private int width; // ความกว้างของอุปสรรค
    private int height; // ความสูงของอุปสรรค
    private double speed; // ความเร็วในการเคลื่อนที่ของอุปสรรค
    private double maxSpeed = 40; // ความเร็วสูงสุด
    private double acceleration = 0.010; // อัตราเร่ง

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
        // เพิ่มความเร็วขึ้นเรื่อยๆ จนถึงความเร็วสูงสุด
        if (speed < maxSpeed) {
            speed += acceleration; // เพิ่มความเร็วอย่างค่อยเป็นค่อยไป
        }

        // เคลื่อนที่อุปสรรคไปทางซ้าย
        this.x -= speed;
        
        // ถ้าอุปสรรคหลุดจากจอ ให้กลับไปที่นอกจอ
        if (this.x + this.width < 0) {
            int n = (int)(Math.random() * 1100) + 1000+(int)(150*this.speed);
            this.x = n;
        }
    }

    public void draw(Graphics g) {
        // วาดอุปสรรคบนหน้าจอ
        if (image != null) {
            g.drawImage(image, (int)x, y, null); // วาดอุปสรรคด้วยขนาดที่กำหนด
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
        return (int)x; // เปลี่ยนให้คืนค่าเป็น int
    }

    public int getWidth() {
        return width;
    }

    public double getSpeed() {
        return speed;
    }
}