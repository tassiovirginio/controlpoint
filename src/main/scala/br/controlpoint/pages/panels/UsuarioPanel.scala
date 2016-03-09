package br.controlpoint.pages.panels

import java.util.ArrayList

import br.controlpoint.entities.Usuario
import br.controlpoint.mediators.TUsuarioMediator
import br.controlpoint.pages.UsuarioPage
import org.apache.wicket.Page
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form._
import org.apache.wicket.markup.html.panel.{FeedbackPanel, Panel}
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

class UsuarioPanel(pagePai: Page, usuario: Usuario, diretoS: java.lang.Boolean) extends Panel("usuarioPanel") {

  var direto = diretoS.asInstanceOf[Boolean]

  @SpringBean
  var usuarioMediator: TUsuarioMediator = _

  def this(pagePai: Page) = this(pagePai, new Usuario(), false)

  var usuarioSelecionado = usuario

  if (usuario.horaEntrada == null) {
    usuario.horaEntrada = (new LocalDateTime())
  }
  if (usuario.horaSaida == null) {
    usuario.horaSaida = (new LocalDateTime())
  }
  if (usuario.jornada == null) {
    usuario.jornada = (new LocalDateTime())
  }

  var dateHoraEntrada = usuario.horaEntrada.toString("hh:mm")
  var dateHoraSaida = usuario.horaSaida.toString("hh:mm")
  var dateJornada = usuario.jornada.toString("hh:mm")

  var form = new Form[Usuario]("form") {
    override protected def onSubmit = {
      val formatter = DateTimeFormat.forPattern("HH:mm")
      usuarioSelecionado.horaEntrada = (formatter.parseLocalDateTime(dateHoraEntrada))
      usuarioSelecionado.horaSaida = (formatter.parseLocalDateTime(dateHoraSaida))
      usuarioSelecionado.jornada = (formatter.parseLocalDateTime(dateJornada))
      usuarioMediator.salvarUsuario(usuarioSelecionado)
      info("Usuario Salvo com Sucesso !!")
      setResponsePage(new UsuarioPage(usuario, false || direto.asInstanceOf[Boolean]))
    }
  };
  add(form);

  var textFieldNome = new RequiredTextField("textFieldNome", new PropertyModel[String](usuarioSelecionado, "nome"))
  var textFieldSenha = new PasswordTextField("textFieldSenha", new PropertyModel[String](usuarioSelecionado, "senha"))
  textFieldSenha.setResetPassword(false)
  var textFieldLogin = new RequiredTextField("textFieldLogin", new PropertyModel[String](usuarioSelecionado, "login"))
  var textFieldEmail = new RequiredTextField("textFieldEmail", new PropertyModel[String](this.usuarioSelecionado, "email"))

  var textFieldHoraEntrada = new TextField[String]("textFieldHoraEntrada", new PropertyModel[String](this, "dateHoraEntrada"))
  var textFieldHoraSaida = new TextField[String]("textFieldHoraSaida", new PropertyModel[String](this, "dateHoraSaida"))
  var textFieldJornada = new TextField[String]("textFieldJornada", new PropertyModel[String](this, "dateJornada"))

  var textFieldIPs = new TextArea("textFieldIPs", new PropertyModel(this.usuarioSelecionado, "ips"))
  var checkBoxAdmin = new CheckBox("checkBoxAdmin", new PropertyModel(this.usuarioSelecionado, "adm"))
  var checkBoxExterno = new CheckBox("checkBoxExterno", new PropertyModel(this.usuarioSelecionado, "externo"))
  var checkBoxAcesso = new CheckBox("checkBoxAcesso", new PropertyModel(this.usuarioSelecionado, "acesso"))

  var listaWallpaper = new ArrayList[String]()
  listaWallpaper.add("wallpaper01")
  listaWallpaper.add("wallpaper02")


  var listChoice = new ListChoice[String]("listaWallpaper", new PropertyModel[String](this.usuarioSelecionado, "wallpaper"), listaWallpaper, 1)
  listChoice.setRequired(true)

  textFieldLogin.setVisible(!direto)

  form.add(new Label("lbHrEntrada", "Hora de Entrada:").setVisible(!direto))
  form.add(new Label("lbHrSaida", "Hora de Saida:").setVisible(!direto))
  form.add(new Label("lbHrJornada", "Hora Jornada:").setVisible(!direto))
  form.add(new Label("lbLogin", "Login:").setVisible(!direto))
  form.add(new Label("lbAdmin", "Admin:").setVisible(!direto))
  form.add(new Label("lbExterno", "Externo:").setVisible(!direto))
  form.add(new Label("lbAcesso", "Acesso:").setVisible(!direto))
  form.add(new Label("lbIps", "Ips:").setVisible(!direto))


  textFieldHoraEntrada.setVisible(!direto)
  textFieldHoraSaida.setVisible(!direto)
  textFieldJornada.setVisible(!direto)

  textFieldIPs.setVisible(!direto)
  checkBoxAdmin.setVisible(!direto)
  checkBoxExterno.setVisible(!direto)
  checkBoxAcesso.setVisible(!direto)

  form.add(listChoice)
  form.add(textFieldNome)
  form.add(textFieldSenha)
  form.add(textFieldLogin)
  form.add(textFieldEmail)

  form.add(textFieldHoraEntrada)
  form.add(textFieldHoraSaida)
  form.add(textFieldJornada)

  form.add(textFieldIPs)
  form.add(checkBoxAdmin)
  form.add(checkBoxExterno)
  form.add(checkBoxAcesso)

  var feedbackPanel = new FeedbackPanel("feedback")
  form.add(feedbackPanel);

  def getUsuarioSelecionado(): Usuario = usuarioSelecionado

  def setUsuarioSelecionado(usuarioSelecionado: Usuario): Unit = {
    this.usuarioSelecionado = usuarioSelecionado;
    textFieldNome.setModel(new PropertyModel(usuarioSelecionado, "nome"))
    textFieldSenha.setModel(new PropertyModel(usuarioSelecionado, "senha"))
    textFieldLogin.setModel(new PropertyModel(usuarioSelecionado, "login"))
    textFieldEmail.setModel(new PropertyModel(usuarioSelecionado, "email"))
    textFieldIPs.setModel(new PropertyModel(usuarioSelecionado, "ips"))
    checkBoxAdmin.setModel(new PropertyModel(usuarioSelecionado, "adm"))
    checkBoxExterno.setModel(new PropertyModel(usuarioSelecionado, "externo"))
    checkBoxAcesso.setModel(new PropertyModel(usuarioSelecionado, "acesso"))
    listChoice.setModel(new PropertyModel(usuarioSelecionado, "wallpaper"))

    if (usuarioSelecionado.horaEntrada == null) {
      usuarioSelecionado.horaEntrada = (new LocalDateTime())
    }
    if (usuarioSelecionado.horaSaida == null) {
      usuarioSelecionado.horaSaida = (new LocalDateTime())
    }
    if (usuarioSelecionado.jornada == null) {
      usuarioSelecionado.jornada = (new LocalDateTime())
    }

    dateHoraEntrada = usuarioSelecionado.horaEntrada.toString("hh:mm")
    dateHoraSaida = usuarioSelecionado.horaSaida.toString("hh:mm")
    dateJornada = usuarioSelecionado.jornada.toString("hh:mm")

    textFieldHoraEntrada.setModel(new PropertyModel(this, "dateHoraEntrada"))
    textFieldHoraSaida.setModel(new PropertyModel(this, "dateHoraSaida"))
    textFieldJornada.setModel(new PropertyModel(this, "dateJornada"))
  }

}

