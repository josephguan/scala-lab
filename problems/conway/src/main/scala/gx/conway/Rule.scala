package gx.conway

/**
 * 描述了细胞状态转换的规则和判断细胞是否稳定的规则
 */
object Rule {
  private val deadEvolveRule = Cell(DEAD) #:: Cell(DEAD) #:: Cell(DEAD) #:: Cell(ALIVE) #:: Stream.continually(Cell(DEAD))
  private val aliveEvolveRule = Cell(DEAD) #:: Cell(DEAD) #:: Cell(ALIVE) #:: Cell(ALIVE) #:: Stream.continually(Cell(DEAD))
  val statesRule = List(deadEvolveRule, aliveEvolveRule)

  private val deadStableRule: Stream[Boolean] = true #:: true #:: true #:: false #:: Stream.continually(true)
  private val aliveStableRule: Stream[Boolean] =  false #:: false #:: true #:: true #:: Stream.continually(false)
  val stableRule = List(deadStableRule, aliveStableRule)
}
