package gx.ninety

import gx.ninety.P31._
import org.scalatest.{FlatSpec, Matchers}

class P31Test extends FlatSpec with Matchers {

  it should "correct" in {
    12.isPrime should be(false)
    13.isPrime should be(true)
  }

}
