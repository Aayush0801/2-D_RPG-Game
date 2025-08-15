package Object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObj {
    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX,worldY;
    public Rectangle solidarea = new Rectangle(0,0,48,48);
    public int solidareadefx = 0;
    public int solidareadefy = 0;
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.p1.worldx + gp.p1.screenx;
        int screenY = worldY - gp.p1.worldy + gp.p1.screeny;
        if(worldX>gp.p1.worldx - gp.p1.screenx - 5*gp.tilesize && worldX<gp.p1.worldx + gp.p1.screenx + 5*gp.tilesize && worldY>gp.p1.worldy - gp.p1.screeny-5*gp.tilesize && worldY<gp.p1.worldy + gp.p1.screeny + 5*gp.tilesize)
            g2.drawImage(image,screenX,screenY,gp.tilesize, gp.tilesize, null);
    }
}
