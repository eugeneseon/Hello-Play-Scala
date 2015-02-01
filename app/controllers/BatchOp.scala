package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import com.freeneo.hdfs._


object BatchOp extends Controller  {
  
   def saveFile = Action{ implicit request =>
    
      Logger.info("save File")
       
       val testfileName = "C://sparkdata/donation/block_1/block_1.csv"
       
       HDFSFileService.saveFile(testfileName)
      
       Ok(Json.toJson("file saved"))
   }
   
    def removeFile = Action{ implicit request =>
    
      Logger.info("delete File")
       
       val testfileName = "block_1.csv"
       
       HDFSFileService.removeFile(testfileName)
      
       Ok(Json.toJson("file deleted"))
   }
    
     def readFile = Action{ implicit request =>
    
      println("read File")
       
    //   val testfileName = "block_1.csv"
      
       val testfileName =  "hdfs://localhost:8020/user/flume/flumeFileSink1C1H5-2015-01-30-09-28--.1422610097683.tmp";
       
       HDFSFileService.readFile(testfileName)
      
       Ok(Json.toJson("file readed"))
   }
}