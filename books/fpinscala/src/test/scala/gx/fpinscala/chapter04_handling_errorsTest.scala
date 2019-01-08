package gx.fpinscala

import org.scalatest.{FlatSpec, Matchers}

import scala.{Either => _, Option => _}

/**
  * Created by 10051039 on 2016/8/29.
  */
class chapter04_handling_errorsTest extends FlatSpec with Matchers {

  val me = chapter04_handling_errors

  def makeOption(x: Int): me.Option[Int] = if (x < 10) me.Some(x) else me.None

  def makeEither(x: Int): me.Either[Int, Double] = if (x < 10) me.Left(x) else me.Right(x.toDouble)

  it should "use option method correctly with Some" in {
    val some = makeOption(1)
    some.map(x => x * 2) should be(me.Some(2))
    some.flatMap(x => me.Some(x * 2)) should be(me.Some(2))
    some.getOrElse("bad") should be(1)
    some.orElse(me.None) should be(me.Some(1))
    some.filter(x => x > 100) should be(me.None)
  }

  it should "use option method correctly with None" in {
    val none = makeOption(20)
    none.map(x => x * 2) should be(me.None)
    none.flatMap(x => me.Some(x * 2)) should be(me.None)
    none.getOrElse("bad") should be("bad")
    none.orElse(me.Some(8)) should be(me.Some(8))
    none.filter(x => x > 100) should be(me.None)
  }

  it should "get the variance" in {
    me.variance(Seq(1, 2, 3, 4, 5)) should be(me.Some(2.0))
  }

  it should "lift a function" in {
    def twice(x: Int): Double = x * 2.0
    val value = makeOption(2)
    me.lift(twice)(value) should be(me.Some(4.0))
  }

  it should "test on map2" in {
    me.map2(makeOption(2), makeOption(4))((a, b) => a * b) should be(me.Some(8))
    me.map2(makeOption(2), makeOption(40))((a, b) => a * b) should be(me.None)
  }

  it should "test bothMatch_2" in {
    me.bothMatch_2(".*Joe.*", ".*Hello.*", "Hello, Joe!") should be(me.Some(true))
    me.bothMatch_2(".*Joe.*", ".*Hello.*", "Hello, Jojo!") should be(me.Some(false))
  }

  it should "test sequence" in {
    me.sequence(List(makeOption(2), makeOption(4))) should be(me.Some(List(2, 4)))
    me.sequence(List(makeOption(2), makeOption(40))) should be(me.None)
  }

  it should "test traverse" in {
    me.traverse(List(1, 2, 3, 4))(x => makeOption(x * 2)) should be(me.Some(List(2, 4, 6, 8)))
    me.traverse(List(1, 2, 30, 4))(x => makeOption(x * 2)) should be(me.None)
  }

  it should "use either Right correctly" in {
    val right = makeEither(11)
    right.map(x => x * 2) should be(me.Right(22.0))
    right.flatMap(x => me.Left(x * 2)) should be(me.Left(22.0))
    right.orElse(me.Left(1)) should be(me.Right(11))
    right.map2(makeEither(20))((x: Double, y: Double) => x * y) should be(me.Right(220.0))
  }

  it should "use either Left correctly" in {
    val left = makeEither(1)
    left.map(x => x * 2) should be(me.Left(1))
    left.flatMap(x => me.Left(x * 2)) should be(me.Left(1))
    left.orElse(me.Left(2)) should be(me.Left(2))
    left.map2(makeEither(20))((x: Double, y: Double) => x * y) should be(me.Left(1))
  }

  it should "test either sequence and traverse" in {
    me.sequenceEither(List(makeEither(20), makeEither(40))) should be(me.Right(List(20.0, 40.0)))
    me.sequenceEither(List(makeEither(2), makeEither(4))) should be(me.Left(2))

    me.traverseEither(List(1, 2, 3, 4))(x => makeEither(x * 10)) should be(me.Right(List(10.0, 20.0, 30.0, 40.0)))
    me.traverseEither(List(10, 2, 3, 4))(x => makeEither(x * 2)) should be(me.Left(4))
  }
}
