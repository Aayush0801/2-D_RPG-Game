package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends SuperObj{
    public OBJ_door(){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;// want this to be solid
    }
}
