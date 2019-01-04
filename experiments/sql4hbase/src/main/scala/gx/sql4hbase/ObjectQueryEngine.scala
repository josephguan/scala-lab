package gx.sql4hbase

import org.josql.Query

//import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class HBaseQueryException(msg: String, cause: Throwable) extends Exception(msg, cause)

object ObjectQueryEngine {

  private val tablePrefix = "htable"

  def query(tablename: String, dataset: List[String], sql: String): List[List[String]] = {
    val c = Class.forName(s"$tablePrefix.$tablename")
    val constructor = c.getConstructor(classOf[String])
    val rs = query(dataset.map(x => constructor.newInstance(x)), sql)
    rs.map(row => row.map(x => x.toString))
  }

  def query[T](dataset: List[T], sql: String): List[List[Any]] = {
    try {
      val query = new Query()
      query.addFunctionHandler(new CustomerFunctions())
      val processedSql = addTablePrefix(sql)
      query.parse(processedSql)
      val results = query.execute(dataset.to[scala.collection.mutable.ListBuffer].asJava)
      val queryResults = results.getResults
      val groupbyResults = results.getGroupByResults

      if (groupbyResults == null) {
        queryResults.asScala.toList.map(o => o.asInstanceOf[java.util.ArrayList[Any]].asScala.toList)
      } else {
        groupbyResults.asScala.toList.map {
          case (k, v) =>
            v.asInstanceOf[java.util.ArrayList[Object]].get(0).asInstanceOf[java.util.ArrayList[Any]].asScala.toList
        }
      }
    }
    catch {
      case e: Exception =>
        throw new HBaseQueryException(s"HBase Query Failed! Please check your sql. ${e.getMessage}", e)
    } finally {
      // do nothing
      Nil
    }
  }

  private def addTablePrefix(sql: String): String = {
    val words = sql.split(" ").filter(_ != "")
    val from = words.map(_.toLowerCase).indexOf("from")
    val table = words(from + 1)
    var sss: String = ""
    if (!table.startsWith(tablePrefix)) {
      sql.replaceAll(table, s"$tablePrefix.$table")
    } else {
      sql
    }
  }

}
