package gx.stackoverflow

/** Question:
  * http://stackoverflow.com/questions/40172423/why-cant-concrete-members-be-overridden-with-abstract-ones-in-scala/40195276#40195276
  */

/** Answer:
  * According to Scala Spec, a concrete definition always overrides an abstract definition.
  * http://www.scala-lang.org/files/archive/spec/2.11/05-classes-and-objects.html#class-members
  */
object answer002 extends App {

  class Animal

  class Dog extends Animal

  trait Base {
    def a: Animal = new Dog
  }

  trait Deri extends Base {
    override def a: Animal
  }

  // OR：
  //
  //  trait Base {
  //    val a: Dog = new Dog
  //  }
  //
  //  trait Deri extends Base {
  //    override val a: Dog
  //  }

}
