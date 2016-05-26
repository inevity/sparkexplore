package com.databricks.apps.logs

//case class ApacheAccessLog(ipAddress: String, clientIdentd: String,
//                          userId: String, dateTime: String, method: String,
//                          endpoint: String, protocol: String,
//                           responseCode: Int, contentSize: Long) {
//
//}

case class SquidAccessLog(dateTime: String, responseTime: Int,
                         clientAddress: String, squidStatus: String, httpStatus: Int,
                         contentSize: Long, method: String, url: String, 
                         userName: String, hierarchyStatus: String, serverIP: String, 
                         mimeType:String, referer: String, userAgent: String,
                         cookie: String, forwardIP: String, range: String,
                         sasasa: String, localIP: String) {

}
object SquidAccessLog {
  /** 
   *val PATTERN = """^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] "(\S+) (\S+) (\S+)" (\d{3}) (\d+)""".r
   */
 // val PATTERN = """^(\S+)\s+(\d+)\s+(\S+)\s+(\w+)\/(\d{3})\s+(\d+)\s+(\S+)\s+(\S+)\s+(\S+)\s+(\w)+\/(\S+)\s+(\S)+\s+"(\S+)"\s+"(\S+)"\s+(\S+)\s+(\S+)\s(\S+)\s+(\S)+\s+(\S+)""".r
//  val PATTERN = """^(\S+)\s+(\d+)\s+(\S+)\s+(\w+)\/(\d{3})\s+(\d+)\s+(\S+)\s+(\S+)\s+(\S+)\s+(\w+)\/(\S+)\s+(\S+)\s+"(\S+)"\s+"(\S+)"\s+(\S+)\s+(\S+)\s(\S+)\s+(\S)+\s+(\S+)""".r

  val PATTERN = """^(\S+)\s+(\d+)\s+(\S+)\s+(\w+)\/(\d+)\s+(\d+)\s+(\S+)\s+(\S+)\s+(\S+)\s+(\w+)\/(\S+)\s+(\S+)\s+"(\S+)"\s+("[^"]*")\s+(\S+)\s+(\S+)\s+(\S+)\s+(\S+)\s+(\S+)""".r

  //def parseLogLine(log: String): ApacheAccessLog = {
  def parseLogLine(log: String): SquidAccessLog = {
    val res = PATTERN.findFirstMatchIn(log)
    if (res.isEmpty) {
      throw new RuntimeException("Cannot parse log line: " + log)
    }
    val m = res.get
//todo: what is up when deal with the first 16 matched case?
//    if (m.group(16) == null) {
//    SquidAccessLog(m.group(1), m.group(2).toInt, m.group(3), m.group(4),
//      m.group(5).toInt, m.group(6).toLong, m.group(7), m.group(8), m.group(9), m.group(10),
//      m.group(11), m.group(12), m.group(13), m.group(14), m.group(15), 0,
//      0, 0, 0)
//  } else 
   SquidAccessLog(m.group(1), m.group(2).toInt, m.group(3), m.group(4),
      m.group(5).toInt, m.group(6).toLong, m.group(7), m.group(8), m.group(9), m.group(10),
      m.group(11), m.group(12), m.group(13), m.group(14), m.group(15), m.group(16),
      m.group(17), m.group(18), m.group(19))
  }
}

