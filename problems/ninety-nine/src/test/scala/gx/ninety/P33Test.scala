package gx.ninety

import gx.ninety.P33._
import org.scalatest.{FlatSpec, Matchers}

class P33Test extends FlatSpec with Matchers {

  it should "correct" in {
    35.isCoprimeTo(64) should be(true)
    36.isCoprimeTo(64) should be(false)
  }

}
