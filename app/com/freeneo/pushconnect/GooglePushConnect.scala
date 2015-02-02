package com.freeneo.pushconnect

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
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import concurrent.{ExecutionContext, Future}
import play.api.libs.ws.WS
import play.api.libs.json._
import play.api.mvc.MultipartFormData._


import org.joda.time.Period
import org.joda.time.DateTime
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import com.fasterxml.jackson.databind.ObjectMapper
import java.util._
import java.io.FileReader
import java.net._
import java.io._
 
import com.google.android.gms.gcm.GoogleCloudMessaging

import com.google.api.client.auth.oauth2.TokenResponseException
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpResponse
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.gson.Gson


import com.google.gdata.util._


object GooglePushConnect{

	def saveregId(s: String) =  //Action{ request =>
	
	{
		println("saveregId")
	
	
	val regId = s
	//.get(0).mkString
	
	println("regId---------------" + regId)
	 
    // 1. URL
    
    val url = new URL("https://android.googleapis.com/gcm/send")
 
    val conn: HttpURLConnection = url.openConnection.asInstanceOf[HttpURLConnection]
    
    val serverkey = "your server key"
//       AIzaSyCq1kO3y6XxQXOgl4VieT3bSPZIouEdxso
    
    val apiKey = serverkey

    var pushDatas =  new java.util.HashMap[String,Any]
    var regids = new java.util.ArrayList[String]
    regids.add(regId)
    var randomkey =  new DateTime().getMillis().toString()
 
//    var title = randomkey + "message"
    
    		 var pushMessages = new java.util.HashMap [String, Any]
    var title = ""
   
    		 val randomno = Random.nextInt(2)
    				 println("randomno----------"+randomno)
    if (randomno == 0) {
    	 title = "2 New Messages"
        pushMessages.put("message", "2 new messages. Check your Messages. thanks")
          pushMessages.put("type", "comments")
           pushDatas.put("collapse_key", "message")
           
        } else {
        	title = "2 New Profiles"		 
        pushMessages.put("message", "2 new Profiles. Please check. thanks.")
             pushMessages.put("type", "profile")
           pushDatas.put("collapse_key", "profile")
        }
    
    pushDatas.put("registration_ids", regids)
     pushDatas.put("message", "my awesome application message");
    pushDatas.put("time_to_live", 172800)
    pushDatas.put("delay_while_idle", true)
    pushMessages.put("title", title)    
    pushMessages.put("soundname","beep.mp3")
 //   pushMessages.put("msgcnt","0")
    
    pushDatas.put("data", pushMessages)

    println("messagetitle" + title)
    
//      var body = Json.toJson(pushDatas)
    var mapper = new ObjectMapper()
    var body = mapper.writeValueAsString(pushDatas)
    
    println(body)
    val bytes = body.getBytes()      
      
    conn.setDoOutput(true)
    conn.setUseCaches(false)
    conn.setFixedLengthStreamingMode(bytes.length)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8")
        //conn.setRequestProperty("Content-Length", String.valueOf(bytes.length))
    conn.setRequestProperty("Authorization", "key=" + apiKey)
 
 	var out = conn.getOutputStream()
   	out.write(bytes)
    out.close()

        val status = conn.getResponseCode()
        println("status" + status)

	var serverresponse = conn.getResponseMessage()
	println(serverresponse)
     
         
  	 //	  Ok(Json.toJson("test"))
		}
  	 }


