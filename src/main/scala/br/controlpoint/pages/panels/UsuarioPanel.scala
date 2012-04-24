package br.controlpoint.pages.panels

import java.util.ArrayList
import java.util.Date
import java.util.List
import org.apache.wicket.Page
import org.apache.wicket.behavior.SimpleAttributeModifier
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.ListChoice
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.DateTime
import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.TUsuarioMediator
import br.controlpoint.util.DateTimeField24h;
import br.controlpoint.pages.UsuarioPage

class UsuarioPanel(pagePai:Page, usuario:Usuario, direto:java.lang.Boolean) extends Panel("usuarioPanel") {

  var serialVersionUID = 1L

  @SpringBean
  var usuarioMediator:TUsuarioMediator = _

  def this(pagePai: Page) = this(pagePai, new Usuario(), false)

  var usuarioSelecionado = usuario;

  if (usuario.horaEntrada == null) {
    usuario.horaEntrada = (new DateTime());
  }
  if (usuario.horaSaida == null) {
    usuario.horaSaida = (new DateTime());
  }
  if (usuario.jornada == null) {
    usuario.jornada = (new DateTime());
  }


  var dateHoraEntrada = usuario.horaEntrada.toDate();
  var dateHoraSaida = usuario.horaSaida.toDate();
  var dateJornada = usuario.jornada.toDate();

  var form = new Form[Usuario]("form") {
    override protected def onSubmit = {
      usuarioSelecionado.horaEntrada = (new DateTime(dateHoraEntrada));
      usuarioSelecionado.horaSaida = (new DateTime(dateHoraSaida));
      usuarioSelecionado.jornada = (new DateTime(dateJornada));
      usuarioMediator.salvarUsuario(usuarioSelecionado);
      info("Usuario Salvo com Sucesso !!");
      setResponsePage(new UsuarioPage(usuario, false || direto.asInstanceOf[Boolean]));
    }
  };
  add(form);

  var textFieldNome = new RequiredTextField("textFieldNome", new PropertyModel[String](usuarioSelecionado, "nome"));
  var textFieldSenha = new PasswordTextField("textFieldSenha", new PropertyModel[String](usuarioSelecionado, "senha"));
  textFieldSenha.setResetPassword(false);
  var textFieldLogin = new RequiredTextField("textFieldLogin", new PropertyModel[String](usuarioSelecionado, "login"));
  var textFieldEmail = new RequiredTextField("textFieldEmail", new PropertyModel[String](this.usuarioSelecionado, "email"));

  var textFieldHoraEntrada = new DateTimeField24h("textFieldHoraEntrada", new PropertyModel(this, "dateHoraEntrada"));
  textFieldHoraEntrada.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  var textFieldHoraSaida = new DateTimeField24h("textFieldHoraSaida", new PropertyModel(this, "dateHoraSaida"));
  textFieldHoraSaida.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  var textFieldJornada = new DateTimeField24h("textFieldJornada", new PropertyModel(this, "dateJornada"));
  textFieldJornada.get("date").add(new SimpleAttributeModifier("type", "hidden"));

  var textFieldIPs = new TextArea("textFieldIPs", new PropertyModel(this.usuarioSelecionado, "ips"));
  var checkBoxAdmin = new CheckBox("checkBoxAdmin", new PropertyModel(this.usuarioSelecionado, "adm"));
  var checkBoxExterno = new CheckBox("checkBoxExterno", new PropertyModel(this.usuarioSelecionado, "externo"));
  var checkBoxAcesso = new CheckBox("checkBoxAcesso", new PropertyModel(this.usuarioSelecionado, "acesso"));

  var listaWallpaper = new ArrayList[String]();
  listaWallpaper.add("wallpaper01");
  listaWallpaper.add("wallpaper07");
  listaWallpaper.add("wallpaper02");
  listaWallpaper.add("wallpaper03");
  var listChoice = new ListChoice[String]("listaWallpaper", new PropertyModel[String](this.usuarioSelecionado, "wallpaper"), listaWallpaper, 1);
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

    if (usuarioSelecionado.horaEntrada == null) {
      usuarioSelecionado.horaEntrada=(new DateTime())
    }
    if (usuarioSelecionado.horaSaida == null) {
      usuarioSelecionado.horaSaida=(new DateTime())
    }
    if (usuarioSelecionado.jornada == null) {
      usuarioSelecionado.jornada=(new DateTime())
    }

    dateHoraEntrada = usuarioSelecionado.horaEntrada.toDate
    dateHoraSaida = usuarioSelecionado.horaSaida.toDate
    dateJornada = usuarioSelecionado.jornada.toDate

    textFieldHoraEntrada.setModel(new PropertyModel(this, "dateHoraEntrada"))
    textFieldHoraSaida.setModel(new PropertyModel(this, "dateHoraSaida"))
    textFieldJornada.setModel(new PropertyModel(this, "dateJornada"))
  }

}