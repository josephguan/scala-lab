package gx.leetcode

object LongestSubstringWithoutRepeatingCharacters extends App {

  /**
    * Solution 1:
    * Runtime: 644 ms
    */
  object Solution_1 {
    def lengthOfLongestSubstring(s: String): Int = {
      var max = 0
      for (i <- 0 until s.length) {
        val len = lengthOfStartSubstring(s.substring(i))
        if (len > max) max = len
      }
      max
    }

    def lengthOfStartSubstring(s: String): Int = {
      for (i <- 1 until s.length; j <- 0 until i) {
        if (s(i) == s(j)) return i
      }
      s.length
    }

  }

  /** Solution 2:
    * Runtime: 396 ms, faster than 95.28%
    */
  object Solution_2 {

    import java.util

    def lengthOfLongestSubstring(s: String): Int = {
      val dict = new util.HashMap[Char, Int]()

      var head = -1
      var max = 0
      for (i <- 0 until s.length) {
        val dup = dict.getOrDefault(s(i), -1)
        if (dup != -1 && dup > head) head = dup
        val len = i - head
        if (len > max) max = len
        dict.put(s(i), i)
      }
      max
    }

  }

  /** Solution 3:
    * Runtime: 396 ms, faster than 95.28%
    */
  object Solution_3 {

    import java.util

    def lengthOfLongestSubstring(s: String): Int = {
      val dict = new util.HashMap[Char, Int]()

      var head = -1
      var max = 0
      var i = 0
      val slen = s.length
      while (i < slen) {
        val dup = dict.getOrDefault(s(i), -1)
        if (dup != -1 && dup > head) head = dup
        val len = i - head
        if (len > max) max = len
        dict.put(s(i), i)
        i += 1
      }
      max
    }

  }


  //test
  println(Solution_1.lengthOfLongestSubstring(""))
  println(Solution_1.lengthOfLongestSubstring(" "))
  println(Solution_1.lengthOfLongestSubstring("abcdbsadfasf"))
  println(Solution_1.lengthOfLongestSubstring("dweafhasdffdfsdfgsdfgdfhgfjghjghjkljkljh;kjlkl'jk'jk;jkftjrtrncvmhjlgdfgsdf"))

  //test
  println(Solution_2.lengthOfLongestSubstring(""))
  println(Solution_2.lengthOfLongestSubstring(" "))
  println(Solution_2.lengthOfLongestSubstring("abcdbsadfasf"))
  println(Solution_2.lengthOfLongestSubstring("dweafhasdffdfsdfgsdfgdfhgfjghjghjkljkljh;kjlkl'jk'jk;jkftjrtrncvmhjlgdfgsdf"))


  //test
  println(Solution_3.lengthOfLongestSubstring(""))
  println(Solution_3.lengthOfLongestSubstring(" "))
  println(Solution_3.lengthOfLongestSubstring("abcdbsadfasf"))
  println(Solution_3.lengthOfLongestSubstring("dweafhasdffdfsdfgsdfgdfhgfjghjghjkljkljh;kjlkl'jk'jk;jkftjrtrncvmhjlgdfgsdf"))

}
