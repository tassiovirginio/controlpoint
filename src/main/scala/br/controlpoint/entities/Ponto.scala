package br.controlpoint.entities

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

import org.joda.time.{LocalDateTime, DateTime}

import java.io.Serializable

@Entity
class Ponto extends Serializable {

  @Id
  @GeneratedValue
  var id: Long = _

  var dataInicio: LocalDateTime = _

  var dataFim: LocalDateTime = _

  @ManyToOne(fetch = FetchType.LAZY)
  var usuario: Usuario = _

  var ipInicio: String = _

  var ipFim: String = _

  var total: Integer = _

  override def equals(that: Any): Boolean = that match {
    case p: Ponto => this.id == p.id
    case _ => false
  }

  override def hashCode = this.id.hashCode

  override def toString = "Ponto(" + this.id + ")"
}