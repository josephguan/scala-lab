package gx.stackoverflow

/** Question:
  * http://stackoverflow.com/questions/40149010/does-this-specific-exercise-lend-itself-well-to-a-functional-style-design-patt/40150083#40150083
  */
object answer001 extends App {
  val input = List(Map("abc" -> "123", "xy" -> "yz", "s12" -> "13"), Map("abc" -> "1", "s" -> "33"))
  val keys = input.flatMap(_.keys).toSet
  //  val keyvalues = input.map(kvs => keys.map(k => (k->kvs.getOrElse(k,""))).toMap)
  //  val values = keyvalues.map(_.values)
  val values = input.map(kvs => keys.toList.map(kvs.getOrElse(_, "")))
  val result = keys.mkString(",") + "\n" + values.map(_.mkString(",")).mkString("\n")
  println(result)
}
