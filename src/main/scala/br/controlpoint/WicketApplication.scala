package br.controlpoint

import java.util.logging.Logger

import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.{TPontoMediator, TUsuarioMediator}
import br.controlpoint.pages._
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.response.filter.AjaxServerAndClientTimeFilter
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.apache.wicket.util.time.Duration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WicketApplication extends WebApplication {

  private[controlpoint] var log: Logger = Logger.getLogger(classOf[WicketApplication].getName)

  @Autowired private var usuarioMediator: TUsuarioMediator = _
  @Autowired private var pontoMediator: TPontoMediator = _

  override def getHomePage = classOf[LoginPage]

  override def init {

    log.info("\n" + "********************************************************************\n" + "***                      Carregando o Sistema                    ***\n" + "********************************************************************")

    getResourceSettings.setResourcePollFrequency(Duration.ONE_SECOND)
    getApplicationSettings.setUploadProgressUpdatesEnabled(true)
    getRequestCycleSettings.addResponseFilter(new AjaxServerAndClientTimeFilter)
    getComponentInstantiationListeners.add(new SpringComponentInjector(this))
    getRequestCycleSettings.setResponseRequestEncoding("UTF-8")
    getMarkupSettings.setDefaultMarkupEncoding("UTF-8")
    getDebugSettings.setAjaxDebugModeEnabled(false)
    getResourceSettings.setThrowExceptionOnMissingResource(false)
    getDebugSettings.setDevelopmentUtilitiesEnabled(true)
    getMarkupSettings.setStripWicketTags(true)

    mountPage("/login/", classOf[LoginPage])
    mountPage("/editponto/", classOf[EditPontoPage])
    mountPage("/ponto/", classOf[PontoPage])
    mountPage("/relatoriodia/", classOf[RelDiaPage])
    mountPage("/relatorioperiodo/", classOf[RelPeriodoPage])
    mountPage("/sobre/", classOf[SobrePage])
    mountPage("/usuario/", classOf[UsuarioPage])

    createAdmin
  }

  def createAdmin {

    log.info("\n" + "********************************************************************\n" + "***  Criando o usuário Administrador - login:admin senha:admin   ***\n" + "********************************************************************")

    val usuario = usuarioMediator.buscarUsuarioPorLogin("admin")

    if (usuario == null) {
      val usuario = new Usuario
      usuario.adm = true
      usuario.email = "admin@email.com"
      usuario.login = "admin"
      usuario.senha = "admin"
      usuario.nome = "Admin"
      usuario.ips = "127.0.0.1,192.168.171.191"
      usuario.wallpaper = "wallpaper01"
      usuarioMediator.salvarUsuario(usuario)
    }
  }

}