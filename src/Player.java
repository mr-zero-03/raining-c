import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Player {

  private int x;
  private int y;
  private static int lives;
  private String imagePath;

  private int steps = 5;

  public Player( int x, int y, int lives, String imagePath ) {
    this.x = x;
    this.y = y;
    Player.lives = lives;
    this.imagePath = imagePath;
  }

  public void setSteps( int newSteps ) {
    steps = newSteps;
  }

  public static void loseLive() {
    lives--;

    if ( lives < 0 ) {
      RainingC.gameStatus = 0;
      RainingC.endGame();
    }
  }

  public void draw( GraphicsContext graphics ) {
    graphics.setFont( new Font( 15 ) );
    //graphics.setFill( Color.RED );
    graphics.drawImage( new Image( imagePath ), x, y );
    graphics.strokeText( "Lives: " + lives, 10, 20 );
  }

  public void move() {
    //Limits on the screen
    if ( x > RainingC.viewportSizeX ) {
        x = 0;
    } else if ( x < 0 ) {
        x = RainingC.viewportSizeX;
    }
    if ( y >= RainingC.viewportSizeY - 60 ) {
      y = RainingC.viewportSizeY - 60;
    } else if ( y <= 0 ) {
      y = 0;
    }

    //Movement
    if ( RainingC.playerUp == true ) {
        y -= steps;
    }
    if ( RainingC.playerDown == true ) {
        y += steps;
    }
    if ( RainingC.playerRight == true ) {
        x += steps;
        imagePath = "player-right.png";
    }
    if ( RainingC.playerLeft == true ) {
        x -= steps;
        imagePath = "player-left.png";
    }

    //Collision
    if ( y >= ( Map.y - 50 ) && y <= ( Map.y - 10 ) ) {
      if ( x >= Map.x1 - 30 && x < Map.x1 + 100 ) {
        Map.compareWords( 0 );
      } else if ( x >= Map.x2 - 30 && x < Map.x2 + 100 ) {
        Map.compareWords( 1 );
      } else if ( x >= Map.x3 - 30 && x < Map.x3 + 100 ) {
        Map.compareWords( 2 );
      }
    }
  }
}