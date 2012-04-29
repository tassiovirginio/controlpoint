name := "Control Point"

version := "1.0"

scalaVersion := "2.9.1"

unmanagedResourceDirectories in Compile += new File("src/main/scala")

defaultExcludes in unmanagedResources := ("*.scala" | "*.java")
  
seq(webSettings :_*)

resolvers += "Google Code" at "http://jqwicket.googlecode.com/svn/m2-repo/releases/"

libraryDependencies ++= Seq(
 	"com.google.code.jqwicket" % "jqwicket" % "0.6",
	"org.apache.wicket" % "wicket" % "1.4.17",
	"org.apache.wicket" % "wicket-ioc" % "1.4.17",
	"org.apache.wicket" % "wicket-spring" % "1.4.17",
	"org.apache.wicket" % "wicket-extensions" % "1.4.17",
	"org.apache.wicket" % "wicket-datetime" % "1.4.17",
	"org.hibernate" % "hibernate" % "3.2.6.ga",
	"org.hibernate" % "ejb3-persistence" % "3.3.2.Beta1",
	"org.hibernate" % "hibernate-annotations" % "3.4.0.GA",
	"commons-dbcp" % "commons-dbcp" % "1.2.2",
	"org.springframework" % "spring" % "2.5.6",
	"org.mortbay.jetty" % "jetty" % "6.1.22" % "container",
	"org.mortbay.jetty" % "jetty-util" % "6.1.22",
	"org.mortbay.jetty" % "jetty-management" % "6.1.22",
	"hsqldb" % "hsqldb" % "1.8.0.10",
	"joda-time" % "joda-time" % "1.6",
	"joda-time" % "joda-time-hibernate" % "1.2",
	"junit" % "junit" % "3.8.2",
	"org.slf4j" % "slf4j-log4j12" % "1.4.2",
	"org.apache.poi" % "poi" % "3.6",
	"postgresql" % "postgresql" % "8.4-701.jdbc4"
)










