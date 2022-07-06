import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Map {

  private static int round;
  private static String[][] words = { //The word at the pos [x][0] is the correct word
    { "void main(){}", "void mein[]{}", "void menu(){}" },
    { "char[] str='ABC';", "char str[]='ABC';", "str='ABC';" },
    { "int x = 0", "Integer x = 0;", "int x = 0;" },
    { "return( 0 );", "ret 0;", "return{ 0 };" },
  };

  public static int y = 50;
  public static int x1 = 10;
  public static int x2 = 260;
  public static int x3 = 510;

  public Map( int round ) {
      Map.round = round;
  }

  public static void nextRound() {
    int roundsAmount = words.length;
    if ( round >= roundsAmount - 1 ) {
      RainingC.gameStatus = 1;
      RainingC.endGame();
    } else {
      round++;
      y = 0;
    }
  }

  public static void compareWords( int wordNum ) {
    int correctWordPos = round;

    if ( correctWordPos == 4 ) {
      correctWordPos = 0;
    }

    if ( wordNum != correctWordPos ) {
      Player.loseLive();
    }
    if ( RainingC.gameStatus != 0 ) {
      nextRound();
    }
  }

  public void launchWords( GraphicsContext graphics ) {
    int wordsSize = Map.words[ round ].length;
    for ( int i = 0; i < wordsSize; i++ ) {
      double wordX = 0;
      switch ( i ) {
        case 0:
        wordX = x1;
        break;
       case 1:
         wordX = x2;
        break;
       case 2:
          wordX = x3;
        break;
      }

      graphics.setFont( new Font( 30 ) );
      graphics.setFill( Color.RED );
      graphics.strokeText( words[ round ][ i ], wordX, y );
    }
  }

  public void moveWords() {
    y += 2;
  }
}