package br.controlpoint.pages.base

import java.util.{List, Locale}

import br.controlpoint.entities.{Ponto, Usuario}
import br.controlpoint.mediators.{PontoMediator, UsuarioMediator}
import br.controlpoint.pages.{LoginPage, PontoPage, RelDiaPage, RelPeriodoPage, SobrePage, UsuarioPage}
import org.apache.wicket.AttributeModifier
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.HiddenField
import org.apache.wicket.markup.html.list.{ListItem, ListView}
import org.apache.wicket.markup.html.{WebComponent, WebPage}
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.LocalDateTime
import org.wicketstuff.scala.DSLWicket

import scala.collection.JavaConversions.asScalaBuffer

class PontoBasePage(usuario: Usuario) extends WebPage with DSLWicket {

  val img = new WebComponent("wallpaper")
  val formContador = form("form_contador")
  private val LOCALE_BR = new Locale("pt_BR")
  @SpringBean var usuarioMediator: UsuarioMediator = _
  @SpringBean var pontoMediator: PontoMediator = _
  var usuarioLogado: Usuario = null

  if (getSession().isSessionInvalidated()) {
    usuarioLogado = null
    getSession().invalidate()
    //    getRequestCycle().setRedirect(true)
  }

  usuarioLogado = usuarioMediator.getUsuarioForId(usuario.id)
  //    this.add(CSSPackageResource.getHeaderContribution("css/base.css"))
  //    val img = Image("wallpaper",null,null)
  var contadorMilesegundos: String = null
  img.add(new AttributeModifier("src", "../imagens/wallpapers/" + usuarioLogado.wallpaper + ".jpg"))
  add(img)

  if (usuarioLogado == null) {
    getSession().invalidate()
    //    getRequestCycle().setRedirect(true)
    setResponsePage(new LoginPage())
  }

  getSession().setLocale(LOCALE_BR)
  this.usuarioLogado = usuarioLogado

  label("labelusuario", "(  " + usuarioLogado.nome + "  )")

  link("lkmnSair", () => {
    usuarioLogado = null; getSession().invalidate()
  })

  link("lkmnCadUsuario", () => setResponsePage(new UsuarioPage(usuarioLogado, false)))
    .setVisible(usuarioLogado.adm.asInstanceOf[Boolean])

  link("lkmnMeusDados", () => setResponsePage(new UsuarioPage(usuarioLogado, true)))

  link("lkmnRelPeriodo", () => setResponsePage(new RelPeriodoPage(usuarioLogado)))

  link("lkmnRelDia", () => setResponsePage(new RelDiaPage(usuarioLogado)))

  link("lkmnPonto", () => setResponsePage(new PontoPage(usuarioLogado, true)))

  link("lkmnSobre", () => setResponsePage(new SobrePage(usuarioLogado)))
  var dataBusca = new LocalDateTime();

  var listaPonto = pontoMediator.listaPontoUsuario(usuarioLogado, dataBusca);

  for (ponto <- listaPonto) {
    if (ponto.dataInicio != null && ponto.dataFim == null) {
      contadorMilesegundos = ponto.dataInicio.getMillisOfSecond() + ""
    }
  }
  var listaPonto2: List[Ponto] = pontoMediator.listaPontoUsuario(new LocalDateTime())
  formContador.add(new HiddenField("milissegundos", new PropertyModel[String](this, "contadorMilesegundos")))
  add(formContador)

  override def getLocale() = LOCALE_BR

  add(new ListView[Ponto]("listaPonto", listaPonto2) {
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject()
      item.add(new Label("nome", ponto.usuario.nome))
    }
  })

}