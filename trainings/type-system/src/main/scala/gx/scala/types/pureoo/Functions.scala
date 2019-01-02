/*
 * Copyright (c) 2019 josephguan
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
 */

package gx.scala.types.pureoo

/**
  * Scala是一个纯面向对象的语言吗？
  *
  * 一个纯面向对象的语言，意味着它的所有值都是一个对象—— type of each value is a class.
  * 对于一般语言来说，第一反应是，可能难免有些例外，比如原生类型、函数。
  *
  * 让我们看看Scala的函数是否是一个对象？
  * 实际上，函数类型A=>B就是scala.Function1[A,B]的缩写
  */
object Functions extends App {

  val f = (x: Int) => x * x

  // 上面的匿名函数实际被展开为：
  val g = {
    class AnonFunc extends Function1[Int, Int] {
      def apply(x: Int) = x * x
    }
    new AnonFunc
  }

  // 把展开简化一下：
  val h = new Function1[Int, Int] {
    def apply(x: Int) = x * x
  }

  // 函数f的调用，就等同于h.apply方法的调用
  println(f(2))
  println(g(2))
  println(h.apply(2))

  /**
    * 思考：def f(x: Int): Int 方法 是不是一个函数值对象呢？
    *
    * A：不是。如果是的话，调用apply方法时会发生什么？
    * 但是，当f用在一个期望是Function类型的地方时，它会被自动转换为一个function value：
    * (x: Int) => f(x)
    */



}
