package gx.performance.loop

import gx.performance.Timer

/**
  * 测试结论：
  * Scala while循环与Java的for循环的性能差不多，略慢一点。
  * Scala For循环很慢。
  * Scla Reduce比For还要慢一倍。
  */
object Main extends App {

  Timer.timing("Java For", JavaLoopPerformance.forLoop(800000000L))
  Timer.timing("Java While", JavaLoopPerformance.whileLoop(800000000L))

  Timer.timing("Scala For", ScalaLoopPerformance.forLoop(800000000L))
  Timer.timing("Scala While", ScalaLoopPerformance.whileLoop(800000000L))
  Timer.timing("Scala Reduce", ScalaLoopPerformance.reduceLoop(800000000L))

}
