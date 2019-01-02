package gx.ninety

import gx.ninety.P16.drop
import org.scalatest.{FlatSpec, Matchers}

class P16Test extends FlatSpec with Matchers {

  it should "correct" in {
    val res = drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res should be(List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
  }

}
