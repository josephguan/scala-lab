/*
 * Copyright (c) 2018 josephguan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package gx.snippet.knapsack

/**
  * Task定义
  */
case class Task(core: Int, memory: Int, value: Int)

/**
  * BigBit
  * 用来存放选择了哪些任务
  */
class BigBit(max: Int) {
  val length: Int = getIndex(max) + 1
  val bits = new Array[Long](length)

  def set(other: BigBit, n: Int): Unit = {
    copy(other)
    setBit(n)
  }

  def toList: List[Int] = {
    def toBitList(num: Long): List[Boolean] = {
      (0 to 63).map(x => ((1L << x) & num) != 0).toList
    }
    bits.flatMap(toBitList).zipWithIndex.filter(_._1).map(_._2).toList
  }

  def setBit(n: Int): Unit = {
    val i = getIndex(n)
    bits(i) = bits(i) | (1L << getBit(n))
  }

  def copy(other: BigBit): Unit = {
    other.bits.copyToArray(bits)
  }

  private def getIndex(n: Int): Int = n / 64

  private def getBit(n: Int): Int = n % 64
}


/**
  * 二维背包问题算法
  * 设optimal[i][v][u]表示前i件物品付出两种代价分别为v和u时可获得的最大价值。状态转移方程就是：
  * optimal[i][v][u]=max{optimal[i-1][v][u],f[i-1][v-a[i]][u-b[i]]+w[i]}
  */
class Knapsack {

  def pack(maxCore: Int, maxMemory: Int, tasks: Array[Task]): List[Task] = {

    // facilities for getting something from task(x)
    def c(x: Int): Int = tasks(x - 1).core
    def m(x: Int): Int = tasks(x - 1).memory
    def v(x: Int): Int = tasks(x - 1).value

    // init
    val number = tasks.length
    val optimal = Array.ofDim[Int](maxCore + 1, maxMemory + 1)
    val choices = Array.ofDim[BigBit](maxCore + 1, maxMemory + 1)
    for (j <- 0.to(maxCore); k <- 0.to(maxMemory)) choices(j)(k) = new BigBit(number)

    // algorithm
    for (i <- 1.to(number); j <- maxCore.to(0, -1); k <- maxMemory.to(0, -1)) {
      if (j - c(i) >= 0 && k - m(i) >= 0 && optimal(j)(k) <= optimal(j - c(i))(k - m(i)) + v(i)) {
        optimal(j)(k) = optimal(j - c(i))(k - m(i)) + v(i)
        choices(j)(k).set(choices(j - c(i))(k - m(i)), i - 1)

      }
    }

    // get result
    val choiceList = choices(maxCore)(maxMemory).toList
    choiceList.map(i => tasks(i))

  }

}
