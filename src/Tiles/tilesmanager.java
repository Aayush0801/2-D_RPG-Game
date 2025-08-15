package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tilesmanager {
    GamePanel gp;
    public tiles[] tile;
    public int maptiles [][];
    String checker = "";
    public tilesmanager(GamePanel gp){
        this.gp = gp;
        tile = new tiles[10];// we can add multiple if we want
        maptiles = new int [gp.maxscreenrow][gp.maxscreencol];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try {
            // dont think that doing tiles[0] = new tiles() is necessary
            tile[0] = new tiles();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));
            tile[1] = new tiles();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));
            tile[1].collision = true;
            tile[2] = new tiles();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));
            tile[2].collision = true;
            tile[3] = new tiles();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));
            tile[4] = new tiles();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));
            tile[4].collision = true;
            tile[5] = new tiles();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadMap(){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col<gp.maxscreencol && row<gp.maxscreenrow){
                String line = br.readLine();
                while(col<gp.maxscreencol){
                    String numbers [] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    maptiles[col][row] = num;
                    col++;
                }
                if(col == gp.maxscreencol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col = gp.maxscreencol;
        int row = gp.maxscreenrow;
        for(int i =0;i<col;i++){
            for(int j = 0;j<row;j++){
                int worldX = j*gp.tilesize;
                int worldY = i*gp.tilesize;
                //getting the tile position wrt to the player and then adding a screen offset so that the things that are within the screen can be seen
                int screenX = worldX - gp.p1.worldx + gp.p1.screenx;
                int screenY = worldY - gp.p1.worldy + gp.p1.screeny;
                if(worldX>gp.p1.worldx - gp.p1.screenx - 5*gp.tilesize && worldX<gp.p1.worldx + gp.p1.screenx + 5*gp.tilesize && worldY>gp.p1.worldy - gp.p1.screeny-5*gp.tilesize && worldY<gp.p1.worldy + gp.p1.screeny + 5*gp.tilesize)
                g2.drawImage(tile[maptiles[j][i]].image,screenX,screenY,gp.tilesize, gp.tilesize, null);

            }

        }
    }
}
