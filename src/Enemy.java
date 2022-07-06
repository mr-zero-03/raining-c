public class Enemy {

    int lives = 0;
    int damage = 0;
    int skinId = 0;
  
    public Enemy( int lives, int damage, int skinId ) {
      this.lives = lives;
      this.damage = damage;
      this.skinId = skinId;
    }
  
    //Lives
    public void die() {
      
    }
  
    public void loseLive() {
      this.lives -= 1;
  
      if ( this.lives == -1 ) {
        die();
      }
    }
  
    //Movement
    public void move() {
    }
  
    public void attack() {
      System.out.println( "Attacking with Sword" );
    }
  
}