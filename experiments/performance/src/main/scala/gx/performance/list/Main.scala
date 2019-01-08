package gx.performance.list

import gx.performance.Timer

object Main extends App {

  /**
    * 结论：
    * 1. List先prepend再reverse，与ListBuffer.append性能差不多；
    * 2. List直接用:::的方式append，奇慢无比
    *
    */

  val ls = List.fill(1000000)(1)
  val lsLite = ls.take(10000)
  Timer.timing("List Prepend Then Reverse", AppendElement.listPrependThenReverse(ls))
  Timer.timing("ListBuffer Append", AppendElement.listBufferAppend(ls))
  Timer.timing("List Append", AppendElement.listAppend(lsLite))

  Timer.timing("List Prepend Then Reverse 2", AppendElement.listPrependThenReverse2(1000000))
  Timer.timing("ListBuffer Append 2", AppendElement.listBufferAppend2(1000000))

}
