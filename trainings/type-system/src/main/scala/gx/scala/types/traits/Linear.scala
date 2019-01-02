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
