package main;
import Object.OBJ_Key;
import Object.OBJ_door;
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setobj(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = gp.tilesize*23;
        gp.obj[0].worldY = gp.tilesize*7;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = gp.tilesize*23;
        gp.obj[1].worldY = gp.tilesize*40;


        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = gp.tilesize*38;
        gp.obj[2].worldY = gp.tilesize*8;

        gp.obj[3] = new OBJ_door();
        gp.obj[3].worldX = gp.tilesize*10;
        gp.obj[3].worldY = gp.tilesize*11;


        gp.obj[4] = new OBJ_door();
        gp.obj[4].worldX = gp.tilesize*8;
        gp.obj[4].worldY = gp.tilesize*28;


        gp.obj[5] = new OBJ_door();
        gp.obj[5].worldX = gp.tilesize*12;
        gp.obj[5].worldY = gp.tilesize*22;


        gp.obj[6] = new OBJ_Key();
        gp.obj[6].worldX = gp.tilesize*10;
        gp.obj[6].worldY = gp.tilesize*7;
    }

}
