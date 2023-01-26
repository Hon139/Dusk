import java.awt.image.BufferedImage;
import java.awt.*;
public class Drone extends Entity {
    private double angleOfMovement; 
    private double rotationStep; 
    private boolean scaning;
    private int scannerRange; 

    Drone(int xPos,int yPos,int stepDistance,Image entityImage, int diameterSize, double rotationStep,int scanRadius){
        super(xPos, yPos, stepDistance, entityImage,diameterSize);
        this.angleOfMovement = 0;
        this.rotationStep = rotationStep;
        this.scaning = false; 
        this.scannerRange = scanRadius;
    }

    // NOTE: "Direction" refers to moving forwards or backwards -1 meaning backwards and vice-versa
    public void move(int direction){
        this.setX(this.getX()+getMoveX(direction));
        this.setY(this.getY()+getMoveY(direction));
    }

    //X-Component of moving 
    public int getMoveX(int direction){
        int step = super.getStepDistance()*direction;
        double angleOfMovementDeg = Math.toRadians(this.angleOfMovement);
        return (int)(Math.cos(angleOfMovementDeg)*step);
    }

    //Y-Component of moving 
    public int getMoveY(int direction){
        int step = super.getStepDistance()*direction;
        double angleOfMovementDeg = Math.toRadians(this.angleOfMovement);
        return (int)(Math.sin(angleOfMovementDeg)*step);
    }

    public void rotate(int direction){
        double step = this.rotationStep*direction; 
        this.angleOfMovement += step; 
    }

    // create rotated instance
    private BufferedImage rotateImage(Image image, double angle){
        BufferedImage bufferedImage = (BufferedImage)image;
        BufferedImage rotatedImage = new BufferedImage(bufferedImage.getWidth(),
        bufferedImage.getHeight(), bufferedImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.rotate(Math.toRadians(this.angleOfMovement), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
        g2d.drawImage(bufferedImage, null, 0, 0);
        g2d.dispose();
        return rotatedImage;
    }

    public static BufferedImage createDroneImage(){
        int width = Constants.DRONE_SIZE;
        int height = Constants.DRONE_SIZE;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        drawCircle(g2d,width/2,height/2,100);
        g2d.drawLine(0,width/2,height,width/2);
        g2d.drawLine(height/2,20,height/2,width-20);
        g2d.drawLine(0,width/2,width-20,height/2+40);
        g2d.drawLine(0,width/2,width-20,height/2-40);
        g2d.dispose();
        return bufferedImage; 
    }

    public void paintEntityCenter(Graphics g){ 
        g.drawImage(rotateImage(getImage(), angleOfMovement),Constants.WIDTH/2-this.getDiameterSize()/2,
        Constants.HEIGHT/2-this.getDiameterSize()/2,null);
    }   

    public boolean getScanning(){
        return scaning; 
    }

    public int getScanRange(){
        return this.scannerRange;
    }
}
