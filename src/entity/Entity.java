package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Stores all the variables for player npc ,monsters
public class Entity {
    public int worldX ,worldY;
    public int speed;

    public BufferedImage up,down,left,right;

    public String direction;

    public Rectangle solidRectangle;
    public boolean isCollision = false;
}
