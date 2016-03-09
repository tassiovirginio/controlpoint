package br.controlpoint.pages

import br.controlpoint.entities.Usuario
import br.controlpoint.pages.base.PontoBasePage
import br.controlpoint.pages.panels.PontoListPanel
import org.apache.wicket.markup.html.WebMarkupContainer
import org.joda.time.LocalDateTime


class PontoPage(usuarioLogado:Usuario, usuarioSelecionado:Usuario, dateBuscaInicio:LocalDateTime, dateBuscaFim:LocalDateTime, editavel:java.lang.Boolean) extends PontoBasePage(usuarioLogado) {

  add(new PontoListPanel(usuarioSelecionado, usuarioLogado, dateBuscaInicio, dateBuscaFim, editavel.asInstanceOf[Boolean], this))

//  var container = new WebMarkupContainer("container"){
//    setOutputMarkupId(true)
//  }
//  add(container)

//  var listData = pontoMediator.listaPontoUsuario(usuarioSelecionado, dateBuscaInicio, dateBuscaFim);
  
//  var export = new WebResource() {
//
//    override def getResourceStream:IResourceStream = {
//      var result = new StringBuffer()
//
//      for (ponto <- listData) {
//		    result.append(ponto.dataInicio.toString("HH:mm dd/MM/yyyy")+",")
//		    if(ponto.dataFim != null){
//		    	result.append(ponto.dataFim.toString("HH:mm dd/MM/yyyy"))
//		    }
//		    result.append("\n")
//	  }
//
//      return new StringResourceStream(result, "text/plain");
//    }
//
//    override def setHeaders(response: WebResponse) {
//      super.setHeaders(response);
//      response.setAttachmentHeader(usuarioSelecionado.login +  ".csv");
//    }
//  }

//  export.setCacheable(false);
//  container.add(new ResourceLink("linkGerarXls", export));
  
//  if(dateBuscaFim == null){
//	 container.setVisible(false)
//  }
  
  def this(usuario:Usuario, editavel:Boolean) = this(usuario,usuario,new LocalDateTime(),null, editavel)
  
  def this(usuario:Usuario) = this(usuario,usuario,new LocalDateTime(),null, false)
  
  def this(usuario:Usuario, dateBuscaInicio:LocalDateTime) = this(usuario,usuario,dateBuscaInicio,null,false)

  def this(usuario:Usuario, dateBuscaInicio:LocalDateTime,dateBuscaFim:LocalDateTime,editavel:java.lang.Boolean) = this(usuario,usuario,dateBuscaInicio,null,editavel)

}