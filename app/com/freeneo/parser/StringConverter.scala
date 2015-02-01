package com.freeneo.parser

import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter
import org.joda.time.DateTime
import scala.util.Random
import org.joda.time.Period

import org.apache.hadoop.hbase.util.Bytes

object StringConverter {
  
    val comparedString1 = "대학"
	val comparedString2 = "university"
	val comparedString3 = "大學"
	val comparedString4 =  "大学"	
	val comparedString5 =  "대학교"
	  val comparedString6 =  " "
//	val comapredString7 =  "美国"
//	val comapredString8=  "加拿大"
	  
	var word  = ""
	var word2 = ""
	  
	val ccomparedString1 = "(주)"
//	val ccomparedString2 = "株式会社"
	val ccomparedString3 = "公司"  
	val ccomparedString4 = "ltd." 
	val ccomparedString5 = "limited." 
	val ccomparedString6 = "inc." 
//	val ccomparedString7 = "有限公司" 
//	val ccomparedString8 = "股份有限公司" 
	val ccomparedString9 = "co.,ldt." 
	val ccomparedString10 = "会社" 
	val ccomparedString11 = "limited" 
	val MD5_LENGTH = 16
	
// 추가 필요 	有限公司,会社, 股份有限公司, co. ltd. co., limited. inc.
	
def finddup(s: String): String = {       	
      
       word = s.replaceAll(" ","").toLowerCase
     
       println("word" + word)
      
	   if(word.endsWith(comparedString1)) {
	   word2 = word.stripSuffix(comparedString1)
//	   println("s ends with university" + s)
	   return word2
	   }
	   
	    if(word.endsWith(comparedString2)) {
	   word2 = word.stripSuffix(comparedString2)
//	   word = word.replaceAll(" ","")
//	   println("s ends with university" + s)
	   return word2
	   }
	   
	   if(word.endsWith(comparedString3)) {
	   word2 = word.stripSuffix(comparedString3)
//	   println("s ends with university" + s)
	   return word2
	   } 
	   
	    if(word.endsWith(comparedString4)) {
	   word2 = word.stripSuffix(comparedString4)
//	   println("s ends with university" + s)
	   return word2
	   } 
	    
	        if(word.endsWith(comparedString5)) {
	   word2 = word.stripSuffix(comparedString5)
	   println("s ends with 대학교" + s)
	   return word2
	   } 
	    
	   println("word:" + word) 
	   return word
   }
    

    
def finddup2(s: String): String = {       	
      
       word = s.replaceAll(" ","").toLowerCase
       
      
	   if(word.endsWith(ccomparedString1)) {
	   word2 = word.stripSuffix(ccomparedString1)
//	   println("s ends with university" + s)
	   return word2
	   }
	   
//	    if(word.endsWith(ccomparedString2)) {
//	   word2 = word.stripSuffix(ccomparedString2)
//	   word = word.replaceAll(" ","")
//	   println("s ends with university" + s)
//	   return word2
//	   }
	   
	   if(word.endsWith(ccomparedString3)) {
	   word2 = word.stripSuffix(ccomparedString3)
	   println("s ends with university" + s)
	   return word2
	   } 
	   
	    if(word.endsWith(ccomparedString4)) {
	   word2 = word.stripSuffix(ccomparedString4)
//	   println("s ends with university" + s)
	   return word2
	   } 
	    
	   if(word.endsWith(ccomparedString5)) {
	   word2 = word.stripSuffix(ccomparedString5)
//	   println("s ends with university" + s)
	   return word2
	   } 
	   
	     if(word.endsWith(ccomparedString6)) {
	   word2 = word.stripSuffix(ccomparedString6)
//	   println("s ends with university" + s)
	   return word2
	   } 
	     
//	      if(word.endsWith(ccomparedString7)) {
//	   word2 = word.stripSuffix(ccomparedString7)
//	   println("s ends with university" + s)
//	   return word2
//	   } 
	      
//	       if(word.endsWith(ccomparedString8)) {
//	   word2 = word.stripSuffix(ccomparedString8)
//	   println("s ends with university" + s)
//	   return word2
//	   } 
	       
	       if(word.endsWith(ccomparedString9)) {
	   word2 = word.stripSuffix(ccomparedString9)
//	   println("s ends with university" + s)
	   return word2
	   } 
	             
	       if(word.endsWith(ccomparedString10)) {
	   word2 = word.stripSuffix(ccomparedString10)
//	   println("s ends with university" + s)
	   return word2
	   } 
	       
	          if(word.endsWith(ccomparedString11)) {
	   word2 = word.stripSuffix(ccomparedString11)
	   println("s ends with university" + s)
	   return word2
	   } 
	    
	   println("word:" + word) 
	   return word
   }


