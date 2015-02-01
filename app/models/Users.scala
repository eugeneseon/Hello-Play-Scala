package models

import scala.collection.JavaConversions._

//import com.freeneo.model.gen.Tables.USERS
//import com.freeneo.model.gen.tables.pojos.Users
//import com.freeneo.model.gen.tables.records.UsersRecord
import org.jooq.impl._
import play.api.db.DB
import play.api.Play.current

import play.Logger

/*
object Users {

  case class User(id: Long, password: String, userindex1: String, userindex2: String)
  
  implicit def dataRecord2Data(r: UsersRecord): Users = r.into(classOf[Users])

  implicit def dataRecordList2DataList(r: List[UsersRecord]): List[Users] = r.map(dataRecord2Data(_))

  def listAll: List[Users] = DB.withConnection { conn =>
    DSL.using(conn)
      .selectFrom(USERS)
      .fetch
      .toList
  }
  
  def insertOne = DB.withConnection { conn =>
    DSL.using(conn).insertInto(USERS, USERS.ID, USERS.PASSWORD)
       .values(2, "Orwell")
       .execute()
    
  }
  
    def findById(id: Int, password: String): Users = DB.withConnection { conn =>
     
    Logger.info("findById is called")  
      
    DSL.using(conn)
    .selectFrom(USERS)
      .where(USERS.ID.eq(id))
      .and(USERS.PASSWORD.eq(password))
      .fetchOne
    
  }


}
*/