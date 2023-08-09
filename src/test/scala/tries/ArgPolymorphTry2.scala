package tries



/*
Polymorh nach Argument-Typ über Typeclass: suche Typeclass zu Parametertyp -> nehme Implementierung daraus
anders: self-type in https://allaboutscala.com/tutorials/scala-exercises-solutions/
 */
object ArgPolymorphTry2:

  enum Vehicle2(name: String):
    case  Car2(name: String, ps : String) extends Vehicle2(name)
    case  Bike2(name: String, bremse : String) extends Vehicle2(name)

    //hier (unter enum): als normale Methode:
    def printme = println(name)

    //extension hier sinnvoll???? bedeutet dasselbe(?) wie normale Methode mit Parameter (desc: String)
    extension (desc: String)
      def printd = println(toString +": "+ desc)

    extension (car: Car2)
      def checkByExtension = {
        println(s"checking stock for vehicle = ${car.name} , PS=${car.ps}")
        car.printd("Spezielle Desc für car")
      }


  trait VehCheck2 :
    extension [V <: Vehicle2](v : V)  def checkByTypeClass =
        v.printd(s"Spezielle Desc von VehCheck für $v")

  import Vehicle2.*
  object VehCheck2:
      given VehCheck2 with
        extension (v : Car2)
          def checkByTypeClass =
              v.printd(s"Spezielle Desc von Car mit PS ${v.ps}")

  object Vehicle2:
    //extension hier eher sinnvoll: nachträglich um Methode erweitert - (vehicle: V) ist hier Subjekt der Erweiterung
    //Achtung: extension erspart Typeclass und implizite Implementierungen/Objekte der Typeclass
    extension (car: Car2)
      def checkByExtension = {
        println(s"checking stock for vehicle = ${car.name} , PS=${car.ps}")
        car.printd("Spezielle Desc für car")
      }
    //hier zudem mit Typparameter V, d.h. liefert Erweiterung für alle V!!!!!
    /*extension [V <: Vehicle] (vehicle: V)
      def checkStock  = ...*/

import tries.ArgPolymorphTry2.*
import Vehicle2.*
import Vehicle2.given
import VehCheck2.given
import munit.Clue.generate

import scala.language.postfixOps

private val mycar2  = Car2("mazda 3 series", "200")
private val mybike2  = Bike2("honda bike firestorm", "Rücktritt")


object VTApp21  extends App :

  extension (car: Car2)
    def checkByExtension = {
      println(s"checking stock for vehicle = ${car.name} , PS=${car.ps}")
      car.printd("Spezielle Desc für car")
    }

  mycar2.checkByTypeClass
  mycar2.checkByExtension
  mybike2.checkByExtension

//alternativ: mit Context-Bound -> givens werden vom Compiler gesucht
object VTApp22  extends App :
  trait VehicleSystem :
    def buyVehicle[V <: Vehicle2](vehicle: V): Unit =
      println(s"buying vehicle $vehicle")
      vehicle.checkByTypeClass
      //      vehiclePricingService.checkPrice(vehicle)

  object VehicleApp extends VehicleSystem:  //with VehicleServices[Vehicle]
    buyVehicle(mycar2)
    buyVehicle(mybike2)

  VehicleApp.buyVehicle(mycar2)
  VehicleApp.buyVehicle(mybike2)

  object VehicleAppE extends App with VehicleSystem  //with VehicleServices[Vehicle]
  //    import ArgPolymorphTry.VehicleInventoryService.given

  VehicleAppE.buyVehicle(mycar2)
  VehicleAppE.buyVehicle(mybike2)


import ArgPolymorphTry2.*
import ArgPolymorphTry2.Vehicle2.*