    def finddup3(s: String): String = {       	
      
       word = s.replaceAll(" ","").toLowerCase
       println("findup3 word" + word)
      
	   return word
   }
	  
	 def makemd5(s: String):String = {
       val md5 = MessageDigest.getInstance("MD5")  
       val myByte = (new HexBinaryAdapter()).marshal(md5.digest(Bytes.toBytes(s)))
       return myByte
    }
	 

	  
   def showtimegap(start: Long, la: String): String = {
    
    if (start == null){
      return ""
    } 
     
    val secondtime = new DateTime(start.toLong)
   	val currenttime = new DateTime()
   		  	    		  
   	println(secondtime) 
    println(currenttime)
   		  	    		  
   	val period = new Period(secondtime,currenttime)
    val days = period.getDays().toString()
   val hours = period.getHours().toString()
   val minutes = period.getMinutes().toString()
   	val seconds = period.getSeconds().toString()
   		  	    		   
   val timeresult = StringConverter.calculatetime(days,hours,minutes,seconds)
   		  	    		  
   println("timeresult:" + timeresult)
   		  	    		  
   val timeindex = timeresult.lastIndexOf(" ")
   val timeexpression = Translation.translatetime(timeresult.substring(timeindex),la)
   val timegap = timeresult.substring(0,timeindex) + timeexpression
  
    return timegap
   }	  
    
   def calculatetime(days: String, hours: String, minutes: String, seconds: String): String = {
     
     println("calculatetime")
     
     var time = ""
     
     if (days == "0") {
    	 	if (hours == "0") {
    	  
    		 			if (minutes == "0" ) {
    		 				time = seconds + " "+ decidemulti(seconds,"second")
    		 				return time	
    	   					} else  {
    	   						time = minutes + " "+ decidemulti(minutes,"minute")
    	   						return time	
    	   					}
    	 			} // the seconds if close
    		 else {
    		 		time = hours + " "+ decidemulti(hours,"hour")
    		 		return time	
    		 		}
     		} // the first if close
     else {
    	     time = days + " "+ decidemulti(days,"day")
    	     return time
    	   }  	
    		 
     }
   
