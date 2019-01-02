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
