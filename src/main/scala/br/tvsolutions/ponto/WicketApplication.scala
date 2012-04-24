package br.tvsolutions.ponto

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.TPontoMediator
import br.tvsolutions.ponto.mediators.TUsuarioMediator
import javax.persistence.Entity
import br.tvsolutions.ponto.pages.LoginPage

@Component
class WicketApplicationScala extends WebApplication{

	@Autowired
	var usuarioMediator:TUsuarioMediator = _ 

	@Autowired
	var pontoMediator:TPontoMediator = _ 
	
	def getHomePage() = classOf[LoginPage]
	
	override def init() = {
		addComponentInstantiationListener(new SpringComponentInjector(this))
		getDebugSettings().setAjaxDebugModeEnabled(false)
		
		//Cadastro do Administrador do Sistema para Testes/Desenvolvimento
		var usuario = new Usuario
		usuario.adm = true
		usuario.email = "admin@email.com"
		usuario.login = "admin"
		usuario.senha = "admin"
		usuario.nome = "Admin"
		usuario.ips = "127.0.0.1"
		usuario.wallpaper = "wallpaper02"
		usuarioMediator.salvarUsuario(usuario)
		
		getApplicationSettings().setPageExpiredErrorPage(getHomePage())
		
	}

}