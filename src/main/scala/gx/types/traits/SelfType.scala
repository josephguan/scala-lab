package gx.types.traits

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
  def saladRecipes:List[Recipe] = List(FruitSalad)
  def showRecipes() = saladRecipes.foreach(println)
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
  def saladRecipes:List[Recipe] = List(FruitSalad)
  def showRecipes() = saladRecipes.foreach(println)
}

class MyApp extends Fruits with SaladRecipe {
  override def usedFruits: List[Food] = List(Apple, Pear)
}

object SelfTypeApp extends App {
  new MyApp().showRecipes()
}
