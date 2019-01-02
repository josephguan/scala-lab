package gx.conway

import org.scalatest.{Matchers, FlatSpec}

class ConwayTest extends FlatSpec with Matchers {

  it should "evolutions as expect" in {
    val evolutions = new Conway("./problems/conway/src/test/resources/testdata01.txt").evolutions
    evolutions.length should be (3)
    evolutions.last.toString should be ("010\n101\n010")
  }

  it should "generate evolution result initData.txt input" in {
    val result = new Conway("./problems/conway/src/test/resources/initData.txt").evolutionResult
    result.isUnstable should be(false)
    result.toString should be("00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000110000011\n00000000000110000011\n00000000000000000000\n00000000000000000000\n11000000000000000000\n11000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000000000000\n00000000000011000000\n00000001100011000000\n00000001100000000000\n00000000000000000000")

  }
}
