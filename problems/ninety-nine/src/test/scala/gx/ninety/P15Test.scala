package gx.ninety

import gx.ninety.P15.duplicateN
import org.scalatest.{FlatSpec, Matchers}

class P15Test extends FlatSpec with Matchers {

  it should "correct" in {
    duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should be(
      List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
  }


}
