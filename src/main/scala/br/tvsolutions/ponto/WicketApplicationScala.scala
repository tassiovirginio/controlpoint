package br.tvsolutions.ponto

import org.apache.wicket.protocol.http.WebApplication
import br.tvsolutions.ponto.mediators.TPontoMediatorScala
import br.tvsolutions.ponto.mediators.TUsuarioMediatorScala
import br.tvsolutions.ponto.entities.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.apache.wicket.spring.injection.annot.SpringComponentInjector

@Component
class WicketApplicationScala extends WebApplication{

	@Autowired
	var usuarioMediatorScala:TUsuarioMediatorScala = _ 

	@Autowired
	var pontoMediatorScala:TPontoMediatorScala = _ 
	
	def getHomePage() = classOf[LoginPageScala]
	
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
		usuarioMediatorScala.salvarUsuario(usuario)
		
		getApplicationSettings().setPageExpiredErrorPage(getHomePage())
		
	}

}