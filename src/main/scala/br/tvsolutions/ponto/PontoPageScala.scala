package br.tvsolutions.ponto

import java.io.OutputStream

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.protocol.http.WebResponse
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.DateTime

import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.util.GerarXLS

class PontoPageScala(usuarioLogado:Usuario, usuarioSelecionado:Usuario, dateBuscaInicio:DateTime, dateBuscaFim:DateTime, editavel:java.lang.Boolean) extends PontoBasePageScala(usuarioLogado) {
  val serialVersionUID: java.lang.Long = 1L

//  @SpringBean
//  var gerarXLS: GerarXLS = _

  var pontoListPanel = new PontoListPanelScala(usuarioSelecionado, usuarioLogado, dateBuscaInicio, dateBuscaFim, editavel.asInstanceOf[Boolean], this)
  add(pontoListPanel)

  var container = new WebMarkupContainer("container")
  container.setOutputMarkupId(true)
  add(container)
  
  var linkGerarXls = new Link("linkGerarXls") {
	override def onClick() {
//		 if(dateBuscaInicio!=null && dateBuscaFim!=null){
//				 var response = getResponse().asInstanceOf[WebResponse]
//				 response.setContentType("application/xls")
//				 response.setHeader("Content-Disposition", "attachment filename="+ usuarioSelecionado.getLogin() + ".xls")
//				 var servletOutputStream = response.getOutputStream()
//		    try {
//				 servletOutputStream.write(gerarXLS.GerarXLSnow(usuarioSelecionado, dateBuscaInicio, dateBuscaFim))
//				 servletOutputStream.flush()
//				 servletOutputStream.close()
//			}catch{
//				case e => e.printStackTrace
//			}
//		 }
	}
  }
  
  container.add(linkGerarXls)

  if(dateBuscaFim == null){
	 container.setVisible(false)
  }
  
  def this(usuario:Usuario, editavel:Boolean) = this(usuario,usuario,new DateTime(),null, editavel)
  
  def this(usuario:Usuario) = this(usuario,usuario,new DateTime(),null, false)
  
  def this(usuario:Usuario, dateBuscaInicio:DateTime) = this(usuario,usuario,dateBuscaInicio,null,false)

  def this(usuario:Usuario, dateBuscaInicio:DateTime,dateBuscaFim:DateTime , editavel:java.lang.Boolean) = this(usuario,usuario,dateBuscaInicio,null,editavel)

}