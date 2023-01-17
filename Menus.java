import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menus{
    static JButton exitButton;
    static JButton settingButton;
    static JButton playButton;
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

    public static void launchMainMenu(JLayeredPane mainLayeredPane){
        mainLayeredPane.removeAll();
        setBackground(mainLayeredPane);
        JLabel title = new JLabel(Constants.GAME_NAME); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.TITLE_FONT);

         exitButton = new JButton("Exit");
        exitButton.addActionListener(new MainActionListener());
        exitButton.addMouseListener(new MainMouseListener());
        Menus.FormatMenuButton(exitButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);

         settingButton = new JButton("Setting");
        settingButton.addActionListener(new MainActionListener());
        settingButton.addMouseListener(new MainMouseListener());
        Menus.FormatMenuButton(settingButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);

        playButton = new JButton("Play");
        playButton.addActionListener(new MainActionListener());
        playButton.addMouseListener(new MainMouseListener());
        Menus.FormatMenuButton(playButton,(int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight(),Constants.BUTTON_TEXTURE_FILE_PATH);

        JPanel foregroundLayer = new JPanel(new FlowLayout(FlowLayout.CENTER,1000,40)); 
        FormatMenuForegroundPanel(foregroundLayer);
        foregroundLayer.add(title);
        foregroundLayer.add(playButton);
        foregroundLayer.add(settingButton); 
        foregroundLayer.add(exitButton); 


        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }

    public static class MainActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        }
    }
    public class MainKeyListener implements KeyListener{   
        public void keyPressed(KeyEvent e){
            if (e.getSource() == exitButton){
                 return; 
            }
        }
        public void keyReleased(KeyEvent e){ 
        }
        public void keyTyped(KeyEvent e){
        }           
    }   
    public static class MainMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){   // moves the box at the mouse location
        }
        public void mousePressed(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mouseReleased(MouseEvent e){  // MUST be implemented even if not used!
        }
        public void mouseEntered(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mouseExited(MouseEvent e){    // MUST be implemented even if not used!
        }
    }  

    
}
