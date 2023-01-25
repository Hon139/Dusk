import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends Entity{
    private int damage;

    Enemy(int xPos, int yPos, int maxLifePoints, int stepDistance, Image entityImage,int damage) {
        super(xPos, yPos, maxLifePoints, stepDistance, entityImage);
        this.damage = damage;
    }

    @Override
    void move(int direction) {
        
    }

    @Override
    void paintEntity(Graphics g) {
        
    }
    
}
