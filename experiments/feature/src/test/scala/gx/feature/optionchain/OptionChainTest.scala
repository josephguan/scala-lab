package gx.feature.optionchain

import gx.feature.optionchain.OptionChain._
import org.scalatest.{FlatSpec, Matchers}

class OptionChainTest extends FlatSpec with Matchers {

  it should "correct" in {
    getEmailQuery("mysql", "joe") should be("joe@gmail.com")
    getEmailQuery("postgre", "joe") should be("null")
    getEmailQuery("mysql", "bobby") should be("null")
  }

  it should "correct in v2" in {
    getEmailQueryV2("mysql", "joe") should be("joe@gmail.com")
    getEmailQueryV2("postgre", "joe") should be("null")
    getEmailQueryV2("mysql", "bobby") should be("null")
  }

}
