package com.freeneo.mailer

import java.io.File
import javax.inject.Inject

import play.Play

import play.api.libs.mailer._
import play.api.Play.current
import play.api._
import org.apache.commons.mail.EmailAttachment

//import play.libs.mailer._

object MailSender{

  def sendMail = {
    


 val email = Email(
      "Simple email",
      "Mister FROM <seon00@dreamwiz.com>",
      Seq("Miss TO <seon00@dreamwiz.com>"),
     attachments = Seq(
    AttachmentFile("favicon.png", new File(current.classloader.getResource("public/images/favicon.png").getPath)),
    // adds inline attachment from byte array
    AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE))
  ),
      bodyText = Some("A text message"),
      bodyHtml = Some("<html><body><p>An <b>html</b> message</p></body></html>")
    )
  //  val id = mailer.send(email)
    
  //MailerPlugin.send(email)
    
 //   this.send(email)
    
    val config = Configuration.load(new File("conf/application.conf"))
    
    val mailer = new CommonsMailer(config)
    mailer.send(email)
    //Play.application().plugin(play.api.libs.mailer.CommonsMailer.class).instance().send(email)
   
  }
}