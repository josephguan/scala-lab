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

// Cake Pattern

/**
  * 首先，和之前一样，定义元件的接口（如DIYEngine，DIYWheel）
  */
trait DIYEngine {
  def start(): Unit
}

trait DIYWheel {
  def rotate(): Unit
}

/**
  * 然后，我们要把元件包在一个Component trait（如EngineComponent和WheelComponent）中，
  * 并提供一个存取元件的方法（如engine、wheel)
  */
trait EngineComponent {
  val engine: DIYEngine
}

trait WheelComponent {
  val wheel: DIYWheel
}

// 元件的具体实现
class V6Engine extends DIYEngine {
  def start(): Unit = println("V6 Engine Start.")
}

class V8Engine extends DIYEngine {
  def start(): Unit = println("V8 Engine Start.")
}

class DunlopWheel extends DIYWheel {
  def rotate(): Unit = println("Dunlop Wheel Roate")
}

/**
  * 在被注入的一方（DIYCar），我们需要使用self-type来声明：
  * 在构造DIYCar时，必须要提供EngineComponent和WheelComponent。
  * 同时，在DIYCar内部，我们可以通过engine和wheel两个成员，调用他们提供的接口。
  */
abstract class DIYCar {
  self: EngineComponent with WheelComponent =>

  def drive() {
    engine.start()
    wheel.rotate()
    println("Vroom vroom")
  }

}

/**
  * 由于我们系统可能包含不止一个元件，建议用Module来定义所有元件
  * 在Cake Pattern上，我们可以用一个ComponentRegistry trait来把所有的元件指定好。
  */

trait InMemoryComponentRegistry extends EngineComponent with WheelComponent {
  override val engine = new V8Engine()
  override val wheel = new DunlopWheel()
}

trait XmlComponentRegistry extends EngineComponent with WheelComponent {
  // read configurations from XML, and construct object by reflect
  override val engine = new V6Engine()
  override val wheel = new DunlopWheel()
}

object CakePatternApp extends App {
  val myCar = new DIYCar with InMemoryComponentRegistry
  myCar.drive()

  val testCar = new DIYCar with XmlComponentRegistry
  testCar.drive()
}