   def decidemulti (s: String, unit: String):String = {
     println("transalteTime" + s + ",unit:" + unit)
     var unitstring = ""
       if(s != "1") {
         unitstring = unit + "s"
         return unitstring
       }
      return unit
   }
   	  
// randomly change geo info based on input geo information    
    def expandsearch (s: String):String = {
        
        val queryloc = StringConverter.decloc(s)
        val querydiv = queryloc.lastIndexOf(" ")
        
        println("queryloc:" + queryloc)
    	
//        var producerminus = -(0.2f)
        var producer = 0.1
        
//        var bypasser =  +(0.01f)
        
    	var startlati = ""
    	var startlong = ""
    	var stoplati  = ""
    	var stoplong  = ""
    	var locstartkey = ""
    	var locstopkey = ""
    	
    	// make random no and decide which one between producer or bypasser will be used.   
    	val randomno = Random.nextInt(5) + 1
    	val randomno2 = Random.nextInt(2) + 1
 //   	val randomno3 = Random.nextInt(2) + 1
    	val randomno4 = (Random.nextInt(3) + 1)
    	
    	val randomno5 = randomno4 * producer
    	val restrange = 0.3 - randomno5
    	
    	val randomno6 = (Random.nextInt(3) + 1)                                                                                  
    	val randomno7 = randomno6 * producer
    	val restrange2 = 0.3 - randomno7
    	
    	var finegrainedrange1 = ((Random.nextInt(9)+1)* 0.01)
    	var finegrainedrange2 = ((Random.nextInt(9)+1)* 0.01)
    
        println("randomno5:" + randomno5 + "restrange:" + restrange + " randomno7:" + randomno7 + " restrange2:" +
        		restrange2 + "finegrainedrange1:" + finegrainedrange1 + "finegrainedrange2:" + finegrainedrange2)
    	
    	if(randomno < 4) {
    	  // use producer 
       				 // set scan start point of longitude and latitude
    				// set scan stop point of latitude and longitude 
    	  
    		startlati =  "%.2f".format(queryloc.substring(0,querydiv).toFloat - randomno5 + finegrainedrange1)
    		startlong = "%.2f".format(queryloc.substring(querydiv+1).toFloat - randomno7 + finegrainedrange2)
    		stoplati = "%.2f".format(queryloc.substring(0,querydiv).toFloat + restrange + finegrainedrange1)
    		stoplong = "%.2f".format(queryloc.substring(querydiv+1).toFloat + restrange2 + finegrainedrange2)
    	
    	} 
    	
    	else if(randomno == 4) {
    	  startlati = "%.2f".format(queryloc.substring(0,querydiv).toFloat)
    	  startlong = "%.2f".format(queryloc.substring(querydiv+1).toFloat)
    	  stoplati = "%.2f".format(queryloc.substring(0,querydiv).toFloat + 0.22)
    	  stoplong = "%.2f".format(queryloc.substring(querydiv+1).toFloat + 0.22) 
    	}
    	else {
    	  // user bypasser
    	      	  		
    	  	  if (randomno2 == 1) {
    				  // decide producer float number between 0.1 and 0.2
    					startlati =  "%.2f".format(queryloc.substring(0,querydiv).toFloat + 0.01)
    					startlong = "%.2f".format(queryloc.substring(querydiv+1).toFloat)
    					stoplati = "%.2f".format(queryloc.substring(0,querydiv).toFloat + 0.21)
    					stoplong = "%.2f".format(queryloc.substring(querydiv+1).toFloat + 0.22)
    				} else {
    					startlati =  "%.2f".format(queryloc.substring(0,querydiv).toFloat )
    					startlong = "%.2f".format(queryloc.substring(querydiv+1).toFloat + 0.01)
    					stoplati = "%.2f".format(queryloc.substring(0,querydiv).toFloat + 0.22 )
    					stoplong = "%.2f".format(queryloc.substring(querydiv+1).toFloat + 0.21)
    				}
    	  	      	                                                                                       // use bypasser
    	}
    	  
  
         println("startlati:" + startlati)
         println("startlong:" + startlong)
         println("stoplati:" + stoplati)
         println("stoplong:" + stoplong)
        
         locstartkey = StringConverter.makeloc(startlong+"div"+startlati)
  	     locstopkey = StringConverter.makeloc(stoplong+"div"+stoplati)
  	     
  	     return locstartkey + ":" + locstopkey
    }
    
    
 // remove '+' if longitude , latitude have '+'     
     def decloc(s: String):String = {
       
        if (s == null ) {
          return s
        }
       
        var long =""
        var lati =""
        long = s.substring(0,3) + s.substring(5,6) + s.substring(7,8) + s.substring(9,10) + s.substring(11,12)
        lati = s.substring(3,5) + s.substring(6,7) + s.substring(8,9) + s.substring(10,11) + s.substring(12,13)
        		
                     
        if(long.contains("+")) {
          println(long.contains("+"))
         long = long.substring(1) 
         lati = lati.substring(1) 
        }
       
        
         println("long:" + long+ "    lati:" + lati)
        
        return lati + " " + long
     }
     
     
    
// convert string value of latitude & longitude(longitude+div+latitude) into geo key set
     
