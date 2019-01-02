/*
 * Copyright (c) 2019 josephguan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gx.scala.types.generic

/**
  * MyList
  */

trait MyList[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: MyList[T]

  // first implementation
  // def prepend(elem: T): List[T] = new Cons(elem, this)

  // 应该如何实现?

}

class Cons[+T](val head: T, val tail: MyList[T]) extends MyList[T] {
  def isEmpty = false
}

object MyNil extends MyList[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}


object VarianceApp extends App {
  val x: MyList[Cat] = MyNil
  val y = new Cons(new Animal(), x)

  println(y.head)

//  val z = x.prepend(new Dog())


}


/** 逆变
  * def f(a: Animal): Apple
  * def g(a: Cat): Fruit
  *
  * cat = new Cat
  * val r: Fruit = g(cat)
  *
  * a = new Animal
  * val r: Apple = f(a)
  *
  * 所以，函数定义是
  * trait Function1[-T, +U] {
  *     def apply(x: T): U
  * }
  */