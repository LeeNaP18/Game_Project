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
    private int x; // ตำแหน่ง x ของอุปสรรค
    private int y; // ตำแหน่ง y ของอุปสรรค
    private int width; // ความกว้างของอุปสรรค
    private int height; // ความสูงของอุปสรรค
    private static final int SPEED = 5; // ความเร็วในการเคลื่อนที่ของอุปสรรค

    public Obstacle(int screenHeight, int desiredWidth, int desiredHeight) {
        // โหลดรูปภาพ
        this.image = new ImageIcon(getClass().getResource("/img/obstacle.png")).getImage();

        // ตรวจสอบว่าโหลดรูปภาพสำเร็จ
        if (image != null) {
            // ตั้งค่าขนาดของอุปสรรคตามที่ต้องการ
            this.width = desiredWidth;  
            this.height = desiredHeight; 
            
            // กำหนดตำแหน่งเริ่มต้น x ให้อยู่ที่นอกจอ (ทางขวา)
            this.x = 1000; // ความกว้างหน้าจอ (หรือค่าคงที่ที่คุณต้องการ)
            // ตำแหน่ง y ให้สุ่มสูงจากพื้น
            this.y = screenHeight - height; // ใช้ตำแหน่ง y ที่จะอยู่บนพื้น
        } else {
            System.out.println("Failed to load image.");
        }

        // ปรับขนาดรูปภาพให้ตรงกับขนาดที่ตั้งไว้
        this.image = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
    }

    public void update() {
        // เคลื่อนที่อุปสรรคไปทางซ้าย
        this.x -= SPEED;
        // ถ้าอุปสรรคหลุดจากจอ ให้กลับไปที่นอกจอ
        if (this.x + this.width < 0) {
            this.x = 1000; // กลับไปที่ตำแหน่งเริ่มต้นที่นอกจอ
        }
    }

    public void draw(Graphics g) {
        // วาดอุปสรรคบนหน้าจอ
        if (image != null) {
            g.drawImage(image, x, y, null); // วาดอุปสรรคด้วยขนาดที่กำหนด
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
        return x;
    }

    public int getWidth() {
        return width;
    }
}
