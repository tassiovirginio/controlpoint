package br.controlpoint.daos

import java.io.Serializable
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import org.springframework.stereotype.Component

import br.controlpoint.entities._

@Component
class DaoUsuario extends DaoAbstract[Usuario, java.lang.Long] with Serializable {

  def domain: Class[Usuario] = classOf[Usuario]

  def buscarUsuarioPorLogin(u: Usuario): Usuario = {
    return Usuarios.findByLogin(u.login).get
  }

  def buscarUsuarioPorLogin(login: String): Usuario = {
    var u = Usuarios.findByLogin(login)
    if(u != None){
      return u.get
    }
    return null
  }
  
  override def save(u:Usuario): Unit = {
    
  }

}

object DaoUsuarioScala {

  def codificarSenha(senha: String): String = {

    var md: MessageDigest = null

    var bytes: Array[Byte] = null

    try {
      md = MessageDigest.getInstance("MD5")
      md.update(senha.getBytes())
      bytes = md.digest()
    } catch {
      case e: NoSuchAlgorithmException => e.printStackTrace()
    }

    var s = new StringBuilder()

    for (i <- 0 to (bytes.length - 1)) {
      var parteAlta = ((bytes(i) >> 4) & 0xf) << 4
      var parteBaixa = bytes(i) & 0xf
      if (parteAlta == 0) s.append('0')
      s.append(Integer.toHexString(parteAlta | parteBaixa))
    }
    return s.toString()
  }
}

