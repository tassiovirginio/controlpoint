package br.controlpoint.pages

import org.apache.wicket.markup.html.WebPage
import br.controlpoint.pages.panels.EditPontoPanel
import br.controlpoint.entities.Ponto
import br.controlpoint.pages.base.PontoBasePage

class EditPontoPage(ponto:Ponto, paginaOrigem:PontoBasePage) extends WebPage {
  add(new EditPontoPanel("panel",ponto,paginaOrigem))
}