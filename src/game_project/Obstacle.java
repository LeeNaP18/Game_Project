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
    private double maxSpeed = 20; // ความเร็วสูงสุด
    private double acceleration = 0.010; // อัตราเร่ง

    public Obstacle(int screenHeight, int desiredWidth, int desiredHeight,double speed) {
        // โหลดรูปภาพ
        this.image = new ImageIcon(getClass().getResource("/img/obstacle.png")).getImage();

        // ตรวจสอบว่าโหลดรูปภาพสำเร็จ
        if (image != null) {
            // ตั้งค่าขนาดของอุปสรรคตามที่ต้องการ
            this.width = desiredWidth;  
            this.height = desiredHeight; 
            
            // กำหนดตำแหน่งเริ่มต้น x ให้อยู่ที่นอกจอ (ทางขวา)
            int n = (int)(Math.random() * 2500) + 1000+(int)(150*this.speed);
            this.x = n; // ค่าตำแหน่งเริ่มต้นที่นอกจอ
            // ตำแหน่ง y ให้สุ่มสูงจากพื้น
            this.y = screenHeight - height; // ใช้ตำแหน่ง y ที่จะอยู่บนพื้น
        } else {
            System.out.println("Failed to load image.");
        }

        // ปรับขนาดรูปภาพให้ตรงกับขนาดที่ตั้งไว้
        this.image = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        
        // ตั้งค่าความเร็วเริ่มต้น
        this.speed = speed; // เริ่มต้นที่ 1
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
        // ตรวจสอบการชนระหว่างอุปสรรคกับตัวละคร
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

    // ฟังก์ชันสร้างอุปสรรคหลายตัว
//    public static Obstacle[] makeObstacles(int size, int screenHeight, int desiredWidth, int desiredHeight) {
//        Obstacle[] obstacles = new Obstacle[size];
//        for (int i = 0; i < size; i++) {
//            obstacles[i] = new Obstacle(screenHeight, desiredWidth, desiredHeight);
//            // ปรับตำแหน่งเริ่มต้นของแต่ละอุปสรรค
//            obstacles[i].x += i * 500; // ตั้งค่าให้ห่างกัน 500 หน่วย
//        }
//        return obstacles;
//    }

    public double getSpeed() {
        return speed;
    }
}