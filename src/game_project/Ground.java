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
    private double maxSpeed = 20.0; // ความเร็วสูงสุด  
    private double acceleration = 0.010; // อัตราเร่ง  
    private int screenWidth; // ความกว้างหน้าจอ  
    private int groundHeight; // ความสูงของภาพพื้น  
    private int groundWidth; // ความกว้างของภาพพื้น  

    public Ground(int screenWidth) {  
        // โหลดภาพพื้นจากโฟลเดอร์ img  
        groundImage = new ImageIcon(getClass().getResource("/img/ground-1 (2).png")).getImage();  

        // กำหนดค่าเริ่มต้นของ x1 และ x2  
        this.screenWidth = screenWidth;  
        this.groundHeight = groundImage.getHeight(null); // เก็บความสูงของภาพพื้น
        this.groundWidth = groundImage.getWidth(null); // เก็บความกว้างของภาพพื้น
        x1 = 0;  
        x2 = groundWidth; // ตั้งค่า x2 ให้ถัดจาก x1 เพื่อสร้างภาพลูป  
    }  

    public void update() {  
        // เพิ่มความเร็วขึ้นเรื่อยๆ จนถึงความเร็วสูงสุด  
        if (speed < maxSpeed) {  
            speed += acceleration; // เพิ่มความเร็วอย่างค่อยเป็นค่อยไป  
        }  

        // พิมพ์ค่าความเร็วในคอนโซลเพื่อดูการเปลี่ยนแปลง  
        System.out.println("Current speed: " + speed);  

        // เลื่อนตำแหน่ง x1 และ x2 ไปทางซ้ายตามความเร็ว  
        x1 -= speed;  
        x2 -= speed;  

        // ตรวจสอบว่าภาพแรกออกจากขอบจอซ้ายหรือยัง ถ้าออกแล้ว ให้รีเซ็ตตำแหน่งใหม่  
        if (x1 + groundWidth <= 0) {  
            x1 = x2 + groundWidth;  
        }  

        // ตรวจสอบภาพที่สองเช่นกัน  
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


