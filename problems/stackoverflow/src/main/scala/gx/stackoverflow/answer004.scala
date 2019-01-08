package gx.stackoverflow

/** Question:
  * http://stackoverflow.com/questions/40209094/how-to-chain-operations-in-idiomatic-scala/40212397#40212397
  */
object answer004 extends App {

  val stopWords = List[String](
    "the",
    "restaurant",
    "bar",
    "[^a-zA-Z -]"
  )

  // original implement
  def CanonicalName(name: String): String = {
    var nameM = name
    for (reg <- stopWords) {
      nameM = nameM.replaceAll(reg, "")
    }

    nameM = nameM.replaceAll(" +", " ").trim
    return nameM
  }

  // answer implement
  def isStopWord(word: String): Boolean = stopWords.exists(word.matches)

  def CanonicalName2(name: String): String = {
    name.replaceAll(" +", " ").trim.split(" ").flatMap(n => if (isStopWord(n)) List() else List(n)).mkString(" ")
  }

  // test
  println(CanonicalName("the     thermal    &    barbecue restaurant star"))

  println(CanonicalName2("the    thermal    &    barbecue restaurant star"))


}