    def makeloc(s: String):String = {
       val div = s.lastIndexOf("div")
       val longitude = s.substring(0,div)
       val latitude = s.substring(div+3)
       val lpointdiv = longitude.lastIndexOf(".")
    
       val lapointdiv = latitude.lastIndexOf(".")
          println("lpointdiv" + lpointdiv + "lapointdiv" + lapointdiv + latitude.substring(lpointdiv))
       var long =""
       var lati =""
       var geoinfo =""
      
       println("longitude:" + longitude +", latitude: " + latitude)
       if (longitude.substring(0,1) == "-") {
         long = longitude
         val longdiv = long.lastIndexOf(".")
         long = longitude.substring(0,longdiv)
        		     
       } else {
         long = "+" + longitude
          val longdiv = long.lastIndexOf(".")
         long = long.substring(0,longdiv)
        		
       }
    
       if (latitude.substring(0,1) =="-"){
         lati = latitude
         val latidiv = lati.lastIndexOf(".")
         lati = latitude.substring(0,latidiv)
       } else {
         lati = "+" + latitude
         val latidiv = lati.lastIndexOf(".")
         lati = lati.substring(0,latidiv)
       	}
         
       var flong = matchTest(long.substring(1).length())
       var flati = matchTest2(lati.substring(1).length())
       var longloc = long.substring(0,1) + flong + long.substring(1) + longitude.substring(lpointdiv)
       var latiloc = lati.substring(0,1) + flati + lati.substring(1) + latitude.substring(lapointdiv)
       
       println("longloc" + longloc + "latiloc" + latiloc)
       
       geoinfo =
      longloc.substring(0,3) + latiloc.substring(0,2) + longloc.substring(3,4) + latiloc.substring(2,3) + 
      longloc.substring(4,5) + latiloc.substring(3,4) + longloc.substring(5,6) + latiloc.substring(4,5) + 
      longloc.substring(6,7) + latiloc.substring(5,6)
       
      println("geoinfo" + geoinfo)
      
      return geoinfo
    }
    
   def matchTest(x: Int): String = x match {
    case 1 => "00" 
    case 2 => "0" 
    case 3 => ""
    }
   
    def matchTest2(x: Int): String = x match {
    case 1 => "0" 
    case 2 => ""
    }
    
    def makeproducer(x: String): String = x match {
      case "1" => "0.1"
      case "2" => "0.2"
      case "3" => "0.4"     
    }
    
  
     
  

// decide if a user has register within one year and randomly make time block of search startkey  
    
