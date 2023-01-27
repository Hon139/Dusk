import java.awt.image.BufferedImage;
import java.awt.*; 
public class Enemy extends Entity{
    
    private int visibility;
    private Color baseColor;

    Enemy(int xPos, int yPos, int stepDistance, Image entityImage, int diameterSize, Color baseColor){
        super(xPos, yPos, stepDistance, entityImage,diameterSize);
        this.visibility = 0;
        this.baseColor = baseColor;
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
    
    public BufferedImage getNewImage(int alphaValue){
        Color enemyBaseColor = new Color(this.baseColor.getRed(),this.baseColor.getGreen(),this.baseColor.getBlue(),visibility);
        return createEnemyImage(enemyBaseColor);
    }

    public void makeAiChoice(Drone primaryDrone){
        if (this.getStepDistance() != 0){
            this.move(new int[]{this.getX()+primaryDrone.getX(),this.getY()+primaryDrone.getY()},primaryDrone);
        }
    }

    public void move(int[] initialCoords, Drone primaryDrone){
        int xDiff = (Constants.WIDTH/2+primaryDrone.getX())-initialCoords[0];
        int yDiff = (Constants.HEIGHT/2+primaryDrone.getY())-initialCoords[1];
        int newX;
        int newY;
        int directionX; 
        int directionY; 

        if ((Constants.WIDTH/2+primaryDrone.getX())> initialCoords[0]){
            directionX = 1; 
        } else if ((Constants.WIDTH/2+primaryDrone.getX()) < initialCoords[0]){
            directionX = -1;       
        } else {
            directionX = 0; 
        }

        if ((Constants.HEIGHT/2+primaryDrone.getY()) > initialCoords[1]){
            directionY = 1; 
        } else if ((Constants.HEIGHT/2+primaryDrone.getY()) < initialCoords[1]){
            directionY = -1; 
        } else {
            directionY = 0;
        }
             
        if (xDiff != 0 && yDiff != 0){
            double angleRadians = Math.atan((yDiff/xDiff));
            newX = (int)(directionX*Math.cos(angleRadians)*this.getStepDistance()+getX());
            newY = (int)(directionY*Math.sin(angleRadians)*this.getStepDistance()+getY());
        }else{
            newX = (int)(directionX*this.getStepDistance()+getX());
            newY = (int)(directionY*this.getStepDistance()+getY());
            if (xDiff ==0){newX =getX();}
            if (yDiff ==0){newY =getY();}
        }

        // System.out.println(newX+"  "+newY);
        // System.out.println(getX()+"  "+getY()+"\n\n\n\n");
        this.setX(newX);
        this.setY(newY);
    }
  
    public void paintEntityCenter(Graphics g, int offsetX, int offsetY) {
        if (visibility != 0){visibility--;}
        g.drawImage(getNewImage(visibility),offsetX+getX()-this.getDiameterSize()/2,
        offsetY+getY()-this.getDiameterSize()/2,null);
    }

    public void setVisibility(int visibility){
        this.visibility = visibility; 
    }
}
