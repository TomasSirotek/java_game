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

        solidRectangle = new Rectangle();
        solidRectangle.x = 0;
        solidRectangle.y = 16;
        solidRectangle.width = 32;
        solidRectangle.height = 32;
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
        }else if(keyInput.downPressed) {
            direction = "down";
        }else if (keyInput.leftPressed){
            direction = "left";
        } else if (keyInput.rightPressed) {
            direction = "right";
        }
        // Tile collision checker
        isCollision = false;
        gamePanel.collisionChecker.checkTile(this);
        // if collision false => cannot move
        if(!isCollision){
            switch (direction){
                case "up" -> worldY -= speed; //  playerY = playerY - playerSpeed;;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;

            }
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
