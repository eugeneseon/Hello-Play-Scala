package com.freeneo.akka.persistence

import akka.actor._
import play.api._

import scala.concurrent.duration._



class PersistenceApplication extends Bootable {
 
      implicit val system = ActorSystem("play")
 

  def startup() = {
    
      val config = system.settings.config

      val pluginSettings = PersistencePluginSettings(config)  
       Logger.info("HBaseJournal Init create table....")
       HBaseJournalInit.createTable(config, pluginSettings.table, pluginSettings.family)
       HBaseJournalInit.createTable(config, pluginSettings.snapshotTable, pluginSettings.snapshotFamily)
       
       
       
       
       
     //  val persistentActor = system.actorOf(Props[ExamplePersistentActor], name = "persistenceActor")
      
    //   Logger.info("send Evt hello 2 times")
       
  
       
    //   persistentActor ! "tick"
       
     //  persistentActor ! "hello2"
       
  //  val config = SimpleEventSourced.Config.load()
 //   val server = system3.actorOf(Props(new TcpServer(config.bind, conn  => Props(new SmtpServer(conn, config)))))
  }

  def shutdown() = {
    Logger.info("shutdown....")
    system.shutdown()
    system.awaitTermination(3.seconds)
  }
}

object PersistenceApplication {

  def start{
    val app = new PersistenceApplication()
    app.startup()
  }
  
}

trait Bootable {
  def startup(): Unit
  def shutdown(): Unit

  sys.ShutdownHookThread(shutdown())
}
