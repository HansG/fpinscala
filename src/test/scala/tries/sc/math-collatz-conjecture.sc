// summary : Collatz conjecture / conjecture de syracuse.
// run-with : scala-cli $file

// ---------------------
//> using scala "3.4.2"
//> using dep "org.scalatest::scalatest:3.2.10"
// ---------------------

/*
Collatz conjecture wikipedia : https://en.wikipedia.org/wiki/Collatz_conjecture
*/

import org.scalatest.*,flatspec.*,matchers.*

def pathLengthToOne(from:Long):Int = {
  @annotation.tailrec
  def loop(current:Long, length:Int=0):Int = {
    if (current == 1) length else {
      if (current % 2 == 0) loop(current / 2, length + 1)
      else loop(3 * current +1, length + 1)
    }
  }
  loop(from)
}


def pathToOne(from:Long):List[Long] = {
  @annotation.tailrec
  def loop(current:Long, currentPath:List[Long]=Nil):List[Long] = {
    if (current == 1L) current::currentPath else {
      if (current % 2 == 0) loop(current / 2, current::currentPath)
      else loop(3 * current +1, current::currentPath)
    }
  }
  loop(from)
}


def highestPathLength(start:Long, end:Long):Int = {
  start.to(end).map(pathLengthToOne).max
}



object CollatzConjectureTest extends AnyFlatSpec with should.Matchers {
  "collatz conjecture path" should "start with 1" in {
    pathToOne(128).head shouldBe 1L
  }

  it should "last with its start value" in {
    pathToOne(17).last shouldBe 17L
  }

  it should "be possible to get the path up to 1" in {
    pathToOne(7) shouldBe List(1, 2, 4, 8, 16, 5, 10, 20, 40, 13, 26, 52, 17, 34, 11, 22, 7)
  }
}

CollatzConjectureTest.execute()