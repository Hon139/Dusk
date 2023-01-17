import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menus{

    public static void FormatMenuButton(JButton menuButton,int imageWidth,int imageHeight,String image){
        menuButton.setForeground(new Color(255,255,255));
        menuButton.setBorderPainted(false);
        menuButton.setBorder(null);
        menuButton.setContentAreaFilled(false);
        menuButton.setHorizontalTextPosition(JButton.CENTER);
        menuButton.setVerticalTextPosition(JButton.CENTER);
        menuButton.setBackground(new Color(72,72,72));
        menuButton.setFont(Constants.MENU_FONT);
        menuButton.setIcon(Utilities.scaleImage(new ImageIcon(".//Assets//MetalTexture.jpg"),
        imageWidth,imageHeight));
    }




    
}
