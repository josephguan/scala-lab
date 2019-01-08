package gx.snippet.knapsack

import java.util.Date

import org.scalatest.{FlatSpec, Matchers}

class KnapsackTest extends FlatSpec with Matchers {

  it should "run all tasks" in {
    val knapsack = new Knapsack()
    val tasks = List(Task(2, 2, 3), Task(3, 3, 4), Task(4, 4, 5), Task(5, 5, 6))
    knapsack.pack(15, 15, tasks.toArray) should be(tasks)
  }

  it should "run 2 of tasks" in {
    val knapsack = new Knapsack()
    val tasks = List(Task(2, 2, 3), Task(3, 3, 4), Task(4, 4, 5), Task(5, 5, 6))
    knapsack.pack(8, 7, tasks.toArray) should be(List(Task(2, 2, 3), Task(5, 5, 6)))
  }

  it should "finish in 500ms for processing 1000 tasks" in {
    val knapsack = new Knapsack()
    val tasks = (1 to 1000).map(x => Task(1, 1, 10)).toList
    val duration = timing {
      knapsack.pack(100, 100, tasks.toArray)
    }
    duration < 500 should be(true)
  }

  it should "slower than java implementations" in {
    val tasks = (1 to 1000).map(x => Task(1, 1, 10)).toList

    val knapsackJava = new KnapsackJava()
    val java = timing {
      knapsackJava.pack2DBetter(100, 100, tasks.toArray)
    }

    val knapsackScala = new Knapsack()
    val scala = timing {
      knapsackScala.pack(100, 100, tasks.toArray)
    }

    println(s"$java(java) vs $scala(scala)")
    scala > java should be(true)
  }

  def timing[A](func: => A): Long = {
    val start = new Date()
    func
    val end = new Date()
    end.getTime - start.getTime
  }

}
