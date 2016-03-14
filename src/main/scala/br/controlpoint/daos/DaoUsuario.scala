package br.controlpoint.daos

import java.io.Serializable

import br.controlpoint.daos.util.{DaoAbstract, Util}
import br.controlpoint.entities.Usuario
import org.springframework.stereotype.Component

@Component
class DaoUsuario extends DaoAbstract[Usuario, java.lang.Long] with Serializable {

  def buscarUsuarioPorLogin(usuario: Usuario): Usuario = {

    var usuarioReturn: Usuario = null

    var usuarioBusca = criteria.add("login".eq_(usuario.login))
      .uniqueResult.asInstanceOf[Usuario]

    if (usuarioBusca != null && usuarioBusca.senha.length() < 15) {
      usuarioBusca.senha = (Util.codificarSenha(usuarioBusca.login.trim() + usuarioBusca.senha.trim()))
      this.save(usuarioBusca)
    }

    if (usuarioBusca != null && usuarioBusca.senha.trim().equals(Util.codificarSenha(usuario.login.trim() + usuario.senha.trim()))) {
      usuarioReturn = usuarioBusca
    }

    return usuarioReturn
  }

  def buscarUsuarioPorLogin(login: String): Usuario = {
    criteria.add("login" eq_ login).uniqueResult.asInstanceOf[Usuario]
  }

}