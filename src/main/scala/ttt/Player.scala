package ttt

import scala.collection.mutable.ListBuffer

class Player(private var name: String, private var symbol: String) {
  private val moves = new ListBuffer[Int]();

  def addMove(move: Int): Unit = {
    this.moves.append(move);
  }

  def getName: String = name;

  def getMoves: List[Int] = this.moves.toList;

  def getSymbol: String = symbol;
}
