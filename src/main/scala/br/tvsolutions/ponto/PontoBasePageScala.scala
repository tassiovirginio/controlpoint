package br.tvsolutions.ponto

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;

import java.util.List
import java.util.Locale

import br.tvsolutions.ponto.entities.Ponto;
import br.tvsolutions.ponto.entities.Usuario;
import br.tvsolutions.ponto.mediators.TPontoMediatorScala;
import br.tvsolutions.ponto.mediators.TUsuarioMediatorScala;

import scala.collection.JavaConversions._
import reflect._

class PontoBasePageScala(usuario: Usuario) extends WebPage {

  @SpringBean
  var usuarioMediatorScala: TUsuarioMediatorScala = _

  @SpringBean
  var pontoMediatorScala: TPontoMediatorScala = _

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

  usuarioLogado = usuarioMediatorScala.getUsuarioForId(usuario.getId())
  this.add(CSSPackageResource.getHeaderContribution("css/base01.css"))
  var img = new Image("wallpaper")
  img.add(new SimpleAttributeModifier("src", "imagens/wallpapers/" + usuarioLogado.getWallpaper() + ".jpg"))
  add(img);

  if (usuarioLogado == null) {
    getSession().invalidate();
    getRequestCycle().setRedirect(true);
    setResponsePage(new LoginPageScala())
  }

  getSession().setLocale(LOCALE_BR)
  this.usuarioLogado = usuarioLogado

  var labelusuario = new Label("labelusuario", "(  " + usuarioLogado.getNome() + "  )")
  add(labelusuario);

  var lkSair = new Link("lkmnSair") {
    def onClick() {
      usuarioLogado = null;
      getSession().invalidate();
      getRequestCycle().setRedirect(true);
      //				setResponsePage(new LoginPageScala());
    }
  };
  add(lkSair);

  var lkCadUsuario = new Link("lkmnCadUsuario") {
    def onClick() {
      setResponsePage(new UsuarioPageScala(usuarioLogado, false));
    }
  };

  lkCadUsuario.setVisible(usuarioLogado.getAdm().asInstanceOf[Boolean]);
  add(lkCadUsuario);

  var lkmnMeusDados = new Link("lkmnMeusDados") {
    def onClick() {
      setResponsePage(new UsuarioPageScala(usuarioLogado, true));
    }
  };
  add(lkmnMeusDados);

  var lkRelPeriodo = new Link("lkmnRelPeriodo") {
    def onClick() {
      setResponsePage(new RelPeriodoPageScala(usuarioLogado));
    }
  };
  add(lkRelPeriodo);

  var lkRelDia = new Link("lkmnRelDia") {
    def onClick() {
      setResponsePage(new RelDiaPageScala(usuarioLogado));
    }
  };
  add(lkRelDia);

  var lkPonto = new Link("lkmnPonto") {
    def onClick() {
      setResponsePage(new PontoPageScala(usuarioLogado, true));
    }
  };
  add(lkPonto);

  var lkmnSobre = new Link("lkmnSobre") {
    def onClick() {
      setResponsePage(new SobrePageScala(usuarioLogado));
    }
  };
  add(lkmnSobre);

  var dataBusca = new DateTime();
  var listaPonto: java.util.List[Ponto] = pontoMediatorScala.listaPontoUsuario(usuarioLogado, dataBusca);

  for (val ponto <- listaPonto) {
    if (ponto.getDataInicio() != null && ponto.getDataFim() == null) {
      contadorMilesegundos = ponto.getDataInicio().getMillis() + "";
    }
  }

  var formContador = new Form("form_contador");
  formContador.add(new HiddenField("milissegundos", new PropertyModel[String](this, "contadorMilesegundos")));
  add(formContador);

  var listaPonto2: List[Ponto] = pontoMediatorScala.listaPontoUsuario(new DateTime());
  add(new ListView[Ponto]("listaPonto", listaPonto2) {
    val serialVersionUID = 1L;
    def populateItem(item: ListItem[Ponto]) {
      var ponto = item.getModelObject();
      item.add(new Label("nome", ponto.getUsuario().getNome()));
    }
  });

}