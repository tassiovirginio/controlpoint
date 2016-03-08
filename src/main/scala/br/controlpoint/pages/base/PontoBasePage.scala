package br.controlpoint.pages.base

import java.util.{List,Locale}
import scala.collection.JavaConversions.asScalaBuffer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Form,HiddenField}
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.markup.html.list.{ListItem,ListView}
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.{LocalDateTime, DateTime}
import br.controlpoint.entities.{Ponto,Usuario}
import br.controlpoint.pages.{LoginPage,PontoPage,RelDiaPage,SobrePage,UsuarioPage}
import org.apache.wicket.markup.html.link.Link
import br.controlpoint.pages.RelPeriodoPage
import br.controlpoint.mediators.{TPontoMediator,TUsuarioMediator}

class PontoBasePage(usuario: Usuario) extends WebPage {

  @SpringBean
  var usuarioMediator: TUsuarioMediator = _

  @SpringBean
  var pontoMediator: TPontoMediator = _

  var usuarioLogado: Usuario = null

  private val LOCALE_BR = new Locale("pt_BR")

  var contadorMilesegundos: String = null

  override def getLocale() = LOCALE_BR

  if (getSession().isSessionInvalidated()) {
    usuarioLogado = null
    getSession().invalidate()
//    getRequestCycle().setRedirect(true)
  }

  usuarioLogado = usuarioMediator.getUsuarioForId(usuario.id)
//  this.add(CSSPackageResource.getHeaderContribution("css/base.css"))
//  val img = Image("wallpaper",Resouce)
//  img.add(new SimpleAttributeModifier("src", "imagens/wallpapers/" + usuarioLogado.wallpaper + ".jpg"))
//  add(img)

  if (usuarioLogado == null) {
    getSession().invalidate()
//    getRequestCycle().setRedirect(true)
    setResponsePage(new LoginPage())
  }

  getSession().setLocale(LOCALE_BR)
  this.usuarioLogado = usuarioLogado

  add(new Label("labelusuario", "(  " + usuarioLogado.nome + "  )"))

  add(new Link("lkmnSair") {
    def onClick() {
      usuarioLogado = null
      getSession().invalidate()
//      getRequestCycle().setRedirect(true)
    }
  })

  add(new Link("lkmnCadUsuario") {
    def onClick() = setResponsePage(new UsuarioPage(usuarioLogado, false))
    setVisible(usuarioLogado.adm.asInstanceOf[Boolean])
  })

  add(new Link("lkmnMeusDados") {
    def onClick = setResponsePage(new UsuarioPage(usuarioLogado, true))
  })

  add(new Link("lkmnRelPeriodo") {
    def onClick = setResponsePage(new RelPeriodoPage(usuarioLogado))
  })

  add(new Link("lkmnRelDia") {
    def onClick = setResponsePage(new RelDiaPage(usuarioLogado))
  })

  add(new Link("lkmnPonto") {
    def onClick = setResponsePage(new PontoPage(usuarioLogado, true))
  })

  add(new Link("lkmnSobre") {
    def onClick = setResponsePage(new SobrePage(usuarioLogado))
  })

  var dataBusca = new LocalDateTime();
  var listaPonto = pontoMediator.listaPontoUsuario(usuarioLogado, dataBusca);

  for (ponto <- listaPonto) {
    if (ponto.dataInicio != null && ponto.dataFim == null) {
      contadorMilesegundos = ponto.dataInicio.getMillisOfSecond() + ""
    }
  }

  var formContador = new Form("form_contador")
  formContador.add(new HiddenField("milissegundos", new PropertyModel[String](this, "contadorMilesegundos")))
  add(formContador)

  var listaPonto2: List[Ponto] = pontoMediator.listaPontoUsuario(new LocalDateTime());
  add(new ListView[Ponto]("listaPonto", listaPonto2) {
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject()
      item.add(new Label("nome", ponto.usuario.nome))
    }
  });

}