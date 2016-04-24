//package com.hadooparchitecturebook.spark.dedup
package org.apache.spark.baul

import java.io.{OutputStreamWriter, BufferedWriter}
import java.util.Random

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}

/**
 * Created by ted.malaska on 12/6/14.
 */
object GenDedupInput {
  def main(args:Array[String]): Unit = {
    if (args.length == 0) {
      println("{outputPath} {numberOfRecords} {numberOfUniqueRecords}")
      return
    }

    //The output file that will hold the data
    val outputPath = new Path(args(0))
    //Number of records to be written to the file
    val numberOfRecords = args(1).toInt
    //Number of unique primary keys
    val numberOfUniqueRecords = args(2).toInt

    //Open fileSystem to HDFS
//    val fileSystem = FileSystem.get(new Configuration())
    var configuration = new Configuration()
//    var hdfscorepath  = new Path("/root/spark/baulindivd/src/main/scala/org/apache/spark/baul/core-site.xml")
    var hdfscorepath  = new Path("/usr/local/share/spark/baulindivd/src/main/scala/org/apache/spark/baul/core-site.xml")
//    var hdfssitepath  = new Path("/root/spark/baulindivd/src/main/scala/org/apache/spark/baul/hdfs-site.xml")
    var hdfssitepath  = new Path("/usr/local/share/spark/baulindivd/src/main/scala/org/apache/spark/baul/hdfs-site.xml")
//  FileSystem hdfsFileSystem = FileSystem.get(new URI("hdfs://sophnep:8020"),configuration)
//    var fileSystem = FileSystem.get(new URI("hdfs://sophnep/"),configuration)
    configuration.addResource(hdfscorepath)
    configuration.addResource(hdfssitepath)
    var fileSystem = FileSystem.get(configuration)
    //Create buffered writer
    val writer = new BufferedWriter(
      new OutputStreamWriter(fileSystem.create(outputPath)))

    val r = new Random()

    //This loop will write out all the record
    //  Every primary key will get about
    //  numberOfRecords/numberOfUniqueRecords records
    for (i <- 0 until numberOfRecords) {
      val uniqueId = r.nextInt(numberOfUniqueRecords)
      //Format: {key}, {timeStamp}, {value}
      writer.write(uniqueId + "," + i + "," + r.nextInt(10000))
      writer.newLine()
    }

    writer.close()
  }
}
