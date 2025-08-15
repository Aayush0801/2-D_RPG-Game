package main;

import Tiles.tilesmanager;
import entity.player;
import Object.SuperObj;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    //final essentially makes the variable unchangeable
    final int origtitsize = 16; // 16*16px character
    final int scale = 3; // need to scale it up for modern monitors
    public final int tilesize = origtitsize * scale;
    public final int maxcol = 16;
    public final int maxrow = 12;
    public final int maxscreencol = 50;
    public final int maxscreenrow = 50;
    public final int screenwidth = maxscreencol*tilesize;
    public final int screenheight = maxscreenrow*tilesize;
    public final int width = maxcol*tilesize;
    public final int height =  maxrow*tilesize;
    Thread gameThread; // creating a small worker to do a task for me while the code runs
    KeyHandler keyh = new KeyHandler();
    public player p1 = new player(this,keyh);
    tilesmanager t1 = new tilesmanager(this);
    public collisonchecke c1 = new collisonchecke(this);
    public SuperObj obj[] = new SuperObj[10];//creating 10 objects that can be used
    public AssetSetter as1 =  new AssetSetter(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(width,height));//screen with width and height
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//improves the games rendering performance
        this.addKeyListener(keyh);//adds a keyhandler
        this.setFocusable(true);// makes sure the gamepanel focuses on the input
    }
    //method to start the game thread
    public void setup(){
        as1.setobj();
    }
    public void startGameThread(){
        gameThread = new Thread(this);// telling it what is the task it is supposed to do
        gameThread.start(); //starting the thread to do the said task
    }
    //this run function serves as what the task will be for our thread
    @Override
    public void run() {
        //while the thread is there do this task
        // the main task of this game loops is essentially to update and draw the changes that are happening to the playe
        double frame = 1e9/60;//duration of a single frame
        double endtime = System.nanoTime() + frame;
        while(gameThread != null){

            update();
            repaint();//this is how we call the paintComponent method
            try {
                double rem_time = endtime - System.nanoTime(); //remaining time after performing update and repaint
                rem_time /= 1e6;//as sleep method only takes millisecond input;
                rem_time = Math.max(rem_time,0);
                Thread.sleep((long) rem_time);
                endtime+=frame;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public void update(){
        //else if because we only want one input at a time
        p1.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);//calls gpanel seedha
        Graphics2D g2 = (Graphics2D) g;// creates a variable player for me
        //for tiles
        t1.draw(g2);
        for(int i = 0;i< obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        //for player
        p1.draw(g2);
        g2.dispose();//just to save memory
    }
}
