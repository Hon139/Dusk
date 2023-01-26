import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D; 
import java.awt.*; 

public class Enemy extends Entity{
    
    Enemy(int xPos, int yPos, int stepDistance, Image entityImage, int diameterSize) {
        super(xPos, yPos, stepDistance, entityImage,diameterSize );
    }
 
    public static BufferedImage createEnemyImage(Color color){
        int width = Constants.ENEMY_SIZE;
        int height = Constants.ENEMY_SIZE;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        drawCircle(g2d,width/2,height/2,100);
        g2d.dispose();
        return bufferedImage; 
    }

    private static void drawCircle(Graphics2D g2d,int centerX,int centerY,int diameter){
        int radius = diameter/2; 
        int startX = centerX - radius;
        int startY = centerY - radius;
        g2d.fillOval(startX, startY, diameter, diameter);
    }
    

    @Override
    void move(int direction){
        
    }

    @Override
    void paintEntityCenter(Graphics g) {
        g.drawImage(getImage(),getX()-this.getDiameterSize()/2,getY()-this.getDiameterSize()/2,null);
    }
    
}
