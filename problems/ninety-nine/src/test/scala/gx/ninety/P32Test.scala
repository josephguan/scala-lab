package gx.ninety

import gx.ninety.P32._
import org.scalatest.{FlatSpec, Matchers}

class P32Test extends FlatSpec with Matchers {

  it should "correct" in {
    gcd(36, 63) should be(9)
  }

}
