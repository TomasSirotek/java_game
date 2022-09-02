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

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel,KeyInput keyInput){
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 35;
        worldY = gamePanel.tileSize * 5;
        speed = 4;
    }

    public void update(){

        if(keyInput.upPressed){
            direction = "up";
            worldY -= speed; //  playerY = playerY - playerSpeed;
        }else if(keyInput.downPressed) {
            direction = "down";
            worldY += speed;
        }else if (keyInput.leftPressed){
            direction = "left";
            worldX -= speed;
        } else if (keyInput.rightPressed) {
            direction = "right";
            worldX += speed;
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
        graphics2D.drawImage(image,screenX,screenY,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
