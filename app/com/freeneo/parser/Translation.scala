package com.freeneo.parser

object Translation {
  
   def translateN(data: String, lang:String):String = {
	   var tdata = ""
       if(data == "Korea") {
	     
	   if(lang == "ko")  {
		    tdata = "대한민국"
	   		return tdata }
	   else if (lang == "jp") {  tdata = "韓国"
	   		return tdata  }
	   else if (lang == "zh" ) { tdata = "韩国"
	   		return tdata }
	   else {  
	   		return data  }
       
     } else if(data == "China") {
	     
	   if(lang == "ko")  {
		    tdata = "중국"
	   		return tdata }
	   else if (lang == "jp") {  tdata = "中国"
	   		return tdata  }
	   else if (lang == "zh" ) { tdata = "中国"
	   		return tdata }
	   else {  tdata = "test"
	   		return tdata  }
   } 
	   
     else if(data == "Japan") {
	     
	   if(lang == "ko")  {
		    tdata = "일본"
	   		return tdata }
	   else if (lang == "jp") {  tdata = "日本"
	   		return tdata  }
	   else if (lang == "zh" ) { tdata = "日本"
	   		return tdata }
	   else {  tdata = "test"
	   		return tdata  }
	   }
    else {
      return data
    }	
   }
   
    def translateD(data: String, lang:String):String = {
	   var tdegree = ""
       if(data == "university") {
	     
	   if(lang == "ko")  {
		    tdegree = "학사"
	   		return tdegree }
	   else if (lang == "jp") {  tdegree = "学士"
	   		return tdegree }
	   else if (lang == "zh" ) { tdegree = "学士学位"
	   		return tdegree }
	   else {  
	   		return "Bachelor's degree"  }
       
     } else if(data == "graduate school") {
	     
	   if(lang == "ko")  {
		    tdegree = "대학원"
	   		return tdegree }
	   else if (lang == "jp") {  tdegree = "大学院"
	   		return tdegree }
	   else if (lang == "zh" ) { tdegree = "大学院"
	   		return tdegree}
	   else {  
	   		return data }
   } 
    else {
      return data
    }	
   }
   
    def translateG(data: String, lang:String):String = {
	   var tgender = ""
       if(data == "fema") {
	     
	   if(lang == "ko")  {
		    tgender = "여자"
	   		return tgender }
	   else if (lang == "jp") {  tgender = "女性"
	   		return tgender }
	   else if (lang == "zh" ) { tgender = "女人"
	   		return tgender }
	   else {  
	   		return "female"  }
       
     } else if(data == "male") {
	     
	   if(lang == "ko")  {
		    tgender = "남자"
	   		return tgender }
	   else if (lang == "jp") {  tgender = "男性"
	   		return tgender }
	   else if (lang == "zh" ) { tgender = "男性"
	   		return tgender}
	   else {  
	   		return data }
   } 
    else {
      return data
    }	
   }
    
     def translateAct(data: String, lang:String):String = {
	   var tact = ""
       if(data == "active") {
	     
	   if(lang == "ko")  {
		    tact = "있음"
	   		return tact }
	   else if (lang == "jp") {  tact = "有"
	   		return tact }
	   else if (lang == "zh" ) { tact = "有"
	   		return tact }
	   else {  
		    tact = "yes"
	   		return tact }
       
     } else if(data == "inactive") {
	     
	   if(lang == "ko")  {
		    tact = "없음"
	   		return tact }
	   else if (lang == "jp") {  tact = "無"
	   		return tact }
	   else if (lang == "zh" ) { tact = "沒有"
	   		return tact}
	   else {  
		   	tact = "no"
	   		return tact }
   } 
    else {
      return data
    }	
   }
     
      def translateStatus(data: String, lang:String):String = {
	   var tact = ""
       if(data == "single") {
	     
	   if(lang == "ko")  {
		    tact = "소개필요"
	   		return tact }
	   else if (lang == "jp") {  tact = "はじめに要"
	   		return tact }
	   else if (lang == "zh" ) { tact = "要介绍"
	   		return tact }
	   else {  
	   		return data  }
       
     } else if(data == "-ing..") {
	     
	   if(lang == "ko")  {
		    tact = "진행중.."
	   		return tact }
	   else if (lang == "jp") {  tact = "進行中"
	   		return tact }
	   else if (lang == "zh" ) { tact = "进行"
	   		return tact}
	   else {  
	   		return data }
   } else if(data == "marry soon") {
	     
	   if(lang == "ko")  {
		    tact = "결혼예정"
	   		return tact }
	   else if (lang == "jp") {  tact = "予定结婚"
	   		return tact }
	   else if (lang == "zh" ) { tact = "马上结婚"
	   		return tact}
	   else {  
	   		return data }
   } 
    else {
      return data
    }	
   }
      
