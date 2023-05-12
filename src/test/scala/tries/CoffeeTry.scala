package tries

import fpinscala.answers.introduction.ThirdExample.{Cafe, CreditCard, *}


@main def tryBuy =

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
