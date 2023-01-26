import java.awt.image.BufferedImage;
import java.awt.*;

public class Drone extends Entity {
    private double angleOfMovement; 
    private double rotationStep; 

    Drone(int xPos,int yPos, int maxLifePoints,int stepDistance,Image entityImage, int DiameterSize, double rotationStep){
        super(xPos, yPos, maxLifePoints, stepDistance, entityImage,DiameterSize);
        this.angleOfMovement = 0;
        this.rotationStep = rotationStep; 
    }

    @Override
    void move(int direction){
        this.setX(this.getX()+getMoveX(direction));
        this.setY(this.getY()+getMoveY(direction));
    }

    int getMoveX(int direction){
        int step = super.getStepDistance()*direction;
        double angleOfMovementDeg = Math.toRadians(this.angleOfMovement);
        return (int)(Math.cos(angleOfMovementDeg)*step);
    }

    int getMoveY(int direction){
        int step = super.getStepDistance()*direction;
        double angleOfMovementDeg = Math.toRadians(this.angleOfMovement);
        return (int)(Math.sin(angleOfMovementDeg)*step);
    }

    void rotate(int direction){
        double step = this.rotationStep*direction; 
        this.angleOfMovement += step; 
    }

    private BufferedImage rotateImage(Image image, double angle){
        BufferedImage bufferedImage = (BufferedImage)image;
        BufferedImage rotatedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.rotate(Math.toRadians(this.angleOfMovement), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
        g2d.drawImage(bufferedImage, null, 0, 0);
        g2d.dispose();
        return rotatedImage;
    }
//----------------------------

    public static BufferedImage createDroneImage(){
        int width = 200;
        int height = 200;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(new Color(14, 161, 230,255));
        g2d.setStroke(new BasicStroke(5));
        drawCircle(g2d,width/2,height/2,100);
        g2d.drawLine(0,width/2,height,width/2);

        g2d.dispose();
        return bufferedImage; 
    }

    private static void drawCircle(Graphics2D g2d,int centerX,int centerY,int diameter){
        int radius = diameter/2; 
        int startX = centerX - radius;
        int startY = centerY - radius;
        g2d.drawOval(startX, startY, diameter, diameter);
    }

    @Override
    void paintEntityCenter(Graphics g){ 
        g.drawImage(rotateImage(getImage(), angleOfMovement),Constants.WIDTH/2-this.getDiameterSize()/2,Constants.HEIGHT/2-this.getDiameterSize()/2,null);
    }

    
}
