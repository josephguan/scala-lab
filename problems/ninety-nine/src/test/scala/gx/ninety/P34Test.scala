package gx.ninety

import gx.ninety.P34._
import org.scalatest.{FlatSpec, Matchers}

class P34Test extends FlatSpec with Matchers {

  it should "correct" in {
    10.totient should be(4)
    1.totient should be(1)
  }

  it should "correct for v2" in {
    10.totientV2 should be(4)
    1.totientV2 should be(1)
  }

}
