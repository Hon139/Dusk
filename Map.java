import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Map{
    int[] bounds; 
  //  ArrayList<Enemy> enemies;
    Drone primaryDrone; 
    int offsetX = 0;
    int offsetY = 0;

    Map(int[] bounds){
        BufferedImage droneImage = Utilities.iconToBufferedImage(Utilities.scaleImage(new ImageIcon(Drone.createDroneImage()), Constants.DRONE_SIZE, Constants.DRONE_SIZE));
        primaryDrone = new Drone(Constants.WIDTH/2, Constants.HEIGHT/2, 0, Constants.DRONE_SPEED, droneImage, Constants.DRONE_SIZE, Constants.DRONE_ROTATE_SPEED);
        this.bounds = bounds;
   //     enemies = new ArrayList<Enemy>();
    }

    public void drawBorders(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Constants.GAME_BACKGROUND_COLOR);
        g2d.fillRect(-300, -300, 10000, 10000);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRect(bounds[0], bounds[1], bounds[2], bounds[3]);
    }

    public void drawEntities(){}

    public void drawMap(Graphics g){
        drawBorders(g);
        drawDrone(g);
    }



    public boolean isCollidingWithBorder(){
        // if (){

        // }



        return true;
    }

    public void drawDrone(Graphics g){
        primaryDrone.paintEntityCenter(g);
    }

    public Drone getDrone(){
        return primaryDrone;
    }



}
