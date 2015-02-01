package controllers

//import play._
import play.Routes
import play.api.Play._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._
//import models._
import play.api.i18n.Messages
import play.api.data.format.Formatter
import play.api.data.format.Formats._
import scala.util.control.Exception
import scala.util.Try
import scala.util.Random
import concurrent.{ExecutionContext, Future}
import play.api.libs.ws._
import scala.collection.JavaConverters._
import scala.util.matching.Regex
import scala.collection.mutable
import play.api.libs.iteratee.Enumerator
import play.api.libs.concurrent.Akka
import play.api.mvc.MultipartFormData._
import play.api.libs.Files.TemporaryFile
import play.api.libs.iteratee.{Cont, Done, Input, Iteratee}
import play.api.libs.iteratee.Concurrent
import scala.collection.JavaConversions._

import com.github.tototoshi.play2.json4s.native._
import org.json4s._
import org.json4s.native.JsonMethods._

import play.api.libs.functional.syntax._
import play.api.libs.json._

import org.imgscalr.Scalr
import com.sksamuel.scrimage.{Image,Format}
import com.sksamuel.scrimage.filter.{ OffsetFilter, BorderFilter }
import javax.imageio.ImageIO
import java.awt.{ Color, Graphics2D }
import java.io.File
import java.io.IOException
import org.apache.hadoop.hbase.util.Bytes
import java.awt.image.BufferedImage
import org.joda.time.DateTime
import org.joda.time.format._
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.util.Date
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter
import org.joda.time.Period


import play.api.libs.concurrent.Akka
import akka.actor._
import com.freeneo.managers._
import com.freeneo.phoenix.Phoenix


import akka.actor._
import javax.inject._

case class User(id: Int, name: String, age: Int)
  

object User extends Controller  with Secured with Json4s{   
  
  implicit val formats = DefaultFormats
	
  	def getUserSta = withAuth { username => implicit request =>
	
    //		Action { implicit request =>            
    	
    		println("getUserSta2")	
   
  			Ok("ok")
	      }  // Action end
  		
  	
  	 def register1 =  Action(parse.json) { implicit request =>	
  //	   Action { implicit request =>	
    	 implicit val userReads = Json.reads[User]

  	   val jacek: User = (request.body).as[User]

       println(jacek)
    	  
  
       
     	Ok("ok")	    			
         	
    			    
    		}   // Action ends
  	 
  	def register2 = Action(parse.json) { implicit request =>
  	  
  	  
  	   println("request body" + request.body)
 
  	   implicit val userReads = Json.reads[User]

  	   val jacek: User = (request.body).as[User]

       println(jacek)
  	   
  	   import play.api.libs.concurrent.Execution.Implicits.defaultContext
  	  
  	   
  	   val futureUnit: Future[Unit] = scala.concurrent.Future {
    	   			Phoenix.test(jacek)
       	}
  	   
  	   futureUnit match {
  	     case (u: scala.concurrent.Future[Unit]) => {
  	       
  	        println("success and ws")
  	       
  	         implicit val system = ActorSystem("transaction")
             implicit val executor = system.dispatcher
          
            
            val manager = system.actorOf(Props(new UserManager(request.body)))
            
            manager ! jacek
            
            
  	        
  	   //     UserManager.transcation(request.body)
  	      
  	  //     WS.url("http://localhost/register1").post(request.body)
       //     println("send ok")
  	  //     	Ok("ok")
   
  	       	  	 Ok(Json.obj("status" ->"OK", "message" -> ("User '"+jacek.name+"' saved.") ))  
  	      
  	     }
  	     case (e: Exception) => 	Ok("error")
  	   }
  	   
	    
		}   // Action ends
  	
}
