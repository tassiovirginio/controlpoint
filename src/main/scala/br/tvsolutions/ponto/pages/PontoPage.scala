package br.tvsolutions.ponto.pages

import org.apache.wicket.markup.html.WebMarkupContainer
import org.joda.time.DateTime
import br.tvsolutions.ponto.entities.Usuario
import org.apache.wicket.markup.html.link.Link
import br.tvsolutions.ponto.pages.base.PontoBasePage
import br.tvsolutions.ponto.pages.panels.PontoListPanel

class PontoPage(usuarioLogado:Usuario, usuarioSelecionado:Usuario, dateBuscaInicio:DateTime, dateBuscaFim:DateTime, editavel:java.lang.Boolean) extends PontoBasePage(usuarioLogado) {
  val serialVersionUID: java.lang.Long = 1L

  add(new PontoListPanel(usuarioSelecionado, usuarioLogado, dateBuscaInicio, dateBuscaFim, editavel.asInstanceOf[Boolean], this))

  var container = new WebMarkupContainer("container"){
    setOutputMarkupId(true)
  }
  add(container)

  var linkGerarXls = new Link("linkGerarXls") {
     override def onClick(){}
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