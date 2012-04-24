package br.controlpoint.pages.panels

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.{ListItem,ListView}
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import br.controlpoint.entities.{Ponto,Usuario}
import br.controlpoint.mediators.TPontoMediator
import org.apache.wicket.markup.html.form.Form
import javax.persistence.Entity
import org.joda.time.DateTime


class PontoEditListPanel(usuario:Usuario, dateBuscaInicio:DateTime, dateBuscaFim:DateTime) extends Panel("PontoEditListPanel") {

  val serialVersionUID = 1L;

  @SpringBean
  var pontoMediator:TPontoMediator = _

  var listData = pontoMediator.listaPontoUsuario(usuario, dateBuscaInicio, dateBuscaFim);
  
  var formEntrada = new Form("formEntrada") {
    override protected def onSubmit() {};
  };
  
  formEntrada.add(new ListView[Ponto]("listaPonto", listData) {
    val serialVersionUID = 1L;
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject();
      item.add(new Label("dataInicio", ponto.getDataInicio().toString("dd/MM/yyyy HH:mm:ss")));
      item.add(new Label("dataFim", ponto.getDataFim().toString("dd/MM/yyyy HH:mm:ss")));
    }
  });
  
  add(formEntrada);
}