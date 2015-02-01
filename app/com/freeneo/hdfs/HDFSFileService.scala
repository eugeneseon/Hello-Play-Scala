package com.freeneo.hdfs

import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import org.apache.hadoop.conf._
import org.apache.hadoop.fs._

import play.Logger

object HDFSFileService {
  private val conf = new Configuration()
  private val hdfsCoreSitePath = new Path("core-site.xml")
  private val hdfsHDFSSitePath = new Path("hdfs-site.xml")

  conf.addResource(hdfsCoreSitePath)
  conf.addResource(hdfsHDFSSitePath)

  private val fileSystem = FileSystem.get(conf)

  def saveFile(filepath: String): Unit = {
    Logger.info("saveFile method is called")
    val file = new File(filepath)
    Logger.info("file" + file.length().toString())
    
    val filetest = fileSystem.exists(new Path(file.getName)) 
    Logger.info("filetest" + filetest)
    val out = fileSystem.create(new Path(file.getName))
     Logger.info("out" + out.toString())
    val in = new BufferedInputStream(new FileInputStream(file))
    var b = new Array[Byte](1024)
    var numBytes = in.read(b)
    while (numBytes > 0) {
      out.write(b, 0, numBytes)
      numBytes = in.read(b)
    }
    in.close()
    out.close()
  }

  def removeFile(filename: String): Boolean = {
    val path = new Path(filename)
    fileSystem.delete(path, true)
  }

  def readFile(filename: String) = {
  //  val path = new Path(filename)
   // fileSystem.open(path)
    
     val file = new File(filename)
     val url = new Path(file.getName)
    
      val hdfs = new HDFSFileRead()
      hdfs.read(url)
      
    
    
  }

  def createFolder(folderPath: String): Unit = {
    val path = new Path(folderPath)
    if (!fileSystem.exists(path)) {
      fileSystem.mkdirs(path)
    }
  }
}