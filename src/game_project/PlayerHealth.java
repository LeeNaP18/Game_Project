/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_project;

/**
 *
 * @author victus
 */
public class PlayerHealth extends Health {

    public PlayerHealth(int initialHealth) {
        super(initialHealth);
    }

    @Override
    public void reduceHealth(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    @Override
    public void resetHealth() {
        this.health = initialHealth;
    }
}
