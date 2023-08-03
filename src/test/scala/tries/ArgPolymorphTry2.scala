package tries



/*
Polymorh nach Argument-Typ über Typeclass: suche Typeclass zu Parametertyp -> nehme Implementierung daraus
anders: self-type in https://allaboutscala.com/tutorials/scala-exercises-solutions/
 */
object ArgPolymorphTry2:

  enum Vehicle(name: String):
    case  Car(name: String, ps : String) extends Vehicle(name)
    case  Bike(name: String, bremse : String) extends Vehicle(name)

    //hier (unter enum): als normale Methode:
    def printme = println(name)

    //extension hier sinnvoll???? bedeutet dasselbe(?) wie nromale Methode mit Parameter (desc: String)
    extension (desc: String)
      def printd = println(desc)


  trait VehCheck[V <: Vehicle] :
    extension (v : V)  def checkStockVC =
        v.printd(s"Spezielle Desc von VehCheck für $v")

  import tries.ArgPolymorphTry2.*
  import Vehicle.*

  object VehCheck:
    given VehCheck[Car] with
      extension (v : Car)  def checkStockVC =
        v.printd(s"Spezielle Desc von VehCheck mit PS ${v.ps}")

  object Vehicle:
    //extension hier eher sinnvoll: nachträglich um Methode erweitert (vehicle: V) ist hier Subjekt der Erweiterung
    //Achtung: extension erspart Typeclass und implizite Implementierungen/Objekte der Typeclass
    //hier zudem mit Typparameter V, d.h. liefert Erweiterung für alle V!!!!!
    extension (car: Car)
      def checkStock = {
        println(s"checking stock for vehicle = ${car.name} , PS=${car.ps}")
        car.printd("Spezielle Desc für car")
      }
    /*extension [V <: Vehicle] (vehicle: V)
      def checkStock  = {
        println(s"checking stock for vehicle = ${vehicle}")
        vehicle.printme
        vehicle.printd("Hi Desc")
      }*/




private val mycar2  = Car("mazda 3 series", "200")
private val mybike2  = Bike("honda bike firestorm", "Rücktritt")


object VTApp21  extends App :

  val mycar3  = Car("mazda 3 series", "200")
  mycar2.checkStock
  mycar3.checkStock
  mybike2.checkStock

//alternativ: mit Context-Bound -> givens werden vom Compiler gesucht
object VTApp22  extends App :
  trait VehicleSystem :
    def buyVehicle[V <: Vehicle](vehicle: V): Unit =
      println(s"buying vehicle $vehicle")
      vehicle.checkStock
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






