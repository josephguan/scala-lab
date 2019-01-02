package gx.conway

/**
 * 用来实现一个康威游戏的全过程， 包含演进过程和最终结果
 */
class Conway(file: String) {

  private val newBorn = Life(file)

  private val evolutionStream: Stream[Life] = Stream.cons(newBorn, evolutionStream.map(x => x.evolve))

  def evolutionsSteps: List[Life] = evolutionStream.takeWhile(_.isUnstable).toList

  def evolutionResult: Life = evolutionStream(evolutionsSteps.length)

  def evolutions: List[Life] = evolutionsSteps ::: List(evolutionResult)

}
