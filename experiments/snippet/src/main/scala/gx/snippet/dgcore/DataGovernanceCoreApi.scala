package gx.snippet.dgcore

import gx.snippet.dgcore.FieldType.FieldType
import gx.snippet.dgcore.KpiType.KpiType
import gx.snippet.dgcore.ValueType.ValueType

/** ***************************************
  * 数据治理枚举常量定义
  * ***************************************/
object FieldType extends Enumeration {
  type FieldType = Value
  val Term = Value(0)
  val Dimension = Value(1)
  val Kpi = Value(2)
}

object KpiType extends Enumeration {
  type KpiType = Value
  val BaseKpi = Value(0)
  val ComposeKpi = Value(1)
}

object ValueType extends Enumeration {
  type ValueType = Value
  val Continuous = Value(0)
  val Discrete = Value(1)
}


/** ***************************************
  * 数据治理核心业务模型定义
  * ***************************************/
trait DataGovernanceModel

case class DgmId(id: Int, name: String, desc: String)

case class DgmDomain(domainId: DgmId, isLeaf: Boolean, level1Id: Int, level2Id: Int, level3Id: Int) extends DataGovernanceModel

case class DgmKpi(kpiId: DgmId, groupId: DgmId, kpiType: KpiType, kpiCode: String, statFunc: String,
                  qualifierId: DgmId, factField: String, factAlg: String, aggrNumerator: String, aggrDenominator: String,
                  aggrAlg: String, source: String, isDrill: Boolean, unitId: DgmId, digits: Int) extends DataGovernanceModel

case class DgmQualifier(qualifierId: DgmId, domainId: DgmId, qualifierAlg: String, source: String) extends DataGovernanceModel

case class DgmTable(tableId: DgmId, domainId: DgmId, tableServiceName: String, layerId: DgmId, cateId: DgmId,
                    timeGrain: DgmId, tag: DgmId, owner: String, createTime: String, modifyTime: String,
                    fields: List[DgmField]) extends DataGovernanceModel

case class DgmField(fieldId: DgmId, tableId: DgmId, fieldServiceName: String, fieldIndex: Int,
                    dataType: String, dataLength: Int, isNull: Boolean, isPrimary: Boolean, unitId: DgmId,
                    fieldType: FieldType, fieldTypeId: DgmId, tag: DgmId) extends DataGovernanceModel

case class DgmTerm(termId: DgmId, groupId: DgmId, termFullName: String, termAbbr: String, valueType: ValueType,
                   valueMax: Double, valueMin: Double, valueList: List[Double], isPrivacy: Boolean, unitId: DgmId) extends DataGovernanceModel

case class DgmUnit(unitId: DgmId) extends DataGovernanceModel

case class DgmDimension(dimId: DgmId, groupId: DgmId) extends DataGovernanceModel

case class DgmCategory(cateId: DgmId) extends DataGovernanceModel

case class DgmLayer(layerId: DgmId) extends DataGovernanceModel

case class DgmKpiGroup(groupId: DgmId, domainId: DgmId) extends DataGovernanceModel

case class DgmDimensionGroup(groupId: DgmId) extends DataGovernanceModel

case class DgmTermGroup(groupId: DgmId) extends DataGovernanceModel


/** ***************************************
  * 数据治理核心数据操作相关API定义
  * ***************************************/
trait CommonDataApi {
  def findById[T <: DataGovernanceModel](id: Int)(implicit m: Manifest[T]): Option[T] = {
    // example
    //    m.runtimeClass.getSimpleName match {
    //      case "DGTable" => Some(DGTable().asInstanceOf[T])
    //      case x => println(x); None
    //    }
    None
  }

  def findAll[T <: DataGovernanceModel](implicit m: Manifest[T]): List[T] = ???

  def findAllByDomainId[T <: DataGovernanceModel](domainId: Int)(implicit m: Manifest[T]): List[T] = ???

  def create[T <: DataGovernanceModel](t: T): DgmId = ???

  def delete[T <: DataGovernanceModel](id: Int)(implicit m: Manifest[T]): Boolean = ???

  def update[T <: DataGovernanceModel](t: T): DgmId = ???
}

/** ***************************************
  * 数据治理核心业务逻辑操作相关API定义
  * ***************************************/
trait CommonServiceApi {

  def genSql(table: DgmTable): DgmTable = ???

  def genKpiAlg(kpi: DgmKpi): DgmKpi = ???

  def genAdmaTaskXml(table: DgmTable): String /*xml.Node*/ = ???

  def genAdmaAlgXml(table: DgmTable): String /*xml.Node*/ = ???

  def genAdmaTableSummary(table: DgmTable): String /*xml.Node*/ = ???

  def genAdmaTableDetail(table: DgmTable): String /*xml.Node*/ = ???

}

/** ***************************************
  * Core最终对外提供的API
  * ***************************************/
object CoreApi extends CommonDataApi with CommonServiceApi


/** ***************************************
  * 操作返回的异常值列表及说明
  * ***************************************/

class CreateFailureException extends Exception

class DeleteFailureException extends Exception

class UpdateFailureException extends Exception

class RecordDuplicatedException extends CreateFailureException

class RecordIncompleteExcpetion extends CreateFailureException
