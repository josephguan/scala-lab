package gx.conway

import java.io.FileWriter

/**
 * 康威生命游戏： 给定一个输入文件，创建一个康威游戏实例，
 * 并按照游戏规则，输出最后的稳定状态（存活用"$"表示，死亡用空格表示）
 *
 */
object ConwayGame extends Using {

  def play(inputFile: String, outputFile: String): Unit = {
    val conway = new Conway(inputFile)
    val result = convertOutputFormat(conway.evolutionResult.toString)
    write(outputFile, result)
  }

  private def convertOutputFormat(str: String): String = {
    str.replace("1", "$").replace("0", " ")
  }

  private def write(outputFile: String, str: String): Unit = {
    using(new FileWriter(outputFile)) {
      writer =>
        writer.write(str)
    }
  }

}
