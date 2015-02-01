package controllers

import play.api.mvc._
import play.api.data.Forms._
import play.api.Logger
import play.api.data._
import play._
import play.api.Play._
import play.api.Routes
import play.api.i18n.Messages
import play.api.i18n.Lang
import play.api.mvc.Results._ 
import play.api.mvc.Result
import play.api.http.MimeTypes

import play.api.libs.json.Json
import play.api.libs.json._

// import play.api.cache.Cache

import play.api.i18n.Messages
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConverters._

import models._
import com.freeneo.phoenix._
import com.freeneo.hdfs._
import com.freeneo.akka.persistence._
import com.freeneo.mailer._

object Application extends Controller with Secured{
   
  
   
  
   def index = //  withAuth { username => implicit request =>
      Action { implicit request => 

   //   PersistAsyncJournalPerfSpec.deliver  
        
       val userdir = System.getProperty("user.home")
        val customLogger = Logger("custom")
        customLogger.info("info")
       
       println("userdir is...." + userdir)
      println("index")
      
     
      MailSender.sendMail
      
      
   

   //   Hdfs.save
      
   //    Logger.info("jooq.listAll")
   //   val users = models.Users.listAll    
  //    if(users==null) users else users.apply(0) 
      
//       println(users.apply(0).getId())
       
    //   Logger.info("jooq.insertOne")
   //    val users2 = models.Users.findById(2,"orwell")
       
   //    Logger.info("findById" + users2.getPassword)
  
  //    Redirect(routes.Application.getHome())
//      Redirect(routes.Twits.list())
        Ok(views.html.Index())
		 }
   
   		
   /*
    def deleteTable = Action { implicit request => 
  
      println("deleteTable")
        import akka.persistence.hbase.journal._
      
    	HBaseAsyncJournalSpec.deletetable
      
         Ok(Json.toJson("deleted"))
//      Redirect(routes.Twits.list())
		 }
   
    
     def createTable = Action { implicit request => 
  
      println("createTable")
        import akka.persistence.hbase.journal._
      
    //	HBaseAsyncJournalSpec.createtable
      
         Ok(Json.toJson("created"))
//      Redirect(routes.Twits.list())
		 }
    */
   def getHome = Action { implicit request => 
//	    implicit val defaultLang = Lang.availables.headOption.getOrElse(Lang.defaultLang)
   //     Logger.info("Phoenix.test====================")  
   //   Phoenix.test
   //     Logger.info("Phoenix.select====================")  
  //  val selectresult = Phoenix.select
   //   println("한글 selectresult" + selectresult)
 //        Logger.info("한글"+selectresult)
	    
	   	     Ok(Json.toJson("한글"))
   		}
		 
}
	


	


trait Secured {

  def username(request: RequestHeader) = request.session.get(Security.username)
  
 def onUnauthorized(request: RequestHeader) = Unauthorized
 
 // Results.Redirect(routes.Auth.login)

         def withAuth(f: => String => Request[AnyContent] => Result) = {

    println("withAuth is called")

    Security.Authenticated(username, onUnauthorized) {
      user =>

     Action(request => {

          println(request.acceptLanguages.map(_.code).mkString(", "))
          val sessionRolloverPeriod = 10800
          val sessionExpiryTime = 86400

            println(sessionRolloverPeriod)
          val tsMayBe = request.session.get("ts")
           println(tsMayBe)
          
          tsMayBe match {
          		case Some(ts) =>
          	
         
          val sessionCTime: String = request.session.get("ts").get  
          println(sessionCTime)
            
          val sessionCreationTime = sessionCTime.toLong
          val currentTime = System.currentTimeMillis() / 1000L
          println("sessionCreationTime:"+sessionCreationTime)
          println("currentTime:"+currentTime)
          
          val ts = (System.currentTimeMillis() / 1000L).toString()
         

          if(currentTime <= (sessionCreationTime + sessionExpiryTime)) {
            if(currentTime >= (sessionCreationTime + sessionRolloverPeriod)) {
              f(user)(request).withSession(request.session + ("ts" -> ts ))
            } else {
              f(user)(request)
            }
          } else {
           Results.Redirect(routes.Auth.login()).withNewSession
          }           
             
          case None =>
            println("case None")
            val ts = (System.currentTimeMillis() / 1000L).toString()
          	 f(user)(request).withSession(request.session + ("ts" -> ts ))
          	}
        }
      )

 //     f(user)(request))
    }
  }

  /**
   * This method shows how you could wrap the withAuth method to also fetch your user
   * You will need to implement UserDAO.findOneByUsername
   */
   /*
   def withUser(f: User => Request[AnyContent] => Result) = withAuth { username => implicit request =>
    UserDAO.findOneByUsername(username).map { user =>
      f(user)(request)
    }.getOrElse(onUnauthorized(request))
  }
  */
}

 
