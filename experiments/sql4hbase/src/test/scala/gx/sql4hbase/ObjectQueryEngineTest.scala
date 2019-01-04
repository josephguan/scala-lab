package gx.sql4hbase

import org.scalatest.{FlatSpec, Matchers}

class ObjectQueryEngineTest extends FlatSpec with Matchers {

  val data = List(
    "aaa,1,1,1,11.11,1.1,1234567",
    "bbb,2,3,4,51.61,7.1,2234567",
    "aaa,3,3,3,41.51,6.1,9234567",
    "aaa,2,3,3,21.41,1.1,1234567"
  )

  it should "query with where and order by" in {
    val rs = ObjectQueryEngine.query("lte_r_capacity_sample", data,
      "SELECT cellkey, regionid, avgrsrp FROM lte_r_capacity_sample where cellkey='aaa' order by avgrsrp"
    )

    rs should be(List(List("aaa", "1", "11.11"), List("aaa", "2", "21.41"), List("aaa", "3", "41.51")))

  }

  it should "order by correctly without 'where' clause" in {
    val rs = ObjectQueryEngine.query("lte_r_capacity_sample", data,
      "SELECT cellkey, regionid, avgrsrp FROM lte_r_capacity_sample order by avgrsrp"
    )

    rs should be(List(
      List("aaa", "1", "11.11"),
      List("aaa", "2", "21.41"),
      List("aaa", "3", "41.51"),
      List("bbb", "2", "51.61")))
  }

  it should "group by correctly" in {
    val rs = ObjectQueryEngine.query("lte_r_capacity_sample", data,
      "SELECT cellkey, @avgLength from lte_r_capacity_sample " +
        "GROUP BY cellkey " +
        "group by order cellkey " +
        "EXECUTE ON GROUP_BY_RESULTS avg (:_allobjs, avgrsrp) AS avgLength"
    )

    rs should be(List(
      List("bbb", "51.61"),
      List("aaa", "24.676666666666666")))
  }

  it should "call function correctly" in {
    val rs = ObjectQueryEngine.query("lte_r_capacity_sample", data,
      "SELECT upper(cellkey), to_string(x_offset * 12 / 7), avgrsrp * 2 " +
        "FROM lte_r_capacity_sample " +
        "WHERE cellkey = 'aaa' " +
        "ORDER BY avgrsrp desc"
    )

    rs should be(List(
      List("AAA", "5.142857142857142", "83.02"),
      List("AAA", "5.142857142857142", "42.82"),
      List("AAA", "1.7142857142857142", "22.22")))
  }

  it should "call user defined function correctly" in {
    val rs = ObjectQueryEngine.query("lte_r_capacity_sample", data,
      "SELECT make_name(cellkey), avgrsrp " +
        "FROM lte_r_capacity_sample " +
        "WHERE avgrsrp < 430 " +
        "ORDER BY avgrsrp desc"
    )

    println(rs)
    rs should be(List(
      List("hello bbb", "51.61"),
      List("hello aaa", "41.51"),
      List("hello aaa", "21.41"),
      List("hello aaa", "11.11")))
  }


}
