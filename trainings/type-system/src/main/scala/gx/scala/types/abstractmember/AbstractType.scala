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

package gx.scala.types.abstractmember

/**
  * 动物和食物的问题。这道题是这样的：
  * 从前有个Animal类，其中有个eat方法，可以用来吃东西。
  * 问题是，如果从eat派生出一个类，比如Cow，那么就只能吃某一种食物，比如Grass。
  * Cow不可以吃Fish之类的其他食物。
  * 你希望有办法可以声明，Cow拥有一个eat方法，且该方法只能用来吃Grass，而不能吃其他东西。
  */


class Food

class Grass extends Food

class Fish extends Food

//编译出错
//abstract class Animal {
//  def eat(food: Food)
//}
//
//class Cow extends Animal {
//  override def eat(food: Grass) {}
//}


abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood): Unit
}

class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass):Unit = { println("cow eating")}
}


object AbstractType extends App {
  val cow = new Cow()
  cow.eat(new Grass)
  // 编译不通过
  //cow.eat(new Fish)
}
