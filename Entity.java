import java.awt.Image;
import java.awt.Graphics; 

abstract class Entity {
    private int xPos;
    private int yPos; 
    private int stepDistance; 
    private Image entityImage;
    private int diameterSize; 

    Entity(int xPos,int yPos,int stepDistance,Image entityImage,int diameterSize){
        this.xPos = xPos;
        this.yPos = yPos; 
        this.stepDistance = stepDistance; 
        this.entityImage = entityImage; 
        this.diameterSize = diameterSize; 
    }

    public int getDiameterSize(){
        return this.diameterSize;
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
    
    public int getStepDistance(){
        return stepDistance;
    }
    
    public Image getImage(){
        return entityImage; 
    }
    
    // public int[] getCenter(){
        //     return new int[]{0,0};
        // }
        
        
    abstract void move(int direction); 
    abstract void paintEntityCenter(Graphics g); 

}
