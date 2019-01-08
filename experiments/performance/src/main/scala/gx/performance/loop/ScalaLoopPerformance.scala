package gx.performance.loop

object ScalaLoopPerformance {

  // For
  def forLoop(times: Long): Long = {
    var cnt: Long = 0
    for (i <- 0L to times) {
      cnt += 1
    }
    cnt
  }

  // While
  def whileLoop(times: Long): Long = {
    var cnt: Int = 0
    var i = 0
    while (i < times) {
      cnt += 1
      i += 1
    }
    cnt
  }

  // Reduce
  def reduceLoop(times: Long): Long = {
    (0L to times).foldLeft(0)((a, _) => a + 1)
  }


}
