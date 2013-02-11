package br.controlpoint.daos


import br.controlpoint.entities._
import java.io.Serializable

import org.joda.time.DateTime
import org.springframework.stereotype.Component

import scala.collection.JavaConversions._


@Component
class DaoPonto extends TDaoPonto with Serializable {
  
  def buscarPontos(u: Usuario, di: DateTime, df: DateTime):List[Ponto] = {
    var dHoraInicio = di.withTime(00, 00, 00, 00)
    var dHoraFim = di.withTime(23, 59, 59, 00)

    if (df != null) dHoraFim = df.withTime(23, 59, 59, 00)

    Pontos.all(u,dHoraInicio,dHoraFim)
  }
 

  def buscarPontos(dInicio: DateTime, dFim: DateTime): List[Ponto] = {
    var dHoraInicio = dInicio.withTime(00, 00, 00, 00)
    var dHoraFim = dInicio.withTime(23, 59, 59, 00)

    if (dFim != null) { dHoraFim = dFim.withTime(23, 59, 59, 00) }

    Pontos.all(dHoraInicio,dHoraFim)
  }

  def save(p:Ponto) = Pontos.salvar(p)

  def delete(p:Ponto) = Pontos.delete(p.id)

  def getAll(offset: Int, maxResult: Int): List[Ponto]  = null

  def getAll(): List[Ponto] = Pontos.all

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): List[Ponto] = null

  def getById(id:Long): Ponto = Pontos.findById(id).get

  def totalCount: Long = Pontos.all.size

  def size: Long = Pontos.all.size
}



trait TDaoPonto extends TDaoAbstract[Ponto, Long] with Serializable {
  def buscarPontos(u: Usuario, dataInicio: DateTime, dataFim: DateTime): List[Ponto]
  def buscarPontos(dInicio: DateTime, dFim: DateTime): List[Ponto]
}