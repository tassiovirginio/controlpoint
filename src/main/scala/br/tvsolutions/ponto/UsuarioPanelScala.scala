package br.tvsolutions.ponto

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;

import br.tvsolutions.ponto.entities.Usuario;
import br.tvsolutions.ponto.mediators.TUsuarioMediatorScala;
import br.tvsolutions.ponto.util.DateTimeField24h;

class UsuarioPanelScala(pagePai: Page, usuario: Usuario, direto: java.lang.Boolean) extends Panel("usuarioPanel") {

  var serialVersionUID = 1L

  var usuarioSelecionado: Usuario = _

  @SpringBean
  var usuarioMediatorScala: TUsuarioMediatorScala = _

  var form: Form[Usuario] = _

  var textFieldNome: RequiredTextField[String] = _
  var textFieldSenha: PasswordTextField = _
  var textFieldLogin: RequiredTextField[String] = _
  var textFieldEmail: RequiredTextField[String] = _
  var textFieldHoraEntrada: DateTimeField24h = _
  var textFieldHoraSaida: DateTimeField24h = _
  var textFieldJornada: DateTimeField24h = _
  var textFieldIPs: TextArea[String] = _
  var checkBoxAdmin: CheckBox = _
  var checkBoxExterno: CheckBox = _
  var checkBoxAcesso: CheckBox = _
  var listChoice: ListChoice[String] = _

  var dateHoraEntrada: Date = _
  var dateHoraSaida: Date = _
  var dateJornada: Date = _

  def this(pagePai: Page) = this(pagePai, new Usuario(), false)

  usuarioSelecionado = usuario;

  if (usuario.getHoraEntrada() == null) {
    usuario.setHoraEntrada(new DateTime());
  }
  if (usuario.getHoraSaida() == null) {
    usuario.setHoraSaida(new DateTime());
  }
  if (usuario.getJornada() == null) {
    usuario.setJornada(new DateTime());
  }

  dateHoraEntrada = usuario.getHoraEntrada().toDate();
  dateHoraSaida = usuario.getHoraSaida().toDate();
  dateJornada = usuario.getJornada().toDate();

  form = new Form[Usuario]("form") {
    override protected def onSubmit = {
      usuarioSelecionado.setHoraEntrada(new DateTime(dateHoraEntrada));
      usuarioSelecionado.setHoraSaida(new DateTime(dateHoraSaida));
      usuarioSelecionado.setJornada(new DateTime(dateJornada));
      usuarioMediatorScala.salvarUsuario(usuarioSelecionado);
      info("Usuario Salvo com Sucesso !!");
      setResponsePage(new UsuarioPageScala(usuario, false || direto.asInstanceOf[Boolean]));
    }
  };
  add(form);

  textFieldNome = new RequiredTextField("textFieldNome", new PropertyModel[String](usuarioSelecionado, "nome"));
  textFieldSenha = new PasswordTextField("textFieldSenha", new PropertyModel[String](usuarioSelecionado, "senha"));
  textFieldSenha.setResetPassword(false);
  textFieldLogin = new RequiredTextField("textFieldLogin", new PropertyModel[String](usuarioSelecionado, "login"));
  textFieldEmail = new RequiredTextField("textFieldEmail", new PropertyModel[String](this.usuarioSelecionado, "email"));

  textFieldHoraEntrada = new DateTimeField24h("textFieldHoraEntrada", new PropertyModel(this, "dateHoraEntrada"));
  textFieldHoraEntrada.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  textFieldHoraSaida = new DateTimeField24h("textFieldHoraSaida", new PropertyModel(this, "dateHoraSaida"));
  textFieldHoraSaida.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  textFieldJornada = new DateTimeField24h("textFieldJornada", new PropertyModel(this, "dateJornada"));
  textFieldJornada.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  textFieldIPs = new TextArea("textFieldIPs", new PropertyModel(this.usuarioSelecionado, "ips"));
  checkBoxAdmin = new CheckBox("checkBoxAdmin", new PropertyModel(this.usuarioSelecionado, "adm"));
  checkBoxExterno = new CheckBox("checkBoxExterno", new PropertyModel(this.usuarioSelecionado, "externo"));
  checkBoxAcesso = new CheckBox("checkBoxAcesso", new PropertyModel(this.usuarioSelecionado, "acesso"));

