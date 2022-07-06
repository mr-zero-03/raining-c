import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.control.Button;
//import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;


public class RainingC extends Application {
  public static int viewportSizeX = 700;
  public static int viewportSizeY = 500;

  private Group root;
  private Scene scene;
  private Canvas canvas;
  private GraphicsContext graphics;
  
  private int userX = 350;
  private int userY = 250;
  private int userLives = 2;
  private String userImagePath = "player-right.png";
  public static boolean playerUp = false;
  public static boolean playerDown = false;
  public static boolean playerRight = false;
  public static boolean playerLeft = false;
  private int playerSteps = 5;
  private int playerStepsFast = 10;
  private Player player;

  private int round = 0;
  private Map map;

  public static int gameStatus = 2; //2 on game, 1 win, 0 lose

  public static void main( String[] args ) {
    launch( args );
  }

  @Override
  public void start( Stage primaryStage ) throws Exception {
    inicializeComponents();
    eventManager();

    primaryStage.setTitle( "Raining C" );
    primaryStage.setScene( scene );
    primaryStage.show();

    gameCycle();
  }

  /* --- Inicialization --- */
  public void inicializeComponents() {
    root = new Group();
    scene = new Scene( root, viewportSizeX, viewportSizeY );
    canvas = new Canvas( viewportSizeX, viewportSizeY );
    player = new Player( userX, userY, userLives, userImagePath );
    map = new Map( round );
    //Button button = new Button( "Iniciar juego" );
    //Text gameTitle = new Text( 0, 20, "Bienvenido a Raining C!" );

    root.getChildren().add( canvas );
    graphics = canvas.getGraphicsContext2D();
  }

  /* --- eventManager --- */
  public void eventManager() {
    //User movement
    scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
      @Override
      public void handle( KeyEvent evento ) {
        String keyPressed = evento.getCode().toString();
        switch( keyPressed ) {
            case "W":
            case "UP":
              playerUp = true;
            break;
            case "S":
            case "DOWN":
              playerDown = true;
            break;
            case "A":
            case "LEFT":
              playerLeft = true;
            break;
            case "D":
            case "RIGHT":
              playerRight = true;
            break;
            case "SPACE":
              player.setSteps( playerStepsFast );
            break;
        }
      }
    } );

    scene.setOnKeyReleased( new EventHandler<KeyEvent>() {
      @Override
      public void handle( KeyEvent evento ) {
        String keyPressed = evento.getCode().toString();
        switch( keyPressed ) {
            case "W":
            case "UP":
              playerUp = false;
            break;
            case "S":
            case "DOWN":
              playerDown = false;
            break;
            case "A":
            case "LEFT":
              playerLeft = false;
            break;
            case "D":
            case "RIGHT":
              playerRight = false;
            break;
            case "SPACE":
              player.setSteps( playerSteps );
            break;
        }
      }
    } );
  }

  /* --- gameCyle --- */
  public void gameCycle() {
    long initialTime = System.nanoTime();
    AnimationTimer animationTimer = new AnimationTimer(){
      @Override
      public void handle( long currentTime ) { //60 fps
        double t = ( currentTime - initialTime ) / 1000000000.0;
        updateState();
        paint();
      }
    };

    animationTimer.start();
  }

  public void updateState() {
    player.move();
    if ( gameStatus == 1 || gameStatus == 0 ) {
      Map.y = -10;
    } else if ( gameStatus == 2 ) {
      map.moveWords(); 
    }
  }

  public void paint() {
    //graphics.fillRect( 0, 0, 100, 100 );
    graphics.drawImage( new Image( "background.png" ), 0, 0 );
    if ( gameStatus != 0 ) {
      player.draw( graphics );
    }

    if ( gameStatus == 1 || gameStatus == 0 ) {
      graphics.setFont( new Font( 80 ) );
      if ( gameStatus == 1 ) {
        graphics.strokeText( "You win!", 350, 250);
      } else {
        graphics.strokeText( "You lose!", 350, 250);
      }
    } else if ( gameStatus == 2 ) {
      map.launchWords( graphics ); 
    }
  }

  public static void endGame() {
System.out.println("Game ended");
  }
}
