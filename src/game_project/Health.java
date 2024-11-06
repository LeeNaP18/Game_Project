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
import java.awt.Font;
import java.awt.Graphics;

public abstract class Health {

    protected int health;
    protected int initialHealth; // Initial health storage variable

    public Health(int initialHealth) {
        this.initialHealth = initialHealth;
        this.health = initialHealth;
    }

    public abstract void reduceHealth(int amount); // Abstract methods for reducing health

    public abstract void resetHealth(); // Abstract method for resetting health

    public int getHealth() {
        return health;
    }

    public void draw(Graphics g, int x, int y) {
        // Show life force
        g.setColor(Color.RED); 
        Font font = new Font("Times New Roman", Font.BOLD, 12);
        g.setFont(font);
        g.drawString("Health: " + health, x, y);   

        // Draw almost life energy
        g.setColor(Color.GREEN); 
        g.fillRect(x, y + 10, health * 2, 20); 

        // Life force frame
        g.setColor(Color.BLACK); 
        g.drawRect(x, y + 10, 200, 20); 
    }
}
