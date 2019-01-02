package gx.conway

/**
 * 用来描述一个细胞，每个细胞有ALIVE和DEAD两种状态，并且可以根据周围细胞的存活情况进行演进
 *
 */
case class Cell(state: State) {

  require(state == ALIVE || state == DEAD, s"state must 0 or 1. actual: $state")

  def evolve(aliveNeighbors: Int): Cell = Rule.statesRule(state)(aliveNeighbors)

  def isUnstable(aliveNeighbors: Int): Boolean = !Rule.stableRule(state)(aliveNeighbors)
}
