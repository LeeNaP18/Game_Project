/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_project;
/**
 *
 * @author victus
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputs extends KeyAdapter {
    private Player player; // Object of the Player class

    public KeyInputs(Player player) {
        this.player = player; // Creating a relationship between KeyInputs and Player
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.jump(); // Call methods in the Player class.
        }
    }
}


