package br.controlpoint.daos

import java.io.Serializable
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import org.hibernate.criterion.Restrictions
import org.springframework.stereotype.Component

import br.controlpoint.entities.Usuario

@Component
class DaoUsuario extends DaoAbstract[Usuario, java.lang.Long] with Serializable {
  
  def domain: Class[Usuario] = classOf[Usuario]
  
  def buscarUsuarioPorLogin(usuario: Usuario): Usuario = {
	  
    var usuarioReturn: Usuario = null
    
    var usuarioBusca = currentSession.createCriteria(classOf[Usuario])
      .add(Restrictions.eq("login", usuario.login))
      .uniqueResult().asInstanceOf[Usuario]

    if (usuarioBusca != null && usuarioBusca.senha.length() < 15) {
      usuarioBusca.senha = (DaoUsuarioScala.codificarSenha(usuarioBusca.login.trim() + usuarioBusca.senha.trim()))
      this.save(usuarioBusca)
    }
    
    if (usuarioBusca != null && usuarioBusca.senha.trim().equals(DaoUsuarioScala.codificarSenha(usuario.login.trim() + usuario.senha.trim()))) {
      usuarioReturn = usuarioBusca
    }
    
    return usuarioReturn
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

