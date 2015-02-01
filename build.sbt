import AssemblyKeys._
import PlayKeys._
import java.io.File

assemblySettings

mainClass in assembly := Some("play.core.server.NettyServer")

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("com", "google", xs @ _*)         => MergeStrategy.deduplicate
       case PathList("net", "sf", xs @ _*)         => MergeStrategy.deduplicate
         //      case PathList("org", "apache","commons", xs @ _*)         => MergeStrategy.first
               // ServerStop on command line is not working properly because of below line
   	//	case PathList("play", "core","server", xs @ _*)         => MergeStrategy.first
        case PathList("org", "slf4j", xs @ _*)         => MergeStrategy.first 
          case PathList("org", "apache","commons","logging", xs @ _*)         => MergeStrategy.first
           case PathList("org", "apache","log4j", xs @ _*)         => MergeStrategy.first
       case PathList("javax", "xml", xs @ _*)         => MergeStrategy.first
    //        case PathList("com", "esotericsoftware", xs @ _*)         => MergeStrategy.first
   // case PathList("org", "cyberneko", xs @ _*)         => MergeStrategy.filterDistinctLines
     case PathList("org", "objectweb", xs @ _*)         => MergeStrategy.filterDistinctLines
  case "application.conf"                            => MergeStrategy.concat
  case "CHANGELOG"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case PathList(ps @ _*) if ps.last endsWith "package-info.class" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".episode" => MergeStrategy.concat
   case PathList(ps @ _*) if ps.last endsWith ".xjb" => MergeStrategy.concat
   case PathList(ps @ _*) if ps.last endsWith "pom.properties" => MergeStrategy.concat
    case PathList(ps @ _*) if ps.last endsWith "pom.xml" => MergeStrategy.concat
  case x  => old(x)
  }
}


lazy val root = (project in file(".")).enablePlugins(PlayScala,SbtWeb)

name := """figaroserver"""

version := "1.0"

scalaVersion := "2.11.2"



//crossScalaVersions := Seq("2.10.4","2.11.2")

val akkaVersion = "2.4-SNAPSHOT"

net.virtualvoid.sbt.graph.Plugin.graphSettings

