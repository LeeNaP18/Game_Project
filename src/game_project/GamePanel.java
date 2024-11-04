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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GamePanel extends JPanel implements Runnable {

    private Player player;
    private Background background;
    private Ground ground;
    private ArrayList<Obstacle> obstacles;
    private Random random;
    private boolean isHit = false;
    private long hitTime;
    private long hitCooldown;
    private int score = 0;
    private boolean isGameStarted = false; // ตัวแปรสถานะการเริ่มเกม
    private boolean isGameOver = false; // ตัวแปรสถานะเกมจบ
    private JButton startButton; // ปุ่มเริ่มเกม
    private JButton playAgainButton; // ปุ่มเล่นอีกครั้ง

    public GamePanel() {
        player = new Player();
        background = new Background();
        ground = new Ground(1250);
        obstacles = new ArrayList<>();
        random = new Random();

        // สร้างปุ่มเริ่มเกม
        startButton = new JButton("Start");
        startButton.setBounds(380, 250, 180, 70); // กำหนดตำแหน่งและขนาดของปุ่ม
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
        startButton.setContentAreaFilled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // เรียกใช้เมธอดเพื่อเริ่มเกม
            }
        });

        this.add(startButton);
        setLayout(null);

        // สร้างปุ่ม "Play Again"
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(350, 250, 280, 100);
        playAgainButton.setFont(new Font("Times New Roman", Font.BOLD, 48));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setVisible(false); // ซ่อนปุ่มเริ่มต้น

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // เรียกใช้เมธอดเพื่อเริ่มเกมใหม่
            }
        });

        this.add(playAgainButton); // เพิ่มปุ่ม "Play Again" ลงใน GamePanel

        addKeyListener(new KeyInputs(player));
        setFocusable(true);

        addObstacle();
    }

    private void startGame() {
        isGameStarted = true;
        isGameOver = false; // Reset game over status
        player.resetHealth(); // รีเซ็ตพลังชีวิตผู้เล่น
        ground.reset(); // รีเซ็ตพื้นกลับไปที่ค่าเริ่มต้น
        score = 0; // รีเซ็ตคะแนนกลับไปที่ 0
        obstacles.clear(); // เคลียร์อุปสรรคเก่า
        addObstacle(); // เพิ่มอุปสรรคใหม่
        startButton.setVisible(false);
        playAgainButton.setVisible(false); // ซ่อนปุ่ม "Play Again"
        new Thread(this).start(); // เริ่มเธรดสำหรับการอัปเดตเกม
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

        g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, getWidth() - 180, 40);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        // แสดงข้อความ "Game Over" เมื่อเกมจบ
        if (isGameOver) {
            g.setFont(new Font("Times New Roman", Font.BOLD, 70));
            g.setColor(Color.WHITE);
            g.drawString("Game Over", getWidth() / 2 - 180, getHeight() / 2 - 60);
            playAgainButton.setVisible(true); // แสดงปุ่ม "Play Again"
        }
    }

    public void update() {
        if (isGameStarted && !isGameOver) { // อัพเดตเฉพาะเมื่อเกมเริ่มและยังไม่จบ
            player.update();
            ground.update();

            for (int i = obstacles.size() - 1; i >= 0; i--) {
                Obstacle obstacle = obstacles.get(i);
                obstacle.update();

                if (obstacle.checkCollision(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                    if (player.getHealth() > 0 && (System.currentTimeMillis() - hitCooldown > 500)) {
                        player.decreaseHealth();
                        hitCooldown = System.currentTimeMillis();
                        isHit = true;
                        hitTime = System.currentTimeMillis();
                    }
                }

                if (obstacle.getX() + obstacle.getWidth() < 0) {
                    obstacles.remove(i);
                    addObstacle();
                }
            }

            if (isHit) {
                this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                if (System.currentTimeMillis() - hitTime > 300) {
                    isHit = false;
                    this.setBorder(BorderFactory.createEmptyBorder());
                }
            }

            // เช็คว่าพลังชีวิตหมดหรือไม่
            if (player.getHealth() <= 0) {
                isGameOver = true; // ตั้งค่าสถานะเกมจบ
                isGameStarted = false; // หยุดเกม
            }

            score++;
            repaint();
        }
    }

    @Override
    public void run() {
        while (isGameStarted) {
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
