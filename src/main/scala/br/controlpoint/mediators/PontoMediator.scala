package br.controlpoint.mediators

import java.util.List

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

  def salvarPonto(ponto: Ponto): Unit = daoPonto.save(ponto)

  def deletePonto(ponto: Ponto): Unit = daoPonto.delete(ponto)

  def sizePonto() = daoPonto.totalCount

  def listaPontoUsuario(usuario: Usuario, dataDiaInicio: LocalDateTime): List[Ponto] = {
    var ponto = new Ponto()
    ponto.usuario = usuario
    return this.daoPonto.buscarPontos(ponto, dataDiaInicio, null)
  }

  def listaPontoUsuario(dataDiaInicio: LocalDateTime): List[Ponto] = {
    return this.daoPonto.buscarPontos(dataDiaInicio, null)
  }

  def listaPontoUsuario(usuario: Usuario, dataDiaInicio: LocalDateTime, dataDiaFim: LocalDateTime): List[Ponto] = {
    var ponto = new Ponto()
    ponto.usuario = usuario
    return this.daoPonto.buscarPontos(ponto, dataDiaInicio, dataDiaFim)
  }

}
