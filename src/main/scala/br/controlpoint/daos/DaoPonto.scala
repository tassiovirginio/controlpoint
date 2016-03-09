package br.controlpoint.daos

import java.io.Serializable
import java.util.{List => jList}

import br.controlpoint.daos.util.DaoAbstract
import br.controlpoint.entities.Ponto
import org.joda.time.LocalDateTime
import org.springframework.stereotype.Component

import scala.collection.JavaConversions._

@Component
class DaoPonto extends DaoAbstract[Ponto, java.lang.Long] with Serializable {

  override def domain = classOf[Ponto]

  def buscarPontos(p: Ponto, dataInicio: LocalDateTime, dataFim: LocalDateTime): jList[Ponto] = {
    var dHoraInicio = dataInicio.withTime(0, 0, 0, 0)
    var dHoraFim = dataInicio.withTime(23, 59, 59, 0)

    if (dataFim != null) dHoraFim = dataFim.withTime(23, 59, 59, 0)

    criteria
      .add("usuario" eq_ (p.usuario))
      .add("dataInicio" between(dHoraInicio, dHoraFim))
      .addOrder("dataInicio" orderAsc)
      .list().asInstanceOf[jList[Ponto]]

  }


  def buscarPontos(dInicio: LocalDateTime, dFim: LocalDateTime): jList[Ponto] = {
    var dHoraInicio = dInicio.withTime(0, 0, 0, 0)
    var dHoraFim = dInicio.withTime(23, 59, 59, 0)

    if (dFim != null) {
      dHoraFim = dFim.withTime(23, 59, 59, 0)
    }

    var lista = criteria
      .add("dataInicio".between(dHoraInicio, dHoraFim))
      .add("dataFim".isNull)
      .addOrder("dataInicio".orderAsc)
      .list().asInstanceOf[jList[Ponto]]

    lista.foreach(p => p.usuario.nome)

    return lista
  }
}