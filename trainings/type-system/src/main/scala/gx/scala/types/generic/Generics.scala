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

package gx.scala.types.generic

// 泛型类
class Pair[A, B](val first: A, val second: B)


object GenericApp extends App {
  val p = new Pair(1, 2)
  println(p.first + ", " + p.second)

  val q = new Pair("good", 2)
  println(q.first + ", " + q.second)
  val r = changeSecond(p, "bye")

  // 泛型方法
  def changeSecond[A, B, T](p: Pair[A, B], second: T): Pair[A, T] = new Pair(p.first, second)
  println(r.first + ", " + r.second)

}

