package br.controlpoint.entities

import javax.persistence.{Entity, FetchType, ManyToOne}

import org.joda.time.LocalDateTime

import scala.beans.BeanProperty

@Entity
class Ponto extends Identifiable {

  @BeanProperty var dataInicio: LocalDateTime = _

  @BeanProperty var dataFim: LocalDateTime = _

  @ManyToOne(fetch = FetchType.LAZY)
  @BeanProperty var usuario: Usuario = _

  @BeanProperty var ipInicio: String = _

  @BeanProperty var ipFim: String = _

  @BeanProperty var total: Integer = _

  override def equals(that: Any): Boolean = that match {
    case u: this.type => this.id == u.id
    case _ => false
  }

  override def hashCode = this.id.hashCode

  override def toString = "Ponto(" + this.id + ")"
}