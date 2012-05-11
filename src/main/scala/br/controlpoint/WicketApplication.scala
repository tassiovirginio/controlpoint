package br.controlpoint

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.google.code.jqwicket.JQContributionConfig
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.TPontoMediator
import br.controlpoint.mediators.TUsuarioMediator
import br.controlpoint.pages.LoginPage
import com.google.code.jqwicket.JQComponentOnBeforeRenderListener
import br.controlpoint.pages.LoginPage

@Component
class WicketApplication extends WebApplication{

	@Autowired
	var usuarioMediator:TUsuarioMediator = _ 

	@Autowired
	var pontoMediator:TPontoMediator = _ 
	
	def getHomePage = classOf[LoginPage]
	
	override def init = {
		addComponentInstantiationListener(new SpringComponentInjector(this))
		getApplicationSettings().setPageExpiredErrorPage(getHomePage())
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8")
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8")
		getDebugSettings().setAjaxDebugModeEnabled(true)

		 var config = new JQContributionConfig("/js/jquery-1.5.1.min.js") 
        config.withJQueryUiJs("/js/jquery-ui-1.8.12.custom.min.js") 
		.withJQueryUiCss("/css/jquery-ui.css"); 
		
        addPreComponentOnBeforeRenderListener(new JQComponentOnBeforeRenderListener(config));
        
		createAdmin
	}
	
	def createAdmin = {
	  
	  var usuario = usuarioMediator.buscarUsuarioPorLogin("admin")
	  
	  if(usuario == null){
		var usuario = new Usuario
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