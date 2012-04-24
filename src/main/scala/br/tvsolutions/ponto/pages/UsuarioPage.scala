package br.tvsolutions.ponto.pages

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.ChoiceRenderer
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.ListChoice
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import java.util.List
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.UsuarioMediator
import br.tvsolutions.ponto.pages.base.PontoBasePage
import br.tvsolutions.ponto.pages.panels.UsuarioPanel

class UsuarioPage(usuario:Usuario, direto:Boolean) extends PontoBasePage(usuario){

  val serialVersionUID:Long = 1L;

  var usuarioSelecionado:Usuario =_

  var usuarioPanel:UsuarioPanel =_

  var container = new WebMarkupContainer("container")
  add(container)
  container.setVisible(!direto)

  usuarioPanel = new UsuarioPanel(this, usuario,direto);
  usuarioPanel.setOutputMarkupId(true);
  usuarioPanel.setVisible(false||direto);
  add(usuarioPanel);

  var form = new Form("form"){
    override protected def onSubmit() {
      usuarioPanel.setUsuarioSelecionado(usuarioSelecionado);
      usuarioPanel.setVisible(true);
    }
  };

  container.add(form);

  var listaUsuario:java.util.List[Usuario] = usuarioMediator.listaUsuarios

  var listChoice:ListChoice[Usuario] = new ListChoice[Usuario]("listaUsuario",new PropertyModel[Usuario](this, "usuarioSelecionado"), listaUsuario,new ChoiceRenderer[Usuario]("nome"),1);
  listChoice.setRequired(true);
  form.add(listChoice);

  var linkNovoUsuario = new Link("linkNovoUsuario"){
    override def onClick() {
      var usuario = new Usuario();
      usuarioPanel.setUsuarioSelecionado(usuario);
      usuarioPanel.setVisible(true);
    }
  };
  container.add(linkNovoUsuario);

  var feedbackPanel = new FeedbackPanel("feedback");
  container.add(feedbackPanel);

}