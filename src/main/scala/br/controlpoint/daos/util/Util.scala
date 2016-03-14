package br.controlpoint.daos.util

import java.security.{MessageDigest, NoSuchAlgorithmException}

/**
 * Created by tassio on 08/03/16.
 */
object Util {

  def codificarSenha(senha: String) = {

    val md: MessageDigest = MessageDigest.getInstance("MD5")
    md.update(senha.getBytes())
    val bytes: Array[Byte] = md.digest()
    val s = new StringBuilder()

    for (i <- 0 to (bytes.length - 1)) {
      val parteAlta = ((bytes(i) >> 4) & 0xf) << 4
      val parteBaixa = bytes(i) & 0xf
      if (parteAlta == 0) s.append('0')
      s.append(Integer.toHexString(parteAlta | parteBaixa))
    }

    s.toString()
  }

}
