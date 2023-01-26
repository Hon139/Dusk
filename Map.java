import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Map{
    int[] bounds; 
    ArrayList<Enemy> enemies;
    Drone primaryDrone; 

    Map(int[] bounds){
        BufferedImage droneImage = Utilities.iconToBufferedImage(Utilities.scaleImage(new ImageIcon
        (Drone.createDroneImage()), Constants.DRONE_SIZE, Constants.DRONE_SIZE));
        primaryDrone = new Drone(0,0,Constants.DRONE_SPEED, 
        droneImage, Constants.DRONE_SIZE, Constants.DRONE_ROTATE_SPEED);
        this.bounds = bounds;
        enemies = new ArrayList<Enemy>();
        generateEnemies(38, bounds);
    }

    public void generateEnemies(int numOfEnemies,int[] borderBounds){
        final int borderOffset = 25; 
        for (int i =0;i< numOfEnemies;i++){
            int randX = Utilities.getRandomInt(borderOffset, borderBounds[2]);
            int randY = Utilities.getRandomInt(borderOffset, borderBounds[3]);
            BufferedImage enemyImage = Enemy.createEnemyImage(Color.RED);
            int enemySpeed = Constants.DRONE_SPEED/2;
            Enemy newEnemy = new Enemy(randX,randY,enemySpeed,enemyImage,enemyImage.getHeight());
            enemies.add(newEnemy);
        }
    }

    private void drawBorders(Graphics g,int offsetX, int offsetY){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Constants.GAME_BACKGROUND_COLOR);
        g2d.fillRect(00+offsetX, 0+offsetY, 500, 300);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawRect(bounds[0]+offsetX, bounds[1]+offsetY, bounds[2], bounds[3]);
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1));

        for (int i =0;i<(int)Constants.BORDER[2]/100;i++){
            g2d.drawLine(i*100+offsetX,offsetY,i*100+offsetX,Constants.BORDER[3]+offsetY);
        }

        for (int i =0;i<(int)Constants.BORDER[3]/100;i++){
            g2d.drawLine(offsetX,i*100+offsetY,Constants.BORDER[2]+offsetX,i*100+offsetY);
        }
    }

    private void drawEntities(Graphics g){
        for (int i = 0; i < enemies.size();i++){
            enemies.get(i).paintEntityCenter(g,primaryDrone.getX(),primaryDrone.getY());
        }
    }
    
    private void drawDrone(Graphics g){
        primaryDrone.paintEntityCenter(g);
    }

    public void drawMap(Graphics g){
        drawBorders(g,primaryDrone.getX(),primaryDrone.getY());
        drawEntities(g);
        drawDrone(g);
    }

    public boolean willCollidingWithBorder(int direction){
        boolean returnStatus = false;
        int droneX = primaryDrone.getX()+primaryDrone.getMoveX(direction);
        int droneY = primaryDrone.getY()+primaryDrone.getMoveY(direction);
        if (!(droneX+primaryDrone.getDiameterSize()/2 <bounds[0]+Constants.WIDTH/2)){
            returnStatus = true;
        }
        if ((droneX-primaryDrone.getDiameterSize()/2 < -bounds[2]+Constants.WIDTH/2)){
            returnStatus = true;
        }
        if (!(droneY+primaryDrone.getDiameterSize()/2 <bounds[1]+Constants.HEIGHT/2)){
            returnStatus = true;
        }
        if ((droneY-primaryDrone.getDiameterSize()/2 < -bounds[3]+Constants.HEIGHT/2)){
            returnStatus = true;
        }
        return returnStatus; 
    }

    public Drone getDrone(){
        return primaryDrone;
    }
}
