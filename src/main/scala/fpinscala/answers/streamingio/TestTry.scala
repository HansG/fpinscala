package fpinscala.answers.streamingio

import fpinscala.answers.iomonad.Monad
import org.junit.Test

class TestTry:
  import fpinscala.answers.streamingio.EffectfulPulls.*
  import Pull.*

  type Id[A] = A

  given Monad[Id] with
    def unit[A](a: => A): A = a

    extension[A] (ia: Id[A])
      def flatMap[B](f: A => Id[B]): Id[B] = f(ia)


  def down(n: Int): Pull[Id, Int, Unit] =
    if (n == 0)  Result(())
    else Output(n) >> down(n - 1)

  @Test
  def tryOutputFilter = 
    val o = Output(1)
    val of = o.filter(_ % 2 == 0)
    val list = of.toList
    println(list) 
  
  @Test
  def tryDown = 
    val in = down(0)
    val inf = in.filter(_ % 2 == 0)
    val list = inf.toList
    println(list)
