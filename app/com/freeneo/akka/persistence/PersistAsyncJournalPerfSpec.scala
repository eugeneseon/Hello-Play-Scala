package com.freeneo.akka.persistence

import java.util.Date
import java.util.concurrent.TimeUnit
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.PreparedStatement
import java.sql.Statement
import akka.actor._
import akka.persistence._
import akka.persistence.hbase.common.TestingEventProtocol.FinishedDeletes
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import com.google.common.base.Stopwatch
import org.apache.hadoop.hbase.client.{Scan, HTable}
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpecLike, Matchers}
import scala.collection.JavaConversions
import scala.concurrent.duration._
import play.api._
import com.typesafe.config.ConfigFactory
import org.joda.time.DateTime

object PersistAsyncJournalPerfSpec {
  
   implicit val system = ActorSystem("play")
  //  val config = system.settings.config
      
    var conf = ConfigFactory.load
     lazy val url = conf.getString("hbase.phoenix.location")
    
 def startup() = {
     
     
     
  //    val pluginSettings = PersistencePluginSettings(config)  
       Logger.info("HBaseJournal Init create table....")
       
        
 
          /** Seed locations for which URLs are valid, example ca, nj etc*/
         
          
          	val conn = DriverManager.getConnection(url) 

	
	      val ddl = "CREATE TABLE IF NOT EXISTS AKKAJOURNAL  (id VARCHAR not null,date VARCHAR, data VARCHAR CONSTRAINT my_pk PRIMARY KEY (id))"
          conn.createStatement().execute(ddl)
      
       
   //    HBaseJournalInit.createTable(config, pluginSettings.table, pluginSettings.family)
   //    HBaseJournalInit.createTable(config, pluginSettings.snapshotTable, pluginSettings.snapshotFamily)
     
         conn.commit()
   }
  
  def deliver(log: String) = {
      
         
       val writer = system.actorOf(Props(new Writer(1l,"myid")), name = "writer")
      
       Logger.info("send Evt hello 2 times")
           
    //   persistentActor ! "ask"
       
       writer ! log
       
       writer ! "ask"
  }
   
   def shutdown() = {
    Logger.info("shutdown....")
    system.shutdown()
    system.awaitTermination(3.seconds)
   }
  
  case class Cmd(data: String)
  case class Evt(data: String)
 
  case class ExampleState(events: List[String] = Nil) {
  def updated(evt: Evt): ExampleState = copy(evt.data :: events)
  def size: Int = events.length
  override def toString: String = events.reverse.toString
  }

  class Writer(untilSeqNr: Long, override val persistenceId: String) extends PersistentActor
    with ActorLogging {

    var lastPersisted: Any = _

    def receiveCommand = {
      case "ask" =>
        log.info("Got ASK command, lastPersisted = {}", lastPersisted)
        if (lastPersisted != null) {
          log.info("Replying with last persisted message {}", lastPersisted)
          sender() ! lastPersisted
        }

      case "delete" =>
        log.info("Deleting messages in {}, until {}", persistenceId, lastSequenceNr)
        deleteMessages(toSequenceNr = lastSequenceNr)

      case "boom" =>
        throw new RuntimeException("Boom!")

      case payload: AnyRef => 
            val data = payload.toString()
            
            Logger.info(data)
            
            val now = new DateTime()
            
            val dml = "UPSERT INTO AKKAJOURNAL  (id, date, data) VALUES ('" + now + "','19721011','" + data+ "')"
            
             Logger.info(dml)
            val conn = DriverManager.getConnection(url) 
            
             Logger.info(conn.toString())
            
            val result = conn.createStatement().execute(dml)
            
            Logger.info(result.toString())
            
              conn.commit()
    
              handlePersisted(data) 
        
    //    persistAsync(payload)(handlePersisted)
    }

    override def receiveRecover: Receive = {
      case r: RecoveryCompleted =>
        context.system.eventStream.publish(r)

      case m: AnyRef =>
        // log.info("Recovered: {}", m)
        handlePersisted(m.toString())
 //       handlePersisted(m)
    }

 //   def handlePersisted(p: AnyRef): Unit = {
     def handlePersisted(p: AnyRef): Unit = {
       Logger.info("handlePersisted")
      if (!recoveryRunning) {
        Logger.info("recovery is ! running")
        // log.debug(s"persisted: {} @ {}", p, lastSequenceNr)
          Logger.info("p-$p")
        sender() ! s"p-$p"
      }

      lastPersisted = p
      
       Logger.info(lastPersisted.toString())
    }
  }

}


