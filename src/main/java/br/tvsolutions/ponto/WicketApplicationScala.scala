package br.tvsolutions.ponto

import org.apache.wicket.protocol.http.WebApplication
import br.tvsolutions.ponto.mediators.TPontoMediatorScala
import br.tvsolutions.ponto.mediators.TUsuarioMediatorScala
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import reflect._

@Component
class WicketApplicationScala extends WebApplication{
	@Autowired
	@BeanProperty 
	var usuarioMediatorScala:TUsuarioMediatorScala = _ 
	
	@Autowired
	@BeanProperty
	var pontoMediatorScala:TPontoMediatorScala = _ 
	
	def getHomePage() = classOf[LoginPageScala]
	
	override def init() = {
		addComponentInstantiationListener(new SpringComponentInjector(this))
		getDebugSettings().setAjaxDebugModeEnabled(false)
		getApplicationSettings().setPageExpiredErrorPage(classOf[LoginPageScala])
	}

}