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

public class Health {
    private int health;
    private int initialHealth; // ตัวแปรเก็บสุขภาพเริ่มต้น

    public Health(int initialHealth) {
        this.initialHealth = initialHealth; // เก็บค่าพลังชีวิตเริ่มต้น
        this.health = initialHealth; // ตั้งค่าพลังชีวิตให้เท่ากับค่าเริ่มต้น
    }

    public void reduceHealth(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0; // ไม่ให้ค่าพลังชีวิตต่ำกว่า 0
        }
    }

    public int getHealth() {
        return health;
    }

    // ฟังก์ชันรีเซ็ตสุขภาพ
    public void resetHealth(int n) {
        this.health = initialHealth; // รีเซ็ตสุขภาพกลับไปที่ค่าเริ่มต้น
    }

    public void draw(Graphics g, int x, int y) {
        // แสดงพลังชีวิต
        g.setColor(Color.RED); // กำหนดสีของข้อความสุขภาพ
        g.drawString("Health: " + health, x, y); // ปรับตำแหน่งตามที่ต้องการ
        
        // วาดแทบพลังชีวิต
        g.setColor(Color.GREEN); // สีเขียวสำหรับแทบพลังชีวิต
        g.fillRect(x, y + 10, health * 2, 20); // แทบพลังชีวิต (ปรับขนาดตามที่ต้องการ)

        // กรอบแทบพลังชีวิต
        g.setColor(Color.BLACK); // กำหนดสีกรอบ
        g.drawRect(x, y + 10, 200, 20); // วาดกรอบแทบพลังชีวิต
    }
}
