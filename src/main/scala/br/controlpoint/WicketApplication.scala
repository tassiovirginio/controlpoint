package br.controlpoint

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.{TPontoMediator,TUsuarioMediator}
import br.controlpoint.pages.LoginPage
import javax.persistence.Entity

@Component
class WicketApplication extends WebApplication{

	@Autowired
	var usuarioMediator:TUsuarioMediator = _ 

	@Autowired
	var pontoMediator:TPontoMediator = _ 
	
	def getHomePage = classOf[LoginPage]
	
	override def init = {
		addComponentInstantiationListener(new SpringComponentInjector(this))
		getDebugSettings().setAjaxDebugModeEnabled(false)
		getApplicationSettings().setPageExpiredErrorPage(getHomePage())
		createAdmin
	}
	
	def createAdmin = {
		var usuario = new Usuario
		usuario.adm = true
		usuario.email = "admin@email.com"
		usuario.login = "admin"
		usuario.senha = "admin"
		usuario.nome = "Admin"
		usuario.ips = "127.0.0.1"
		usuario.wallpaper = "wallpaper02"
		usuarioMediator.salvarUsuario(usuario)
	}

}