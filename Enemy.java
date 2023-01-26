import java.awt.image.BufferedImage;
import java.awt.*; 
import java.util.Queue;
public class Enemy extends Entity{
    
    Queue<int[]> movementLocations;
    int visibility;
    Color baseColor;

    Enemy(int xPos, int yPos, int stepDistance, Image entityImage, int diameterSize) {
        super(xPos, yPos, stepDistance, entityImage,diameterSize);
        this.visibility = 0;
        baseColor = Color.RED;
    }
 
    public static BufferedImage createEnemyImage(Color color){
        int width = Constants.ENEMY_SIZE;
        int height = Constants.ENEMY_SIZE;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        drawCircle(g2d,width/2,height/2,Constants.ENEMY_SIZE);
        g2d.dispose();
        return bufferedImage; 
    }
    
    public BufferedImage getNewImage(Color baseColor,int alphaValue){
        Color enemyBaseColor = new Color(baseColor.getRed(),baseColor.getGreen(),baseColor.getBlue(),visibility);
        return createEnemyImage(enemyBaseColor);
    }

    public void makeAiChoice(){
        Utilities.getRandomInt(0, getDiameterSize());
    }

    public void move(int[] destinationCoord){
        int xDiff = getX() - destinationCoord[0];
        int yDiff = getY() - destinationCoord[1];
        int newX;
        int newY;
        if (xDiff != 0 || yDiff != 0){
        double angleRadians = Math.acos((yDiff/xDiff));
        newX = (int)(Math.cos(angleRadians)*this.getStepDistance()+getX());
        newY = (int)(Math.sin(angleRadians)*this.getStepDistance()+getY());
        }else {
            newX = (int)(this.getStepDistance()+getX());
            newY = (int)(this.getStepDistance()+getY());
            if (xDiff ==0 ){newX =getX();}
            if (yDiff ==0 ){newY = getY();}
        }
        this.setX(newX);
        this.setY(newY);
    }
  
    public void paintEntityCenter(Graphics g, int offsetX, int offsetY) {
        if (visibility != 0){visibility--;}
        

        g.drawImage(getNewImage(Color.RED,visibility),offsetX+getX()-this.getDiameterSize()/2,
        offsetY+getY()-this.getDiameterSize()/2,null);
    }

    public void setVisibility(int visibility){
        this.visibility = visibility; 
    }
}
