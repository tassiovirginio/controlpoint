package br.controlpoint.pages

import br.controlpoint.entities.Usuario
import br.controlpoint.pages.base.PontoBasePage
import br.controlpoint.pages.panels.UsuarioPanel
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.{ChoiceRenderer, Form, ListChoice}
import org.apache.wicket.model.PropertyModel

class UsuarioPage(usuario: Usuario, direto: Boolean) extends PontoBasePage(usuario) {

  var usuarioSelecionado: Usuario = _

  var usuarioPanel: UsuarioPanel = _

  usuarioPanel = new UsuarioPanel(this, usuario, direto)
  usuarioPanel.setOutputMarkupId(true)
  usuarioPanel.setVisible(false || direto)
  add(usuarioPanel)

  var form = new Form("form") {
    override protected def onSubmit() {
      usuarioPanel.setUsuarioSelecionado(usuarioSelecionado)
      usuarioPanel.setVisible(true)
    }
  }

  var container = new WebMarkupContainer("container")
  container.setVisible(!direto)
  container.add(form)
  add(container)

  var listaUsuario: java.util.List[Usuario] = usuarioMediator.listaUsuarios

  var listChoice: ListChoice[Usuario] = new ListChoice[Usuario]("listaUsuario", new PropertyModel[Usuario](this, "usuarioSelecionado"), listaUsuario, new ChoiceRenderer[Usuario]("nome"), 1)
  listChoice.setRequired(true)
  form.add(listChoice)

  container.add {
    link("linkNovoUsuario", () => {
      usuarioPanel.setUsuarioSelecionado(new Usuario())
      usuarioPanel.setVisible(true)
    })
    feedback("feedback")
  }

}