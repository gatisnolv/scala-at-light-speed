package other.rockthejvm.tutorials

// https://www.youtube.com/watch?v=aX-Lc6NXhC8
// https://blog.rockthejvm.com/scala-syntax-tricks-for-expressiveness/
object TricksForExpressiveness extends App {

  // trick 1 - the single abstract method pattern
  trait Action {
    def anImplementedMethod() = 43

    def act(x: Int): Int
  }

  val myAction: Action = (x: Int) => x + 1
  /*
    val myAction: Action = new Action {
      def act(x:Int) = x + 1
    }
   */

  new Thread(() => println("I'm running easy")).start()

  // trick 2 - right-associative methods
  val prependedElement = 2 :: List(3, 4)
  1 :: 2 :: 3 :: List()
  // equivalent
  1 :: (2 :: (3 :: List()))
  // compiler rewrites
  List().::(3).::(2).::(1)

  class MessageQueue[T] {
    //    an enqueue method
    def -->:(value: T): MessageQueue[T] = new MessageQueue[T]
  }

  val queue = 3 -->: 2 -->: 1 -->: new MessageQueue[Int]

  // trick 3 - baked-in "setters"
  class MutableIntWrapper {
    private var internalValue = 0

    // getter
    def value = internalValue

    // setter
    def value_=(newValue: Int) = {
      internalValue = newValue
    }
  }

  val wrapper = new MutableIntWrapper
  wrapper.value = 43 // wrapper.value_=(43)

  // trick 4 - multi-word members
  case class Person(name: String) {
    def `then said`(thing: String) = s"$name then said: $thing"
  }

  val jim = new Person("Jim")
  jim `then said` "Scala is pretty awesome!"

  // real life: Akka HTTP

  // trick 5 - backticks for Pattern Matching
  val meaningOfLife = 42
  val data: Any = 45

  // pattern matching against meaningOfLife
  val pm = data match {
    case meaningOfLife => // use it here
  } // wrong

  val pm2 = data match {
    case x if x == meaningOfLife => // use x here
  }

  // elegant way
  val pm3 = data match {
    case `meaningOfLife` => // continue your logic
  }

}

