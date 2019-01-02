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

case class Food(name: String)

case class Recipe(name: String, compose: List[Food])

class BigModule {

  // Fruits
  object Pear extends Food("Pear")

  object Apple extends Food("Apple")

  object Banana extends Food("Banana")

  def usedFruits: List[Food] = List(Apple, Pear)

  // salad recipe
  object FruitSalad extends Recipe("FruitSalad", usedFruits)

  def saladRecipes: List[Recipe] = List(FruitSalad)

  def showRecipes(): Unit = saladRecipes.foreach(println)
}

//=========================================
// splitting modules into traits
//=========================================
trait Fruits {

  object Pear extends Food("Pear")

  object Apple extends Food("Apple")

  object Banana extends Food("Banana")

  def usedFruits: List[Food]
}

trait SaladRecipe {
  self: Fruits =>

  object FruitSalad extends Recipe("FruitSalad", usedFruits)

  def saladRecipes: List[Recipe] = List(FruitSalad)

  def showRecipes(): Unit = saladRecipes.foreach(println)
}

class MyApp extends Fruits with SaladRecipe {
  override def usedFruits: List[Food] = List(Apple, Pear)
}

object SelfTypeApp extends App {
  new MyApp().showRecipes()
}
