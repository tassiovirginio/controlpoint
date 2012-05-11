package br.controlpoint.pages

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.{ChoiceRenderer,Form,ListChoice}
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.UsuarioMediator
import br.controlpoint.pages.base.PontoBasePage
import br.controlpoint.pages.panels.UsuarioPanel
import java.util.List

class UsuarioPage(usuario:Usuario, direto:Boolean) extends PontoBasePage(usuario){

  var usuarioSelecionado:Usuario =_

  var usuarioPanel:UsuarioPanel =_

  var container = new WebMarkupContainer("container")
  add(container)
  container.setVisible(!direto)

  usuarioPanel = new UsuarioPanel(this, usuario,direto)
  usuarioPanel.setOutputMarkupId(true)
  usuarioPanel.setVisible(false||direto)
  add(usuarioPanel)

  var form = new Form("form"){
    override protected def onSubmit() {
      usuarioPanel.setUsuarioSelecionado(usuarioSelecionado);
      usuarioPanel.setVisible(true);
    }
  }

  container.add(form)

  var listaUsuario:java.util.List[Usuario] = usuarioMediator.listaUsuarios

  var listChoice:ListChoice[Usuario] = new ListChoice[Usuario]("listaUsuario",new PropertyModel[Usuario](this, "usuarioSelecionado"), listaUsuario,new ChoiceRenderer[Usuario]("nome"),1);
  listChoice.setRequired(true)
  form.add(listChoice)

  var linkNovoUsuario = new Link("linkNovoUsuario"){
    override def onClick() {
      var usuario = new Usuario()
      usuarioPanel.setUsuarioSelecionado(usuario)
      usuarioPanel.setVisible(true)
    }
  };
  container.add(linkNovoUsuario)

  var feedbackPanel = new FeedbackPanel("feedback")
  container.add(feedbackPanel)

}