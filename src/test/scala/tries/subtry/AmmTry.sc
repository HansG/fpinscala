import fpinscala.answers.datastructures.List

import List.*

val x: List[Int] = Cons(1, Nil)

val result = List(1,2,3,4,5) match
  case Cons(x, Cons(2, Cons(4, _))) => x
  case Nil => 42
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  case Cons(h, t) => h + sum(t)
  case null => 102

println(result)