     def translateIndi(data: String, lang:String):String = {
	   var tact = ""
       if(data == "yes") {
	     
	   if(lang == "ko")  {
		    tact = "인기회원"
	   		return tact }
	   else if (lang == "jp") {  tact = "人気のメンバー"
	   		return tact }
	   else if (lang == "zh" ) { tact = "人气会员"
	   		return tact }
	   else {  
	   		return data  }
     
       } 
       else {
    	   return data
       }	
   }
  
  
     def translatetime(data: String, lang: String): String = {
      var tact = ""
      
        if(data.contains("day")) {
	     
	   if(lang == "ko")  {
		    tact = "일전"
	   		return tact }
	   else if (lang == "jp") {  tact = "日前"
	   		return tact }
	   else if (lang == "zh" ) { tact = "天之前"
	   		return tact }
	   else {  
	   		return data + " before" }
     
       } 
       if(data.contains("hour")) {
	     
	   if(lang == "ko")  {
		    tact = "시간전"
	   		return tact }
	   else if (lang == "jp") {  tact = "時間前"
	   		return tact }
	   else if (lang == "zh" ) { tact = "小时之前"
	   		return tact }
	   else {  
	   		return data + " before" }
     
       } 	
         if(data.contains("minute")) {
	     
	   if(lang == "ko")  {
		    tact = "분전"
	   		return tact }
	   else if (lang == "jp") {  tact = "分前"
	   		return tact }
	   else if (lang == "zh" ) { tact = "分之前"
	   		return tact }
	   else {  
	   		return data + " before" }
     
       } 
          if(data.contains("second")) {
	     
	   if(lang == "ko")  {
		    tact = "초전"
	   		return tact }
	   else if (lang == "jp") {  tact = "秒前"
	   		return tact }
	   else if (lang == "zh" ) { tact = "秒之前"
	   		return tact }
	   else {  
	   		return data + " before" }
     
       } else {
         return data + " before"
       }
   }
     
     def translatemessage(data: String, lang: String): String = {
      var tact = "has been sent"
      var tact2 = "has been recieved"
      
        if(data.contains("fired")) {
	     
	   if(lang == "ko")  {
		    tact = "송부됨"
	   		return tact }
	   else if (lang == "jp") {  tact = "送信"
	   		return tact }
	   else if (lang == "zh" ) { tact = "发送"
	   		return tact }
	   else {  
	   		return tact }
     
       }  
        else if(data.contains("receive")) {
     
          if(lang == "ko")  {
		    tact2 = "받음"
	   		return tact2 }
          else if (lang == "jp") {  tact2 = "受け取る"
	   		return tact2 }
	   		else if (lang == "zh" ) { tact2 = "收到"
	   		return tact2 }
          
	   		else {  
	   		return tact2 }
        	
        }     
        else {
         return data
       }
      
   }
    
       def translatetype(data: String, lang: String): String = {
      var tact = "self-introduction"
      var tact2 = "message"
      
        if(data == "profile") {
	     
	   if(lang == "ko")  {
		    tact = "프로파일"
	   		return tact }
	   else if (lang == "jp") {  tact = "イントロ"
	   		return tact }
	   else if (lang == "zh" ) { tact = "自我介绍"
	   		return tact }
	   else {  
	   		return tact }
     
       }  
        else if(data.contains("message")) {
     
          if(lang == "ko")  {
		    tact2 = "메세지"
	   		return tact2 }
          else if (lang == "jp") {  tact2 = "信息"
	   		return tact2 }
	   		else if (lang == "zh" ) { tact2 = "信息"
	   		return tact2 }
          
	   		else {  
	   		return tact2 }
        	
        }     
        else {
         return data
       }
       }
     
     
}