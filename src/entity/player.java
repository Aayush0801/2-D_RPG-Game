package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class player extends Entity{
    GamePanel gp;
    KeyHandler keyh;
    public int haskey = 0;
    public final int screenx;
    public final int screeny;
    public player(GamePanel gp, KeyHandler keyh){
        this.gp = gp;
        this.keyh = keyh;
        screenx = gp.width/2 - gp.tilesize;
        screeny = gp.height/2 - gp.tilesize;
        solidarea = new Rectangle(8,16,24,24);
        solidAreadefx = solidarea.x;
        solidAreadefy = solidarea.y;
        setdefval();
        getplayimage();
    }
    public void setdefval(){
        worldx = gp.tilesize*22;
        worldy = gp.tilesize*23;
        speed = 4;
        direction = "down";
    }
    public void getplayimage(){
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        // only update when the key is pressed not anytime else

        if(keyh.up || keyh.down || keyh.left || keyh.right) {
            if (keyh.up == true) {
                direction = "up";
            } else if (keyh.down == true) {
                direction = "down";
            } else if (keyh.left == true) {
                direction = "left";
            } else if (keyh.right == true) {
                direction = "right";
            }
            //we do this hear instead of there because it serves the same purpose so better to do it here
            collisionon = false;
            gp.c1.checktiles(this);
            int obj_idx = gp.c1.getidx(this, true);
            pickobj(obj_idx);
            if (collisionon == false) {
                switch (direction) {
                    case "up":
                        worldy -= speed;
                        break;
                    case "down":
                        worldy += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;
                    case "right":
                        worldx += speed;
                        break;
                }
            }
            spritecount++;
            if (spritecount > 12) {
                if (spritenum == 1) spritenum = 2;
                else spritenum = 1;
                spritecount = 0;//reset the counter to go back to the motion
            }
        }

    }
    public void pickobj(int idx){
        if(idx!=999){
            String name = gp.obj[idx].name;
            switch (name){
                case "Door":
                    if(haskey>0){
                        gp.obj[idx] = null;
                        haskey--;
                    }
                    break;
                case "Key":
                    haskey++;
                    gp.obj[idx] = null;
                    break;



            }
        }
    }
    public  void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spritenum == 1)image = up1;
                else image = up2;
                break;
            case "down":
                if(spritenum == 1)image = down1;
                else image = down2;
                break;
            case "right":
                if(spritenum == 1)image = right1;
                else image = right2;
                break;
            case "left":
                if(spritenum == 1)image = left1;
                else image = left2;
                break;
        }
        g2.drawImage(image,screenx,screeny,gp.tilesize, gp.tilesize,null);
    }
}
