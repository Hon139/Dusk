import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class Main{
    JFrame frame; 
    JLayeredPane mainLayeredPane; 
    GraphicsPanel gamePanel; 
    JButton newLoadButton;
    JLabel backgroundImage;

    long lastMovementMillis = 0;
    static Main main; 
    boolean leftKey = false; 
    boolean rightKey = false;
    boolean upKey = false;
    boolean downKey = false;
    boolean escapeKey = false;
    boolean enterKey = false;

    JButton exitButton; 
    JButton playButton;
    JButton settingButton;

    JLabel consoleHistory;
    JTextField consoleTextField;
    JPanel consolePanel;

    boolean scanning = false; 

    Map map;

    int gameState = 0;
    final static int GAME_STATE_MENU = 0;
    final int GAME_STATE_MAIN_GAME = 1;
    final static int GAME_STATE_PAUSED = -1;

    public static void main(String [] args){
        main = new Main();
        main.launchMainMenu();
        
        while (true){
            main.performMovementLogic();
            main.gamePanel.repaint();
        }
    }

    Main(){
        frame = new JFrame(Constants.GAME_NAME);
        Ui.setupFrame(frame, Constants.WIDTH, Constants.HEIGHT, ".//Assets//dusks.png");
        frame.addKeyListener(new MainKeyListener());        
        map = new Map(Constants.BORDER);
        gamePanel = new GraphicsPanel();
        gamePanel.setVisible(true);
        gamePanel.setOpaque(true);
        gamePanel.setBounds(0,0,Constants.WIDTH,Constants.HEIGHT);
        gamePanel.addMouseListener(new MainMouseListener());
        gamePanel.addKeyListener(new MainKeyListener());
        gamePanel.setFocusable(true);

        mainLayeredPane = new JLayeredPane();
        mainLayeredPane.setOpaque(true);
        mainLayeredPane.setVisible(true);
        mainLayeredPane.requestFocusInWindow();

        consoleTextField = new JTextField(">Input Commands Here");
        consoleHistory = new JLabel("<html><html>");
        consolePanel = new JPanel();
        consoleTextField.addKeyListener(new MainKeyListener());

        exitButton = new JButton();
        exitButton.addActionListener(new MainActionListener());
        playButton = new JButton();
        playButton.addActionListener(new MainActionListener());
        settingButton = new JButton();
        settingButton.addActionListener(new MainActionListener()); 

        frame.setFocusable(true);
        frame.add(mainLayeredPane);
        frame.repaint();
        frame.revalidate();
    }   

    public void launchMainMenu(){
        Ui.launchMainMenu(mainLayeredPane, playButton, settingButton, exitButton);
    }

    public void launchMainGame(){
        Ui.launchMainGame(mainLayeredPane, gamePanel);
        gameState = main.GAME_STATE_MAIN_GAME;
        Ui.setupConsole(consolePanel, consoleTextField,consoleHistory);
        mainLayeredPane.add(consolePanel,JLayeredPane.POPUP_LAYER);
    }

    public void performMovementLogic(){
        if (gameState == GAME_STATE_MAIN_GAME){
            if (Constants.MOVEMENT_INPUT_DELAY <= System.currentTimeMillis()-lastMovementMillis){
                if (leftKey || rightKey || upKey || downKey){lastMovementMillis = System.currentTimeMillis();}
                if (leftKey){map.getDrone().rotate(-1);}
                if (rightKey){map.getDrone().rotate(1);}
                if (downKey && !map.willCollideWithBorder(-1)){map.getDrone().move(-1);}
                if (upKey && !map.willCollideWithBorder(1)){map.getDrone().move(1);}
            }
            if (scanning == true){
                map.scan();
            }
            try{Thread.sleep(Constants.TICK_SPEED_MILLISECONDS);} catch (InterruptedException e){}
        }
    }

    public void pushConsoleCommand(){
        int length = consoleHistory.getText().length();
        String inputText = Utilities.cleanString(consoleTextField.getText());
        boolean commandStatus = performConsoleCommands(inputText);
        if (commandStatus == false){
        consoleHistory.setText(consoleHistory.getText().substring(0,length-6)+"\""+inputText+"\""+" Isn't recognized!"+"<br>"+consoleHistory.getText().substring(length-6));
        }
        consoleHistory.setText(consoleHistory.getText().substring(0,length-6)+consoleTextField.getText()+"<br>"+consoleHistory.getText().substring(length-6));
        consoleTextField.setText(">");
    }

    public boolean performConsoleCommands(String command){
        String[] knownCommands = {"scan","scanner","shock","blast","detonate"};
        if (Arrays.deepToString(knownCommands).contains(command)!=true){
            return false;
        }
        if (command.equals(knownCommands[0]) || command.equals(knownCommands[1])){
            this.scanning = !this.scanning;
        }
        if (command.equals(knownCommands[2]) || command.equals(knownCommands[3]) || command.equals(knownCommands[4])){
            map.blastWave();
        }
        return true;
    }

    public class MainActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == playButton){
                main.launchMainGame();
            }
            if (e.getSource() == exitButton){
                frame.dispose();
            }
        }
    }
    
    public class MainKeyListener implements KeyListener{   
        public void keyPressed(KeyEvent e){
            if (consoleTextField.isFocusOwner()){
                if (e.getKeyCode() == KeyEvent.VK_TAB){
                    gamePanel.requestFocusInWindow();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    pushConsoleCommand();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    gamePanel.requestFocusInWindow();
                }
            }   
            if (!consoleTextField.isFocusOwner()){
                if (e.getKeyCode() == KeyEvent.VK_TAB){
                    consoleTextField.grabFocus();
                    consoleTextField.requestFocusInWindow();
                    consoleTextField.isFocusable();
                    consoleTextField.validate();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    upKey = true; 
                } 
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    downKey = true; 
                } 
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    leftKey = true; 
                } 
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    rightKey = true;
                } 
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                escapeKey = true;
            } 

            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                enterKey = true;
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

            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightKey = false;
            } 

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                escapeKey = false;
            } 

            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                enterKey = false;
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
        public void paintComponent(Graphics g){
            super.paintComponent(g); //required
            map.drawMap(g);
            repaint();
        }
    }
}