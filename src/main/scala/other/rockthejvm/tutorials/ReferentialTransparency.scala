package other.rockthejvm.tutorials

// https://www.youtube.com/watch?v=CmHEid4qnco
// https://blog.rockthejvm.com/referential-transparency/
object ReferentialTransparency {

  // example of a RT expression
  def add(a: Int, b: Int): Int = a + b

  val five = add(2, 3)
  val ten = five + five
  val ten_v2 = add(2, 3) + add(2, 3)
  val ten_v3 = 5 + add(2, 3)
  val ten_4 = 10

  // ask money from mob boss
  def showMeTheMoney(money: Int): Int = { // NOT RT
    println("Here's your cash, your excellency.") // side effect
    money * 110 / 110
  }

  val aGrandWI = showMeTheMoney(1000)
  val twoGrandWI = showMeTheMoney(1000) + showMeTheMoney(1000)
  val twoGrandWI_v2 = aGrandWI + aGrandWI // meaning changed

  // another example: russian roulette
  def whatsTheTime(): Long = System.currentTimeMillis()

  val currentTime = whatsTheTime()
  val russianRoulette = if (whatsTheTime() % 6 == 0) "BANG" else "click"
  val russianRoulette_v2 = if (currentTime % 6 == 0) "BANG" else "click" // may be different

  // why
  // refactor 1
  def anRTFunction(a: Int, b: Int) = a + b

  def aBigComputation() = {
    val comp1 = anRTFunction(2, 3)
    val comp2 = anRTFunction(2, 3)
    val comp3 = anRTFunction(2, 3)
    comp1 + comp2 + comp3
  }

  // refactor 2
  def rtf1(a: Int) = a + 1

  def rtf2(a: Int) = a * 10

  def rtf3(a: Int) = a + 100

  def rtf4(a: Int) = a + a

  def aBigProgram = {
    //    anRTFunction(anRTFunction(rtf1(1), rtf2(2)), anRTFunction(rtf3(3), rtf4(4)))
    val e1 = rtf1(1)
    val e2 = rtf2(2)
    val e3 = rtf3(3)
    val e4 = rtf4(4)
    val e12 = anRTFunction(e1, e2)
    val e34 = anRTFunction(e3, e4)
    anRTFunction(e12, e34)
  }

  // keep track of code execution
  def sumN(n: Int): Int =
    if (n <= 0) 0
    else n + sumN(n - 1)

  /*
    sumN(5) =
    5 + sumN(4) =
    5 + 4 + sumN(3) =
    5 + 4 + 3 + sumN(2) =
    5 + 4 + 3 + 2 + sumN(1) =
    5 + 4 + 3 + 2 + 1 + sumN(0) =
    5 + 4 + 3 + 2 + 1 + 0 = 15
   */

  def aBigComputation_v2() = {
    val comp = anRTFunction(2, 3)
    comp + comp + comp
  }

  def main(args: Array[String]): Unit = {
    println(russianRoulette)
    println(russianRoulette_v2)
  }
}
