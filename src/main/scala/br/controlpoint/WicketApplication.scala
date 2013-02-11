package br.controlpoint

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.google.code.jqwicket.JQContributionConfig
import br.controlpoint.mediators.TPontoMediator
import br.controlpoint.mediators.TUsuarioMediator
import br.controlpoint.pages.LoginPage
import com.google.code.jqwicket.JQComponentOnBeforeRenderListener
import br.controlpoint.pages.LoginPage
import br.controlpoint.entities._
import br.controlpoint.entities.Implicits._
import scala.slick.driver.HsqldbDriver.simple._
import Database.threadLocalSession
import org.joda.time.DateTime

@Component
class WicketApplication extends WebApplication {
  
  @Autowired
  var usuarioMediator: TUsuarioMediator = _

  @Autowired
  var pontoMediator: TPontoMediator = _

  def getHomePage = classOf[LoginPage]

  override def init = {
    addComponentInstantiationListener(new SpringComponentInjector(this))
    getApplicationSettings().setPageExpiredErrorPage(getHomePage())
    getRequestCycleSettings().setResponseRequestEncoding("UTF-8")
    getMarkupSettings().setDefaultMarkupEncoding("UTF-8")
    getDebugSettings().setAjaxDebugModeEnabled(true)

    var config = new JQContributionConfig("js/jquery-1.5.1.min.js")
    config.withJQueryUiJs("js/jquery-ui-1.8.12.custom.min.js")
      .withJQueryUiCss("css/jquery-ui.css");

    addPreComponentOnBeforeRenderListener(new JQComponentOnBeforeRenderListener(config));
    
    slick
    
    //createAdmin
  }
  
  def slick {
    println("Iniciando o Slick")
    
    var session:Session = database.createSession
    
    session.withTransaction{
      println("Criando as Tabelas")
      Pontos.ddl.create(session)
      Usuarios.ddl.create(session)
      println(Pontos.ddl.createStatements.foreach(println))
      println(Usuarios.ddl.createStatements.foreach(println))
    }


    var usuario = new Usuario(0,true,"admin","admin@email.com","admin","admin","127.0.0.1,192.168.171.191",
        true,false,true,"wallpaper01",new DateTime,new DateTime,new DateTime,new DateTime)

    Usuarios.salvar(usuario)



    
  }

  def createAdmin = {

    var usuario:Usuario = usuarioMediator.buscarUsuarioPorLogin("admin")

    if (usuario == null) {
      var usuario = new Usuario(0,true,"admin","admin@email.com","admin","admin","127.0.0.1,192.168.171.191",
        true,false,true,"wallpaper01",new DateTime,new DateTime,new DateTime,new DateTime)
      usuarioMediator.salvarUsuario(usuario)
    }
  }

}