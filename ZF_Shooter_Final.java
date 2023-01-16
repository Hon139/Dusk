// NAMES: Andrew n Timothy  
// DATE: 2022/01/26
// DESCRIPTION: Final Game Project - Top-Down Shooter 

// Imports 
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.sound.sampled.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class ZF_Shooter_Final {

  static GraphicsPanel mainGraphicPanel;
  static JFrame frame;
  static JLayeredPane mainPane;

  // window size
  static final int WIDTH = 1300;
  static final int HEIGHT = 1090;

  // names of SFX and music
  static final String[] SFXName = { "menuHover", "buttonEnter", "buttonLeave", "gameStart", "gunshot", "error",
      "playerDeath", "healthRegen" };
  static final String[] TrackName = { "BattleTheme", "MenuMusic" };

  // audio stuff for playing stuff
  static int musicVolumeLevel = 100;
  static int soundVolumeLevel = 100;
  static int sliderStart = 6;
  static float tempMusicVolumeLevel = 100;
  static float currentMusicVolume = 0;
  static boolean clearSound = false;

  static FloatControl musicGainControl;

  static Clip sound;
  static Clip music;

  // define images for player, background, and bullets
  static BufferedImage backgroundImage;
  static BufferedImage bulletImage;

  static String bulletType1;
  static String bulletType2;
  static String bulletType3;

  static String mannerIdle;
  static String mannerLeft1;
  static String mannerLeft2;
  static String mannerRight1;
  static String mannerRight2;
  static String mannerBack1;
  static String mannerBack2;
  static String mannerFront1;
  static String mannerFront2;

  static String pausePicture;
  static BufferedImage manner;
  static BufferedImage skull;
  static BufferedImage healthImage;
  // images defined ^

  // gets windows username for file paths
  static final String userName = System.getProperty("user.name");

  static boolean gameLoop = true;
  static boolean alteredStep = true;
  static boolean isMoving = false;
  static boolean isShooting = false;

  // time stuff (milliseconds elapsed)
  static long previousSpawnMillis = 0;
  static long previousSpawnMovementMillis = 0;
  static long previousHitMillis = 0;
  static long gameLoopStartTime = 0;
  static long pauseTimeElapsed = 0;
  static long pauseStartTime = 0;

  // how long since the program started
  static long programStartTime;
  static long previousTime;
  static long previousTimeBulletSpeed;
  static long previousTimeBulletFireSpeed;

  static long previousPointTime = 0; // time since last passive points
  static int passivePointInterval = 2000; // time for collecting passive points (every 2000 milliseconds)

  // arrays which store player and game information such as position, direction
  // facing/heading,
  // speed/movement, animation sequences, and borders
  static int[] startPosition = { 300, 300 };
  static int[][] playerPosition = { startPosition, { 200, 200 } };
  static int[][] playerSpeed = { { 0, 0 }, { 0, 0 } };
  static int[] gameBorder = { 40, 112, 920, 935 };
  static int[] nextPic = { 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2 };

  // bullet stuff that defines max ammo and counts current bullets
  static int[][] bulletProperties = new int[120][6]; // xspeed, yspeed, xpos,ypos, ammo, penetration
  static int bulletCount = -1;
  static int bulletSpeed = 6;
  static int currentAmmo = 1;

  static int intervalDelayManner = 15;
  static int intervalDelayBullet = 1;
  static int intervalDelayBulletFireSpeed = 200;

  // Manner Properties
  static int turnState = 0;
  static int spriteCounter = 0;
  // 0 == Pause | 1 == mainGame | -1 == death screen | 2 == Main Menu
  // 3=difficulty|4==Settings
  static int currentScreen = 2;

  // enemy properties:
  // max enemies, random spawns, current amount
  static final int MAX_SPAWNS = 60;
  static int[][] spawnZones = { { 50, 900, 50, 900 }, { 150, 150, 950, 950 } };
  static int[][] spawns = new int[70][9]; // XSpeed, YSpeed, XPos, YPos, AI Type, Health, spriteCounter, isAttacking,
                                          // turnstate
  static int[] enemySpeeds = { 2, 2, 2 };
  static int currentSpawnAmount = 0;
  static int spawnInterval = 3000;
  static int spawnWaveSize = 1;
  static int highestSpawnWave = 4;
  static int lowestSpawnWave = 1;
  static int currentEnemySpeed = 2;
  // animation arrays for skull enemy
  static int[] LRAnimation = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
  static int[] upDownAnimation = { 1, 1, 1, 2, 2, 2 };
  static int[] attackAnimation = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3 };

  // game stuff
  static int score = 0;
  static int lives = 5;
  static int maxLives = 5;
  static int playerSpeedMultiplier = 3; // pixels moved every frame
  static int hitInvincibilityInterval = 3000; // i frame duration

  // time since last enemy spawned
  static int spawnMovementInterval = 15;
  // determines time between passively getting more lives
  static int passiveLifeInterval = 500;
  // gives you a life once you have acquired this many points
  static int nextLifeScoreCount = 500;

  // buttons and panels for controlling basic graphics
  static Border basicBorder = BorderFactory.createLineBorder(Color.black, 1);
  static JPanel pauseButtonPanel;
  static JPanel masterPausePanel;

  static JButton pauseExitButton;
  static JButton pauseRestartButton;
  static JButton pauseResumeButton;

  static JButton mainStartButton = new JButton("START");
  static JButton mainSettingsButton = new JButton("SETTINGS");
  static JButton mainExitButton = new JButton("EXIT");

  static JLabel menuBackgroundImageLabel;

  // settings menu
  static JSlider musicVolumeSlider;
  static JButton volumeButtonConfirm;
  static JButton volumeButtonCancel;
  static JPanel masterSettingsPanel = new JPanel();
  static JPanel masterMainMenuPanel;
  static JLabel mainMenuTitle = new JLabel("ZF_Shooter");

  // difficulty menu stuff
  static JPanel difficultyButtonPanel;
  static JPanel masterDifficultyPanel;
  static JButton easyButton;
  static JButton mediumButton;
  static JButton hardButton;

  static JLabel settingsTitle;
  static JLabel volumeTitle;
  static JLabel minVolume;
  static JLabel maxVolume;
  static JLabel controls;
  static JLabel movement;
  static JLabel shooting;
  static JLabel difficultyTitle;
  /* ------------------------------- Main Method ------------------------------ */

  public static void main(String[] args) { // le main method
    MusicManager(1); // begin Music
    // Get program Start Time to Calibrate time
    // Define File Paths for player sprite to allowing passing in methods
    mannerIdle = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerIdle.png";
    mannerLeft1 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerLeft1.png";
    mannerLeft2 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerLeft2.png";
    mannerRight1 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerRight1.png";
    mannerRight2 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerRight2.png";
    mannerBack1 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerBack1.png";
    mannerBack2 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerBack2.png";
    mannerFront1 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerFront1.png";
    mannerFront2 = "C:\\Users\\" + userName + "\\Assets\\Character\\MannerFront2.png";
    bulletType1 = "C:\\Users\\" + userName + "\\Assets\\Projectiles\\bulletType1.png";
    bulletType2 = "C:\\Users\\" + userName + "\\Assets\\Projectiles\\bulletType1.png";
    bulletType3 = "C:\\Users\\" + userName + "\\Assets\\Projectiles\\bulletType1.png";

    try {
      healthImage = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Icons\\HP.png"));
    } catch (Exception x) {
    }

    programStartTime = System.currentTimeMillis(); // Calibrate time clock

    // Define Canvas & LayeredPane Properties
    mainGraphicPanel = new GraphicsPanel();
    mainGraphicPanel.setOpaque(true);
    mainGraphicPanel.setLayout(null);

    mainPane = new JLayeredPane();
    mainPane.setLayout(null);
    mainPane.setBounds(0, 0, 1300, 1090);
    mainPane.add(mainGraphicPanel, Integer.valueOf(0));

    // Define Frame Properties
    frame = new JFrame("ZF_Shooter");
    ImageIcon framePicture = new ImageIcon("C://Users//" + userName + "//Assets//Icons//ZF_studios.png");
    frame.setIconImage(framePicture.getImage());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WIDTH, HEIGHT);
    frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    frame.setResizable(false);
    frame.add(mainPane); // Implement Layered Pane
    frame.setContentPane(mainGraphicPanel);
    frame.addKeyListener(new keys());
    frame.setVisible(true);
    runGameLoop();
  }

  /* ---------------------------- // Main Game Loop --------------------------- */
  public static void runGameLoop() {
    // Request Window focus on this game window
    frame.setFocusable(true);
    frame.requestFocusInWindow();

    gameLoopStartTime = (System.currentTimeMillis() - programStartTime);
    while (true) {
      frame.repaint();

      // check if currentScreen == maingameLoop and if so perform all background
      // operations such as collisions etc
      if (currentScreen == 1) {

        // Check if the player is moving
        if (playerSpeed[0][0] != 0 || playerSpeed[0][1] != 0) {
          isMoving = true;
        } else {
          isMoving = false;
        }

        // Move Player & prevent going out of bounds
        if ((System.currentTimeMillis() - programStartTime) - previousTime >= intervalDelayManner && isMoving == true) {
          previousTime = System.currentTimeMillis() - programStartTime;
          // Move X Position
          if (playerSpeed[0][0] > 0 && playerPosition[0][0] < gameBorder[2]) {
            playerPosition[0][0] = playerPosition[0][0] + playerSpeed[0][0];
          } else if (playerSpeed[0][0] < 0 && playerPosition[0][0] > gameBorder[0]) {
            playerPosition[0][0] = playerPosition[0][0] + playerSpeed[0][0];
          }
          // Move Y Position
          if (playerSpeed[0][1] > 0 && playerPosition[0][1] < gameBorder[3]) {
            playerPosition[0][1] = playerPosition[0][1] + playerSpeed[0][1];
          } else if (playerSpeed[0][1] < 0 && playerPosition[0][1] > gameBorder[1]) {
            playerPosition[0][1] = playerPosition[0][1] + playerSpeed[0][1];
          }
        }

        // Control collision with enemies and makes the bullets disappear
        for (int i = 0; i < bulletCount + 1; i++) {
          for (int u = 0; u < currentSpawnAmount; u++) {
            int bulletDistance = (int) (Math.sqrt(Math.pow(((bulletProperties[i][2] + 12) - (spawns[u][2] + 25)), 2.0)
                + Math.pow(((bulletProperties[i][3] + 12) - (spawns[u][3] + 25)), 2.0)));
            if (bulletDistance < 50) {
              // Increase Score for hits and or kills
              score += 15;
              spawns[u][5] -= bulletProperties[i][4];
              bulletProperties[i][5]--;
              if (bulletProperties[i][5] <= 0) {
                bulletProperties = RemoveIndex(bulletProperties, i);
                bulletCount--;
              }
              if (spawns[u][5] <= 0) {
                score += 20;
                spawns = RemoveIndex(spawns, u);
                currentSpawnAmount--;
              }
            }
          }
        }

        // if you acquire 500 more points, add +1 life if you don't have max already
        if (score >= nextLifeScoreCount) {
          nextLifeScoreCount += passiveLifeInterval;
          if (lives < maxLives) {
            SFXManager(7);
            lives++;
          }
        }

        // checks if you are shooting or not
        if (bulletProperties[bulletCount + 1][0] != 0 || bulletProperties[bulletCount + 1][1] != 0) {
          isShooting = true;
        } else {
          isShooting = false;
        }

        // upgrades your ammo type once you reach higher scores
        if (score > 3000) {
          currentAmmo = 3;
          currentEnemySpeed = enemySpeeds[2];
        } else if (score > 1250) {
          currentAmmo = 2;
          currentEnemySpeed = enemySpeeds[1];
        } else {
          currentAmmo = 1;
          currentEnemySpeed = enemySpeeds[0];
        }

        // checks if you can fire (fire rate), and creates a bullets with attcheted
        // speeds depending on your previous button presses
        if ((System.currentTimeMillis() - programStartTime)
            - previousTimeBulletFireSpeed >= intervalDelayBulletFireSpeed && isShooting == true) { // Fire Rate
          previousTimeBulletFireSpeed = System.currentTimeMillis() - programStartTime;
          bulletProperties[bulletCount + 1][2] = playerPosition[0][0] + 10; // starting x
          bulletProperties[bulletCount + 1][3] = playerPosition[0][1] + 10; // starting y
          bulletProperties[bulletCount + 1][4] = currentAmmo; // damage Value
          bulletProperties[bulletCount + 1][5] = currentAmmo; // penetration value
          bulletCount = bulletCount + 1;
          SFXManager(4); // play sound effect
        }

        // if the bullets hit the walls of the map (game border), then remove them
        for (int i = 0; i < bulletCount + 1; i++) {
          if (bulletProperties[i][2] > gameBorder[2] + 10 || bulletProperties[i][2] < gameBorder[0] - 10
              || bulletProperties[i][3] > gameBorder[3] + 10 || bulletProperties[i][3] < gameBorder[1] - 10) {
            bulletProperties = RemoveIndex(bulletProperties, i);
            bulletCount--;
          }
        }

        // Spawn enemies if a certain interval has passed and attach properties based on
        // variables controlled by time
        if ((System.currentTimeMillis() - programStartTime) - previousSpawnMillis >= spawnInterval
            && currentSpawnAmount <= MAX_SPAWNS) {

          // randomize spawn event intervals
          previousSpawnMillis = (int) (System.currentTimeMillis() - programStartTime);
          spawnInterval = ((int) (3 * Math.random() + 1)) * 1000;

          // generate a random amount of enemies to spawn given a spawn event
          int waveSize = (int) ((highestSpawnWave + 1) * Math.random() + lowestSpawnWave);

          // pick a random place within a random corner to spawn the enemies from
          for (int i = 0; i < waveSize && currentSpawnAmount <= MAX_SPAWNS; i++) {
            int spawnLocation = (int) (4 * Math.random() + 0);
            // spawn & attach properties to the enemies
            spawns[currentSpawnAmount][0] = currentEnemySpeed; // speedX
            spawns[currentSpawnAmount][1] = currentEnemySpeed; // speedy
            spawns[currentSpawnAmount][2] = spawnZones[0][spawnLocation];
            spawns[currentSpawnAmount][3] = spawnZones[1][spawnLocation];
            spawns[currentSpawnAmount][4] = 1;
            spawns[currentSpawnAmount][5] = 1; // Hp
            spawns[currentSpawnAmount][6] = 0;
            spawns[currentSpawnAmount][7] = 0;
            currentSpawnAmount++;
          }
        }

        // Check for player and enemy collisions
        if ((System.currentTimeMillis() - programStartTime) - previousHitMillis >= hitInvincibilityInterval) {
          playerSpeedMultiplier = 3;
          for (int i = 0; i < currentSpawnAmount && playerSpeedMultiplier != 5; i++) {
            int hitDistance = (int) (Math.sqrt(Math.pow(((playerPosition[0][0] + 25) - (spawns[i][2] + 25)), 2.0)
                + Math.pow(((playerPosition[0][1] + 25) - (spawns[i][3] + 25)), 2.0))); // Check Distance

            // subtract a life, play the hurt sound, and give i frame & speed increase if
            // player is in range of enemy
            if (hitDistance < 20) {
              previousHitMillis = System.currentTimeMillis() - programStartTime;
              SFXManager(5); // hurt sound
              lives--;
              playerSpeedMultiplier = 5;
              // if you deplete all your lives, play death sound, stop music, and go to death
              // screen
              if (lives < 1) {
                SFXManager(6); // death sound
                clearSound = true;
                currentScreen = -1;
                MusicFlusher(); // stops music
              }
            }
          }
        }

        // accrue points passively as time goes on
        if ((System.currentTimeMillis() - programStartTime) - previousPointTime >= passivePointInterval) {
          previousPointTime = System.currentTimeMillis() - programStartTime;
          score += 10;
        }
      }

      // Main Loop Delay
      try {
        Thread.sleep(2);
      } catch (Exception e) {
      }
    }
  } /* ----------------------------- Main Method End ---------------------------- */

  /* --------------------------------- METHODS -------------------------------- */

  /* ------------------------- controls sound effects ------------------------- */
  public static void SFXManager(int sfx) {
    // load the sound file at the specified array number in SFX array
    try {
      sound = AudioSystem.getClip();
      File sfxFile = new File("C:\\Users\\" + userName + "\\Assets\\AudioFiles\\" + SFXName[sfx] + ".wav");
      sound.open(AudioSystem.getAudioInputStream(sfxFile));

    } catch (Exception ex) {
      System.out
          .println("Error loading sound file: " + SFXName[sfx] + ".wav\nIt may be missing or in the wrong directory.");
    }
    sound.flush();
    sound.start();
  }

  // -----------------------------------------------------------------------------------------------------------
  // manages music tracks
  public static void MusicManager(int track) {
    // same as above, load the music file at the array index of the music track
    // array
    try {
      music = AudioSystem.getClip();
      File musicFile = new File("C:\\Users\\" + userName + "\\Assets\\AudioFiles\\" + TrackName[track] + ".wav");
      // open input stream and set the volume controls
      music.open(AudioSystem.getAudioInputStream(musicFile));
      musicGainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
      musicGainControl.setValue(sliderStart);
    } catch (Exception ex) {
      System.out.println("Error loading sound file: " + TrackName[track] + ".wav");
    }
    // play and loop music
    music.start();
    music.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /* ------------------------------- stops music ------------------------------ */
  public static void MusicFlusher() {
    try {
      // Flush out and stop any music being played
      music.flush();
      music.stop();
    } catch (Exception ex) {
      // incase there was no music to begin with
      System.out.println("Error flushing: No tracks playing");
    }
  }

  /* ----------- cleans empties a row index from a 2d array ---------- */
  public static int[][] RemoveIndex(int[][] originalArray, int indexRemove) {
    int[][] copyArray = new int[originalArray.length][originalArray[0].length];

    // removes that index and sends it to brazil (the back of the array)
    int hit = 0;

    // iterate through entire array
    for (int i = 0; i < copyArray.length; i++) {
      if (i != indexRemove) { // check if its the target index
        copyArray[i + hit] = originalArray[i];
      } else {
        hit = -1; // if target index is reached, leave it as 0,0,0 and move remaining indexes
                  // forward
      }
    }
    return copyArray;
  }

  /* ----------------- controls the ai for the skulls/enemies ----------------- */
  public static int[] enemyAISimplified(int enemyX, int enemyY) {
    int[] movement = { 0, 0, 0, 0 }; // top, right, bottom, left
    double distanceX = Math.abs(enemyX) - Math.abs(playerPosition[0][0]); // adjacent
    double distanceY = Math.abs(enemyY) - Math.abs(playerPosition[0][1]); // opposite

    // checks direction player is relative to enemy and move the enemy in that
    // direction
    if (distanceX < 1 && distanceX < -1) {
      movement[0] = 1;
      movement[2] = 2;
    } else if (distanceX > 1 && distanceX > -1) {
      movement[0] = -1;
      movement[2] = 4;
    }
    if (distanceY < 0) {
      movement[1] = 1;
      movement[2] = 3;
    } else if (distanceY > 0) {
      movement[1] = -1;
      movement[2] = 1;
    }

    // when attacks are activated, make them sit next to you and deal damage
    // initiating attack while enemy is above player
    if (distanceY >= -20 && distanceY <= 0 && distanceX < 20 && distanceX > -20 && movement[2] == 2) {
      movement[3] = 1;
      movement[1] = 0;
      // initiating attack while enemy is below player
    } else if (distanceY >= 35 && distanceY <= 40 && distanceX < 20 && distanceX > -20 && movement[2] == 1) {
      movement[3] = 1;
      movement[1] = 0;
      // initiating attack while left of player
    } else if (distanceX >= -50 && distanceX <= 0 && movement[2] == 4) {
      movement[3] = 1;
      movement[0] = 0;
      // initiating attack while right of player
    } else if (distanceX <= 50 && distanceX >= 0 && movement[2] == 3) {
      movement[3] = 1;
      movement[0] = 0;
    } else {
      movement[3] = 0;
    }
    return movement;
  }

  /* ---------------------------- keyboard listener --------------------------- */
  static class keys implements KeyListener {
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
      // when a physical key is pressed down, uses keycode, int output.

      // Movement - Also Change Character Sprite to match direction of movement
      if (e.getKeyCode() == KeyEvent.VK_A) {
        playerSpeed[0][0] = -playerSpeedMultiplier;
        turnState = 1;
        CharacterLoad(turnState);
      } else if (e.getKeyCode() == KeyEvent.VK_D) {
        playerSpeed[0][0] = playerSpeedMultiplier;
        turnState = 2;
        CharacterLoad(turnState);
      } else if (e.getKeyCode() == KeyEvent.VK_W) {
        playerSpeed[0][1] = -playerSpeedMultiplier;
        turnState = 3;
        CharacterLoad(turnState);
      } else if (e.getKeyCode() == KeyEvent.VK_S) {
        playerSpeed[0][1] = playerSpeedMultiplier;
        turnState = 4;
        CharacterLoad(turnState);
      }

      // Shooting
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        bulletProperties[bulletCount + 1][0] = -bulletSpeed;
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        bulletProperties[bulletCount + 1][0] = bulletSpeed;
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        bulletProperties[bulletCount + 1][1] = -bulletSpeed;
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        bulletProperties[bulletCount + 1][1] = bulletSpeed;
      }
    }

    // movement
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_A) {
        turnState = 0;
        playerSpeed[0][0] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_D) {
        turnState = 0;
        playerSpeed[0][0] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_W) {
        turnState = 0;
        playerSpeed[0][1] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_S) {
        turnState = 0;
        playerSpeed[0][1] = 0;
      }
      // shooting
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        bulletProperties[bulletCount + 1][0] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        bulletProperties[bulletCount + 1][0] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        bulletProperties[bulletCount + 1][1] = 0;
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        bulletProperties[bulletCount + 1][1] = 0;
      }

      // if esc is pressed, then open pause menu
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        if (currentScreen == 1) {
          currentScreen = 0;
          pauseStartTime = (int) (System.currentTimeMillis() - programStartTime);
        } else if (currentScreen == 0) {
          currentScreen = 1;
          pauseTimeElapsed += (System.currentTimeMillis() - programStartTime) - pauseStartTime;
          frame.setContentPane(mainGraphicPanel);
        }
      }
      if (e.getKeyCode() == KeyEvent.VK_R && currentScreen == -1) {
        ResetValues();
      }
    }
  }

  /* ------- listens to the slider for volume found in the settings menu ------ */
  static class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent e) throws IllegalArgumentException {

      musicGainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);

      // For changing the volume of the music
      if (e.getSource() == musicVolumeSlider) {
        currentMusicVolume = musicVolumeSlider.getValue();
        // constrain slider values to acceptable float ranges
        if (currentMusicVolume == -44) {
          currentMusicVolume = -80;
        }
        // Reduce volume by slider decibels
        musicGainControl.setValue(currentMusicVolume);
      }

    }
  }

  /* ---------------------------- Button Listeners ---------------------------- */
  static class Listeners implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      // asks if you're sure you want to quit
      if (e.getSource() == pauseExitButton) {
        int exitResponse = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit?", "Message Notification",
            JOptionPane.YES_NO_OPTION);
        if (exitResponse == 0) {
          MusicFlusher();
          frame.dispose();
        }
      }
      // listens to the resume button in the pause menu (continues the game)
      if (e.getSource() == pauseResumeButton) {
        SFXManager(1);
        if (currentMusicVolume > -44) {
          sliderStart = (int) currentMusicVolume;
        } else if (currentMusicVolume < -44) {
          sliderStart = -44;
        }
        currentScreen = 1;
        pauseTimeElapsed += (System.currentTimeMillis() - programStartTime) - pauseStartTime;
        frame.setContentPane(mainGraphicPanel);
      }
      // listens to the restart button in the pause menu (restarts the game)
      if (e.getSource() == pauseRestartButton) {
        SFXManager(1);
        ResetValues();
        if (currentMusicVolume > -44) {
          sliderStart = (int) currentMusicVolume;
          musicGainControl.setValue(sliderStart);
        } else if (currentMusicVolume < -44) {
          sliderStart = -44;
          musicGainControl.setValue(-80);
        }
        frame.setContentPane(mainGraphicPanel);
      }

      if (e.getSource() == mainStartButton) {
        SFXManager(1);
        currentScreen = 3;
        frame.setContentPane(mainGraphicPanel);
      }

      if (e.getSource() == mainSettingsButton) {
        SFXManager(1);
        currentScreen = 4;
        frame.setContentPane(mainGraphicPanel);
      }

      // buttons in the controls menu
      if (e.getSource() == volumeButtonConfirm) {
        SFXManager(1);
        tempMusicVolumeLevel = currentMusicVolume;
        if (currentMusicVolume > -44) {
          sliderStart = (int) currentMusicVolume;
        } else if (currentMusicVolume < -44) {
          sliderStart = -44;
        }
        currentScreen = 2;
        frame.setContentPane(mainGraphicPanel);
      }
      if (e.getSource() == volumeButtonCancel) {
        SFXManager(2);
        try {
          musicGainControl.setValue(sliderStart);
        } catch (Exception ex) {
        }

        currentScreen = 2;
        frame.setContentPane(mainGraphicPanel);
      }

      // buttons in the difficulty menu
      if (e.getSource() == easyButton) {
        MusicFlusher();
        MusicManager(0);
        SFXManager(3);
        currentScreen = 1;
        maxLives = 5;
        lives = maxLives;
        gameLoopStartTime = (System.currentTimeMillis() - programStartTime);
        Arrays.fill(enemySpeeds, 2);
        highestSpawnWave = 3;
        lowestSpawnWave = 1;
        frame.setContentPane(mainGraphicPanel);
      }
      if (e.getSource() == mediumButton) {
        MusicFlusher();
        MusicManager(0);
        SFXManager(3);
        currentScreen = 1;
        maxLives = 3;
        lives = maxLives;
        gameLoopStartTime = (System.currentTimeMillis() - programStartTime);
        Arrays.fill(enemySpeeds, 2);
        enemySpeeds[2] = 3;
        highestSpawnWave = 4;
        lowestSpawnWave = 2;
        frame.setContentPane(mainGraphicPanel);
      }
      if (e.getSource() == hardButton) {
        MusicFlusher();
        MusicManager(0);
        SFXManager(3);
        currentScreen = 1;
        maxLives = 2;
        lives = maxLives;
        gameLoopStartTime = (System.currentTimeMillis() - programStartTime);
        enemySpeeds[0] = 2;
        enemySpeeds[1] = 3;
        enemySpeeds[2] = 4;
        highestSpawnWave = 6;
        lowestSpawnWave = 3;
        frame.setContentPane(mainGraphicPanel);
      }
      if (e.getSource() == mainExitButton) {
        SFXManager(4);
        frame.dispose();
        System.exit(0);
      }
    }
  }

  static class mouse implements MouseListener {

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    // for playing sounds when hovering over a button
    public void mouseEntered(MouseEvent e) {
      if (e.getSource() == pauseExitButton) {
        SFXManager(0);
      }
      if (e.getSource() == pauseRestartButton) {
        SFXManager(0);
      }
      if (e.getSource() == pauseResumeButton) {
        SFXManager(0);
      }
      if (e.getSource() == mainStartButton) {
        SFXManager(0);
      }
      if (e.getSource() == mainSettingsButton) {
        SFXManager(0);
      }
      if (e.getSource() == mainExitButton) {
        SFXManager(0);
      }
      if (e.getSource() == volumeButtonConfirm) {
        SFXManager(0);
      }
      if (e.getSource() == volumeButtonCancel) {
        SFXManager(0);
      }
      if (e.getSource() == easyButton) {
        SFXManager(0);
      }
      if (e.getSource() == mediumButton) {
        SFXManager(0);
      }
      if (e.getSource() == hardButton) {
        SFXManager(0);
      }
    }

    public void mouseExited(MouseEvent e) {
    }
  }

  // for when you restart the game, it resets all the values like score, lives,
  // position, enemies, etc.
  // also clears any current music and restarts the battle theme from the
  // beginning
  public static void ResetValues() {
    lives = maxLives;
    score = 0;
    gameLoopStartTime = System.currentTimeMillis() - programStartTime;
    currentScreen = 1;
    pauseTimeElapsed = 0;
    playerPosition[0] = startPosition;
    MusicFlusher();
    MusicManager(0); // plays battle theme

    // fills all the skull values with 0 (i.e. gets rid of all of them)
    for (int i = 0; i < currentSpawnAmount; i++) {
      Arrays.fill(spawns[i], 0);
    }
    // fills all the bullet values with 0 (deletes and resets all bullets)
    for (int i = 0; i < bulletCount + 1; i++) {
      Arrays.fill(bulletProperties[i], 0);
    }
    // sets bullets and spawned skulls back to default
    bulletCount = -1;
    currentSpawnAmount = 0;
  }

  // --------- controls animation for player sprite (warning: scuffed) ---------

  public static void CharacterLoad(int turnState) {

    // checks direction facing
    if (turnState == 1) {
      try { // loads image for that state
        manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerLeft1.png"));
      } catch (Exception x) {
      }
      spriteCounter++;
      if (spriteCounter < 7) { // after a certain counter has elapsed, set the next pic and reset the counter
        try {
          manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerLeft"
              + Integer.valueOf(nextPic[spriteCounter]) + ".png"));
        } catch (Exception x) {
        }
      } else if (spriteCounter > 3) {
        spriteCounter = 0;
      }
    } else if (turnState == 2) { // repeat above process for different turn/movement directions
      try {
        manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerRight1.png"));
      } catch (Exception x) {
      }
      spriteCounter++;

      if (spriteCounter < 7) {
        try {
          manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerRight"
              + Integer.valueOf(nextPic[spriteCounter]) + ".png"));
        } catch (Exception x) {
        }
      } else if (spriteCounter > 3) {
        spriteCounter = 0;
      }
    } else if (turnState == 3) {
      try {
        manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerBack1.png"));
      } catch (Exception x) {
      }
      spriteCounter++;

      if (spriteCounter < 7) {
        try {
          manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerBack"
              + Integer.valueOf(nextPic[spriteCounter]) + ".png"));
        } catch (Exception x) {
        }
      } else if (spriteCounter > 3) {
        spriteCounter = 0;
      }
    } else if (turnState == 4) {
      try {
        manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerFront1.png"));
      } catch (Exception x) {
      }
      spriteCounter++;

      if (spriteCounter < 7) {
        try {
          manner = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Character\\MannerFront"
              + Integer.valueOf(nextPic[spriteCounter]) + ".png"));
        } catch (Exception x) {
        }
      } else if (spriteCounter > 3) {
        spriteCounter = 0;
      }
    }
  }

  /* ----------------------------- GRAPHICS CLASS ----------------------------- */
  static public class GraphicsPanel extends JPanel{
    public void paint(Graphics g){
      super.paintComponent(g);

      // Draw Main Game if in Main Game Loop (currentScreen)
      if (currentScreen == 1) {
        frame.setContentPane(mainGraphicPanel);
        // displayBackground and Information Elements
        backgroundGenerate(g);
        Ui(g);
        // if the player is sitting still, draw the idle state image
        if (isMoving == false) {
          try {
            manner = ImageIO.read(new File(mannerIdle));
          } catch (Exception x) {
          }
        }
        // move bullets
        if ((System.currentTimeMillis() - programStartTime) - previousTimeBulletSpeed >= intervalDelayBullet) {
          previousTimeBulletSpeed = System.currentTimeMillis() - programStartTime;
          for (int i = 0; i < (bulletCount + 1); i++) {
            // load bullet image
            try {
              bulletImage = ImageIO.read(
                  new File("C:\\Users\\" + userName + "\\Assets\\Projectiles\\bulletType" + currentAmmo + ".png"));
            } catch (Exception x) {
            }
            // draw the images increment the position values of the bullet and draw the
            // bullet at that position
            bulletProperties[i][2] = bulletProperties[i][2] + bulletProperties[i][0];
            bulletProperties[i][3] = bulletProperties[i][3] + bulletProperties[i][1];
            g.drawImage(bulletImage, bulletProperties[i][2], bulletProperties[i][3], this);
          }
        }
        // spawn les skulls facing/moving in their directions once it is time for them
        // to spawn
        if ((System.currentTimeMillis() - programStartTime) - previousSpawnMovementMillis >= spawnMovementInterval) {
          previousSpawnMovementMillis = (int) (System.currentTimeMillis() - programStartTime);
          for (int i = 0; i < currentSpawnAmount; i++) {
            int[] movementDirection = enemyAISimplified(spawns[i][2], spawns[i][3]);
            spawns[i][2] += spawns[i][0] * movementDirection[0];
            spawns[i][3] += spawns[i][1] * movementDirection[1];
            spawns[i][7] = movementDirection[3];
            spawns[i][8] = movementDirection[2];

            String[] outputString = SkullLoad(spawns[i][8], spawns[i][7], spawns[i][6]);
            try {
              skull = ImageIO.read(new File(outputString[0]));
            } catch (Exception x) {
            }

            spawns[i][6] = Integer.parseInt(outputString[1]);

            // draw the skull at it's index and location which get's updated by the ai
            // method. This makes the skulls move

          }
        }
        // draw the skulls for the amount that's supposed to be on screen
        // (currentSpawnAmount)
        for (int i = 0; i < currentSpawnAmount; i++) {
          g.drawImage(skull, spawns[i][2] + 8, spawns[i][3] + 8, this);
        }
        // draw the main character at the player position ([0][0] = x, [0][1] = y)
        g.drawImage(manner, playerPosition[0][0], playerPosition[0][1], this);

        // if the screen is 0, then call the pause menu method which draws the menu
      } else if (currentScreen == 0) {
        PauseMenu();
      } else if (currentScreen == 2) {
        MainMenuScreen();
      } else if (currentScreen == 3) {
        DifficultyMenu();
      } else if (currentScreen == 4) {
        SettingsMenu();
      } else if (currentScreen == -1) {
        death(g);
      }
    }

    /* ------------------------------ Frame manager ----------------------------- */
    public void FrameManger(int expectedFrame) {
      if (expectedFrame == 2) {
        try {
          mainPane.remove(settingsTitle);
          mainPane.remove(volumeTitle);
          mainPane.remove(minVolume);
          mainPane.remove(maxVolume);
          mainPane.remove(controls);
          mainPane.remove(shooting);
          mainPane.remove(movement);
          mainPane.remove(musicVolumeSlider);
          mainPane.remove(volumeButtonConfirm);
          mainPane.remove(volumeButtonCancel);
        } catch (Exception x) {
        }
      }

      if (expectedFrame == 4) {

        mainPane.remove(mainMenuTitle);
        mainPane.remove(mainSettingsButton);
        mainPane.remove(mainStartButton);
        mainPane.remove(mainExitButton);
      }
    }

    /* ---------------------------- pause menu method --------------------------- */
    public void PauseMenu() {
      // define the buttons, labels, and panels
      pauseButtonPanel = new JPanel(new FlowLayout());
      pauseExitButton = new JButton("EXIT GAME");
      pauseRestartButton = new JButton("RESTART");
      pauseResumeButton = new JButton("RESUME");
      masterPausePanel = new JPanel();
      JLabel pauseTitle = new JLabel("PAUSED!");

      // make a title text object that says the game is paused
      pauseTitle.setFont(new Font("Monospaced", Font.BOLD, 72));
      pauseTitle.setBounds(500, 200, 600, 70);
      masterPausePanel.add(pauseTitle);

      // set sizing, formatting, and text for all buttons vvv
      pauseExitButton.setBounds(815, 700, 170, 70);
      pauseExitButton.setFocusable(false);
      pauseExitButton.setBorder(basicBorder);
      pauseExitButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      pauseExitButton.setBackground(new Color(162, 185, 229));
      pauseExitButton.setForeground(new Color(0, 0, 0));
      pauseExitButton.addActionListener(new Listeners());
      pauseExitButton.addMouseListener(new mouse());
      masterPausePanel.add(pauseExitButton);

      // volume
      volumeTitle = new JLabel("Music Volume:");
      minVolume = new JLabel("0");
      maxVolume = new JLabel("100");

      musicVolumeSlider = new JSlider(-44, 6, sliderStart);
      musicVolumeSlider.setMajorTickSpacing(5);
      musicVolumeSlider.setMinorTickSpacing(1);
      musicVolumeSlider.setPaintTicks(true);
      musicVolumeSlider.setBounds(420, 450, 450, 100);
      musicVolumeSlider.addChangeListener(new SliderListener());
      musicVolumeSlider.setOpaque(false);
      musicVolumeSlider.setFocusable(false);
      musicVolumeSlider.setForeground(new Color(162, 185, 229));
      masterPausePanel.add(musicVolumeSlider);

      volumeTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
      volumeTitle.setForeground(new Color(0, 0, 0));
      volumeTitle.setBounds(525, 410, 600, 70);
      masterPausePanel.add(volumeTitle);

      // displays a '0' and '100' at either end of the slider
      minVolume.setFont(new Font("Monospaced", Font.PLAIN, 18));
      minVolume.setForeground(new Color(0, 0, 0));
      minVolume.setBounds(420, 490, 600, 70);
      masterPausePanel.add(minVolume);

      maxVolume.setFont(new Font("Monospaced", Font.PLAIN, 18));
      maxVolume.setBounds(845, 490, 600, 70);
      masterPausePanel.add(maxVolume);

      pauseRestartButton.setBounds(565, 700, 170, 70);
      pauseRestartButton.setFocusable(false);
      pauseRestartButton.setBorder(basicBorder);
      pauseRestartButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      pauseRestartButton.setBackground(new Color(162, 185, 229));
      pauseRestartButton.setForeground(new Color(0, 0, 0));
      pauseRestartButton.addActionListener(new Listeners());
      pauseRestartButton.addMouseListener(new mouse());
      masterPausePanel.add(pauseRestartButton);

      pauseResumeButton.setBounds(315, 700, 170, 70);
      pauseResumeButton.setFocusable(false);
      pauseResumeButton.setBorder(basicBorder);
      pauseResumeButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      pauseResumeButton.setBackground(new Color(162, 185, 229));
      pauseResumeButton.setForeground(new Color(0, 0, 0));
      pauseResumeButton.addActionListener(new Listeners());
      pauseResumeButton.addMouseListener(new mouse());
      masterPausePanel.add(pauseResumeButton);
      // finished formatting buttons ^^^

      // set the background colour for the pause menu
      masterPausePanel.setBackground(new Color(253, 213, 145));
      masterPausePanel.setLayout(null);
      masterPausePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      masterPausePanel.add(pauseButtonPanel);
      masterPausePanel.setVisible(true);

      // add the pause panel to the pane and repaint the screen
      mainPane.add(masterPausePanel, Integer.valueOf(1));
      frame.setContentPane(masterPausePanel);
      frame.repaint();
    }

    /* ---------------------------- main menu method ---------------------------- */
    public void MainMenuScreen() {
      FrameManger(2);
      // make the JLabels for containing the images and text
      menuBackgroundImageLabel = new JLabel();
      ImageIcon menuImage = new ImageIcon("C:\\Users\\" + userName + "\\Assets\\Backgrounds\\grass.png");

      mainMenuTitle.setBounds(435, 30, 430, 100);
      mainMenuTitle.setFont(new Font("Monospaced", Font.BOLD, 72));
      mainMenuTitle.setForeground(new Color(0, 0, 0));

      mainStartButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      mainStartButton.setBounds(500, 250, 300, 100);
      mainStartButton.setFocusable(false);
      mainStartButton.setForeground(new Color(0, 0, 0));
      mainStartButton.setBackground(new Color(162, 185, 229, 100));
      mainStartButton.setBorder(basicBorder);
      mainStartButton.addActionListener(new Listeners());
      mainStartButton.addMouseListener(new mouse());

      mainSettingsButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      mainSettingsButton.setBounds(500, 450, 300, 100);
      mainSettingsButton.setFocusable(false);
      mainSettingsButton.setForeground(new Color(0, 0, 0));
      mainSettingsButton.setBackground(new Color(162, 185, 229, 100));
      mainSettingsButton.setBorder(basicBorder);
      mainSettingsButton.addActionListener(new Listeners());
      mainSettingsButton.addMouseListener(new mouse());

      mainExitButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      mainExitButton.setBounds(500, 650, 300, 100);
      mainExitButton.setFocusable(false);
      mainExitButton.setForeground(new Color(0, 0, 0));
      mainExitButton.setBackground(new Color(162, 185, 229, 100));
      mainExitButton.setBorder(basicBorder);
      mainExitButton.addActionListener(new Listeners());
      mainExitButton.addMouseListener(new mouse());
      // set the background image
      menuBackgroundImageLabel.setIcon(menuImage);
      menuBackgroundImageLabel.setVisible(true);
      menuBackgroundImageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      // add the images to the label & pane
      mainPane.add(mainExitButton, Integer.valueOf(3));
      mainPane.add(mainSettingsButton, Integer.valueOf(3));
      mainPane.add(mainMenuTitle, Integer.valueOf(3));
      mainPane.add(menuBackgroundImageLabel, Integer.valueOf(2));
      mainPane.add(mainStartButton, Integer.valueOf(3));
      frame.setContentPane(mainPane);
      frame.repaint();
    }

    /* -------------------- Difficulty Selection Menu Method -------------------- */
    public void DifficultyMenu() {
      FrameManger(4);
      // buttons and panel creation
      easyButton = new JButton("Easy");
      mediumButton = new JButton("Medium");
      hardButton = new JButton("Hard");

      // labels
      JLabel difficultyTitle = new JLabel("SELECT DIFFICULTY:");
      difficultyTitle.setBounds(444, 100, 414, 100);
      difficultyTitle.setFont(new Font("Monospaced", Font.BOLD, 38));

      // bootans
      easyButton.setBounds(450, 250, 400, 150);
      easyButton.setFocusable(false);
      easyButton.setBorder(basicBorder);
      easyButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      easyButton.setBackground(new Color(162, 185, 229, 100));
      easyButton.setForeground(new Color(0, 0, 0));
      easyButton.addActionListener(new Listeners());
      easyButton.addMouseListener(new mouse());

      mediumButton.setBounds(450, 450, 400, 150);
      mediumButton.setFocusable(false);
      mediumButton.setBorder(basicBorder);
      mediumButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      mediumButton.setBackground(new Color(162, 185, 229, 100));
      mediumButton.setForeground(new Color(0, 0, 0));
      mediumButton.addActionListener(new Listeners());
      mediumButton.addMouseListener(new mouse());

      hardButton.setBounds(450, 650, 400, 150);
      hardButton.setFocusable(false);
      hardButton.setBorder(basicBorder);
      hardButton.setFont(new Font("Monospaced", Font.BOLD, 23));
      hardButton.setBackground(new Color(162, 185, 229, 100));
      hardButton.setForeground(new Color(0, 0, 0));
      hardButton.addActionListener(new Listeners());
      hardButton.addMouseListener(new mouse());

      // add the settings panel to the pane and repaint the screen
      mainPane.add(easyButton, Integer.valueOf(3));
      mainPane.add(mediumButton, Integer.valueOf(3));
      mainPane.add(hardButton, Integer.valueOf(3));
      mainPane.add(difficultyTitle, Integer.valueOf(3));
      frame.setContentPane(mainPane);
      frame.repaint();
    }

    /* -------------------------- Settings Menu Method -------------------------- */
    public void SettingsMenu() {
      FrameManger(4);

      ImageIcon menuImage = new ImageIcon("C:\\Users\\" + userName + "\\Assets\\Backgrounds\\grass.png");
      // define buttons, labels, panels, and slider
      musicVolumeSlider = new JSlider(-44, 6, sliderStart);
      volumeButtonConfirm = new JButton("Accept");
      volumeButtonCancel = new JButton("Cancel");
      settingsTitle = new JLabel("SETTINGS");
      volumeTitle = new JLabel("Music Volume:");
      minVolume = new JLabel("0");
      maxVolume = new JLabel("100");
      controls = new JLabel("Gameplay Controls:");
      movement = new JLabel("<html><body>MOVEMENT:<br>Up   " + "\u2800"
          + "  :         W<br>Down  :    S<br>Left  :       A<br>Right:     D</body></html>");
      shooting = new JLabel("<html><body>SHOOTING:<br>Up  " + "\u2800"
          + "  :         Up Arrow<br>Down  :    Down Arrow<br>Left  :       Left Arrow<br>Right:     Right Arrow</body></html>");

      // make the titles
      settingsTitle.setFont(new Font("Monospaced", Font.BOLD, 72));
      settingsTitle.setForeground(new Color(0, 0, 0));
      settingsTitle.setBounds(500, 100, 600, 70);

      volumeTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
      volumeTitle.setForeground(new Color(0, 0, 0));
      volumeTitle.setBounds(555, 210, 600, 70);

      // displays a '0' and '100' at either end of the slider
      minVolume.setFont(new Font("Monospaced", Font.PLAIN, 18));
      minVolume.setForeground(new Color(0, 0, 0));
      minVolume.setBounds(450, 290, 600, 70);

      maxVolume.setFont(new Font("Monospaced", Font.PLAIN, 18));
      maxVolume.setBounds(875, 290, 600, 70);

      // display text for controls
      controls.setFont(new Font("Monospaced", Font.PLAIN, 32));
      controls.setForeground(new Color(0, 0, 0));
      controls.setBounds(500, -50, 400, 900);

      shooting.setFont(new Font("Monospaced", Font.PLAIN, 24));
      shooting.setForeground(new Color(0, 0, 0));
      shooting.setBounds(650, 100, 300, 900);

      movement.setFont(new Font("Monospaced", Font.PLAIN, 24));
      movement.setForeground(new Color(0, 0, 0));
      movement.setBounds(450, 100, 300, 900);

      // set sizing, formatting, and text for all buttons (and slider) vvv
      musicVolumeSlider.setMajorTickSpacing(5);
      musicVolumeSlider.setMinorTickSpacing(1);
      musicVolumeSlider.setPaintTicks(true);
      musicVolumeSlider.setBounds(450, 250, 450, 100);
      musicVolumeSlider.addChangeListener(new SliderListener());
      musicVolumeSlider.setOpaque(false);
      musicVolumeSlider.setFocusable(false);
      musicVolumeSlider.setForeground(new Color(162, 185, 229));

      volumeButtonConfirm.setBounds(715, 800, 170, 70);
      volumeButtonConfirm.setFocusable(false);
      volumeButtonConfirm.setBorder(basicBorder);
      volumeButtonConfirm.setFont(new Font("Monospaced", Font.BOLD, 23));
      volumeButtonConfirm.setBackground(new Color(162, 185, 229, 100));
      volumeButtonConfirm.setForeground(new Color(0, 0, 0));
      volumeButtonConfirm.addActionListener(new Listeners());
      volumeButtonConfirm.addMouseListener(new mouse());

      volumeButtonCancel.setBounds(465, 800, 170, 70);
      volumeButtonCancel.setFocusable(false);
      volumeButtonCancel.setBorder(basicBorder);
      volumeButtonCancel.setFont(new Font("Monospaced", Font.BOLD, 23));
      volumeButtonCancel.setBackground(new Color(162, 185, 229, 100));
      volumeButtonCancel.setForeground(new Color(0, 0, 0));
      volumeButtonCancel.addActionListener(new Listeners());
      volumeButtonCancel.addMouseListener(new mouse());
      // finished formatting buttons ^^^

      // set the background colour for the pause menu
      masterSettingsPanel.setLayout(null);
      masterSettingsPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      masterSettingsPanel.setVisible(true);

      menuBackgroundImageLabel.setIcon(menuImage);
      // add the settings panel to the pane and repaint the screen
      mainPane.add(settingsTitle, Integer.valueOf(3));
      mainPane.add(volumeTitle, Integer.valueOf(3));
      mainPane.add(minVolume, Integer.valueOf(3));
      mainPane.add(maxVolume, Integer.valueOf(3));
      mainPane.add(controls, Integer.valueOf(3));
      mainPane.add(shooting, Integer.valueOf(3));
      mainPane.add(movement, Integer.valueOf(3));
      mainPane.add(musicVolumeSlider, Integer.valueOf(3));
      mainPane.add(volumeButtonConfirm, Integer.valueOf(3));
      mainPane.add(volumeButtonCancel, Integer.valueOf(3));

      frame.setContentPane(mainPane);
      frame.repaint();
    }

    /* ------------------- Display the in-game map (dungeon) ------------------- */
    public void backgroundGenerate(Graphics g) {
      super.paintComponent(g);
      // load the background image
      try {
        backgroundImage = ImageIO.read(new File("C:\\Users\\" + userName + "\\Assets\\Backgrounds\\dungeonMap.png"));
      } catch (IOException ex) {
      }
      // Draw the Background image and the UI Side Grey Bar
      g.drawImage(backgroundImage, -112, -87, 1224, 1224, this);
      g.fillRect(frame.getWidth() - 300, 00, frame.getWidth(), frame.getHeight());
    }

    /* --------------------------- death screen method -------------------------- */
    public void death(Graphics g) {
      // set a white background and then draw the text
      mainGraphicPanel.setBackground(Color.WHITE);
      g.setFont(new Font("Monospaced", Font.BOLD, 72));
      g.drawString("YOU DIED!", 500, 490);
      g.setFont(new Font("Monospaced", Font.BOLD, 10));
      g.drawString("lmao", 500, 500);
      g.setFont(new Font("Monospaced", Font.BOLD, 25));
      g.drawString("Press 'r' to restart", 530, 545);
      mainGraphicPanel.repaint();
    }

    /* ------- method for the interface on the side of the screen in-game ------- */
    public void Ui(Graphics g) {

      // Set Font & Color
      g.setColor(new Color(255, 255, 255));
      g.setFont(new Font("Monospaced", Font.PLAIN, 25));
      // display the time elapsed with zeros before it
      g.drawString(("Time: " + DigitFiller(
          ((int) Math.floor((System.currentTimeMillis() - programStartTime - gameLoopStartTime - pauseTimeElapsed))
              / 1000),
          9)), 1020, 50);
      // display the current score with zeros before it as well as the ammo
      g.drawString("Score: " + DigitFiller(score, 8), 1020, 110);
      g.drawString("Ammo: ", 1020, 170);
      // load the bullet icon to represent ammo
      try {
        bulletImage = ImageIO
            .read(new File("C:\\Users\\" + userName + "\\Assets\\Projectiles\\bulletType" + currentAmmo + ".png"));
      } catch (Exception x) {
      }
      // display current Bullet
      g.drawImage(bulletImage, 1110, 150, this);
      // draw the amount of lives left
      g.drawString("Lives Left: " + lives, 1020, 230);
      for (int i = 0; i < lives; i++) {
        g.drawImage(healthImage, 1007 + (i * 55), 265, this);
      }
    }
  }

  /* ------------------------ Random One - Three Method ----------------------- */
  // picks a random number from the 3 inputs and returns a random number
  // proportional to the inputs
  // Not used**
  public static int RandomOneToThree(int numOne, int numTwo, int numThree) {
    int totalElements = numOne + numTwo + numThree;
    int[] newArray = new int[totalElements];

    for (int i = 0; i < numOne; i++) {
      newArray[i] = 1;
    }
    for (int i = numOne; i < (numOne + numTwo); i++) {
      newArray[i] = 2;
    }
    for (int i = numOne + numTwo; i < (totalElements); i++) {
      newArray[i] = 3;
    }
    int randNum = (int) (totalElements * Math.random() + 0);
    return newArray[randNum];
  }

  /* --------------------------- Digit Filler Method -------------------------- */
  // takes the values such as the score or time elapsed and adds zeros before the
  // value to make it look nice (i.e. "score: 50" --> "score: 00000050")
  public static String DigitFiller(int originalNumber, int totalDigits) {
    char[] numberString = new char[totalDigits];
    String stringedNumber = "" + originalNumber;
    String newString = "";
    Arrays.fill(numberString, '0');

    // Inverse Array
    for (int i = 0; i < stringedNumber.length(); i++) {
      numberString[i] = stringedNumber.charAt(stringedNumber.length() - 1 - i);
    }
    // Compile array into a string and fill remaining spots with 0
    for (int i = totalDigits - 1; i > 0; i--) {
      newString += numberString[i - 1];
    }
    return newString;
  }

  /* --------------------------- Skeleton Animation --------------------------- */

  // controls the animation and loading of the skull sprites (obsolete)
  public static String[] SkullLoad(int direction, int isAttacking, int spriteCounterSkull) {
    String enemyImage = "";
    String[] outputString = new String[2];
    // The name's Bone Malone: He's a creepy ol' floatin' skull that'll give ya a
    // right real chomp on the rear if ya
    // let him too close t'ya! Don't let his chompers to take a piece out o' your
    // hind if you value the un-damaged
    // condition o' yer dumpy caboose.

    if (direction == 1 && isAttacking != 1) { // going upwards
      if (spriteCounterSkull > 5) {
        spriteCounterSkull = 0;
      }
      enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullBack"
          + upDownAnimation[spriteCounterSkull] + ".png";
    }

    else if (direction == 3 && isAttacking != 1) { // going downwards
      if (spriteCounterSkull > 5) {
        spriteCounterSkull = 0;
      }
      enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullFront"
          + upDownAnimation[spriteCounterSkull] + ".png";
    }

    else if (direction == 4 && isAttacking != 1) { // going left
      if (spriteCounterSkull > 11) {
        spriteCounterSkull = 0;
      }
      enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullLeft"
          + LRAnimation[spriteCounterSkull]
          + ".png";
    }

    else if (direction == 2 && isAttacking != 1) { // going right
      if (spriteCounterSkull > 11) {
        spriteCounterSkull = 0;
      }
      enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullRight"
          + LRAnimation[spriteCounterSkull]
          + ".png";
    }

    else if (isAttacking == 1) {
      if (direction == 3) {
        if (spriteCounterSkull > 15) {
          spriteCounterSkull = 0;
        }
        enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullFrontAttack"
            + attackAnimation[spriteCounterSkull] + ".png";
      } else if (direction == 4) {
        if (spriteCounterSkull > 15) {
          spriteCounterSkull = 0;
        }
        enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullLeftAttack"
            + attackAnimation[spriteCounterSkull] + ".png";
      } else if (direction == 2) {
        if (spriteCounterSkull > 15) {
          spriteCounterSkull = 0;
        }
        enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullRightAttack"
            + attackAnimation[spriteCounterSkull] + ".png";
      } else if (direction == 1) {
        if (spriteCounterSkull > 5) {
          spriteCounterSkull = 0;
        }
        enemyImage = "C:\\Users\\" + userName + "\\Assets\\Character\\skull\\skullBack"
            + upDownAnimation[spriteCounterSkull] + ".png";
      }
    }
    spriteCounterSkull++;
    outputString[0] = enemyImage;
    outputString[1] = "" + spriteCounterSkull;
    return outputString;
    // Here ends the animation shenanigans of Bone Malone, but woe, this be the very
    // beginning; you've yet to
    // face him and his skele crew out on the battlefield!
  } 
}
// program end