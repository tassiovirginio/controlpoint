package br.tvsolutions.ponto.pages

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.protocol.http.request.WebClientInfo
import org.apache.wicket.spring.injection.annot.SpringBean
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.TUsuarioMediator
import javax.persistence.Entity
import br.tvsolutions.ponto.mediators.TUsuarioMediator

class LoginPage extends WebPage {

  val serialVersionUID = 1L;

  @SpringBean
  var usuarioMediatorScala: TUsuarioMediator = _

  var info2 = getSession.getClientInfo.asInstanceOf[WebClientInfo]

  var usuario = new Usuario()

  getSession().clear()

  var form = new Form[Usuario]("form") {
    val serialVersionUID = 1L
    override protected def onSubmit() {
      var usuarioLogado = usuarioMediatorScala.fazerLogin(usuario)
      if (usuarioLogado != null) {
        var teste = info2.getProperties().getRemoteAddress()
        var teste2 = teste.replace("."," ").split(" ")
        teste = teste2(0) + "." + teste2(1) + "." + teste2(2) + "."
        if (usuarioLogado.ips.contains(teste)) {
          setResponsePage(new PontoPage(usuarioLogado,true))
        } else {
          info("Você não tem acesso desse ip!")
        }
      } else {
        info("Login Incorretor!")
      }
    }
  }

  var login = new TextField[String]("login", new PropertyModel[String](usuario, "login"))
  login.setRequired(true)
  form.add(login)

  form.add(new PasswordTextField("senha", new PropertyModel[String](usuario, "senha")))

  form.add(new FeedbackPanel("feedback"))

  this.add(form)

}