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
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    private Player player;
    private Background background;
    private Ground ground;
    private ArrayList<Obstacle> obstacles;
    private Random random;
    private long lastObstacleTime;
    private final int OBSTACLE_SPAWN_DELAY = 2000;
    private boolean isHit = false;
    private long hitTime;
    private long time;
    private long hitCooldown; // เวลา cooldown หลังจากโดนชน

    public GamePanel() {
        player = new Player();
        background = new Background();
        ground = new Ground(1000);
        obstacles = new ArrayList<>();
        random = new Random();
        lastObstacleTime = System.currentTimeMillis();
        time = System.currentTimeMillis();

        addObstacle();

        new Thread(this).start();
    }

    private void addObstacle() {
        obstacles.add(new Obstacle(450, 100, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g, getWidth(), getHeight());
        ground.draw(g);
        player.draw(g);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    public void update() {
        player.update();
        ground.update();

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obstacle = obstacles.get(i);
            obstacle.update();
            
            if (System.currentTimeMillis() - time < 2000) {
                continue;
            }

            // ตรวจสอบการชนระหว่างผู้เล่นกับอุปสรรค
            if (obstacle.checkCollision(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                if (player.getHealth() > 0 && (System.currentTimeMillis() - hitCooldown > 500)) { // ตรวจสอบสุขภาพและ cooldown
                    player.decreaseHealth(); // ลดเลือด
                    hitCooldown = System.currentTimeMillis(); // ตั้งเวลา cooldown
                    isHit = true;
                    hitTime = System.currentTimeMillis(); // เก็บเวลาที่เกิดการชน
                }
            }

            // ถ้าอุปสรรคหลุดจากจอ ก็จะลบอุปสรรคออกจาก ArrayList
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                obstacles.remove(i);
                addObstacle(); // เพิ่มอุปสรรคใหม่แทนที่เมื่อหลุดจอ
            }
        }

        // เปลี่ยนขอบหน้าต่างเป็นสีแดงชั่วคราวเมื่อเกิดการชน
        if (isHit) {
            this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            if (System.currentTimeMillis() - hitTime > 300) { // ขอบสีแดงอยู่แค่ 300 ms
                isHit = false;
                this.setBorder(BorderFactory.createEmptyBorder()); // รีเซ็ตขอบกลับ
            }
        }

        repaint();
    }

    @Override
    public void run() {
        while (true) {
            update();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}
