import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Map{
    int[] bounds; 
    ArrayList<Enemy> enemies;
    Drone primaryDrone; 

    Map(int[] bounds){
        BufferedImage droneImage = Utilities.iconToBufferedImage(Utilities.scaleImage(new ImageIcon(Drone.createDroneImage()), Constants.DRONE_SIZE, Constants.DRONE_SIZE));
        primaryDrone = new Drone(0,0, 0, Constants.DRONE_SPEED, droneImage, Constants.DRONE_SIZE, Constants.DRONE_ROTATE_SPEED);
        this.bounds = bounds;
        enemies = new ArrayList<Enemy>();
    }


    public void generateEnemies(){
        

    }


    public void drawBorders(Graphics g,int offsetX, int offsetY){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Constants.GAME_BACKGROUND_COLOR);
        g2d.fillRect(00+offsetX, 0+offsetY, 500, 300);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRect(bounds[0]+offsetX, bounds[1]+offsetY, bounds[2], bounds[3]);
    }

    public void drawEntities(){}

    public void drawMap(Graphics g){
        drawBorders(g,primaryDrone.getX(),primaryDrone.getY());
        drawDrone(g);
        
    }

    public boolean willCollidingWithBorder(int direction){
        boolean returnStatus = false;
        if (!(primaryDrone.getX()+primaryDrone.getMoveX(direction)+primaryDrone.getDiameterSize()/2 <bounds[0]+Constants.WIDTH/2)){
            returnStatus = true;
        }
        if ((primaryDrone.getX()+primaryDrone.getMoveX(direction)-primaryDrone.getDiameterSize()/2 < -bounds[2]+Constants.WIDTH/2)){
            returnStatus = true;
        }
        if (!(primaryDrone.getY()+primaryDrone.getMoveY(direction)+primaryDrone.getDiameterSize()/2<bounds[1]+Constants.HEIGHT/2)){
            returnStatus = true;
        }
        if ((primaryDrone.getY()+primaryDrone.getMoveY(direction)-primaryDrone.getDiameterSize()/2 < -bounds[3]+Constants.HEIGHT/2)){
            returnStatus = true;
        }
        return returnStatus; 
    }

    public void drawDrone(Graphics g){
        primaryDrone.paintEntityCenter(g);
    }

    public Drone getDrone(){
        return primaryDrone;
    }



}
