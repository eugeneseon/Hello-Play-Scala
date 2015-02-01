package com.freeneo.security

import com.typesafe.config.ConfigFactory

object AccessToken {
  
   case class Config(tokentype: String)
   
   object Config {
    def load(): Config = {
      val raw = ConfigFactory.load("application").getConfig("accesstoken.config")
      Config(
       
        tokentype = raw.getString("tokentype")
      )
    }
  }
  
}
