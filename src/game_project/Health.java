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

    public Health(int initialHealth) {
        this.health = initialHealth;
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
