package com.freeneo.akka.persistence

import play.api.libs.concurrent.Akka

import akka.actor._
import akka.persistence._
import akka.actor.{Actor, Props}

import play.api._

import com.typesafe.config.ConfigFactory


object SimpleEventSourced {
   implicit val system = ActorSystem("application")
  
    import play.api.Play.current
    
   
    
        def startup = {
    		
    				
   
      
    	}
    
		def send(data: Cmd) = {
		    
            Logger.info("send step 1")
            
            try {
                Logger.info("send step 2")
    		val userPersistentActor = system.actorOf(Props(new UserPersistentActor()),"userPsersitentActor")
         Logger.info(userPersistentActor.toString())
    		//  userPersistentActor ! data
                
             //   val userPersistentActor = Akka.system.actorSelection("user/userPersistenctActor")
                userPersistentActor ! data
                
            } catch {
               case e: Exception =>  e.printStackTrace()
            }
    		 
    		
		   
    	}
    
    case class Cmd(data: String)
case class Evt(data: String)
 
case class ExampleState(events: List[String] = Nil) {
  def updated(evt: Evt): ExampleState = { copy(evt.data :: events)}
  def size: Int = events.length
  override def toString: String = events.reverse.toString
	}
 
class UserPersistentActor extends PersistentActor {
  
    override def persistenceId = "sample-id-1"
 
  var state = ExampleState()
 
  def updateState(event: Evt): Unit =
    state = state.updated(event)
 
  def numEvents =
    state.size
 
  val receiveRecover: Receive = {
    case evt: Evt                                 => updateState(evt)
    case SnapshotOffer(_, snapshot: ExampleState) => state = snapshot
  }
 
  val receiveCommand: Receive = {
    case Cmd(data) =>
      
      Logger.info("receiveCommand")
      Logger.info(Evt(s"${data}-${numEvents}").toString())
        Logger.info(Evt(s"${data}-${numEvents + 1}").toString())
      
      persist(Evt(s"${data}-${numEvents}"))(updateState)
      persist(Evt(s"${data}-${numEvents + 1}")) { event =>
        updateState(event)
        context.system.eventStream.publish(event)
      }
    case "snap"  => saveSnapshot(state)
    case "print" => println(state)
  }
  
  override def preStart() = {
    	//	self ! Recover(toSequenceNr = 457L)    
  }
 
}

}