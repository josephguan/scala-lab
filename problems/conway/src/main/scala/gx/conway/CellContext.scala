package gx.conway

/**
 * 用来描述一个细胞和其周围的环境
 * 其中包含这个细胞在整个Life中的位置，以及其周围的细胞
 *
 */
case class CellContext(cellCoordinate: Coordinate, life: Life) {

  private val neighbors: List[Cell] = {
    val rangeX = (cellCoordinate.x - 1) to (cellCoordinate.x + 1)
    val rangeY = (cellCoordinate.y - 1) to (cellCoordinate.y + 1)
    val allCoordinates = rangeX flatMap (x => rangeY map (y => Coordinate(x,y)))
    val neighborCoordinates = allCoordinates filter (_ != cellCoordinate) filter (_.isValid(life.maxX, life.maxY))
    neighborCoordinates.toList map life.getCell
  }

  def evolve: Cell = {
    life.getCell(cellCoordinate) evolve neighbors.map(_.state).sum
  }

  def isUnstable: Boolean = {
    life.getCell(cellCoordinate) isUnstable neighbors.map(_.state).sum
  }

}
