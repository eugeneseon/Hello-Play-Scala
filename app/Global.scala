import play.api._
import play.api.mvc._
import play.api.mvc.Result
import play.api.mvc.Results._
import play.api.libs.json.Json
import scala.concurrent.{Future, ExecutionContext}
import play.api.{Application, GlobalSettings}
import akka.actor.{Actor, Props}
import akka.actor.ActorSystem
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Play.current
import scala.collection.JavaConversions._
import scala.concurrent.duration._


import java.io._

import java.nio.file.{Paths, Files}
import java.net.InetSocketAddress

import java.util._
import java.util.concurrent.TimeUnit
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.Seconds.secondsBetween

import org.apache.hadoop.hbase.client._

import akka.persistence.hbase.journal._


//import com.freeneo.spark._
import com.freeneo.logprocess._
import com.freeneo.managers._

object AccessLoggingFilter extends Filter {
  
  val accessLogger = Logger("access")
  
  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val resultFuture = next(request)
    
    resultFuture.foreach(result => {
      val msg = s"method=${request.method} uri=${request.uri} remote-address=${request.remoteAddress}" +
        s" status=${result.header.status}";
      accessLogger.info(msg)
    })
    
    resultFuture
  }
}


object Global extends WithFilters(AccessLoggingFilter)   {


    
	override def onError(request: RequestHeader, ex: Throwable) = {
	     println(ex.printStackTrace())
	  
		 Future.successful(InternalServerError("Oops"
	    		
	    ))
	  } 
    
    override def onHandlerNotFound(request: RequestHeader): Future[Result] = {
     Future.successful(NotFound("not found"))
    }

	
	
	override def onStart(app: Application) {
	  
	
  //  play.api.Logger.info("Scheduling job...")
     Logger.info("Application has started")
     
     println("TcpEcho starting...........")
     
    
    

    
  class KeepaliveActor extends Actor {
    
  import play.api.libs.concurrent.Execution.Implicits.defaultContext
   
  def receive = {
    case "tick" => { 
    	
    		println("tick")
    		
    
    			} 	// case ends		  
  		}  // def ends
    } // class ends


  class LogcheckActor extends Actor {
    
  import play.api.libs.concurrent.Execution.Implicits.defaultContext
  
  def receive = {
    case "tick" => { 
    	
            println("save")
           
        
    //     implicit val system = ActorSystem("tcpclient")
     //        implicit val executor = system.dispatcher
          
            
      //      val client = system.actorOf(Props(new Client(new InetSocketAddress("localhost",9090), self)))
            
    	//	Logger.info("save")
    	//	val isExist = Files.exists(Paths.get("custom*"))
    		
    	//    for (file <- new java.io.File (".").listFiles;
    	//    		if (file.getName ().matches (".*\\.done"))) 
    	//    println (file)
    	    
    	    
    		
    	
    		
    			} 	// case ends		  
  		}  // def ends
    } // class ends
  
  
     val now = new DateTime()
     val plannedStart = new DateTime()
                .withDayOfWeek(DateTimeConstants.SUNDAY)
                .withHourOfDay(20)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0)

     Logger.info("plannedStart: " + plannedStart.toString())  
      Logger.info("now: " + now)  	      
     
     val nextRun =  if(now.isAfter(plannedStart))  plannedStart.plusWeeks(1)  
    		 		else plannedStart
                
       Logger.info("nextRun: " + nextRun.toString())  		 		
   
     val nextRunInSeconds = (secondsBetween(now, nextRun).getSeconds()).toLong
    
     Logger.info("nextRunInSeconds: " + nextRunInSeconds.toString) 
   
     
    val keepaliveActor = Akka.system.actorOf(Props[KeepaliveActor], name = "keepaliveActor")
    Akka.system.scheduler.schedule(0.microsecond, 60.second, keepaliveActor, "tick")
    
    
import scala.concurrent.duration._
    /*
   Akka.system.scheduler.schedule(
                Duration.create(nextRunInSeconds, TimeUnit.SECONDS),
                Duration.create(1000, TimeUnit.SECONDS) ,
                testActor, "tick"
        )
        
        */
        
    val logcheckActor = Akka.system.actorOf(Props[LogcheckActor], name = "logcheckActor")
   Akka.system.scheduler.schedule(0.microsecond, 60.second, logcheckActor, "tick")
    
//   Akka.system.scheduler.schedule(0.microsecond,  Duration.create(60, TimeUnit.SECONDS) , logcheckActor, "save")

    EmbeddedFlume.startup
   
    } //onStart ends
	
	 override def onStop(app: Application) {
	
	     Akka.system.shutdown()
	    Akka.system.awaitTermination(3.seconds)
		    Logger.info("Application shutdown...")
		 //    app.global.onStop(app)
	 }
	
	 
}
	
 
/**
 * Actor for the given warehouse, which the Akka scheduler sends messages to.
 */



