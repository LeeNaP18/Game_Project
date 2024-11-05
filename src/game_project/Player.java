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
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {

    private int x, y;
    private boolean jumping;
    private int jumpHeight;
    private Image playerImage;
    private int gravity = 1;
    private int velocityY = 0;
    private final int groundY = 240;
    private PlayerHealth health; // ใช้ PlayerHealth แทน Health
    private boolean hit;
    private long hitTime; // ตัวแปรเก็บเวลาที่เกิดการชน

    public Player() {
        x = 50;
        y = groundY;
        jumpHeight = -18;
        playerImage = new ImageIcon(getClass().getResource("/img/chihiro1-1.gif")).getImage();
        health = new PlayerHealth(100); // ใช้ PlayerHealth โดยตั้งสุขภาพเริ่มต้นที่ 100
        hit = false;
    }

    public void jump() {
        if (!jumping) {
            jumping = true;
            velocityY = jumpHeight;
        }
    }

    public void update() {
        if (jumping) {
            y += velocityY;
            velocityY += gravity;

            if (y >= groundY) {
                y = groundY;
                velocityY = 0;
                jumping = false;
            }
        }

        // รีเซ็ตสถานะการชนหลังผ่านไป 200 มิลลิวินาที
        if (hit && System.currentTimeMillis() - hitTime > 200) {
            hit = false;
        }
    }

    public void draw(Graphics g) {
        if (hit) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, 1000, 600);
        }

        g.drawImage(playerImage, x - 100, y - 47, 350, 260, null);
        health.draw(g, 20, 20); // วาดสุขภาพเหนือผู้เล่น
    }

    public void decreaseHealth() {
        health.reduceHealth(10); // เรียกใช้ฟังก์ชันจาก PlayerHealth
        hit = true;
        hitTime = System.currentTimeMillis(); // เก็บเวลาที่เกิดการชน
    }

    public int getHealth() {
        return health.getHealth(); // คืนค่าพลังชีวิตจาก PlayerHealth
    }

    public void resetHealth() {
        health.resetHealth(); // รีเซ็ตสุขภาพกลับไปที่ 100
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return 50;
    }

    public int getHeight() {
        return 150;
    }
}
