package gx.sql4hbase

/**
  * 自定义HBase表字段处理函数集合
  */
class CustomerFunctions {
  def make_name(s: String): String = {
    s"hello $s"
  }

}
