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

trait Closable {
  def close(): Unit
}

class MyFile extends Closable {
  override def close(): Unit = println("close myfile")
  def write(): Unit = println("write myfile")
}

class MyDB extends Closable {
  override def close(): Unit = println("close my database")
  def query(): Unit = println("query my database")
}


object BoundsApp extends App {

  // Upper bound
  def using[R <: Closable, T](resource: => R)(f: R => T) = {
    try f(resource) finally resource.close()
  }

  using(new MyFile) {
    file =>
      file.write()
  }

  using(new MyDB) {
    db =>
      db.query()
  }


  // 问题： 如果写MyFile库的人只写了close方法，但是没有继承Closable接口怎么办？





}
