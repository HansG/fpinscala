package tries



/*
Polymorh nach Argument-Typ über Typeclass: suche Typeclass zu Parametertyp -> nehme Implementierung daraus
anders: self-type in https://allaboutscala.com/tutorials/scala-exercises-solutions/
 */
object ArgPolymorphTry2:

  enum Vehicle2(name: String):
    case  Car2(name: String, ps : String) extends Vehicle2(name)
    case  Bike2(name: String, bremsArt : String) extends Vehicle2(name)

    //hier (unter enum): als normale Methode:
    def printme = println(name)

    //extension hier sinnvoll???? bedeutet dasselbe(?) wie normale Methode mit Parameter (desc: String)
    extension (desc: String)
      def printd = println(toString +": "+ desc)


  object Vehicle2:
    //extension hier eher sinnvoll: nachträglich um Methode erweitert - (vehicle: V) ist hier Subjekt der Erweiterung
    //Achtung: extension erspart Typeclass und implizite Implementierungen/Objekte der Typeclass
    extension (v: Vehicle2)
      def checkByExtension = {
        println(s"checkByExtension for vehicle = ${v}")
      }
      //hier zudem mit Typparameter V, d.h. liefert Erweiterung für alle V!!!!!
      /*extension [V <: Vehicle] (car: V)
        def checkStock  = ...*/

  //hier zudem mit Typparameter V, d.h. liefert Erweiterung für alle V!!!!!
  /*extension (vehicle: V)
    def checkStock  = ...*/
  trait VehCheck2 :
    extension  (v : Vehicle2)  def checkByTypeClass : Unit

  object VehCheck2:
      import tries.ArgPolymorphTry2.Vehicle2.*
      given VehCheck2 with
        extension   (v : Vehicle2)
          def checkByTypeClass = v match
            case c : Car2 => v.printd(s"Car Speziell mit PS ${c.ps}")
            case b : Bike2 => b.printd(s"Bike Speziell  mit bremsArt ${b.bremsArt}")


import tries.ArgPolymorphTry2.{Vehicle2, *}
import Vehicle2.{ *, given}
import VehCheck2.given
import munit.Clue.generate
import  Vehicle2.*

import scala.language.postfixOps

private val mycar2  = Car2("mazda 3 series", "200")
private val mybike2  = Bike2("honda bike firestorm", "Rücktritt")


object VTApp21  extends App :
  import munit.Clue.generate

  mycar2.checkByTypeClass
  mycar2.checkByExtension
  mybike2.checkByTypeClass

//alternativ: mit Context-Bound -> givens werden vom Compiler gesucht
object VTApp22  extends App :
  trait VehicleSystem :
    def buyVehicle[V <: Vehicle2](vehicle: V): Unit =
      println(s"buying vehicle $vehicle")
      vehicle.checkByTypeClass
      //      vehiclePricingService.checkPrice(vehicle)

  object VehicleApp extends VehicleSystem:  //with VehicleServices[Vehicle]
    buyVehicle(mycar2)
    println("einmal init")
    buyVehicle(mybike2)

  VehicleApp.buyVehicle(mycar2)
//  VehicleApp.buyVehicle(mybike2)




