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
    private boolean isGameStarted = false;
    private boolean isGameOver = false;
    private JButton startButton;
    private JButton playAgainButton;
    private double gamespeed = 1;
    private NoFace noface; // เพิ่ม Noface เป็นตัวแปรใหม่

    public GamePanel() {
        player = new Player();
        background = new Background(25); 
        ground = new Ground(1250);
        obstacles = new ArrayList<>();
        random = new Random();
        gamespeed = 1;

        // สร้างอินสแตนซ์ของ Noface และกำหนดตำแหน่งให้ยืนเฉยๆ ทางขวา
        noface = new NoFace(1000, 240); 

        // Create a game start button
        startButton = new JButton("Start");
        startButton.setBounds(380, 250, 180, 70);
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
        startButton.setContentAreaFilled(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        this.add(startButton);
        setLayout(null);

        // Create a "Play Again" button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(350, 250, 280, 100);
        playAgainButton.setFont(new Font("Times New Roman", Font.BOLD, 48));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setVisible(false);

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        this.add(playAgainButton);

        addKeyListener(new KeyInputs(player));
        setFocusable(true);

        addObstacle();
    }

    private void startGame() {
        isGameStarted = true;
        isGameOver = false;
        player.resetHealth();
        ground.reset();
        score = 0;
        obstacles.clear();
        gamespeed = 1;
        addObstacle();
        startButton.setVisible(false);
        playAgainButton.setVisible(false);
        new Thread(this).start();
    }

    private void addObstacle() {
        obstacles.add(new Obstacle(450, 100, 100, gamespeed));
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

        // วาด Noface ที่ตำแหน่งด้านขวาของหน้าต่าง
        noface.draw(g);

        if (isGameOver) {
            g.setFont(new Font("Times New Roman", Font.BOLD, 70));
            g.setColor(Color.WHITE);
            g.drawString("Game Over", getWidth() / 2 - 180, getHeight() / 2 - 60);
            playAgainButton.setVisible(true);
        }
    }

    public void update() {
        if (isGameStarted && !isGameOver) {
            player.update();
            ground.update();
            
            if (!obstacles.isEmpty()) {
                setGamespeed(obstacles.get(0).getSpeed());
                background.setBackgroundImage((int) gamespeed);
            }

            if (score % 1500 == 0) {
                addObstacle();
            }

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

            // ตรวจสอบการชนกับ Noface
            if (noface.checkCollision(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                if (player.getHealth() > 0 && (System.currentTimeMillis() - hitCooldown > 500)) {
                    player.decreaseHealth();
                    hitCooldown = System.currentTimeMillis();
                    isHit = true;
                    hitTime = System.currentTimeMillis();
                }
            }

            if (isHit) {
                this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                if (System.currentTimeMillis() - hitTime > 300) {
                    isHit = false;
                    this.setBorder(BorderFactory.createEmptyBorder());
                }
            }

            if (player.getHealth() <= 0) {
                isGameOver = true;
                isGameStarted = false;
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

    public void setGamespeed(double gamespeed) {
        this.gamespeed = gamespeed;
    }
}