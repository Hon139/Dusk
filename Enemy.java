import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends Entity{


    Enemy(int xPos, int yPos, int maxLifePoints, int stepDistance, Image entityImage, int diameterSize) {
        super(xPos, yPos, maxLifePoints, stepDistance, entityImage,diameterSize );
    }

    @Override
    void move(int direction) {
        
    }

    @Override
    void paintEntityCenter(Graphics g) {
        
    }
    
}
