package br.controlpoint.pages.panels

import br.controlpoint.entities.{Ponto, Usuario}
import br.controlpoint.mediators.PontoMediator
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.list.{ListItem, ListView}
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.LocalDateTime


class PontoEditListPanel(usuario:Usuario, dateBuscaInicio:LocalDateTime, dateBuscaFim:LocalDateTime) extends Panel("PontoEditListPanel") {

  @SpringBean
  var pontoMediator:PontoMediator = _

  var listData = pontoMediator.listaPontoUsuario(usuario, dateBuscaInicio, dateBuscaFim);
  
  var formEntrada = new Form("formEntrada") {
    override protected def onSubmit() {}
  };
  
  formEntrada.add(new ListView[Ponto]("listaPonto", listData) {
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject()
      item.add(new Label("dataInicio", ponto.dataInicio.toString("dd/MM/yyyy HH:mm:ss")))
      item.add(new Label("dataFim", ponto.dataFim.toString("dd/MM/yyyy HH:mm:ss")))
    }
  })
  
  add(formEntrada)
}