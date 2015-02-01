package com.freeneo.security

import play.api.mvc._
import play.api.libs.ws.WS

object AsyncTest {

import play.api.libs.concurrent.Execution.Implicits._

	def someAsyncAction = Action.async {
  		import play.api.Play.current
  		WS.url("http://www.playframework.com").get().map { response =>
    // This code block is executed in the imported default execution context
    // which happens to be the same thread pool in which the outer block of
    // code in this action will be executed.
  		  println("code is working")
  		Results.Ok("The response code was " + response.status)
  		}
	}

}