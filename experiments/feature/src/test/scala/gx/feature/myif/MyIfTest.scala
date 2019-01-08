package gx.feature.myif

import java.io.ByteArrayOutputStream

import gx.feature.myif.MyIf._
import org.scalatest.{FlatSpec, Matchers}

class MyIfTest extends FlatSpec with Matchers {

  it should "correct" in {
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      // test myif control statement
      myif(1 + 1 != 2) {
        println("I am in myif")
      } myelse {
        myif(1 > 2) {
          println("I am in else if")
        } myelse {
          println("I am in else else")
        }
      }
    }
    stream.toString should be ("I am in else else\r\n")

  }

}
