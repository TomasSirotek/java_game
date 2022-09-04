package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int originalTileSize = 16; // 16x16px
    final int scale = 3; // scaling for resolution
    public final int tileSize = originalTileSize * scale; // 48px tile
    public final int maxScreenColumn = 16; // horizontally  16
    public final int maxScreenRow = 12; // vertically 12

    public final int screenWidth = tileSize * maxScreenColumn; // 768 px

    public final int screenHeight = tileSize * maxScreenRow; // 576 px


    // SETTINGS
    public final int maxWorldColumn = 40;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldColumn;
    public final int worldWHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;
    TileManager tileManager = new TileManager(this);
    // Key input controller
    KeyInput keyInput = new KeyInput();
    // Keeps running until its manually stopped
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyInput);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);   // complete background
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);  // added input
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
        // core game loop
        // DELTA METHOD OF LOOP
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();// return current value  in nanos 1 000 ms = 1s
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                // UPDATE INFORMATION / POSITION etc.
                update();
                // DRAW SCREEN WITH UPDATED INFORMATION
                repaint();
                // -1 delta
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS" + drawCount);
                drawCount = 0 ;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){
         // parent class of this class (parent class =  JPanel)
         super.paintComponent(graphics);
         // Extending graphics class to Graphics2D for better control  
         Graphics2D graphics2D = (Graphics2D)graphics;

         tileManager.draw(graphics2D);
         player.draw(graphics2D);

         graphics2D.dispose();
    }
}
