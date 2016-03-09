package br.controlpoint

import org.apache.wicket.util.time.Duration
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.bio.SocketConnector
import org.eclipse.jetty.webapp.WebAppContext

object Start {

  def main(args: Array[String]) {
    val timeout: Int = Duration.ONE_HOUR.getMilliseconds.toInt
    var webPort: String = System.getenv("PORT")
    if (webPort == null || webPort.isEmpty) {
      webPort = "8080"
    }
    val server: Server = new Server
    val connector: SocketConnector = new SocketConnector
    connector.setMaxIdleTime(timeout)
    connector.setSoLingerTime(-1)
    connector.setPort(Integer.valueOf(webPort))
    server.addConnector(connector)
    val bb: WebAppContext = new WebAppContext
    bb.setServer(server)
    bb.setContextPath("/")
    bb.setWar("src/main/webapp")
    server.setHandler(bb)
    try {
      server.start
      server.join
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
  }
}