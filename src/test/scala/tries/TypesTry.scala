package tries

import org.junit.Test

object TypesTry:
  object Logarithms:
    //vvvvvv this is the important difference!
    opaque type Logarithm = Double

    object Logarithm:
      def apply(d: Double): Logarithm = math.log(d)

    extension (x: Logarithm)
      def toDouble: Double = math.exp(x)
      def toString: Double = toDouble
      def +(y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
      def *(y: Logarithm): Logarithm = x + y


  class LogarithmsTry:
    import Logarithms.*

    @Test
    def tryPlusMal =
      val log2 = Logarithm(2.0)
      val log3 = Logarithm(3.0)
      println(log2) // prints 6.0
      println(log3) // prints 4.999...
      println((log2).toDouble) // prints 6.0
      println((log3).toDouble) // prints 4.999...
      println((log2 * log3).toDouble) // prints 6.0
      println((log2 + log3).toDouble) // prints 4.999...

  //val d: Double = log2  ERROR: Found Logarithm required Double


  object StructTypes:
    class Record1(elems: (String, Any)*) extends Selectable:
      private val fields = elems.toMap

      def selectDynamic(name: String): Any = fields(name)

    type Person = Record1 {
      val name: String
      val age: Int
    }


    type Book = Record1 {
      val title: String
      val author: String
      val year: Int
      val rating: Double
    }


    class RecordTry:
      @Test
      def emma =
        val person = Record1(
          "name" -> "Emma",
          "age" -> 42
        ).asInstanceOf[Person]

        println(s"${person.name} is ${person.age} years old.")

      @Test
      def rye =
        val book = Record1(
        "title" -> "The Catcher in the Rye",
        "author" -> "J. D. Salinger",
        "year" -> 1951,
        "rating" -> 4.5
        ).asInstanceOf[Book]
        println(s"${book.title} is ${book.year} years old.")


  end StructTypes


  object DependantTypes:

    trait Nums:
      // the type of numbers is left abstract
      type Num

      // some operations on numbers
      def lit(d: Double): Num
      def add(l: Num, r: Num): Num
      def mul(l: Num, r: Num): Num

    object Doubles extends Nums:
      type Num = Double

      def lit(d: Double): Num = d
      def add(l: Num, r: Num): Num = l + r
      def mul(l: Num, r: Num): Num = l * r


  //  type Prog = (n: Nums) => n.Num => n.Num
    trait Prog :
      def apply(n : Nums)(n.Num) : n.Num

    val ex: Prog = nums => x => nums.add(nums.lit(0.8), x)

    def derivative(input: Prog): Double = 0.8

    derivative { nums => x => x }
    derivative { nums => x => nums.add(nums.lit(0.8), x) }

  end DependantTypes

end TypesTry

