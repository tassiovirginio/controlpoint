package br.controlpoint.daos.util

import java.security.{MessageDigest, NoSuchAlgorithmException}

/**
  * Created by tassio on 08/03/16.
  */
object Util {

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
