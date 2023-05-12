package tries

import fpinscala.answers.introduction.ThirdExample.{Cafe, CreditCard, *}


def tryBuy =

  val cc  = List(new CreditCard, new CreditCard)
  val ca = new Cafe
  val buys = cc.foldLeft((List[Coffee](),List[Charge]())){(ll, c)  =>
    val (lc, ch) =  ca.buyCoffees(c, 10)
    (lc ++ ll._1, ch :: ll._2   )
  }

  println(coalesce(buys._2))
  println("show me the code".capitalizeAllWords)




class Person(name : String)

val t = (11, "eleven", Person("Eleven"))
val n = t._1
val p = t._3


enum Color:
  case Red, Green, Blue

enum Color1(val rgb: Int):
  case Red   extends Color1(0xFF0000)
  case Green extends Color1(0x00FF00)
  case Blue  extends Color1(0x0000FF)
  case Mix(mix: Int) extends Color1(mix)

enum Box[T](contents: T):
  case IntBox(n: Int) extends Box[Int](n)
  case BoolBox(b: Boolean) extends Box[Boolean](b)

object Box:
  def extract[T](b: Box[T]): T = b match
    case IntBox(n)  => n + 1
    case BoolBox(b) => !b

enum Nat:
  case Zero
  case Succ(n: Nat)

enum List1[+A]:
  case Nil
  case Cons(head: A, tail: List1[A])

enum Option1[+T]:
  case Some(x: T)
  case None

  def isDefined: Boolean = this match
    case None => false
    case Some(_) => true

object Option1:
  def apply[T >: Null](x: T): Option1[T] =
    if (x == null) None else Some(x)



enum Planet(mass: Double, radius: Double):

  private final val G = 6.67300E-11
  def surfaceGravity = G * mass / (radius * radius)
  def surfaceWeight(otherMass: Double) =  otherMass * surfaceGravity

  case Mercury extends Planet(3.303e+23, 2.4397e6)
  case Venus   extends Planet(4.869e+24, 6.0518e6)
  case Earth   extends Planet(5.976e+24, 6.37814e6)

object Planet:
  @main def test =
    weights("5")
    weights("8")
    weights("20")

  def weights(earthWeight:  String | Double) =
    val earthWeightd  = earthWeight match
      case ew:String =>  ew.toDouble
      case ewd:Double => ewd

    val mass = earthWeightd / Earth.surfaceGravity
    for (p <- values)
      println(s"Your weight on $p is ${p.surfaceWeight(mass)}")