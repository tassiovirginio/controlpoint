package br.controlpoint.pages.panels

import java.util.Locale
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.format.DateTimeFormat
import com.google.code.jqwicket.ui.datetimepicker.DateTimePickerOptions
import com.google.code.jqwicket.ui.datetimepicker.DateTimePickerTextField
import br.controlpoint.entities.Ponto
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.TPontoMediator
import br.controlpoint.pages.base.PontoBasePage
import javax.persistence.Entity
import br.controlpoint.pages.EditPontoPage

class EditPontoPanel(id:String, ponto:Ponto, paginaOrigem:PontoBasePage) extends Panel(id) {

  var dateHoraSaida:String = _
  
  val dateFormatter = "dd/MM/yyyy HH:mm"
  
  var dateHoraEntrada = ponto.getDataInicio().toString(dateFormatter)

  var timeEndNull:Boolean =_

  var container:WebMarkupContainer = _

  @SpringBean
  var pontoMediator:TPontoMediator = _

  if (ponto.getDataFim() != null) {
    this.dateHoraSaida = ponto.getDataInicio().toString(dateFormatter)
  }
    
  
  var form = new Form[Usuario]("form"){
    override def onSubmit(){
    	val formatter = DateTimeFormat.forPattern(dateFormatter)
	      ponto.setDataInicio(formatter.parseDateTime(dateHoraEntrada))
	      
	      if (timeEndNull) {
	        ponto.setDataFim(null)
	      }else{
	    	ponto.setDataFim(formatter.parseDateTime(dateHoraSaida))
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
  
  var dateOpt = new DateTimePickerOptions()
  dateOpt.dateFormat("dd/mm/yy")
  dateOpt.timeFormat("hh:mm")

  var textFieldHoraEntrada = new DateTimePickerTextField("textFieldHoraEntrada",dateOpt)
  textFieldHoraEntrada.setModel(new PropertyModel(this, "dateHoraEntrada"))
  textFieldHoraEntrada.setRequired(true);
  form.add(textFieldHoraEntrada);

  container = new WebMarkupContainer("container");

  var textFieldHoraSaida = new DateTimePickerTextField("textFieldHoraSaida",dateOpt)
  textFieldHoraSaida.setModel(new PropertyModel(this, "dateHoraSaida"))
  container.add(textFieldHoraSaida);
  var checknull = new CheckBox("endTimeisNull",new PropertyModel(this, "timeEndNull"))
  container.add(checknull);
  form.add(container);
  
  timeEndNull = ponto.getDataFim() == null

}