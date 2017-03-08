package gx.types.traits

import scala.collection.mutable.ArrayBuffer


abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}


class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get() = buf.remove(0)

  def put(x: Int) {
    buf += x
  }
}


trait Doubling extends IntQueue {
  // 你必须对这种方法打上 abstract override 的标志。
  // 这样是为了告诉编译器你的目的：特质必须被混入到某个具有期待方法的具体定义的类中。
  abstract override def put(x: Int) {
    super.put(2 * x)
  }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {
    super.put(x + 1)
  }
}


trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}


object StackableApp extends App {

  // 基本类的行为
  val queue = new BasicIntQueue
  queue.put(10)
  queue.put(20)
  println(queue.get())

  // 放入队列时加倍
  val queue2 = new BasicIntQueue with Doubling
  queue.put(10)
  queue.put(20)
  println(queue.get())

  // 有一个队列能够即过滤负数又对每个进队列的数字增量
  val queue3 = new BasicIntQueue with Doubling with Filtering with Incrementing


}
