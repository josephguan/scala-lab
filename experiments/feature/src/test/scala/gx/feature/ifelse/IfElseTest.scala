package gx.feature.ifelse

import gx.feature.ifelse.IfElse._
import org.scalatest.{FlatSpec, Matchers}

class IfElseTest extends FlatSpec with Matchers {

  it should "correct" in {
    (1 > 0) ? "good" | "bad" should be("good")
    (1 < 0) ? 1 | 2 should be(2)
  }

}
