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

public class Background {
    private Image backgroundImage;

    public Background() {
        // โหลดภาพพื้นหลังจากโฟลเดอร์ img
        backgroundImage = new ImageIcon(getClass().getResource("/img/bg-3.jpg")).getImage();
    }

    public void draw(Graphics g, int width, int height) {
        // วาดภาพพื้นหลังให้ครอบคลุมทั้งหน้าจอ
        g.drawImage(backgroundImage, 0, -70, width, height, null);
    }
}
