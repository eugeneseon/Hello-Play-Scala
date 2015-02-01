package controllers

import play.api.mvc._
import play.api.data.Forms._
import play.api._
import play.api.data._
import play._
import play.api.Play._
import play.api.i18n.Messages
import scala.collection.JavaConversions._
import org.joda.time.DateTime

import java.util.Arrays.asList
import java.util.HashMap
import play.api.libs.iteratee.Enumerator
import play.api.libs.json
import play.api.libs.json.Json
import play.api.libs.json.JsObject 
import play.api.libs.json.JsValue 
import play.api.libs.json.JsValue 
import play.api.libs.json.JsString 

import models._
import com.freeneo.parser.{StringConverter}



object Auth extends Controller { 

	 val loginForm = Form(
    tuple(
    "username" -> text.verifying(Messages("error.email.required"), {_.matches( """(.*)@(.*)""")}),
     "password" -> text.verifying(Messages("error.email.required"), {_.length > 7})
    ) 
 //     verifying ("Invalid email or password", result =>
 //     result match {
 //     case (email, password) => println("case and gose to check")
 //               check(email, password)
 //     	}
  //   )
	)
  
	 /*
	def authenticate = Action { implicit request =>
			println("authenticate")
		loginForm.bindFromRequest.fold(
				formWithErrors => BadRequest(views.html.login(formWithErrors)),
				user => {
				  
				     try {
				    println("def check (user)" + user._1 + user._2)
				    
				    val hashuser = StringConverter.makemd5((user._1))
				    val hashpw = StringConverter.makemd5(user._2)
				    println(hashpw + hashuser)
				    
				    
				    
				    var ulist =  new java.util.LinkedHashMap[String, String]
				    ulist.put("key",hashuser)
				    ulist.put("pwdate",null)
				    ulist.put("password",hashpw)
				    ulist.put("secondkey",null)
				   
				   var result = List[String]()
				    
				   
				    val userdao = new UsersDAO()
			//	    val result = userdao.getUser(ulist,"auth").toList
				    result = userdao.authUser(ulist,"auth").toList
				     
				  				    
				    println("authUser:" + result)
				    
				    if(result.length == 3) {
				    
				    val secondkey = result.apply(2)  
				      
				    val userdao2 = new UsersDAO()
				    
				    val ulist2 =  new java.util.LinkedHashMap[String, String]
				    ulist2.put("key",secondkey)
				    ulist2.put("registerstep",null)
				   
				    println("result.length is 3")
				    
				    val javaList = userdao2.getUser(ulist2,"info")
				    javaList.remove(0)
				    
				    val doublecheck = javaList.toList
				    
				    println("doublecheck:" + doublecheck)
				    
				    if (doublecheck.length != 0) {
				    			if (doublecheck.apply(0) == "111") {
				    					val finalsession = hashuser + "basic" + secondkey
				    					println("user register step is not 111")
				    					Redirect(routes.Application.index).withSession(Security.username -> finalsession)
				    					}
				    
				    				else {
				    				  val finalsession = hashuser + "basic" + secondkey
				    					Redirect(routes.Users.getUserSta).withSession(Security.username -> finalsession)
				    			//		Ok("ok")
				    					} // the third if/else clause
				    
				    	} else  {
				    	val finalsession = hashuser + "basic" + secondkey
				    	Redirect(routes.Users.getUserSta).withSession(Security.username -> finalsession)
				    	  //	Ok("ok")
				    	}
				    	
				    }  else {
				      
				      val dummyForm = Form(ignored("dummy"))
  				 	 
				         	 println("the second if")
				      Ok(views.html.login(loginForm.fill(user._1,user._2).withGlobalError(Messages("user.login.error"))))
				    } // the first if/else clause				   
				 
				        } catch {
				       case ex: Exception =>{
   		  		   	    	    Ok(views.html.NetworkError())	
   		 		   	      	}
				        }
				     }
				)
	 	}
	 */
   
	
	 def authenticate = Action(parse.json) { request =>
	        println("authenticate")
	    
	        val userJson = request.body
	  //      val user = userJson.as[User]

	   		Unauthorized	
 }
				     
				
	  
		 
	

  def logout = Action {  implicit request =>
    println(request)
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
    //Unauthorized
  }
/*
  def checkstatus(luser : HashMap[String,String] ) = {
     val userdao = new UsersDAO()
     val user = userdao.getStatus(luser)
     println("checkstatus:" + user)
     user
  }
  */
  /*
  def check(username: String, password: String) = {
//    (username == "seon00@dreamwiz.com" && password == "seon00")
    println("check is called")
    val userdao = new UsersDAO()
    val user = userdao.getUser(username, password)
    println(user)
    (user)

  }
 
  */

  def login = Action { implicit request =>
    println("action login")
    Ok(Json.toJson("login, mock login required"))
 //    Ok("login")
  }
}
