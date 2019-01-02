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

package gx.scala.types.traits

/**
  * Dependency Injection & Inversion of Control是Martin Fowler在2004年所提出來的一个概念.
  * 在IoC概念出现以前，对象依赖的元件（Component）一般是对象建立时就绑定的。 Martin的贡献在于，
  * IoC把对象对元件的依赖拆出来，变成可以替换的实体。
  *
  * IoC主要是通过依赖注入（DI）实现的。
  *
  * 在Java中，DI可以有三种形式来实现:
  * Constructor Injection, Setter Injection, and Interface Injection.
  *
  * Scala也可以使用上面三种形式，但是Scala的self-type又提供了一种新的形式
  *
  */

trait Engine {
  def start(): Unit
}

trait DieselEngine extends Engine {
  override def start(): Unit = println("DieselEngine Start.")
}

abstract class Car {
  this: Engine =>

  def drive() {
    start()
    println("Vroom vroom")
  }
}


object DependencyInjectionApp extends App {

  val myCar = new Car with DieselEngine
  myCar.drive()

}
