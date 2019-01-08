package gx.stackoverflow

/** Question:
  * http://stackoverflow.com/questions/40199115/initialize-several-objects-at-once/40199899#40199899
  */
object answer003 extends App {

  class Mark

  var markA: Mark = _
  var markB: Mark = _
  var markC: Mark = _
  var markD: Mark = _

  // declare
  //  var marks:Array[Mark] = (1 to 4) map (x=>null) toArray
  var marks = Array.fill[Mark](4)(null)

  // initialize
  marks = marks map (x => new Mark)

  // test
  marks(0) = null
  marks foreach println

}
