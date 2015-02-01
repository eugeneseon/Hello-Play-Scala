package com.freeneo.logprocess

import java.util._

import org.apache.flume.agent.embedded.EmbeddedAgent
import org.apache.flume.FlumeException





object EmbeddedFlume {

   def startup = {
    val client = new MyRpcClientFacade();
    // Initialize client with the remote Flume agent's host and port
    // client.init("146.148.45.38", 21414);
    
     client.init();
    
    println(client.toString())

    // Send 10 events to the remote Flume agent. That agent should be
    // configured to listen with an AvroSource.
    val sampleData = "Hello Flume!";
    for ( i <- 1 until 10) {
      println("flume sending start")
      client.sendDataToFlume(sampleData);
    }

    client.cleanUp();
  }

} // object ends

