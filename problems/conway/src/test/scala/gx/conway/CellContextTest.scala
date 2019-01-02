package gx.conway


import org.scalatest.{FlatSpec, Matchers}

class CellContextTest extends FlatSpec with Matchers {

  it should "be evolve to dead" in {
    val input = List(
      List(1, 1, 1),
      List(1, 0, 1),
      List(0, 1, 1))

    val life = new Life(input.map(_.map(Cell)))
    CellContext(Coordinate(0, 1), life).evolve.state should be(DEAD)
  }

  it should "be unstable" in {
    val input = List(
      List(1, 1, 1),
      List(1, 0, 1),
      List(0, 1, 1))

    val life = new Life(input.map(_.map(Cell)))
    CellContext(Coordinate(0, 1), life).isUnstable should be(true)
  }
}
