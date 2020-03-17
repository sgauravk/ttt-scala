package ttt

object Main extends App {

  printf(" Enter player 1 name : ");
  val player1 = new Player(scala.io.StdIn.readLine(), "X");
  printf("hello %s your symbol is %s\n\n", player1.getName, player1.getSymbol);

  printf(" Enter player 2 name : ");
  val player2 = new Player(scala.io.StdIn.readLine(), "O");
  printf("hello %s your symbol is %s\n\n", player2.getName, player2.getSymbol);

  val game = new Game(player1, player2);
  game.play();
}
