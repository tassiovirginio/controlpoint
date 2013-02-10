package br.controlpoint.daos


import br.controlpoint.entities.Ponto
import java.io.Serializable
import java.util.{ List => jList }

import org.joda.time.DateTime
import org.springframework.stereotype.Component

import scala.collection.JavaConversions._


@Component
class DaoPonto extends DaoAbstract[Ponto, java.lang.Long] with TDaoPonto with Serializable {
  
  override def domain = classOf[Ponto]

  def buscarPontos(p: Ponto, dataInicio: DateTime, dataFim: DateTime): jList[Ponto] = {
    var dHoraInicio = dataInicio.withTime(00, 00, 00, 00)
    var dHoraFim = dataInicio.withTime(23, 59, 59, 00)

    if (dataFim != null) dHoraFim = dataFim.withTime(23, 59, 59, 00)
    
    null
  }
 

  def buscarPontos(dInicio: DateTime, dFim: DateTime): jList[Ponto] = {
    var dHoraInicio = dInicio.withTime(00, 00, 00, 00)
    var dHoraFim = dInicio.withTime(23, 59, 59, 00)

    if (dFim != null) { dHoraFim = dFim.withTime(23, 59, 59, 00) }

    return null
  }
}



trait TDaoPonto extends TDaoAbstract[Ponto, java.lang.Long] with Serializable {
  def buscarPontos(p: Ponto, dataInicio: DateTime, dataFim: DateTime): jList[Ponto] 
  def buscarPontos(dInicio: DateTime, dFim: DateTime): jList[Ponto] 
}