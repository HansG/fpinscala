package tries


import scala.collection.mutable.ArrayBuffer

//Toplevel Definitionen: überall im package sichtbar (wenn explizit in package ....: ... -> dann auch in Unterpackages sichtbar
enum Topping:
  case Cheese, Pepperoni, Mushrooms

extension (s: String)
  def capitalizeAllWords = s.split(" ").map(_.capitalize).mkString(" ")

import Topping.*
class Pizza:
  val toppings = ArrayBuffer[Topping]()

val pizza = Pizza()


val hwUpper = "hello, world".capitalizeAllWords

type Money = BigDecimal

// more definitions here as desired ...

@main def myApp =
  pizza.toppings += Cheese
  println("show me the code".capitalizeAllWords)