libraryDependencies ++= Seq(
   jdbc,
   cache,
  anorm,
  ws,
  filters,
  // add jooq and its dependencies
 "org.jumpmind.symmetric.jdbc" % "mariadb-java-client" % "1.1.1",
  "org.jooq" % "jooq" % "3.4.0" ,
  "org.jooq" % "jooq-meta" % "3.4.0",
  "org.jooq" % "jooq-scala" % "3.4.0" ,
  "org.jooq" % "jooq-codegen" % "3.4.0" ,
  "javax.persistence" % "persistence-api" % "1.0.2",
  "javax.validation" % "validation-api" % "1.1.0.Final",
  "com.fasterxml.uuid" % "java-uuid-generator" % "3.1.3",
    // add akka-persistence-hase 
 "pl.project13.scala" % "akka-persistence-hbase_2.11" % "0.4.1" excludeAll (ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "org.eclipse.jdt"),ExclusionRule(organization = "commons-beanutils",name="commons-beanutils-core"),ExclusionRule(organization = "org.apache.hadoop"),ExclusionRule(organization = "org.cloudera.htrace"),ExclusionRule(organization = "com.typesafe.akka"),ExclusionRule(organization = "com.google.guava")),
   // add phoenix and its dependencies 
   "org.apache.phoenix" % "phoenix-core" % "4.2.2" excludeAll (ExclusionRule(organization = "sqlline"),ExclusionRule(organization = "net.sourceforge.findbugs"),ExclusionRule(organization = "org.apache.hadoop"),ExclusionRule(organization = "org.cloudera.htrace"),ExclusionRule(organization="org.mortbay.jetty"),ExclusionRule(organization = "com.google.guava")),
   "sqlline" % "sqlline" % "1.1.7",
   	  // add hbase client and its dependencies 
   	 "org.apache.hbase" % "hbase-common" % "0.98.4-hadoop2" excludeAll (ExclusionRule(organization = "javax.servlet"),ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "com.google.guava")),
 	"org.apache.hbase" % "hbase-client" % "0.98.4-hadoop2" excludeAll (ExclusionRule(organization = "javax.servlet"),ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "com.google.guava")),
 	"org.apache.hbase" % "hbase-hadoop2-compat" % "0.98.4-hadoop2" excludeAll (ExclusionRule(organization = "javax.servlet"),ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "commons-beanutils"),ExclusionRule(organization = "com.google.guava")),	
    "org.apache.hadoop" % "hadoop-common" % "2.6.0"  excludeAll (ExclusionRule(organization = "javax.servlet"),ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "commons-beanutils"),ExclusionRule(organization = "com.google.guava")),
     "org.apache.hadoop" % "hadoop-client" % "2.6.0" excludeAll (ExclusionRule(organization = "javax.servlet"),ExclusionRule(organization = "org.mortbay.jetty"),ExclusionRule(organization = "commons-beanutils"),ExclusionRule(organization = "com.google.guava")),
     "org.xerial.snappy" % "snappy-java" % "1.1.1.6",
      "com.google.protobuf" % "protobuf-java" % "2.5.0",
    //   "com.google.guava" % "guava" % "14.0",
    // add imgscala and scrimage
    "org.imgscalr" % "imgscalr-lib" % "4.2",
 	  "com.sksamuel.scrimage" % "scrimage-core_2.10" % "1.4.2",
 	  "com.sksamuel.scrimage" % "scrimage-filters_2.10" % "1.4.2", 
  "org.slf4j" % "slf4j-log4j12" % "1.7.5",
  		 "org.eclipse.jetty" % "jetty-util" % "8.1.14.v20131031",
  		  "org.eclipse.jetty" % "jetty-websocket" % "8.1.14.v20131031",
  		  "org.eclipse.jetty" % "jetty-http" % "8.1.14.v20131031",
  		  "org.eclipse.jetty" % "jetty-server" % "8.1.14.v20131031",
  		  "org.eclipse.jetty" % "jetty-security" % "8.1.14.v20131031",
  		  "com.codahale.metrics" % "metrics-core" % "3.0.2",
  		  "com.codahale.metrics" % "metrics-json" % "3.0.2",
  		//  "org.json4s" % "json4s-jackson_2.10" % "3.2.11",
  		//  "net.liftweb" % "lift-json_2.11" % "2.6",
  		    "com.github.tototoshi" %% "play-json4s-native" % "0.3.1",
  "com.github.tototoshi" %% "play-json4s-test-native" % "0.3.1" % "test",
 		 "com.typesafe.play" %% "play-json" % "2.4.0-M2",
  		  "org.eclipse.jetty" % "jetty-servlet" % "8.1.14.v20131031",
  		   "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile",
  		  // add akka modules
  			"com.typesafe.akka" % "akka-actor_2.11" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			"com.typesafe.akka" % "akka-remote_2.11" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			 "io.netty" % "netty-all" % "4.0.23.Final", 
  			"com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			"com.typesafe.akka" % "akka-slf4j_2.11" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			"com.typesafe.akka" %% "akka-persistence-experimental" % akkaVersion excludeAll (ExclusionRule(organization = "org.jboss.netty"),ExclusionRule(organization = "org.iq80.leveldb")),  
  			"com.github.krasserm" %% "akka-persistence-testkit" % "0.3.4" excludeAll (ExclusionRule(organization = "com.typesafe.akka")),
  			"com.typesafe.akka" %% "akka-testkit" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"), 
  			"com.typesafe.akka" %% "akka-contrib" % akkaVersion excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			"com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M2" excludeAll ExclusionRule(organization = "org.jboss.netty"),
  			"com.github.krasserm" %% "akka-persistence-testkit"      % "0.3.3"     % "test", 
  			"org.scalatest" % "scalatest_2.11" % "2.0",
  			"com.esotericsoftware.reflectasm" % "reflectasm" % "1.09" classifier "shaded",
  				"org.apache.httpcomponents" % "httpclient" % "4.3.2",
  				//add apns library
  				"com.notnoop.apns" % "apns" % "0.1.6",
  				//add google api-client library
  				"com.google.api-client" % "google-api-client" % "1.19.0"  excludeAll ExclusionRule(organization = "com.google.guava", name = "guava-jdk5"),
  				"com.google.api-client" % "google-api-client-android" % "1.19.0",
  				 "com.google.api-client" % "google-api-client-jackson2" % "1.19.0",
  				 "com.google.api-client" % "google-api-client-gson" % "1.19.0",
  				  "org.scalaz.stream" %% "scalaz-stream" % "0.6",
  				   "org.webjars" % "angularjs" % "1.3.8",
 					 "org.webjars" % "requirejs" % "2.1.15",
 					 "org.webjars" % "bootstrap" % "3.2.0",
 					 "org.webjars" %% "webjars-play" % "2.3.0-2",
 					 "org.webjars" % "leaflet" % "0.7.3",
 					 "org.webjars" % "angular-ui" % "0.4.0-3",
 					 "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
 					 "org.webjars" % "ionic" % "1.0.0-beta.14",
 					 "org.apache.flume" % "flume-ng-embedded-agent" % "1.5.2" excludeAll (ExclusionRule(organization = "org.mortbay.jetty")),
 					 "org.apache.flume" % "flume-ng-sdk" % "1.5.2" excludeAll (ExclusionRule(organization = "org.mortbay.jetty")),
 					 "com.typesafe.play" %% "play-mailer" % "3.0.0-M1"		  		
)

resolvers += Resolver.url("Edulify Repository", url("http://edulify.github.io/modules/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("pk11 repo", url("http://pk11-scratch.googlecode.com/svn/trunk"))(Resolver.ivyStylePatterns)

resolvers += Resolver.file("LocalIvy", file(Path.userHome + File.separator + ".ivy2" + File.separator + "local"))(Resolver.ivyStylePatterns)

resolvers ++= Seq(
            "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo",
 			 "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
            "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
            "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
        //    "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
            "dnvriend at bintray" at "http://dl.bintray.com/dnvriend/maven",
            "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven",
            "sean8223 Releases" at "https://github.com/sean8223/repository/raw/master/releases",
            "sqlline Release" at "http://conjars.org/repo",
            "Rhinofly Internal Release Repository" at "http://maven-repository.rhinofly.net:8081/artifactory/libs-release-local",
            "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
            "akka-snapshots" at "http://repo.akka.io/snapshots/"
            )

//libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.31" % "jooq"

pipelineStages := Seq(rjs, digest, gzip)

//DigestKeys.algorithms += "sha1"

