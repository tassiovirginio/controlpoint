name := "Control Point"

version := "1.1"

scalaVersion := "2.9.2"

unmanagedResourceDirectories in Compile += new File("src/main/scala")

defaultExcludes in unmanagedResources := ("*.scala" | "*.java")
  
seq(webSettings :_*)

resolvers += "Google Code" at "http://jqwicket.googlecode.com/svn/m2-repo/releases/"

libraryDependencies ++= { 
	val wicketVersion = "1.4.21"
	val jettyVersion = "6.1.22"
	Seq(
		"org.apache.wicket" % "wicket" % wicketVersion,
		"org.apache.wicket" % "wicket-ioc" % wicketVersion,
		"org.apache.wicket" % "wicket-spring" % wicketVersion,
		"org.apache.wicket" % "wicket-extensions" % wicketVersion,
		"org.apache.wicket" % "wicket-datetime" % wicketVersion,
		"org.mortbay.jetty" % "jetty" % jettyVersion % "container",
		"org.mortbay.jetty" % "jetty-util" % jettyVersion,
		"org.mortbay.jetty" % "jetty-management" % jettyVersion,
		"hsqldb" % "hsqldb" % "1.8.0.10",
		"com.google.code.jqwicket" % "jqwicket" % "0.6",
		"org.hibernate" % "hibernate" % "3.2.6.ga",
		"org.hibernate" % "ejb3-persistence" % "3.3.2.Beta1",
		"org.hibernate" % "hibernate-annotations" % "3.4.0.GA",
		"commons-dbcp" % "commons-dbcp" % "1.2.2",
		"org.springframework" % "spring" % "2.5.6",
		"joda-time" % "joda-time" % "1.6",
		"joda-time" % "joda-time-hibernate" % "1.2",
		"junit" % "junit" % "3.8.2",
		"org.slf4j" % "slf4j-log4j12" % "1.4.2"
	)
}










