
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import scala.sys.process.*


val sizeThreshold = 10 * 1024 * 1024 // 1 MB in Bytes

def listLargeFiles(directory: String, sizeThreshold: Long): Seq[String] = {
  val cmd = s"find $directory -type f -size +${sizeThreshold}c"
  cmd.!!
    .split("\n")
    .toSeq
}

val directory = "C:\\se\\doc"



class Ammtest1 extends AnyFlatSpec with should.Matchers {
  override def suiteName: String = "FactTest"

  "largeFiles" should "..bla" in {
    val largeFiles = listLargeFiles(directory, sizeThreshold)
    largeFiles.foreach(println)
//    listLargeFiles(directory, sizeThreshold) shouldBe Seq("nn")
  }
}



