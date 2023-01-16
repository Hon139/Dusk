import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

 
import javax.imageio.ImageIO;
public class Utilities{
 
    public static ImageIcon scaleImage(ImageIcon image,int newWidth,int newHeight){
        Image newImage = image.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }



    private Utilities(){}
}