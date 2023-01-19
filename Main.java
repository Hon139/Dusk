import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
    JFrame frame; 
    JLayeredPane mainLayeredPane; 
    GraphicsPanel gamePanel; 
    JButton newLoadButton;
    JLabel backgroundImage;
    Drone D1; 

    long lastMovementMillis = 0;
    static Main main; 
    boolean leftKey = false; 
    boolean rightKey = false;
    boolean upKey = false;
    boolean downKey = false;

    JButton exitButton; 
    JButton playButton;
    JButton settingButton;


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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.frame.setMaximumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        this.frame.setMinimumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        this.frame.setResizable(false);
        frame.add(mainLayeredPane);
        this.frame.addKeyListener(new MainKeyListener());
        this.frame.setVisible(true);

        exitButton = new JButton();
        exitButton.addActionListener(new MainActionListener());
        playButton = new JButton();
        playButton.addActionListener(new MainActionListener());
        settingButton = new JButton();
        settingButton.addActionListener(new MainActionListener()); 
    }

    public void launchMenu(){
        Menus.launchMainMenu(mainLayeredPane, playButton, settingButton, exitButton);
    }

    public void loop(){
        while (true){}
    }

    public void runMainGame(){
        System.out.println("QWUHWQERKQWGRWE");
        mainLayeredPane.removeAll();
        mainLayeredPane.repaint();
        mainLayeredPane.revalidate();

        D1 = new Drone(0, 0, 0, 10,Drone.createDroneImage() ,10);
        this.mainLayeredPane.add(gamePanel);
        while(true){
            System.out.println("QWE");
            if (Constants.MOVEMENT_INPUT_DELAY <= System.currentTimeMillis()-lastMovementMillis){
                if (leftKey){D1.rotate(-1);}
                if (rightKey){D1.rotate(1);}
                if (downKey){D1.move(-1);}
                if (upKey){D1.move(1);}
            }
            this.gamePanel.repaint();
            this.mainLayeredPane.repaint();
            this.frame.repaint();
            System.out.println("QWUEGHILHEOIQR");
            try{Thread.sleep(Constants.TICK_SPEED_MILLISECONDS);} catch (InterruptedException e){}
        }

    }

    public static void main(String [] args){
        main = new Main();
        main.launchMenu();
    }

    public class MainActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            if (e.getSource() == playButton){
                runMainGame();
                
            }
            if (e.getSource() == exitButton){
                frame.dispose();
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
            System.out.println("qwer");
            D1.paintEntity(g);
            repaint();
        }
    }
}