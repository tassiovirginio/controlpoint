package br.controlpoint.pages.panels

import java.util.Locale

import br.controlpoint.entities.{Ponto, Usuario}
import br.controlpoint.mediators.TPontoMediator
import br.controlpoint.pages.EditPontoPage
import br.controlpoint.pages.base.PontoBasePage
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.{CheckBox, Form, TextField}
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.format.DateTimeFormat

class EditPontoPanel(id:String, ponto:Ponto, paginaOrigem:PontoBasePage) extends Panel(id) {

  var dateHoraSaida:String = _
  
  val dateFormatter = "dd/MM/yyyy HH:mm"
  
  var dateHoraEntrada = ponto.dataInicio.toString(dateFormatter)

  var timeEndNull:Boolean =_

  var container:WebMarkupContainer = _

  @SpringBean
  var pontoMediator:TPontoMediator = _

  if (ponto.dataFim != null) {
    this.dateHoraSaida = ponto.dataInicio.toString(dateFormatter)
  }
    
  
  var form = new Form[Usuario]("form"){
    override def onSubmit(){
    	val formatter = DateTimeFormat.forPattern(dateFormatter)
	      ponto.dataInicio = formatter.parseLocalDateTime(dateHoraEntrada)
	      
	      if (timeEndNull) {
	        ponto.dataFim = null
	      }else{
	    	ponto.dataFim = formatter.parseLocalDateTime(dateHoraSaida)
	      }
	      
    	println(dateHoraEntrada.toString)
	    println(dateHoraSaida.toString)
	    pontoMediator.salvarPonto(ponto)
//	    setResponsePage(paginaOrigem)
	    setResponsePage(new EditPontoPage(ponto,paginaOrigem))
	    
    }
  }

  add(form);
  form.setOutputMarkupId(true);

  getSession().setLocale(new Locale("pt", "BR"));
  
  form.add(new TextField[String]("textFieldHoraEntrada", new PropertyModel[String](this, "dateHoraEntrada")).setRequired(true))

  container = new WebMarkupContainer("container");

  form.add(new TextField[String]("textFieldHoraSaida", new PropertyModel[String](this, "dateHoraSaida")).setRequired(true))

  var checknull = new CheckBox("endTimeisNull",new PropertyModel(this, "timeEndNull"))
  container.add(checknull);
  form.add(container);
  
  timeEndNull = ponto.dataFim == null

}