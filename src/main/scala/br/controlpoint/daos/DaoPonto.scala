package br.controlpoint.daos


import br.controlpoint.entities.Ponto
import java.io.Serializable
import java.util.{ List => jList }

import org.hibernate.criterion.Order
import org.joda.time.DateTime
import org.springframework.stereotype.Component

import org.hibernate.criterion.Restrictions._
import org.hibernate.criterion.Restrictions.{ eq => _eq }
import org.hibernate.criterion.Order.{ asc => OrderAsc }
import org.hibernate.criterion.Order.{ desc => OrderDesc }

import scala.collection.JavaConversions._


@Component
class DaoPonto extends DaoAbstract[Ponto, java.lang.Long] with Serializable {
  
  override def domain = classOf[Ponto]

  def buscarPontos(p: Ponto, dataInicio: DateTime, dataFim: DateTime): jList[Ponto] = {
    var dHoraInicio = dataInicio.withTime(00, 00, 00, 00)
    var dHoraFim = dataInicio.withTime(23, 59, 59, 00)

    if (dataFim != null) dHoraFim = dataFim.withTime(23, 59, 59, 00)

    createCriteria
      .add("usuario" eq_(p.usuario))
      .add("dataInicio" between(dHoraInicio, dHoraFim))
      .addOrder("dataInicio" orderAsc)
      .list().asInstanceOf[jList[Ponto]]
      
  }
  
  
  

  def buscarPontos(dInicio: DateTime, dFim: DateTime): jList[Ponto] = {
    var dHoraInicio = dInicio.withTime(00, 00, 00, 00)
    var dHoraFim = dInicio.withTime(23, 59, 59, 00)

    if (dFim != null) { dHoraFim = dFim.withTime(23, 59, 59, 00) }

    var lista = createCriteria
      .add("dataInicio".between(dHoraInicio, dHoraFim))
      .add("dataFim".isNull)
      .addOrder("dataInicio".orderAsc)
      .list().asInstanceOf[jList[Ponto]]

    lista.foreach(p => p.usuario.nome)

    return lista
  }
}