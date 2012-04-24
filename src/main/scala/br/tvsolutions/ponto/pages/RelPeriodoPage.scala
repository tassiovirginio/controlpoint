package br.tvsolutions.ponto.pages

import java.util.ArrayList
import java.util.Date
import java.util.List
import org.apache.wicket.extensions.markup.html.form.DateTextField
import org.apache.wicket.extensions.yui.calendar.DatePicker
import org.apache.wicket.markup.html.form.ChoiceRenderer
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.ListChoice
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.DateTime
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.TUsuarioMediator
import br.tvsolutions.ponto.pages.base.PontoBasePage

class RelPeriodoPage(usuario: Usuario, travarLista: java.lang.Boolean) extends PontoBasePage(usuario) {

  val serialVersionUID = 1L
  var usuarioSelecionado: Usuario = _

  def this(usuario: Usuario) = this(usuario, false)

  setUsuarioLogado(usuario);

  var dataPesquisaInicio = new Date();
  var dataPesquisaFim = new Date();

  var form = new Form("form") {
    override protected def onSubmit() {
      setResponsePage(new PontoPage(getUsuarioLogado(), usuarioSelecionado, new DateTime(dataPesquisaInicio), new DateTime(dataPesquisaFim), false));
    }
  };
  add(form);

  var dateTextFieldInicio = new DateTextField("dataPesquisaInicio", new PropertyModel[Date](this, "dataPesquisaInicio"), "dd/MM/yy");
  dateTextFieldInicio.add(new DatePicker());
  dateTextFieldInicio.setRequired(true);
  form.add(dateTextFieldInicio);

  var dateTextFieldFim = new DateTextField("dataPesquisaFim", new PropertyModel[Date](this, "dataPesquisaFim"), "dd/MM/yy");
  dateTextFieldFim.add(new DatePicker());
  dateTextFieldFim.setRequired(true);
  form.add(dateTextFieldFim);

  var listaUsuario = new ArrayList[Usuario]();

  if (getUsuarioLogado().adm.asInstanceOf[Boolean]) {
    listaUsuario.addAll(usuarioMediator.listaUsuarios);
  } else {
    listaUsuario.add(getUsuarioLogado());
  }

  var listChoice = new ListChoice[Usuario]("listaUsuario", new PropertyModel[Usuario](this, "usuarioSelecionado"), listaUsuario, new ChoiceRenderer[Usuario]("nome"), 1);
  listChoice.setRequired(true);
  if (travarLista.asInstanceOf[Boolean]) {
    usuarioSelecionado = getUsuarioLogado();
    listChoice.setVisible(false);
  }
  form.add(listChoice);

}