package tries

import org.junit.Test
import better.files.*
import scala.sys.process.*


class CommandTry:
  val dirs = List("C:/se/intj/Projekte/trunk/01-Technik/03-Fremdsystemschnittstellen/VkDtoMapDefinition/etc"
    ,"C:/se/intj/Projekte/OK.VERKEHR/01-Technik/03-Fremdsystemschnittstellen/VkCommonsKonnektor/etc/secproxy"
    ,"C:/se/intj/Programme/okjboss-7_4_1/akdb/verkehr/work/config"
    ,"C:/se/intj/Programme/secproxy_lokal"
    )


  @Test def openExplorer =
    dirs.foreach(path =>  {
      val directory = File(path)
      if (directory.exists) {
        s"explorer.exe ${directory.path}".! //funktioniert!!
      } else {
        println(s"Path does not exist: $path")
      }
//      val runtime = Runtime.getRuntime
//      runtime.exec(s"explorer.exe \"$path\"")) funktioniert nicht!! 
    }
    )



