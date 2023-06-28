package fpinscala.answers.streamingio

import fpinscala.answers.iomonad.Monad
import org.junit.Test

class TestSimpleTry:
  import fpinscala.answers.streamingio.SimplePulls.*
  import Pull.*

  def down(n: Int): Pull[Int, Unit] =
    if (n == 0)  Result(())
    else Output(n) >> down(n - 1)

  @Test
  def tryContinually =
    val o =  continually(1) 
    val of = o.take(1)
    show(of)
    
    
  @Test
  def tryOutputFilter = 
    val o = Output(1)
    val of = o.filter(_ % 2 == 0)
    show(of)
  
  @Test
  def tryDown = 
    val in = down(0)
    val inf = in.filter(_ % 2 == 0)
    show(inf)


  def show[O,R](inf : Pull[O,R]) =
    val list = inf.toList
    println(list)
