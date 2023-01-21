import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Ui{
    static JButton newLoadButton;

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

    public static void FormatMenuForegroundPanel(JPanel foregroundPanel){
        foregroundPanel.setBackground(new Color(0,0,0,0));
        foregroundPanel.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        foregroundPanel.setOpaque(false);
        foregroundPanel.setVisible(true);
    }

    public static void setBackground(JLayeredPane mainLayeredPane){
        JLabel backgroundImage = new JLabel(new ImageIcon(".//Assets//static.jpg")); 
        backgroundImage.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        backgroundImage.setVisible(true);
        backgroundImage.setOpaque(true);
        mainLayeredPane.add(backgroundImage,JLayeredPane.DEFAULT_LAYER);
    }

    public static void launchMainMenu(JLayeredPane mainLayeredPane,JButton playButton, JButton settingButton, JButton exitButton){
        mainLayeredPane.removeAll();
        setBackground(mainLayeredPane);
        JLabel title = new JLabel(Constants.GAME_NAME); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.TITLE_FONT);

        exitButton.setText("Exit");
        Ui.FormatMenuButton(exitButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        settingButton.setText("Setting");

        Ui.FormatMenuButton(settingButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        playButton.setText("Play");
        Ui.FormatMenuButton(playButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);

        JPanel foregroundLayer = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,40)); 
        FormatMenuForegroundPanel(foregroundLayer);
        foregroundLayer.add(title);
        foregroundLayer.add(playButton);
        foregroundLayer.add(settingButton); 
        foregroundLayer.add(exitButton); 

        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }
}
