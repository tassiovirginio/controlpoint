import sbt._
import Keys._
import com.github.siasia._
import PluginKeys._

object DummySbtScalaWicket extends Build {
  
  val auxxTask = TaskKey[Unit]("auxx")

  val setngs = Seq(
    auxxTask <<= (copyResources in Compile, auxCompile in Compile) map {
      (c, p) =>
    }
  )

  val root = Project("root", file(".")) settings(setngs:_*)

  override val projects = Seq(root)

}



