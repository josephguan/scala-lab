package htable

/**
  * 这个文件是工具自动生成的，请不要手动修改！
  */
class lte_r_capacity_sample(content: String) extends HBaseTable {
  private val _row_ = content.split(",").toList

  var cellkey = toString(_row_, 0)
  var regionid = toInteger(_row_, 1)
  var x_offset = toInteger(_row_, 2)
  var y_offset = toInteger(_row_, 3)
  var avgrsrp = toDouble(_row_, 4)
  var bigint = toBigInteger(_row_, 5)
  var float = toFloat(_row_, 6)

}
