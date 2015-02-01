package com.freeneo.phoenix


import play.api.Logger

import com.typesafe.config.ConfigFactory

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.PreparedStatement
import java.sql.Statement

object Phoenix  {
  
 //   case class User(id: String, password: String, name: String, documents: Option[List[Document]])
    
 //   case class Document(name: String, contents: String)
    
   var conf = ConfigFactory.load
 
	/** Seed locations for which URLs are valid, example ca, nj etc*/
   lazy val url = conf.getString("hbase.phoenix.location")
    
    
        
    def test(user: controllers.User) = {
	    Logger.info("Phoenix.test")
	    println("Phoenix.test" + user)
	    
	    val dml2 = "UPSERT INTO Users (id, date, name) VALUES('" + user.id + "','" + user.name + "','" + user.age + "')"
	    
	    println(dml2)
	    
	    val conn = DriverManager.getConnection(url) 
	println(conn)
	
	  val result2 = conn.createStatement().execute(dml2)
           	Logger.info(result2.toString())
        conn.commit()
	  	
	/*
	
	
		 val ddl2 = "DROP TABLE Users" 
          conn.createStatement().execute(ddl2)
	
	      val ddl = "CREATE TABLE IF NOT EXISTS Users (id VARCHAR not null,date VARCHAR, name VARCHAR CONSTRAINT my_pk PRIMARY KEY (id))"
          conn.createStatement().execute(ddl)
          val dml = "UPSERT INTO Users (id, date, name) VALUES('2','19721011','선종철')"
           val result = conn.createStatement().execute(dml)
           	Logger.info(result.toString())
           
              val dml2 = "UPSERT INTO Users (id, date, name) VALUES('3','19721011','선종철')"
           conn.createStatement().execute(dml2)
           
             val result2 = conn.createStatement().execute(dml)
           	Logger.info(result2.toString())
        conn.commit()
		*/
	
	}
	
	def select :String= {
	    Logger.info("Phoenix.selet")
	  //	val rset = new ResultSet
	val conn = DriverManager.getConnection(url) 
	println(conn)
	
	    var selectresult = "" 
	    
	 
	    val dml = "select * from users"
	    
	    val rset = conn.createStatement().executeQuery(dml)
     
		while (rset.next()) {
			println(rset.getString("id"))
			selectresult = rset.getString("name")
			println(selectresult)
		}
		
		conn.commit()
		
		return selectresult
		
	}
}