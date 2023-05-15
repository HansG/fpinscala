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
      def +(y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
      def *(y: Logarithm): Logarithm = x + y


  class LogarithmsTry:
    import Logarithms.*

    @Test
    def tryPlusMal =
      val log2 = Logarithm(2.0)
      val log3 = Logarithm(3.0)
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

    class RecordTry:
      @Test
      def emma =
        val person = Record1(
          "name" -> "Emma",
          "age" -> 42
        ).asInstanceOf[Person]

        println(s"${person.name} is ${person.age} years old.")




end TypesTry

