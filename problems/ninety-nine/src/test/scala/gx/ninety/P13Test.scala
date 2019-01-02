package gx.ninety

import gx.ninety.P13.encodeDirect
import org.scalatest.{FlatSpec, Matchers}

class P13Test extends FlatSpec with Matchers {

  it should "correct" in {
    val res = encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res should be(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  }

}
