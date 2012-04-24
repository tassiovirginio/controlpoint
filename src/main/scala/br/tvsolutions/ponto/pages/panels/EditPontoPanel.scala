package br.tvsolutions.ponto.pages.panels

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.DateTime
import br.tvsolutions.ponto.entities.Ponto
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.TPontoMediator
import br.tvsolutions.ponto.util.DateTimeField24h
import org.apache.wicket.markup.html.form._
import java.util.Date
import java.util.Locale
import br.tvsolutions.ponto.pages.base.PontoBasePage
import br.tvsolutions.ponto.pages.base.PontoBasePage
import br.tvsolutions.ponto.mediators.TPontoMediator

class EditPontoPanel(id:String, ponto:Ponto, modal:ModalWindow, paginaOrigem:PontoBasePage) extends Panel(id) {

  var form = new Form[Usuario]("form");

  var dateHoraSaida:Date = _

  var timeEndNull:Boolean =_

  var container:WebMarkupContainer = _

  @SpringBean
  var pontoMediator: TPontoMediator = _

  var dateHoraEntrada = ponto.getDataInicio().toDate();
  if (ponto.getDataFim() != null)
    this.dateHoraSaida = ponto.getDataFim().toDate();

  add(form);
  form.setOutputMarkupId(true);

  form.add(new AjaxButton("ajax-button", form) {

    def onSubmit(target: AjaxRequestTarget, form: Form[_]) {
      ponto.setDataInicio(new DateTime(dateHoraEntrada))
      if (timeEndNull) {
        ponto.setDataFim(null)
      }else{
    	ponto.setDataFim(new DateTime(dateHoraSaida))
      }
      pontoMediator.salvarPonto(ponto)
      modal.close(target)
    }

    override def onError(target: AjaxRequestTarget, form: Form[_]) {
      println("Erro")
    }
  }
    );
  getSession().setLocale(new Locale("pt", "BR"));

  var textFieldHoraEntrada = new DateTimeField24h("textFieldHoraEntrada", new PropertyModel(this, "dateHoraEntrada"));
  textFieldHoraEntrada.setRequired(true);
  form.add(textFieldHoraEntrada);

  container = new WebMarkupContainer("container");

  var textFieldHoraSaida = new DateTimeField24h("textFieldHoraSaida", new PropertyModel(this, "dateHoraSaida"));
  container.add(textFieldHoraSaida);
  var checknull = new CheckBox("endTimeisNull",new PropertyModel(this, "timeEndNull"))
  container.add(checknull);
  form.add(container);
  
  timeEndNull = ponto.getDataFim() == null

}