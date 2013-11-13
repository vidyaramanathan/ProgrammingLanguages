package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */

  def pascal(c: Int, r: Int): Int = {
    def factorial(n: Int): Int = {
      def loop(acc: Int, n: Int): Int =
        if (n == 0) acc
        else loop(acc * n, n - 1)
      loop(1, n)
    }
    // when the column is equal to the row, its always 1
    if (c == r) 1
    // When the column is 0, its always 1
    if (c == 0) 1
    // On all the cases, apply the formula to derive the result
    else factorial(r) / (factorial(c) * factorial(r - c))

  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    
    def recur(open: Int,  chars: List[Char]): Boolean = {
      
      if(chars.isEmpty) {
        open == 0
      } else if(chars.head == ('(') ) {
        recur(open + 1, chars.tail)
      } else if(chars.head == (')')) {
        open > 0 && recur(open - 1, chars.tail)
      } else {
        recur(open, chars.tail)
      }
    }
    
    if (chars.isEmpty) true
    else recur(0, chars)
  }
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def waysToCount(money : Int, coins : List[Int]) : Int = {
      if ( money == 0 ) 1
      else if (money < 0 || coins.isEmpty) 0 
      else {
        waysToCount(money - coins.head, coins) + waysToCount(money, coins.tail)
      }
    }
    
    if(money == 0) 0
    else waysToCount(money, coins)
    
  }
}
