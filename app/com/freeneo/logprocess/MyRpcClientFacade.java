package com.freeneo.logprocess;

import java.util.Properties;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;
import java.nio.charset.Charset;

public class MyRpcClientFacade {
  private RpcClient client;
 // private String hostname;
 // private int port;

 // public void init(String hostname, int port) {
	  
	  public void init() {
    // Setup the RPC connection
  //  this.hostname = hostname;
 //   this.port = port;
    
    Properties props = new Properties();
    props.put("client.type", "default_failover");

    // List of hosts (space-separated list of user-chosen host aliases)
    props.put("hosts", "h1 h2 h3");

    // host/port pair for each host alias
    String host1 = "freeneoweb1.c.absolute-runner-550.internal:21414";
    String host2 = "freeneoweb2.c.absolute-runner-550.internal:21414";
    String host3 = "localhost:21414";
    
    props.put("hosts.h1", host1);
    props.put("hosts.h2", host2);
    props.put("hosts.h3", host3);
    
    
    
  //  this.client = RpcClientFactory.getDefaultInstance(hostname, port);
    this.client = RpcClientFactory.getInstance(props);
    // Use the following method to create a thrift client (instead of the above line):
    // this.client = RpcClientFactory.getThriftInstance(hostname, port);
  }

  public void sendDataToFlume(String data) {
    // Create a Flume Event object that encapsulates the sample data
    Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

    // Send the event
    try {
      client.append(event);
    } catch (EventDeliveryException e) {
      // clean up and recreate the client
      client.close();
      client = null;
  //    client = RpcClientFactory.getDefaultInstance(hostname, port);
      // Use the following method to create a thrift client (instead of the above line):
      // this.client = RpcClientFactory.getThriftInstance(hostname, port);
    }
  }

  public void cleanUp() {
    // Close the RPC connection
    client.close();
  }

}
   
