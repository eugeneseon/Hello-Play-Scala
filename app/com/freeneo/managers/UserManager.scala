package com.freeneo.managers

import com.typesafe.config.ConfigFactory

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.PreparedStatement
import java.sql.Statement

import akka.actor.{ActorRef, ActorSystem, Props, Actor,OneForOneStrategy}
import akka.actor.UnhandledMessage
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

import play.api._
import play.api.libs.ws._
import play.api.libs.json._

//case class Multiplication(m:Int, n:Int)
//case class Division(m:Int, n:Int)
//case class Command(s: String) 
//case class Message(s: String) 
	
case class Delete(id: String)
case class User(id: String, password: String, name: String, documents: Option[List[Document]])
	//  case class User(id: String, password: String, name: String)
case class Document(name: String, contents: String)

case class Transaction(uniqueid: String, time: String, query: String)
   
    
object Phoenix  {
  
     var conf = ConfigFactory.load
 
	/** Seed locations for which URLs are valid, example ca, nj etc*/
   lazy val url = conf.getString("hbase.phoenix.location")
  
  def insertUser = {
		  	
		  	val conn = DriverManager.getConnection(url) 
		  	println(conn)
	
		  	val dml2 = "UPSERT INTO Users (id, date, name) VALUES('3','19721011','선종철')"
           conn.createStatement().execute(dml2)
        
        conn.commit()
	
  }
	
  
  
}


class UserPersist(js: JsValue) extends Actor {
  
   
  override def preStart { println("UserPersist: preStart") }
  
  override def postStop { println("User: postStop") }
  
  override def preRestart(reason: Throwable, message: Option[Any]) {
    println("User: preRestart")
    println(s"  MESSAGE: ${message.getOrElse("")}")
    println(s"  REASON: ${reason.getMessage}")
    super.preRestart(reason, message)
  }
  
  override def postRestart(reason: Throwable) {
    println("User: postRestart")
    println(s"  REASON: ${reason.getMessage}")
    super.postRestart(reason)
  }
 
	def receive = {
		 case u: controllers.User => 
		
		println("child received" + js)		
	   
		import play.api.Play.current
	    WS.url("http://localhost/register1").post(js)
		
		try {
		     sender() ! "saved" }
		catch {
		case e: Exception => sender() ! e
		}
		
		case Delete(s:String) =>
		try {
		println("user" + s.toString() + "deleted")
		    sender() ! "deleted"
		} catch {
		case e: Exception => sender() ! e
		}
	
	}
 }
 
class UserManager(js: JsValue) extends Actor {
	override val supervisorStrategy =
			OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
 
	case _: ArithmeticException => {
		println("child exception try restart")
		
	//	val child:ActorRef = context.actorOf(Props[Calculator], "child")
	//	 child ! 
	 //  Restart
		Resume
	//	Stop
		
		}
	}
 
	val child:ActorRef = context.actorOf(Props(new UserPersist(js)), "child")
	
	
	
	def receive = { case d: Delete => 
				 
	  println("UserManager receives" + d.toString())
	  child ! d 
	  case u: controllers.User => 
	     
	     Thread sleep 5000
	    
	     println("UserManager receives" + u)
	  child ! u
	  
	  case s:String => println(s)
	  
	  case e: Exception => e.printStackTrace()
	  			//		   sender() ! 

	}
 
}

class MessageHandler extends Actor{
 def receive = {
 case UnhandledMessage(message, sender, recipient) =>
 println("unhandled message: "+message);
 }
 }