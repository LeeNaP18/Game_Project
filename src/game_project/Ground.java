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
    private int x1, x2; // ตำแหน่งของภาพสองภาพที่ใช้ลูป
    private int speed = 5; // ความเร็วในการเลื่อนพื้น
    private int screenWidth; // ความกว้างหน้าจอ

    public Ground(int screenWidth) {
        // โหลดภาพพื้นจากโฟลเดอร์ img
        groundImage = new ImageIcon(getClass().getResource("/img/ground-1 (2).png")).getImage();
        
        // กำหนดค่าเริ่มต้นของ x1 และ x2
        this.screenWidth = screenWidth;
        x1 = 0;
        x2 = screenWidth; // ให้ x2 อยู่ถัดจาก x1 เพื่อสร้างภาพลูป
    }

    public void update() {
        // เลื่อนตำแหน่ง x1 และ x2 ไปทางซ้ายตามความเร็ว
        x1 -= speed;
        x2 -= speed;

        // ตรวจสอบว่าภาพแรกออกจากขอบจอซ้ายหรือยัง ถ้าออกแล้ว ให้รีเซ็ตตำแหน่งใหม่
        if (x1 + screenWidth <= 0) {
            x1 = x2 + screenWidth;
        }

        // ตรวจสอบภาพที่สองเช่นกัน
        if (x2 + screenWidth <= 0) {
            x2 = x1 + screenWidth;
        }
    }

    public void draw(Graphics g) {
        // วาดภาพพื้นสองภาพเรียงต่อกันเต็มหน้าจอ
        g.drawImage(groundImage, x1, 410, screenWidth, groundImage.getHeight(null), null);
        g.drawImage(groundImage, x2, 410, screenWidth, groundImage.getHeight(null), null);
    }
}


