package br.controlpoint.entities

import javax.persistence.{Entity, GeneratedValue, Id}

import org.joda.time.{LocalDateTime}
import java.lang.{Boolean => JBoolean}

import java.io.Serializable

@Entity
class Usuario extends Serializable {

  @Id
  @GeneratedValue
  var id: Long = _

  var acesso: Boolean = _

  var nome: String = _

  var email: String = _

  var login: String = _

  var senha: String = _

  var ips: String = _

  var externo: JBoolean = _

  var gtalk: JBoolean = _

  var adm: Boolean = _

  var wallpaper: String = _

  var horaEntrada: LocalDateTime = _

  var horaSaida: LocalDateTime = _

  var jornada: LocalDateTime = _

  var alerta1: LocalDateTime = _

  override def equals(that: Any): Boolean = that match {
    case u: Usuario => this.id == u.id
    case _ => false
  }

  override def hashCode = this.id.hashCode

  override def toString = "Usuario(" + this.id + ")"

}