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
import java.util.Random;

public class Background {
    private Image currentBackgroundImage;
    private Image[] backgroundImages; // ภาพพื้นหลังหลายภาพ
    private int changeThresholdSpeed; // ความเร็วที่ต้องการเพื่อเปลี่ยนฉากหลัง
    private boolean isChanged; // ตรวจสอบว่าฉากหลังเปลี่ยนแล้วหรือยัง
    private Random random;

    public Background(int changeThresholdSpeed) {
        // Load multiple background images
        backgroundImages = new Image[] {
            new ImageIcon(getClass().getResource("/img/bg-3.jpg")).getImage(),
            new ImageIcon(getClass().getResource("/img/bg-1.gif")).getImage()
        };
        
        currentBackgroundImage = backgroundImages[0]; // Set the default as the first image.
        this.changeThresholdSpeed = changeThresholdSpeed;
        isChanged = false;
        random = new Random();
    }

    // เพิ่มเมธอดเพื่อเปลี่ยนภาพพื้นหลัง
    public void setBackgroundImage(int speed) {
        if (speed >= changeThresholdSpeed && !isChanged) {
            currentBackgroundImage = backgroundImages[1]; // เปลี่ยนเป็น bg-1
            isChanged = true; // ตั้งค่าเป็นว่าพื้นหลังเปลี่ยนแล้ว
        } else if (speed < changeThresholdSpeed) {
            currentBackgroundImage = backgroundImages[0]; // กลับไปเป็น bg-3
            isChanged = false; // ตั้งค่าเป็นว่าพื้นหลังยังไม่ได้เปลี่ยน
        }
    }

    public void updateBackground(int currentSpeed) {
        // ไม่ต้องทำอะไรที่นี่ ถ้าคุณใช้เมธอด setBackgroundImage
    }

    public void draw(Graphics g, int width, int height) {
        g.drawImage(currentBackgroundImage, 0, -70, width, height, null);
    }
}
