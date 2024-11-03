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
    private Player player;

    public KeyInputs(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.jump(); // กระโดดเมื่อกด Space, W, หรือ ลูกศรชี้ขึ้น
        }
    }
}


