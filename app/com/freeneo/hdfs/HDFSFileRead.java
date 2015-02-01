package com.freeneo.hdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
 
public class HDFSFileRead {
 
 public void read(Path url) throws Exception {
 
  try {
	  System.out.println("hdfs reading start.......");	  
	  
   Path path = url;
   FileSystem fileSystem = FileSystem.get(new Configuration());
   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileSystem.open(path)));
   String line = bufferedReader.readLine();
   while (line != null) {
	   System.out.println("not null");	  
    System.out.println(line);
    line = bufferedReader.readLine();
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
 
}