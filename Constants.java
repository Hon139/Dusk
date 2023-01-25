import java.awt.Font;
import java.awt.Color; 
import java.awt.Dimension; 
public class Constants{

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 820;
    public static final int GROUND = 560;
    public static final int TICK_SPEED_MILLISECONDS = 20;
    public static final String GAME_NAME = "SELTIN";
    public static final Font MENU_FONT = new Font("Monospaced", Font.PLAIN, 30); 
    public static final Font TITLE_FONT = new Font("Monospaced", Font.PLAIN, 50); 
    public static final Color MENU_BUTTON_COLOR = new Color(1,1,1);
    public static final Color MENU_BUTTON_BACKGROUND_COLOR = new Color(72,72,72);
    public static final Dimension BUTTON_SIZE = new Dimension(500,110); 

    public static final int MOVEMENT_INPUT_DELAY = 20;

    public static final String BUTTON_TEXTURE_FILE_PATH = ".//Assets//MetalTexture.jpg";

    public static final Color GAME_BACKGROUND_COLOR = new Color(4, 13, 28);


    private Constants(){
    }
}