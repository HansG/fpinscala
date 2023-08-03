package tries

import tries.ArgPolymorphTry.{Bike, Car, Vehicle}


/*
Polymorh nach Argument-Typ über Typeclass: suche Typeclass zu Parametertyp -> nehme Implementierung daraus
anders: self-type in https://allaboutscala.com/tutorials/scala-exercises-solutions/
 */
object ArgPolymorphTry:

  abstract class Vehicle(name: String)
  case class Car(name: String, ps : String) extends Vehicle(name)
  case class Bike(name: String, bremse : String) extends Vehicle(name)

  trait VehicleInventoryService[V <: Vehicle] :
    extension (vehicle: V)
      def checkStock(): Unit = {
        println(s"checking stock for vehicle = $vehicle")
      }


  object VehicleInventoryService :
    given VehicleInventoryService[Car]  with
      extension (vehicle: Car)
        override def checkStock(): Unit = {
          println(s"checking stock for !Car! = ${vehicle.name + vehicle.ps}")
        }

    given VehicleInventoryService[Bike]  with
      extension (vehicle: Bike)
        override def checkStock(): Unit = {
          println(s"checking stock for !Bike! = ${vehicle.name + vehicle.bremse}")
        }


private val mycar: Car = Car("mazda 3 series", "200")
private val mybike: Bike = Bike("honda bike firestorm", "Rücktritt")


object VTApp  extends App :
  import ArgPolymorphTry.*
  import ArgPolymorphTry.VehicleInventoryService.given

  mycar.checkStock()

//alternativ: mit Context-Bound -> givens werden vom Compiler gesucht
object VTApp2  extends App :
  import ArgPolymorphTry.*

  trait VehicleSystem :
    def buyVehicle[V <: Vehicle : VehicleInventoryService](vehicle: V): Unit =
      println(s"buying vehicle $vehicle")
      vehicle.checkStock()
      //      vehiclePricingService.checkPrice(vehicle)

  object VehicleApp extends VehicleSystem:  //with VehicleServices[Vehicle]
    buyVehicle(mycar)
    buyVehicle(mybike)

  VehicleApp.buyVehicle(mycar)
  VehicleApp.buyVehicle(mybike)

  object VehicleAppE extends App with VehicleSystem  //with VehicleServices[Vehicle]
  //    import ArgPolymorphTry.VehicleInventoryService.given

  VehicleAppE.buyVehicle(mycar)
  VehicleAppE.buyVehicle(mybike)






