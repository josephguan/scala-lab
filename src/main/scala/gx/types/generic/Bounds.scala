package gx.types.generic

class NumberSet {
  def isAllPos(result: Boolean): Boolean = result
}

class IntSet extends NumberSet

class EmptySet extends IntSet


object Bounds extends App {

  def assertAllPos(x: NumberSet): NumberSet = {
    if (x.isInstanceOf[EmptySet]) return x
    if (x.isAllPos(true)) x
    else throw new Exception("not all pos")
  }


  println(assertAllPos(new IntSet()))

}
