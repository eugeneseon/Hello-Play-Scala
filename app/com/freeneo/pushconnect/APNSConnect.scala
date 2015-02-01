package com.freeneo.pushconnect

import play.api._
import com.notnoop.apns._


object APNSConnect {
  def testAPNSPush {
    Logger.debug(" APNS Starting...")
    val APNSCertPath = "testmockCert" // "/home/neelkanth/Desktop/cert.p12"
    val APNSCertPassword = ""
    val service = APNS.newService.withCert(APNSCertPath, APNSCertPassword).withSandboxDestination.build
     Logger.debug("Build The Service.........")
  
    val payload = APNS.newPayload.alertBody("Hello From Neelkanth Sachdeva").badge(1).sound("default").customField("name", "neel").build
      Logger.debug("Build The Payload.........")
 
    val token = "<<<<<<<<<<<<<<<<<<<<<<---Device Token--->>>>>>>>>>>>>>>>>>>>>>"
    val notif = service.push(token, payload)
   
  }
}