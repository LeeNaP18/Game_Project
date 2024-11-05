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
    private double x1, x2; // ใช้ double เพื่อให้การเคลื่อนไหวสมูทมากขึ้น  
    private double speed = 1.0; // ความเร็วเริ่มต้นเป็น double  
    private double maxSpeed = 40.0; // ความเร็วสูงสุด  
    private double acceleration = 0.010; // อัตราเร่ง  
    private int screenWidth; // ความกว้างหน้าจอ  
    private int groundHeight; // ความสูงของภาพพื้น  
    private int groundWidth; // ความกว้างของภาพพื้น  

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
        // วาดภาพพื้นสองภาพเรียงต่อกันเต็มหน้าจอ  
        g.drawImage(groundImage, (int)x1, 410, screenWidth, groundHeight, null);  
        g.drawImage(groundImage, (int)x2, 410, screenWidth, groundHeight, null);  
    }  

    // ฟังก์ชันสำหรับคืนค่าความสูงของพื้น  
    public int getHeight() {  
        return groundHeight; // คืนค่าความสูงของพื้น  
    }

    // ฟังก์ชันสำหรับคืนค่าความเร็วของพื้น  
    public double getSpeed() {  
        return speed; // คืนค่าความเร็วของพื้น  
    }  
    
        // ฟังก์ชันรีเซ็ตพื้นกลับไปที่ค่าเริ่มต้น
    public void reset() {
        speed = 1.0; // รีเซ็ตความเร็วกลับไปที่ค่าเริ่มต้น
        x1 = 0;      // รีเซ็ตตำแหน่ง x1 กลับไปที่ 0
        x2 = groundWidth; // ตั้งค่า x2 ให้ถัดจาก x1 เพื่อสร้างภาพลูป
    }

}


