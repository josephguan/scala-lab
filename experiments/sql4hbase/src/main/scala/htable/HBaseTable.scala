package htable

import java.lang._
import java.math.{BigDecimal, BigInteger}

/**
  * Created by 10051039 on 2017/5/12.
  */
abstract class HBaseTable {

  // helper functions
  def toInteger(row: List[String], index: Int, default: Integer = null): Integer = {
    try {
      new Integer(row(index))
    } catch {
      case e: Exception => default
    }
  }

  def toDouble(row: List[String], index: Int, default: Double = null): Double = {
    try {
      new Double(row(index))
    } catch {
      case e: Exception => default
    }
  }

  def toFloat(row: List[String], index: Int, default: Float = null): Float = {
    try {
      new Float(row(index))
    } catch {
      case e: Exception => default
    }
  }

  def toBigInteger(row: List[String], index: Int, default: BigInteger = null): BigInteger = {
    try {
      new BigInteger(row(index))
    } catch {
      case e: Exception => default
    }
  }

  def toBigDecimal(row: List[String], index: Int, default: BigDecimal = null): BigDecimal = {
    try {
      new BigDecimal(row(index))
    } catch {
      case e: Exception => default
    }
  }

  def toString(row: List[String], index: Int, default: String = ""): String = {
    try {
      row(index)
    } catch {
      case e: Exception => default
    }
  }


}
