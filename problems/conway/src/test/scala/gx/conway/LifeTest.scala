package gx.conway

import org.scalatest.{Matchers, FlatSpec}

class LifeTest extends FlatSpec with Matchers {

  it should "evolve as expect" in {
    val input = List(
      List(1, 1, 1),
      List(1, 0, 1),
      List(0, 1, 1))

    val life = new Life(input.map(_.map(Cell)))
    life.isUnstable should be (true)

    val firstEvolution = life.evolve
    firstEvolution.toString should be("101\n100\n011")
    firstEvolution.isUnstable should be (true)

    val secondEvolution = firstEvolution.evolve
    secondEvolution.toString should be("010\n101\n010")
    secondEvolution.isUnstable should be (false)
  }

  it should "initialize with file" in {
    val life = Life("./problems/conway/src/test/resources/testdata01.txt")
    life.evolve.toString should be("101\n100\n011")
  }

  it should "throw exception while read bad file" in {
    val thrown = intercept[IllegalArgumentException]{
      Life("./problems/conway/src/test/resources/testdata02.txt")
    }
    thrown.getMessage should be ("requirement failed: wrong length for line 2. expect: 3, actual: 2")
  }

  it should "throw exception while file is not exist" in {
    val thrown = intercept[IllegalArgumentException]{
      Life("./testdata03.txt")
    }
    thrown.getMessage should be ("requirement failed: ./testdata03.txt is not exist.")
  }

}
