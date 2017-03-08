package gx.types.traits

class Animal {
  def say(): Unit = println("Animal")
}

trait Furry extends Animal {
  override def say(): Unit = {
    println("Furry")
    super.say()
  }
}

trait HasLegs extends Animal {
  override def say(): Unit = {
    println("HasLegs")
    super.say()
  }
}

trait FourLegged extends HasLegs {
  override def say(): Unit = {
    println("FourLegged")
    super.say()
  }
}

class Cat extends Animal with Furry with FourLegged {
  override def say(): Unit = {
    println("Cat")
    super.say()
  }
}


object LinearApp extends App {

  new Cat().say()

}
