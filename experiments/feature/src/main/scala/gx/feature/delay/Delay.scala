package gx.feature.delay

/**
  * 有了函数式的支持，实现一个lazy value是很简单的事。
  */
class Delay[A](v: => A) {
  private var isFirstRun = true
  private var value: A = _

  def compute(): A = {
    if (isFirstRun) {
      value = v
      isFirstRun = false
    }
    value
  }
}

object Delay {
  def apply[A](v: => A): Delay[A] = new Delay(v)

  def delay[A](v: => A): () => A = { () => v }

  def force[A](dv: () => A): A = dv()

}


