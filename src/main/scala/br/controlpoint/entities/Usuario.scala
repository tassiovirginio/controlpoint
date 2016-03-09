package br.controlpoint.entities

import java.lang.{Boolean => JBoolean}
import javax.persistence.Entity

import org.joda.time.LocalDateTime

import scala.beans.BeanProperty

@Entity
class Usuario extends Identifiable {

  @BeanProperty var acesso: Boolean = _

  @BeanProperty var nome: String = _

  @BeanProperty var email: String = _

  @BeanProperty var login: String = _

  @BeanProperty var senha: String = _

  @BeanProperty var ips: String = _

  @BeanProperty var externo: JBoolean = _

  @BeanProperty var gtalk: JBoolean = _

  @BeanProperty var adm: Boolean = _

  @BeanProperty var wallpaper: String = _

  @BeanProperty var horaEntrada: LocalDateTime = _

  @BeanProperty var horaSaida: LocalDateTime = _

  @BeanProperty var jornada: LocalDateTime = _

  @BeanProperty var alerta1: LocalDateTime = _

  override def equals(that: Any): Boolean = that match {
    case u: this.type => this.id == u.id
    case _ => false
  }

  override def hashCode = this.id.hashCode

  override def toString = "Usuario(" + this.id + ")"

}