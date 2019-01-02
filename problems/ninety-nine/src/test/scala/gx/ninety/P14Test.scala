package gx.ninety

import gx.ninety.P14.duplicate
import org.scalatest.{FlatSpec, Matchers}

class P14Test extends FlatSpec with Matchers {

  it should "correct" in {
    duplicate(List('a, 'b, 'c, 'c, 'd)) should be(List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }

}
