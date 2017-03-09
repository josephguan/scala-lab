package gx.types.generic


/**
  * Given: Cat <:  Animal
  * is: List[Cat] <: List[Animal]  ?
  *
  * 看起来这是合理的，因为Cat的列表是Animal列表的一个特例（即子类型）。
  * 我们把这种关系叫做“协变”
  *
  */


/**
  * 所有类型都是“协变”吗？我们看看Java的Array协变引起的类型陷阱
  *
  * Cat[] a = new Cat[]{new Cat(), new Cat()}
  * Animal[] b = a
  * b[0] = new Dog()
  * Cat s = a[0]
  */


/**
  * Scala 版本的Array会发生什么情况？
  */

class Animal
class Cat extends Animal
class Dog extends Animal

//object ScalaArrayTest {
//  val a: Array[Cat] = Array(new Cat(), new Cat())
//  val b: Array[Animal] = a
//  b(0) = new Dog()
//  val s: Cat = a(0)
//}



object CovarianceApp extends App {

}

// 一般来说，一个类型的元素是可变的（mutable），则不能是协变的
// 如果元素是不可变的(immutable), 则可以是协变的
