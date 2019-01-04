package htable

class lte_netmaxl_nbi_calldrop_cell_hbase (content: String) extends HBaseTable {
  private val _row_ = content.split(",").toList

  var id= toString(_row_, 0)
  var imsi= toString(_row_, 1)
  var releaseenb= toString(_row_, 2)
  var releasecellid= toString(_row_, 3)
  var releasepci= toString(_row_, 4)
  var releaselon= toString(_row_, 5)
  var releaselat= toString(_row_, 6)
  var relaseresult= toString(_row_, 7)
  var releasefailreason= toString(_row_, 8)
  var releaseservrsrp= toString(_row_, 9)
  var releaseservrsrq= toString(_row_, 10)
  var callstarttime= toString(_row_, 11)
  var calldur= toString(_row_, 12)
  var releasetime= toString(_row_, 13)
  var causeclass= toString(_row_, 14)
  var causesubclass= toString(_row_, 15)
  var causedescription= toString(_row_, 16)
  var releaseregionid= toString(_row_, 17)
  var releasex_offset= toString(_row_, 18)
  var releasey_offset= toString(_row_, 18)
  var mmec= toString(_row_, 20)
  var mmeapid= toString(_row_, 21)
  var mmegroupid= toString(_row_, 22)
  var gid= toString(_row_, 23)
  var callrecordueid= toString(_row_, 24)
  var releaseerfcn= toString(_row_, 25)
  var ulsinr= toString(_row_, 26)
  var cqi= toString(_row_, 27)
  var ulbler= toString(_row_, 28)
  var dlbler= toString(_row_, 29)
  var dlpathloss= toString(_row_, 30)
  var dropedqci= toString(_row_, 31)

}
