import java.util.Scanner;
import java.util.Arrays;

public class Player {

  int lives = 3;
  String nickname = "";
  int[] skills = new int[ 3 ]; // 0: No skill / 1: Sword / 2: Immortality / 3: Big jump

  public Player( String nickname ) {
    this.nickname = nickname;
  }

  public void endGame() {
    System.out.println( "Game Ended" );
  }

  //Lives
  public void loseLive() {
    this.lives -= 1;

    if ( this.lives == -1 ) {
      endGame();
    }
  }

  public void addLive() {
    this.lives += 1;
  }

  //Skills
  private boolean skillExist( int skill ) {
    boolean skillExist = Arrays.stream( this.skills ).anyMatch( x -> x == skill );

    return( skillExist );
  }

  public void addSkill( int skill ) {
    if ( !skillExist( skill ) ) {
      int skillsSize = this.skills.length;
      this.skills[ skillsSize ] = skill;
    }
  }

  public void removeSkill( int skill ) {
    if ( skillExist( skill ) ) {
    int skillsSize = this.skills.length;
      for ( int i=0; i < skillsSize; i++ ) {
        if ( this.skills[ i ] == skill ) {
          this.skills[ i ] = 0;
        }
      }
    }
  }

  //Movement
  public void move() {
  }

  public void attack() {
    if ( skillExist( 1 ) ) {
      System.out.println( "Attacking with Sword" );
    } else {
      System.out.println( "Attacking with Hand" );
    }
  }

}
