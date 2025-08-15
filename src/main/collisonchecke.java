package main;

import entity.Entity;

public class collisonchecke {
    GamePanel gp;
    public collisonchecke(GamePanel gp){
        this.gp = gp;
    }
    public void checktiles(Entity entity){
        int entityleftworldx = entity.worldx + entity.solidarea.x;
        int entityrightworldx = entity.worldx + entity.solidarea.x+ entity.solidarea.width;
        int entitytopworldy = entity.worldy + entity.solidarea.y;
        int entitybottomworldy = entity.worldy + entity.solidarea.y + entity.solidarea.height;
        int entityleftcol = entityleftworldx/gp.tilesize;
        int entityrightcol = entityrightworldx/gp.tilesize;
        int entitytoprow = entitytopworldy/gp.tilesize;
        int entitybottomrow = entitybottomworldy/gp.tilesize;
        int tilenum1, tilenum2;
        switch (entity.direction) {
            case "up":
                entitytoprow = (entitytopworldy - entity.speed) / gp.tilesize; //if the collison is one press input away from happening
                tilenum1 = gp.t1.maptiles[entityleftcol][entitytoprow];
                tilenum2 = gp.t1.maptiles[entityrightcol][entitytoprow];
                if (gp.t1.tile[tilenum1].collision == true || gp.t1.tile[tilenum2].collision == true) {
                    entity.collisionon = true;
                }
                break;
            case "down":
                entitybottomrow = (entitybottomworldy + entity.speed) / gp.tilesize; //if the collison is one press input away from happening
                tilenum1 = gp.t1.maptiles[entityleftcol][entitybottomrow];
                tilenum2 = gp.t1.maptiles[entityrightcol][entitybottomrow];
                if (gp.t1.tile[tilenum1].collision == true || gp.t1.tile[tilenum2].collision == true) {
                    entity.collisionon = true;
                }
                break;
            case "left":
                entityleftcol = (entityleftworldx - entity.speed) / gp.tilesize; //if the collison is one press input away from happening
                tilenum1 = gp.t1.maptiles[entityleftcol][entitytoprow];
                tilenum2 = gp.t1.maptiles[entityleftcol][entitybottomrow];
                if (gp.t1.tile[tilenum1].collision == true || gp.t1.tile[tilenum2].collision == true) {
                    entity.collisionon = true;
                }
                break;
            case "right":
                entityrightcol = (entityrightworldx + entity.speed) / gp.tilesize; //if the collison is one press input away from happening
                tilenum1 = gp.t1.maptiles[entityrightcol][entitytoprow];
                tilenum2 = gp.t1.maptiles[entityrightcol][entitybottomrow];
                if (gp.t1.tile[tilenum1].collision == true || gp.t1.tile[tilenum2].collision == true) {
                    entity.collisionon = true;
                }
                break;
        }
    }
    public int getidx(Entity entity, boolean player){
        int idx = 999;
        for(int i = 0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //get entity pos
                entity.solidarea.x = entity.worldx + entity.solidarea.x;
                entity.solidarea.y = entity.worldy + entity.solidarea.y;
                //get the obj pos
                gp.obj[i].solidarea.x = gp.obj[i].worldX + gp.obj[i].solidarea.x;
                gp.obj[i].solidarea.y = gp.obj[i].worldY + gp.obj[i].solidarea.y;

                switch(entity.direction){
                    case "up":
                        //basically checking where the entity would be after it has moved
                        entity.solidarea.y -= entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea)){
                            if(gp.obj[i].collision){
                                entity.collisionon = true;
                            }
                            if(player){
                                idx = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidarea.y+=entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea)){
                            if(gp.obj[i].collision){
                                entity.collisionon = true;
                            }
                            if(player){
                                idx = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidarea.x -=entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea)){
                            if(gp.obj[i].collision){
                                entity.collisionon = true;
                            }
                            if(player){
                                idx = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidarea.x += entity.speed;
                        if(entity.solidarea.intersects(gp.obj[i].solidarea)){
                            if(gp.obj[i].collision){
                                entity.collisionon = true;
                            }
                            if(player){
                                idx = i;
                            }
                        }
                        break;

                }
                entity.solidarea.x = entity.solidAreadefx;
                entity.solidarea.y = entity.solidAreadefy;
                gp.obj[i].solidarea.x = gp.obj[i].solidareadefx;
                gp.obj[i].solidarea.y = gp.obj[i].solidareadefy;

            }
        }
        return idx;
    }
}
