package entity;

import main.GamePanel;
import main.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel gamePanel,KeyInput keyInput){
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){

        if(keyInput.upPressed){
            direction = "up";
            y -= speed; //  playerY = playerY - playerSpeed;
        }else if(keyInput.downPressed) {
            direction = "down";
            y+= speed;
        }else if (keyInput.leftPressed){
            direction = "left";
            x -= speed;
        } else if (keyInput.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void getPlayerImage(){
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/right.png")));

        } catch(IOException e) {
             e.printStackTrace();
        }

    }
    public void draw(Graphics2D graphics2D){

       BufferedImage image = switch (direction) {
           case "up" -> up;
           case "down" -> down;
           case "left" -> left;
           case "right" -> right;
           default -> null;
       };
        graphics2D.drawImage(image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
