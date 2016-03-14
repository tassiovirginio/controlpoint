package br.controlpoint.mediators

import java.util.{List => jList}
import br.controlpoint.daos.DaoPonto
import br.controlpoint.entities.{Ponto, Usuario}
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PontoMediator {
  @Autowired private var daoPonto: DaoPonto = _

  def salvarPonto(ponto: Ponto) = daoPonto.save(ponto)
  def deletePonto(ponto: Ponto) = daoPonto.delete(ponto)
  def sizePonto = daoPonto.totalCount

  def listaPontoUsuario(usuario: Usuario, dataDiaInicio: LocalDateTime): jList[Ponto] = {
    val ponto = new Ponto()
    ponto.usuario = usuario
    this.daoPonto.buscarPontos(ponto, dataDiaInicio, null)
  }

  def listaPontoUsuario(dataDiaInicio: LocalDateTime): jList[Ponto] = {
    this.daoPonto.buscarPontos(dataDiaInicio, null)
  }

  def listaPontoUsuario(usuario: Usuario, dataDiaInicio: LocalDateTime, dataDiaFim: LocalDateTime): jList[Ponto] = {
    val ponto = new Ponto()
    ponto.usuario = usuario
    this.daoPonto.buscarPontos(ponto, dataDiaInicio, dataDiaFim)
  }

}
