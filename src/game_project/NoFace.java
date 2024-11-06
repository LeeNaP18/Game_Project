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

public class NoFace {

    private int x, y;
    private Image nofaceImage;

    public NoFace(int windowWidth, int groundY) {
        this.x = windowWidth - 150; // Set the right side of the screen position
        this.y = groundY - 100; // Set to stand on the ground
        this.nofaceImage = new ImageIcon(getClass().getResource("/img/noface.png")).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(nofaceImage, 620, 90, 430, 480, null);
    }
}
