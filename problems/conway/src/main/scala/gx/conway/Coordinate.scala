package gx.conway

/**
 * 坐标：用来描述一个Cell在Life中的位置
 *
 */
case class Coordinate(x: Int, y: Int) {

  def isValid(maxX: Int, maxY: Int): Boolean = {
    (x >= 0) && (x <= maxX) && (y >= 0) && (y <= maxY)
  }

}
