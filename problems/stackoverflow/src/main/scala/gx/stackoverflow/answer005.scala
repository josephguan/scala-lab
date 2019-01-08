package gx.stackoverflow

/** Question:
  * http://stackoverflow.com/questions/40239959/stub-a-val-of-a-trait-with-scalamock
  */
object answer005 {

  class Location {
    def continuousFeatureValues: String = "location"
  }

  class MyClass() {
    val location: Location = new Location
  }

  class MyTrait extends MyClass

}
