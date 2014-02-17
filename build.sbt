name := "Kennerspiel-Interface"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.guava" % "guava" % "15.0"
)

play.Project.playJavaSettings

publishTo := Some(Resolver.file("file", new File("/srv/www/philihp.com/public_html/repo")))
