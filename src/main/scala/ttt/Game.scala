package ttt

import scala.collection.mutable

class Game(player1: Player, player2: Player) {
  var currentPlayer: Player = player1;
  val winningMoves = List(1 :: 5 :: 9 :: Nil, 3 :: 5 :: 7 :: Nil,
    1 :: 2 :: 3 :: Nil, 4 :: 5 :: 6 :: Nil, 7 :: 8 :: 9 :: Nil,
    1 :: 4 :: 7 :: Nil, 2 :: 5 :: 8 :: Nil, 3 :: 6 :: 9 :: Nil);
  var allMoves: mutable.Map[Int, String] = scala.collection.mutable.Map[Int, String]();

  def changeTurn(): Unit = {
    val players = List(player1, player2);
    val Some(nextPlayer) = players.find(player => player.getSymbol != this.currentPlayer.getSymbol);
    this.currentPlayer = nextPlayer;
  }

  def takeMove(): Int = {
    printf("Hello %s! Enter your move : ", this.currentPlayer.getName);
    val move = scala.io.StdIn.readInt();
    if (this.isValid(move)) return move;
    println("move is already equipped or invalid \n");
    this.takeMove()
  }

  def isSubset(superSet: List[Int], subset: List[Int]): Boolean = subset.forall(i => superSet.contains(i));

  def hasWon: Boolean = this.winningMoves.exists(pattern => isSubset(this.currentPlayer.getMoves, pattern));

  def isInBound(move: Int): Boolean = Range(1, 10).contains(move);

  def isValid(move: Int): Boolean = isInBound(move) && !this.allMoves.contains(move);

  def readMove(index: Int): String = this.allMoves.getOrElse(index, " ");

  def displayBoard(): Unit = {
    printf("\n  %s | %s | %s \n ---+---+---", this.readMove(1), this.readMove(2), this.readMove(3));
    printf("\n  %s | %s | %s \n ---+---+---", this.readMove(4), this.readMove(5), this.readMove(6));
    printf("\n  %s | %s | %s \n", this.readMove(7), this.readMove(8), this.readMove(9));
  }

  def showWinningMsg(): Unit = {
    printf("\n Congratulations %s YOU WON! \n", this.currentPlayer.getName);
    scala.sys.exit();
  }

  def play(): Unit = {
    while (this.allMoves.size < 9) {
      val move = takeMove();
      this.currentPlayer.addMove(move);
      this.allMoves.put(move, this.currentPlayer.getSymbol);
      this.displayBoard();
      if (hasWon) this.showWinningMsg();
      this.changeTurn();
    }
    println("\n ---- OOPS! Match Draw ---- \n")
  }
}
