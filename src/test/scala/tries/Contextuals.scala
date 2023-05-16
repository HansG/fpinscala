package tries

import org.junit.Test

object Contextuals {

  case class Circle(x: Double, y: Double, radius: Double)

  extension (c: Circle)
    def circumference: Double = c.radius * math.Pi * 2
    def diameter: Double = c.radius * 2
    def area: Double = math.Pi * c.radius * c.radius


  class CircleTry:
    val aCircle = Circle(2, 3, 5)

    @Test def testcf = println(aCircle.circumference)


  case class Config(port: Int, baseUrl: String)
  def renderWebsite(path: String)(using  Config): String =
    "<html>" + renderWidget(List("cart")) + "</html>"
  //                                  ^
  //                   no argument config required anymore

  def renderWidget(items: List[String])(using config: Config): String = items.foldLeft(config.baseUrl)(  (text, li) =>  text+"<div>"+li+ "/<div>" )


  class UsingTry:

    val config = Config(8080, "http://localhost")

    @Test def testcf = println(renderWebsite("/home")(using config))

    val config1 = Config(8080, "docs.scala-lang.org")
    // this is the type that we want to provide the
    // canonical value for
    given Config = config1
    //             ^^^^^^
    // this is the value the Scala compiler will infer
  // as argument to contextual parameters of type Config
    @Test def testcf1 = println(renderWebsite("/home"))


  /** Defines how to compare values of type `A` */
  trait Ord[A]:
    def greaterThan(a1: A, a2: A): Boolean

  /** Returns the maximum of two values */
  def max[A](a1: A, a2: A)(using ord: Ord[A]): A =
    if ord.greaterThan(a1, a2) then a1 else a2
  def maxElement0[A](as: List[A])(using ord: Ord[A]): A =
    as.reduceLeft(max(_, _)(using ord))
  //besser:
  def maxElement1[A](as: List[A])(using Ord[A]): A =
    as.reduceLeft(max(_, _))
  def maxElement[A : Ord](as: List[A]): A =
    as.reduceLeft(max(_, _))

  class OrdTry:
    given Ord[String] with
      def greaterThan(a1: String, a2: String) = a1.compareTo(a2) > 0

    @Test def testMax = println(maxElement( List("home", "go", "today" )) )


  object A:
    class TC
    given tc: TC = ???
    def f(using TC) = ???

  object B:

    import A.* //  * brings all definitions other than givens or extensions into scope
    import A.given //  brings all givens—including those resulting from extensions—into scope
    /*enables importing all givens without importing anything else.
    This is important because givens can be anonymous, so the usual use of named imports is not practical.*/


  // a type class
  trait Showable[A]:
    extension (a: A) def show: String

  class ShowableTry:
    case class Person(firstName: String, lastName: String)
    given Showable[Person] with
      extension (p: Person) def show: String =
        s"${p.firstName} ${p.lastName}"

    @Test def tryit = {
      val person = Person("John", "Doe")
      println(person.show)
    }


  enum Book(author: String, title: String, year: Int):
    case PrintedBook(author: String,title: String,year: Int,pages: Int) extends Book(author, title, year)
    case AudioBook(author: String,title: String,year: Int, lengthInMinutes: Int) extends Book(author, title, year)
    // override equals einzeln für AudioBook und PrintedBook: funktioniert nur bei trait Book ...case class .... override def equals
    // -> https://docs.scala-lang.org/scala3/book/ca-multiversal-equality.html
    //hier bei enum nur override equals für Book:
      override def equals(that: Any): Boolean = that match
        case a: AudioBook =>
          if this.author == a.author && this.title == a.title && this.year == a.year // && a.lengthInMinutes == 0
          then true else false
        case p: PrintedBook =>
          if this.author == p.author && this.title == p.title
          then true else false
        case _ => false


  class CanEqualTry:
    import Book.*
    // [1] add this import, or this command line flag: -language:strictEquality
    import scala.language.strictEquality
    given CanEqual[Book, Book] = CanEqual.derived
   // given CanEqual[PrintedBook, PrintedBook] = CanEqual.derived
   // given CanEqual[AudioBook, AudioBook] = CanEqual.derived

    @Test def comparePB = {
      // [4a] comparing two printed books works as desired
      val p1 = PrintedBook("1984", "George Orwell", 1961, 328)
      val p2 = PrintedBook("1984", "George Orwell", 1961, 328)
      println(p1 == p2)         // true
    }

    @Test def comparePA = {
      // [4b] you can’t compare a printed book and an audiobook
      val pBook = PrintedBook("1984", "George Orwell", 1961, 328)
      val aBook = AudioBook("1984", "George Orwell", 1961, 682)
      println(pBook == aBook)
    }



}
