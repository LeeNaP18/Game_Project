/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game_project;

/**
 *
 * @author victus
 */
import javax.swing.JFrame;

public class Spirite_World_Jumper {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spirite World Jumper");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        // เพิ่ม KeyListener สำหรับการควบคุม
        frame.addKeyListener(new KeyInputs(gamePanel.getPlayer()));
    }
}


