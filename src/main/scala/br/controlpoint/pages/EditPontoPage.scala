package br.controlpoint.pages

import br.controlpoint.entities.Ponto
import br.controlpoint.pages.base.PontoBasePage
import br.controlpoint.pages.panels.EditPontoPanel
import org.apache.wicket.markup.html.WebPage

class EditPontoPage(ponto: Ponto, paginaOrigem: PontoBasePage) extends WebPage {

  add(new EditPontoPanel("panel", this.ponto, paginaOrigem))

}