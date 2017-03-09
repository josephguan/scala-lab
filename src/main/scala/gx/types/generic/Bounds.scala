package gx.types.generic


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
