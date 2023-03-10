import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class Ui{
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

    public static void FormatForegroundPanel(JPanel foregroundPanel){
        foregroundPanel.setBackground(new Color(0,0,0,0));
        foregroundPanel.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        foregroundPanel.setOpaque(false);
        foregroundPanel.setVisible(true);
    }

    public static void setupMenuBackground(JLayeredPane mainLayeredPane){
        JLabel backgroundImage = new JLabel(new ImageIcon(".//Assets//static.jpg")); 
        backgroundImage.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        backgroundImage.setVisible(true);
        backgroundImage.setOpaque(true);
        mainLayeredPane.add(backgroundImage,JLayeredPane.DEFAULT_LAYER);
    }   

    public static void setupConsole(JPanel panel, JTextField textField, JLabel textHistory){
        int panelWidth = 500;
        int panelHeight = 200;
        int fieldWidth = panelWidth;
        int fieldHeight = 30;

        Border lineBorder = BorderFactory.createLineBorder(Color.gray, 1);
        Border marginBorder = new EmptyBorder(0, 5, 0, 0); 
        CompoundBorder border = new CompoundBorder(lineBorder, marginBorder);

        textHistory.setBorder(border);
        textHistory.setBackground(new Color(0,0,0));
        textHistory.setBounds(0,0 ,panelWidth,panelHeight-fieldHeight);
        textHistory.setVerticalAlignment(JLabel.BOTTOM);
        textHistory.setVisible(true);
        textHistory.setOpaque(true);

        textField.setBounds(0,panelHeight-fieldHeight,fieldWidth,fieldHeight);
        textField.setBackground(new Color(0,0,0));
        textField.setCaretColor(new Color(255,255,255));
        textField.setForeground(new Color(91, 209, 73));
        textField.setColumns(30);
        textField.setVisible(true);
        textField.setSelectionColor(new Color(60, 230, 85));
        textField.setBorder(border);

        panel.setBounds(950,555,panelWidth,panelHeight);
        panel.setLayout(null);
        panel.add(textField);
        panel.add(textHistory);
    }

    public static void launchGameOverScreen(JLayeredPane mainLayeredPane, JButton restartButton,JButton exitButton){
        mainLayeredPane.removeAll();
        setupMenuBackground(mainLayeredPane);

        JLabel title = new JLabel("GAME OVER"); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.TITLE_FONT);
        exitButton.setText("Exit");
        Ui.FormatMenuButton(exitButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
       
        restartButton.setText("Restart");
        Ui.FormatMenuButton(restartButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        JPanel foregroundLayer = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,40)); 
        FormatForegroundPanel(foregroundLayer);
        foregroundLayer.add(title);
        foregroundLayer.add(restartButton);
        foregroundLayer.add(exitButton); 
        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }

    public static void launchControlsMenu(JLayeredPane mainLayeredPane){
        mainLayeredPane.removeAll();
        setupMenuBackground(mainLayeredPane);
        
        JLabel title = new JLabel("Controls"); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.TITLE_FONT);

        JLabel bodyText = new JLabel("");
        bodyText.setFont(Constants.MENU_FONT);
        bodyText.setForeground(new Color(255,255,255));
        bodyText.setText( // utilize html formmating to create line breaks as escape sequences don't work 
            "<html>Objective: Survive as long as possible"+
            "<br> Use WASD to move "+
            "<br> Utilize tools through the console"+
            "<br> Enemies will be hidden by deafult"+
            "<br> COMANDS primaries: scan & blast"+
            "<br> You can exit console focus with ESCAPE"+
        "<html>");

        JPanel foregroundLayer = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,40)); 
        FormatForegroundPanel(foregroundLayer);
        foregroundLayer.add(title);
        foregroundLayer.add(bodyText); 
        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }

    public static void launchMainMenu(JLayeredPane mainLayeredPane,JButton playButton, JButton controlsButton, JButton exitButton){
        mainLayeredPane.removeAll();
        setupMenuBackground(mainLayeredPane);
        JLabel title = new JLabel(Constants.GAME_NAME); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.TITLE_FONT);
        exitButton.setText("Exit");
        Ui.FormatMenuButton(exitButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        controlsButton.setText("Controls");
        Ui.FormatMenuButton(controlsButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        playButton.setText("Play");
        Ui.FormatMenuButton(playButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);
        JPanel foregroundLayer = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,40)); 
        FormatForegroundPanel(foregroundLayer);
        foregroundLayer.add(title);
        foregroundLayer.add(playButton);
        foregroundLayer.add(controlsButton); 
        foregroundLayer.add(exitButton); 
        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }

    public static void launchMainGame(JLayeredPane mainLayeredPane, JPanel gamePanel){
        mainLayeredPane.removeAll();
        gamePanel.setBackground(Constants.GAME_BACKGROUND_COLOR);
        mainLayeredPane.add(gamePanel,JLayeredPane.PALETTE_LAYER);
    }
}
