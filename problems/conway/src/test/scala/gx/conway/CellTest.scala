package gx.conway

import org.scalatest.{FlatSpec, Matchers}

class CellTest extends FlatSpec with Matchers {

  it should "be dead while alive neighbors is less than 2 for alive cell" in {
    Cell(ALIVE).evolve(0).state should be(DEAD)
    Cell(ALIVE).evolve(1).state should be(DEAD)
  }

  it should "be alive while alive neighbors is 2 or 3 for alive cell" in {
    Cell(ALIVE).evolve(2).state should be(ALIVE)
    Cell(ALIVE).evolve(3).state should be(ALIVE)
  }

  it should "be alive while alive neighbors is greater than 3 for alive cell" in {
    Cell(ALIVE).evolve(4).state should be(DEAD)
    Cell(ALIVE).evolve(5).state should be(DEAD)
    Cell(ALIVE).evolve(6).state should be(DEAD)
    Cell(ALIVE).evolve(7).state should be(DEAD)
    Cell(ALIVE).evolve(8).state should be(DEAD)
  }

  it should "be alive while alive neighbors is equal to 3 for dead cell" in {
    Cell(DEAD).evolve(3).state should be(ALIVE)
    Cell(DEAD).evolve(2).state should be(DEAD)
    Cell(DEAD).evolve(4).state should be(DEAD)
  }

  it should "be unstable for dead cell" in {
    Cell(DEAD).isUnstable(3) should be(true)
  }

  it should "be stable for dead cell" in {
    Cell(DEAD).isUnstable(0) should be(false)
    Cell(DEAD).isUnstable(1) should be(false)
    Cell(DEAD).isUnstable(2) should be(false)
    Cell(DEAD).isUnstable(4) should be(false)
  }

  it should "be unstable for alive cell" in {
    Cell(ALIVE).isUnstable(0) should be(true)
    Cell(ALIVE).isUnstable(1) should be(true)
    Cell(ALIVE).isUnstable(4) should be(true)
    Cell(ALIVE).isUnstable(8) should be(true)
  }

  it should "be stable for alive cell" in {
    Cell(ALIVE).isUnstable(2) should be(false)
    Cell(ALIVE).isUnstable(3) should be(false)
  }

  it should "throw exception while state is incorrect" in {
    val thrown = intercept[IllegalArgumentException]{
      Cell(3)
    }
    thrown.getMessage should be ("requirement failed: state must 0 or 1. actual: 3")
  }

}
