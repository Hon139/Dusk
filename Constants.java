import java.awt.Font;
import java.awt.Color; 
import java.awt.Dimension; 
public class Constants{
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 820;
    public static final int TICK_SPEED_MILLISECONDS = 5;
    public static final String GAME_NAME = "SELTIN";
    public static final Font MENU_FONT = new Font("Monospaced", Font.PLAIN, 30); 
    public static final Font TITLE_FONT = new Font("Monospaced", Font.PLAIN, 50); 
    public static final Color MENU_BUTTON_COLOR = new Color(1,1,1);
    public static final Color MENU_BUTTON_BACKGROUND_COLOR = new Color(72,72,72);
    public static final Dimension BUTTON_SIZE = new Dimension(500,110); 
    public static final int MOVEMENT_INPUT_DELAY = 20;
    public static final String BUTTON_TEXTURE_FILE_PATH = ".//Assets//MetalTexture.jpg";
    public static final Color GAME_BACKGROUND_COLOR = new Color(4, 5, 13);
    public static final int DRONE_SIZE = 130;
    public static final int DRONE_SPEED = 10;
    public static final int DRONE_ROTATE_SPEED = 5;
    public static final int ENEMY_SIZE = 200;
    public static final int[] BORDER = {0,0,7000,5000}; //rect
    public static final int DRONE_SCAN_RADIUS = 500; 
    public static final int enemyDetectionRadius = 3000;
    private Constants(){
    }
}