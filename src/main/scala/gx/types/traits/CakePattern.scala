package gx.types.traits


trait Engine {
  def start(): Unit = {
    println(s"Engine started. Using $fuelType")
  }

  def fuelType: String
}

trait DieselEngine extends Engine {
  override val fuelType = "Diesel"
}

trait Car {
  this: Engine =>

  def drive() {
    start()
    println("Vroom vroom")
  }
}

object CakePatternApp extends App {
  new Cat().say()
}
