package tries

import munit.CatsEffectSuite
import io.github.quafadas.scautable.scautable

class TableTest extends CatsEffectSuite {

  import scautable.{*, given}
  test {
    case class Easy(s: String, d: Double)
    println(scautable(Seq(Easy("thing", 1.2)))).toString()
  }

}
