package br.tvsolutions.ponto.pages.base

import java.util.List
import java.util.Locale
import scala.collection.JavaConversions.asScalaBuffer
import scala.reflect.BeanProperty
import org.apache.wicket.behavior.SimpleAttributeModifier
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.HiddenField
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.CSSPackageResource
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.DateTime
import br.tvsolutions.ponto.entities.Ponto
import br.tvsolutions.ponto.entities.Usuario
import br.tvsolutions.ponto.mediators.TPontoMediator
import br.tvsolutions.ponto.mediators.TUsuarioMediator
import br.tvsolutions.ponto.pages.LoginPage
import br.tvsolutions.ponto.pages.PontoPage
import br.tvsolutions.ponto.pages.RelDiaPage
import br.tvsolutions.ponto.pages.RelPeriodoPage
import br.tvsolutions.ponto.pages.SobrePage
import br.tvsolutions.ponto.pages.UsuarioPage
import javax.persistence.Entity
import org.apache.wicket.markup.html.link.Link
import br.tvsolutions.ponto.pages.RelPeriodoPage
import br.tvsolutions.ponto.mediators.TPontoMediator
import br.tvsolutions.ponto.mediators.TUsuarioMediator
import br.tvsolutions.ponto.pages.UsuarioPage
import br.tvsolutions.ponto.pages.SobrePage

class PontoBasePage(usuario:Usuario) extends WebPage {

  @SpringBean
  var usuarioMediator: TUsuarioMediator = _

  @SpringBean
  var pontoMediator: TPontoMediator = _

  @BeanProperty
  var usuarioLogado: Usuario = null

  private val LOCALE_BR = new Locale("pt_BR");

  var contadorMilesegundos: String = null;

  override def getLocale() = LOCALE_BR
  
  if (getSession().isSessionInvalidated()) {
    usuarioLogado = null;
    getSession().invalidate();
    getRequestCycle().setRedirect(true);
  }

  usuarioLogado = usuarioMediator.getUsuarioForId(usuario.id)
  this.add(CSSPackageResource.getHeaderContribution("css/base01.css"))
  var img = new Image("wallpaper")
  img.add(new SimpleAttributeModifier("src", "imagens/wallpapers/" + usuarioLogado.wallpaper + ".jpg"))
  add(img);

  if (usuarioLogado == null) {
    getSession().invalidate();
    getRequestCycle().setRedirect(true);
    setResponsePage(new LoginPage())
  }

  getSession().setLocale(LOCALE_BR)
  this.usuarioLogado = usuarioLogado

  add(new Label("labelusuario", "(  " + usuarioLogado.nome + "  )"))

  add(new Link("lkmnSair") {
    def onClick() {
      usuarioLogado = null;
      getSession().invalidate();
      getRequestCycle().setRedirect(true);
    }
  })


  add(new Link("lkmnCadUsuario") {
    def onClick() {
      setResponsePage(new UsuarioPage(usuarioLogado, false))
    }
    setVisible(usuarioLogado.adm.asInstanceOf[Boolean])
  })

  add(new Link("lkmnMeusDados") {
    def onClick() {
      setResponsePage(new UsuarioPage(usuarioLogado, true));
    }
  })

  add(new Link("lkmnRelPeriodo") {
    def onClick() {
      setResponsePage(new RelPeriodoPage(usuarioLogado));
    }
  })

  add(new Link("lkmnRelDia") {
    def onClick() {
      setResponsePage(new RelDiaPage(usuarioLogado));
    }
  })

  add(new Link("lkmnPonto") {
    def onClick() {
      setResponsePage(new PontoPage(usuarioLogado, true));
    }
  })

  add(new Link("lkmnSobre") {
    def onClick() {
      setResponsePage(new SobrePage(usuarioLogado));
    }
  })

  var dataBusca = new DateTime();
  var listaPonto = pontoMediator.listaPontoUsuario(usuarioLogado, dataBusca);

  for (ponto <- listaPonto) {
    if (ponto.getDataInicio() != null && ponto.getDataFim() == null) {
      contadorMilesegundos = ponto.getDataInicio().getMillis() + "";
    }
  }

  var formContador = new Form("form_contador");
  formContador.add(new HiddenField("milissegundos", new PropertyModel[String](this, "contadorMilesegundos")));
  add(formContador);

  var listaPonto2: List[Ponto] = pontoMediator.listaPontoUsuario(new DateTime());
  add(new ListView[Ponto]("listaPonto", listaPonto2) {
    val serialVersionUID = 1L;
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject();
      item.add(new Label("nome", ponto.getUsuario().nome));
    }
  });

}