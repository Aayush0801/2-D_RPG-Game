package main;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        JFrame win = new JFrame();//creating a window for us
        GamePanel gp = new GamePanel();// creating a game panel class
        win.add(gp); //adds the game panel to the window too
        win.pack();//allows the window to take the dimensions of the game panel itself
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// making sure the window closes on hitting x
        win.setResizable(false); //cant resize the window
        win.setTitle("First Game?"); // giving the window a title
        win.setVisible(true);// so that we can see the window
        win.setLocationRelativeTo(null); // setting the location of the screen to the centre of the screen
        gp.setup();//to set the key objects and all;
        gp.startGameThread();// start the gamethread in the main
    }
}
