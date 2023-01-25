import java.awt.Image;
import java.awt.Graphics; 

abstract class Entity {
    private int xPos;
    private int yPos; 
    private int maxLifePoints; 
    private int lifePoints; 
    private int stepDistance; 
    private Image entityImage;
    private int DiameterSize; 

    Entity(int xPos,int yPos, int maxLifePoints,int stepDistance,Image entityImage,int radius){
        this.xPos = xPos;
        this.yPos = yPos; 
        this.maxLifePoints = maxLifePoints;
        this.stepDistance = stepDistance; 
        this.entityImage = entityImage; 
        this.lifePoints = maxLifePoints;  
        this.DiameterSize = radius; 
    }

    public int getDiameterSize(){
        return this.DiameterSize;
    }

    abstract void move(int direction); 

    public int getMaxLifePoints(){
        return this.maxLifePoints; 
    }

    public int getX(){
        return xPos; 
    }

    public void setX(int newX){
        this.xPos = newX; 
    }

    public int getY(){
        return yPos;
    }

    public void setY(int newY){
        this.yPos = newY;
    }

    public int getLife(){
        return lifePoints;
    }

    public int getStepDistance(){
        return stepDistance;
    }

    public Image getImage(){
        return entityImage; 
    }



    abstract void paintEntityCenter(Graphics g); 

}
