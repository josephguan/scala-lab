package gx.stackoverflow

import gx.stackoverflow.answer005._
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class answer005Test extends FlatSpec with Matchers with MockFactory {

  it should "mock" in {
    val loc = mock[Location]
    val dor: MyTrait = new MyTrait {
      override val location = loc
    }
    (loc.continuousFeatureValues _).expects().returning("good")
    dor.location.continuousFeatureValues shouldBe "good"
  }

}