  var listaWallpaper = new ArrayList[String]();
  listaWallpaper.add("wallpaper01");
  listaWallpaper.add("wallpaper07");
  listaWallpaper.add("wallpaper02");
  listaWallpaper.add("wallpaper03");
  listChoice = new ListChoice[String]("listaWallpaper", new PropertyModel[String](this.usuarioSelecionado, "wallpaper"), listaWallpaper, 1);
  listChoice.setRequired(true);

  //Se for para o proprio usuario alterar seus dados
  var diretoS = direto.asInstanceOf[Boolean]
  textFieldLogin.setVisible(!diretoS);

  textFieldHoraEntrada.setVisible(!diretoS);
  textFieldHoraSaida.setVisible(!diretoS);
  textFieldJornada.setVisible(!diretoS);

  textFieldIPs.setVisible(!diretoS);
  checkBoxAdmin.setVisible(!diretoS);
  checkBoxExterno.setVisible(!diretoS);
  checkBoxAcesso.setVisible(!diretoS);

  form.add(listChoice);
  form.add(textFieldNome);
  form.add(textFieldSenha);
  form.add(textFieldLogin);
  form.add(textFieldEmail);

  form.add(textFieldHoraEntrada);
  form.add(textFieldHoraSaida);
  form.add(textFieldJornada);

  form.add(textFieldIPs);
  form.add(checkBoxAdmin);
  form.add(checkBoxExterno);
  form.add(checkBoxAcesso);

  var feedbackPanel = new FeedbackPanel("feedback");
  form.add(feedbackPanel);

  //XXXXXXXXXXXXXXXXXXXXXXXXX

  def getUsuarioSelecionado(): Usuario = usuarioSelecionado

  def setUsuarioSelecionado(usuarioSelecionado: Usuario): Unit = {
    this.usuarioSelecionado = usuarioSelecionado;
    textFieldNome.setModel(new PropertyModel(usuarioSelecionado, "nome"));
    textFieldSenha.setModel(new PropertyModel(usuarioSelecionado, "senha"));
    textFieldLogin.setModel(new PropertyModel(usuarioSelecionado, "login"));
    textFieldEmail.setModel(new PropertyModel(usuarioSelecionado, "email"));
    textFieldIPs.setModel(new PropertyModel(usuarioSelecionado, "ips"));
    checkBoxAdmin.setModel(new PropertyModel(usuarioSelecionado, "adm"));
    checkBoxExterno.setModel(new PropertyModel(usuarioSelecionado, "externo"));
    checkBoxAcesso.setModel(new PropertyModel(usuarioSelecionado, "acesso"));
    listChoice.setModel(new PropertyModel(usuarioSelecionado, "wallpaper"));

    if (usuarioSelecionado.getHoraEntrada() == null) {
      usuarioSelecionado.setHoraEntrada(new DateTime());
    }
    if (usuarioSelecionado.getHoraSaida() == null) {
      usuarioSelecionado.setHoraSaida(new DateTime());
    }
    if (usuarioSelecionado.getJornada() == null) {
      usuarioSelecionado.setJornada(new DateTime());
    }

    dateHoraEntrada = usuarioSelecionado.getHoraEntrada().toDate();
    dateHoraSaida = usuarioSelecionado.getHoraSaida().toDate();
    dateJornada = usuarioSelecionado.getJornada().toDate();

    textFieldHoraEntrada.setModel(new PropertyModel(this, "dateHoraEntrada"));
    textFieldHoraSaida.setModel(new PropertyModel(this, "dateHoraSaida"));
    textFieldJornada.setModel(new PropertyModel(this, "dateJornada"));
  }

}