package br.controlpoint.pages

import org.apache.wicket.markup.html.form.{Form,PasswordTextField,TextField}
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.protocol.http.request.WebClientInfo
import org.apache.wicket.spring.injection.annot.SpringBean
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.TUsuarioMediator
import javax.persistence.Entity
import br.controlpoint.pages.panels.EditPontoPanel
import br.controlpoint.entities.Ponto
import br.controlpoint.pages.base.PontoBasePage

class EditPontoPage(ponto:Ponto, paginaOrigem:PontoBasePage) extends WebPage {

  add(new EditPontoPanel("panel",this.ponto,paginaOrigem))

}