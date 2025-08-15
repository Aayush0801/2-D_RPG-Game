package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldx,worldy;
    public int speed;
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
    public String direction;
    public int spritecount = 0;
    public int spritenum = 1;
    public Rectangle solidarea;
    public int solidAreadefx,solidAreadefy;
    public boolean collisionon = false;
}
