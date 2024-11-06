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
    private Image[] backgroundImages; // Multiple background images
    private int changeThresholdSpeed; // The speed required to change the background
    private boolean isChanged; // Check if the background has changed.
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

    // Added a method to change the background image.
    public void setBackgroundImage(int speed) {
        if (speed >= changeThresholdSpeed && !isChanged) {
            currentBackgroundImage = backgroundImages[1]; // Change to bg-1
            isChanged = true; // Set as background changed
        } else if (speed < changeThresholdSpeed) {
            currentBackgroundImage = backgroundImages[0]; // Back to bg-3
            isChanged = false; // Set as if the background has not changed.
        }
    }

    public void updateBackground(int currentSpeed) {
        
    }

    public void draw(Graphics g, int width, int height) {
        g.drawImage(currentBackgroundImage, 0, -70, width, height, null);
    }
}
