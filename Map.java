public class Map {

  int level = 0;
  int words[];

  String correctWord = "";

  public Map( int level, int maxWordsPerBatch ) {
    this.level = level;
    this.words = new int[ maxWordsPerBatch ];
  }

  public void launchThunder() {
  }

  public void launchWords( String words[], String correctWord ) {
    this.correctWord = correctWord;

    int wordsSize = this.words.length
    for ( int i = 0; i < wordsSize; i++ ) {
      this.words[ i ] = words[ i ];
    }
  }

  public void compareSelectedWord( String selectedWord ) {
    if ( selectedWord == this.correctWord ) {
      System.out.println( "Well done" );
    } else {
      System.out.println( "Player loses live" );
    }
  }

}
