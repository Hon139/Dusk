import javax.swing.*;
import java.awt.*;
public class Ui{
    static JButton newLoadButton;

    public static void FormatMenuButton(JButton menuButton,int imageWidth,int imageHeight,String imagePath){
        menuButton.setForeground(new Color(255,255,255));
        menuButton.setBorderPainted(false);
        menuButton.setBorder(null);
        menuButton.setContentAreaFilled(false);
        menuButton.setHorizontalTextPosition(JButton.CENTER);
        menuButton.setVerticalTextPosition(JButton.CENTER);
        menuButton.setBackground(new Color(72,72,72));
        menuButton.setFont(Constants.MENU_FONT);
        menuButton.setIcon(Utilities.scaleImage(new ImageIcon(imagePath),
        imageWidth,imageHeight));
    }

    public static void setupFrame(JFrame frame,int width, int height,String iconPath){
        ImageIcon framePicture = new ImageIcon(iconPath);
        frame.setIconImage(framePicture.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void FormatMenuForegroundPanel(JPanel foregroundPanel){
        foregroundPanel.setBackground(new Color(0,0,0,0));
        foregroundPanel.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        foregroundPanel.setOpaque(false);
        foregroundPanel.setVisible(true);
    }

    public static void setMenuBackground(JLayeredPane mainLayeredPane){
        JLabel backgroundImage = new JLabel(new ImageIcon(".//Assets//static.jpg")); 
        backgroundImage.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        backgroundImage.setVisible(true);
        backgroundImage.setOpaque(true);
        mainLayeredPane.add(backgroundImage,JLayeredPane.DEFAULT_LAYER);
    }

    public static void setupConsole(JPanel panel, JTextField textField){
        panel.setBounds(800,500,600,400);
        panel.setBackground(new Color(0,0,0,0));
        panel.add(textField);

        textField.setBackground(new Color(0,0,0));
        textField.setCaretColor(new Color(255,255,255));
        textField.setVisible(true);
        textField.setForeground(new Color(91, 209, 73));
    }

    public static void launchMainMenu(JLayeredPane mainLayeredPane,JButton playButton, JButton settingButton, JButton exitButton){
        mainLayeredPane.removeAll();
        setMenuBackground(mainLayeredPane);
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

    public static void launchMainGame(JLayeredPane mainLayeredPane, JPanel gamePanel){
        mainLayeredPane.removeAll();
        gamePanel.setBackground(new Color(7, 23, 48));
        mainLayeredPane.add(gamePanel,JLayeredPane.PALETTE_LAYER);
    }



}
