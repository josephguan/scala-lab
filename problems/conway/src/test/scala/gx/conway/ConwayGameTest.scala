package gx.conway

import org.scalatest.{Matchers, FlatSpec}
import java.io.File

class ConwayGameTest extends FlatSpec with Matchers {

  it should "output result file" in {
    ConwayGame.play("./problems/conway/src/test/resources/testdata01.txt", "./testresult01.txt")
    val file = new File("./testresult01.txt")
    file.exists() should be (true)
    file.delete()
  }
}
