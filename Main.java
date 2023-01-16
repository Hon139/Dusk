import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
    JFrame frame; 
    JLayeredPane mainLayeredPane; 
    GraphicsPanel gamePanel; 

    JButton exitButton; 
    JButton settingButton;
    JButton playButton;

    JButton newLoadButton;

    JLabel backgroundImage;
    Drone D1; 

    long lastMovementMillis = 0;

    static Main main; 

    boolean leftKey = false; 
    boolean rightKey = false;
    boolean upKey = false;
    boolean downKey = false;

    Main(){
        this.frame = new JFrame(Constants.GAME_NAME);
        this.gamePanel = new GraphicsPanel();
        this.gamePanel.setVisible(true);
        this.gamePanel.setOpaque(true);
        this.gamePanel.setBounds(0,0,Constants.WIDTH,Constants.HEIGHT);
        this.gamePanel.addMouseListener(new MainMouseListener());
        this.gamePanel.setFocusable(true);
        this.mainLayeredPane = new JLayeredPane();
        mainLayeredPane.setOpaque(true);
        ImageIcon framePicture = new ImageIcon(".//Assets//dusks.png");
        frame.setIconImage(framePicture.getImage());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.frame.setMaximumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        this.frame.setMinimumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        this.frame.setResizable(false);
        frame.add(mainLayeredPane);
        this.frame.addKeyListener(new MainKeyListener());
        this.frame.setVisible(true);
    }

    public void runMain(){
        D1 = new Drone(0, 0, 0, 10,Drone.customDrone() ,10);
        this.mainLayeredPane.removeAll();
        this.mainLayeredPane.add(gamePanel,JLayeredPane.DEFAULT_LAYER);
        this.mainLayeredPane.repaint();
        while(true){
            if (Constants.MOVEMENT_INPUT_DELAY <= System.currentTimeMillis()-lastMovementMillis){
                if (leftKey){D1.rotate(-1);}
                if (rightKey){D1.rotate(1);}
                if (downKey){D1.move(-1);}
                if (upKey){D1.move(1);}

            }
            this.gamePanel.repaint();
            try{Thread.sleep(Constants.TICK_SPEED_MILLISECONDS);} catch (InterruptedException e){}
        }
    }

    public void launchMainMenu(){
        backgroundImage = new JLabel(new ImageIcon(".//Assets//static.jpg")); 
        backgroundImage.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        backgroundImage.setBackground(Color.RED);
        backgroundImage.setVisible(true);
        backgroundImage.setOpaque(true);

        JLabel title = new JLabel(Constants.GAME_NAME); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.MENU_FONT);

        exitButton = new JButton("Exit",Utilities.scaleImage(
        new ImageIcon(".//Assets//MetalTexture.jpg"),
        (int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight())); 
        exitButton.setForeground(new Color(255,255,255));
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new MainActionListener());
        exitButton.setBorder(null);
        exitButton.setContentAreaFilled(false);
        exitButton.setHorizontalTextPosition(JButton.CENTER);
        exitButton.setVerticalTextPosition(JButton.CENTER);
        exitButton.setBackground(new Color(72,72,72));
        exitButton.addMouseListener(new MainMouseListener());
        exitButton.setFont(Constants.MENU_FONT);

        settingButton = new JButton("Setting",Utilities.scaleImage(
        new ImageIcon(".//Assets//MetalTexture.jpg"),
        (int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight())); 
        settingButton.setForeground(new Color(255,255,255));
        settingButton.setBorderPainted(false);
        settingButton.addActionListener(new MainActionListener());
        settingButton.setBorder(null);
        settingButton.setContentAreaFilled(false);
        settingButton.setHorizontalTextPosition(JButton.CENTER);
        settingButton.setVerticalTextPosition(JButton.CENTER);
        settingButton.setBackground(new Color(72,72,72));
        settingButton.addMouseListener(new MainMouseListener());
        settingButton.setFont(Constants.MENU_FONT);

        playButton = new JButton("Play",Utilities.scaleImage(
        new ImageIcon(".//Assets//MetalTexture.jpg"),
        (int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight())); 
        playButton.setForeground(new Color(255,255,255));
        playButton.setBorderPainted(false);
        playButton.addActionListener(new MainActionListener());
        playButton.setBorder(null);
        playButton.setContentAreaFilled(false);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setBackground(new Color(72,72,72));
        playButton.addMouseListener(new MainMouseListener());
        playButton.setFont(Constants.MENU_FONT);

        JPanel foregroundLayer = new JPanel(new FlowLayout()); 
        foregroundLayer.setBackground(new Color(0,0,0,0));
        foregroundLayer.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        foregroundLayer.setOpaque(false);
        foregroundLayer.setVisible(true);
        foregroundLayer.add(title);
        foregroundLayer.add(playButton);
        foregroundLayer.add(settingButton); 
        foregroundLayer.add(exitButton); 


        mainLayeredPane.add(backgroundImage,JLayeredPane.DEFAULT_LAYER);
        mainLayeredPane.add(foregroundLayer,JLayeredPane.PALETTE_LAYER);
    }

    public void launchGameLoad(){
        mainLayeredPane.removeAll();
        mainLayeredPane.add(backgroundImage,JLayeredPane.DEFAULT_LAYER);
        frame.repaint();

        JLabel title = new JLabel(Constants.GAME_NAME); 
        title.setForeground(new Color(255,255,255,150));
        title.setFont(Constants.MENU_FONT);

        newLoadButton = new JButton("Exit",Utilities.scaleImage(
        new ImageIcon(".//Assets//MetalTexture.jpg"),
        (int)Constants.BUTTON_SIZE.getWidth(),(int)Constants.BUTTON_SIZE.getHeight())); 
        newLoadButton.setForeground(new Color(255,255,255));
        newLoadButton.setBorderPainted(false);
        newLoadButton.addActionListener(new MainActionListener());
        newLoadButton.setBorder(null);
        newLoadButton.setContentAreaFilled(false);
        newLoadButton.setHorizontalTextPosition(JButton.CENTER);
        newLoadButton.setVerticalTextPosition(JButton.CENTER);
        newLoadButton.setBackground(new Color(72,72,72));
        newLoadButton.addMouseListener(new MainMouseListener());
        newLoadButton.setFont(Constants.MENU_FONT);

        JPanel foregroundLayerLoadMenu = new JPanel(new FlowLayout()); 
        foregroundLayerLoadMenu.setBackground(new Color(0,0,0,0));
        foregroundLayerLoadMenu.setBounds(new Rectangle(0,0,Constants.WIDTH,Constants.HEIGHT));
        foregroundLayerLoadMenu.setOpaque(false);
        foregroundLayerLoadMenu.setVisible(true);
        foregroundLayerLoadMenu.add(title);
        foregroundLayerLoadMenu.add(playButton);
        foregroundLayerLoadMenu.add(settingButton); 
        foregroundLayerLoadMenu.add(exitButton); 
    }

    public static void main(String [] args){
        main = new Main();
        main.runMain();
        //main.launchMainMenu();
    }

    public class MainActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == exitButton){
                frame.dispose();
            }

            if (e.getSource() == settingButton){

            }

            if (e.getSource() == playButton){
                main.launchGameLoad(); 
            }
        }
    }
    public class MainKeyListener implements KeyListener{   
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_UP){
                upKey = true; 
                lastMovementMillis = System.currentTimeMillis();
            } 
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                downKey = true; 
                lastMovementMillis = System.currentTimeMillis();
            } 
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                leftKey = true; 
                lastMovementMillis = System.currentTimeMillis();
            } 
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightKey = true;
                lastMovementMillis = System.currentTimeMillis();
            } 
        }
        public void keyReleased(KeyEvent e){ 
            if (e.getKeyCode() == KeyEvent.VK_UP){
                upKey = false; 
            } 
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                downKey = false; 
            } 
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                leftKey = false; 
            } 
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightKey = false;
            } 
        }   
        public void keyTyped(KeyEvent e){
        }           
    }   
    public class MainMouseListener implements MouseListener{
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

    public class GraphicsPanel extends JPanel{
        public void paintComponent(Graphics g) { 
            super.paintComponent(g); //required
            D1.paintEntity(g);
        }
    }
}