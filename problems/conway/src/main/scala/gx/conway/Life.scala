package gx.conway

import java.io.File

import scala.io.Source

/**
  * 用来描述一个生命体， 生命体中包含了一个二维的细胞列表，生命体可以进行演进。
  *
  */
class Life(cells: List[List[Cell]]) {

  val maxX: Int = cells.length - 1
  val maxY: Int = cells.head.length - 1

  sanityCheck()

  // 注：此处case是用来模式匹配的，只为提高可读性，不属于switch case 范畴。
  val cellContexts: List[List[CellContext]] = cells.zipWithIndex map {
    case (row, x) => row.zipWithIndex map {
      case (item, y) => CellContext(Coordinate(x, y), this)
    }
  }

  def getCell(coordinate: Coordinate): Cell = {
    require(coordinate.x >= 0 && coordinate.x <= maxX, s"x = ${coordinate.x} is out of range")
    require(coordinate.y >= 0 && coordinate.y <= maxY, s"y = ${coordinate.y} is out of range")
    cells(coordinate.x)(coordinate.y)
  }

  def evolve: Life = {
    new Life(cellContexts.map(_.map(_.evolve)))
  }

  def isUnstable: Boolean = {
    // cellContexts.map(_.exists(_.isUnstable)).exists(_ == true)
    cellContexts.exists(_.exists(_.isUnstable))
  }

  override def toString: String = {
    cells.map(_.map(_.state).mkString("")).mkString("\n")
  }

  private def sanityCheck(): Unit = {
    cells.zipWithIndex foreach {
      case (line, index) =>
        require(line.length == maxY + 1,
          s"wrong length for line ${index + 1}. expect: ${maxY + 1}, actual: ${line.length}")
    }
  }
}

object Life extends Using {

  def apply(inputFile: String): Life = {
    require(new File(inputFile).exists(), s"$inputFile is not exist.")

    val cellList = using(Source.fromFile(inputFile)) {
      file =>
        file.getLines().toList map {
          line => line.toCharArray.toList map (char => Cell(char.toString.toInt))
        }
    }
    new Life(cellList)
  }
}

