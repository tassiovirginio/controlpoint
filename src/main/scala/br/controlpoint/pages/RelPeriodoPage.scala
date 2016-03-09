package br.controlpoint.pages

import java.util.{ArrayList, Date}

import br.controlpoint.entities.Usuario
import br.controlpoint.pages.base.PontoBasePage
import org.apache.wicket.extensions.markup.html.form.DateTextField
import org.apache.wicket.extensions.yui.calendar.DatePicker
import org.apache.wicket.markup.html.form.{ChoiceRenderer, Form, ListChoice}
import org.apache.wicket.model.PropertyModel
import org.joda.time.LocalDateTime

class RelPeriodoPage(usuario: Usuario, travarLista: java.lang.Boolean) extends PontoBasePage(usuario) {

  var usuarioSelecionado: Usuario = _
  var dataPesquisaInicio = new Date();

  usuarioLogado = usuario
  var dataPesquisaFim = new Date();
  var form = new Form("form") {
    override protected def onSubmit() {
      setResponsePage(new PontoPage(usuarioLogado, usuarioSelecionado, new LocalDateTime(dataPesquisaInicio), new LocalDateTime(dataPesquisaFim), false));
    }
  };
  var dateTextFieldInicio = new DateTextField("dataPesquisaInicio", new PropertyModel[Date](this, "dataPesquisaInicio"), "dd/MM/yy");
  add(form);
  var dateTextFieldFim = new DateTextField("dataPesquisaFim", new PropertyModel[Date](this, "dataPesquisaFim"), "dd/MM/yy");
  dateTextFieldInicio.add(new DatePicker());
  dateTextFieldInicio.setRequired(true);
  form.add(dateTextFieldInicio);
  var listaUsuario = new ArrayList[Usuario]();
  dateTextFieldFim.add(new DatePicker());
  dateTextFieldFim.setRequired(true);
  form.add(dateTextFieldFim);
  var listChoice = new ListChoice[Usuario]("listaUsuario", new PropertyModel[Usuario](this, "usuarioSelecionado"), listaUsuario, new ChoiceRenderer[Usuario]("nome"), 1);

  if (usuarioLogado.adm.asInstanceOf[Boolean]) {
    listaUsuario.addAll(usuarioMediator.listaUsuarios);
  } else {
    listaUsuario.add(usuarioLogado);
  }

  def this(usuario: Usuario) = this(usuario, false)
  listChoice.setRequired(true);
  if (travarLista.asInstanceOf[Boolean]) {
    usuarioSelecionado = usuarioLogado;
    listChoice.setVisible(false);
  }
  form.add(listChoice);

}