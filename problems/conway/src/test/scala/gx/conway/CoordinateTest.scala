package gx.conway

import org.scalatest.{Matchers, FlatSpec}

class CoordinateTest extends FlatSpec with Matchers {

  it should "be valid coordinate" in {
    Coordinate(0, 0).isValid(5, 6) should be(true)
    Coordinate(5, 6).isValid(5, 6) should be(true)
    Coordinate(3, 2).isValid(5, 6) should be(true)
  }

  it should "be invalid coordinate" in {
    Coordinate(-1, 0).isValid(5, 6) should be(false)
    Coordinate(6, 6).isValid(5, 6) should be(false)
    Coordinate(5, 7).isValid(5, 6) should be(false)
  }

}
