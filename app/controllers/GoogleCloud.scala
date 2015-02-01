package controllers

import play.api.mvc._
import play.api.data.Forms._
import play.api._
import play.api.Play
import play.api.data._
import play._
import play.api.Play._
import play.api.Routes
import models._
import play._
import play.api.i18n.Messages
import play.api.i18n.Lang
import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global
import concurrent.{ExecutionContext, Future}
import play.api.libs.ws.WS
import play.api.libs.json._
import play.api.mvc.MultipartFormData._

 
import com.freeneo.pushconnect._


object GoogleCloud extends Controller{

	def saveregId =  Action{ request =>
	
		println("saveregId")
		println(request.body)
		
		val regId = request.body.asFormUrlEncoded.get("regId").apply(0).toString()
		//.get(0).mkString
		GooglePushConnect.saveregId(regId)
		 
  	 	  Ok(Json.toJson("ok"))
  	 }

	

}