    def makeTime(register: Long): Long = {
    
        val svcstart = new DateTime("2014-03-24").getMillis()
   		       
   		var now = new DateTime().getMillis() 		
   		val forfresh = new DateTime().minusMonths(12).getMillis()
   		println("forfresh: " + forfresh)
   		
   		val forfreshdiv = forfresh
   		var earliertime = new DateTime().getMillis()
   		
   		
 // query if a user has been registered within one year  		
   		
   		if (register > forfreshdiv) {
   		     		
   		  if (forfreshdiv < svcstart) {
   			  earliertime = svcstart
   		  } else {
   		      earliertime = forfreshdiv
   		  }
   		  
   		  val gapforf = now - earliertime
    	  println("svcstart: "  + earliertime + " now: " + now)
   	//	  var redivstarttime = Random.nextInt(10) + 1
   	//	  var divearliertime = gapforf/redivstarttime
   	//	  println("divearliertime" + divearliertime)
   		
   		  val random = Random.nextInt(200000) + 1
   		  		
   		  println("Math.random"+random)
   		  val startforf = forfresh + gapforf/random
   		
   		 println("startforf: " + startforf )
     
        return startforf
   		  
   		} else {
   		    val gap = now - svcstart
   		    val random = Random.nextInt(200000) + 1
   		  		
   		   println("Math.random"+random)
   		   val start = svcstart + gap/random
   		  
   		   return start
   		}
   	
    }
 
/*
 def makeTime2(register: Long): Long = {
    
        val svcstart = new DateTime("2011-04-03").getMillis()
   		       
   		var now = new DateTime().getMillis() 		
   		val forfresh = new DateTime().minusMonths(12).getMillis()
   		println("forfresh: " + forfresh)
   		
   		val forfreshdiv = forfresh
   		
   // decide where register time is within one year from current time		
   		
   		if (register > forfreshdiv) {
   		     		
   		  val gapforf = now - forfresh
    	  println("svcstart: "  +svcstart + " now: " + now)
   		
   		  val random = Random.nextInt(1000) + 1
   		  		
   		  println("Math.random"+random)
   		  val startforf = forfresh + gapforf/random
   		
   		 println("startforf: " + startforf )
     
        return startforf
   		  
   		} else {
   		    val gap = now - svcstart
   		    val random = Random.nextInt(1000) + 1
   		  		
   		   println("Math.random"+random)
   		   val start = svcstart + gap/random
   		  
   		   return start
   		}
   	
    }
*/
    def replacenull(result: java.util.List[String])= {
     
      
      for(i <- 0 to result.size()-1 ) {
       
    	 if (result.get(i) == null ) {
    	     result.set(i,"")
    	 }
     }
    }
    
    def removenull(result: List[String],index: Int): List[String] = {
        val picdiv = result.indexOf("",index)
        println("picdiv:" + picdiv)
        val finalresult = result.slice(0,picdiv)
       return finalresult
    }
    
    def changenull(s: String): Long = {
        if (s == null ) {
          return "0".toLong
        } else {
       return s.toLong 
        }
    }
    
    def addminimum(s: Long): Long = {
    
    	return s + 1L
    }
    
   def makepercent(s1: Long, s2: Long, s3:Long):String = {
     
       if (s1 == 0) {
              
         if (s2 == 0 ) { 
        	 
            if (s3 == 0) {
              
              return "33" + "33" + "33"
            }    
         	}
           }
        
       var s1re = s1.toDouble
       var s2re = s2.toDouble
       var s3re = s3.toDouble
       if (s1 == 0 ) {
           s1re = addminimum(s1).toDouble }
       if (s2 == 0) {
           s2re = addminimum(s2).toDouble }
       if (s3 ==0 ) {
           s3re =  addminimum(s3).toDouble
       }
         var total = (s1re + s2re + s3re) 
         println("s1re" + s1re + "s2re" + s2re + "s3re" + s3re)
        		 	var s1percent = (((s1re / total)*100).toInt).toString() 
        		 	var s2percent = (((s2re / total)*100).toInt).toString()
        		 	var s3percent = (((s3re / total)*100).toInt).toString()
        		 	
        		 return s1percent + s2percent + s3percent
   }
   
   def makeagegender(age: Int, gender: String): String = {
     
     var birth1 = "" 
   	 var searchgender =""	
   		
   		if (gender == "male") {
   		    searchgender = "fema"
   		    birth1 = (age + 3).toString()
   		  
   		} else {
   		   searchgender = "male"
   		   birth1 = (age - 3).toString()
   		}
     
     return searchgender + birth1
   }
   
}

// 	scala image process code
  	//	  	   	var orImg = ImageIO.read(picture.ref.file)
  	//			var rsImg = 
  	//			  Scalr.resize(orImg, 600)	
  	//			 Scalr.resize(orImg, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
  	//					 	 150, 100, Scalr.OP_ANTIALIAS)
  	//			Scalr.resize(orImg, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
  	//					 	 150, 200, Scalr.OP_GRAYSCALE)
  						
  	//			ImageIO.write(rsImg, "jpg", new File("public/images/"+picturename)) 