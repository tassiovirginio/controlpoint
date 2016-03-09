package br.controlpoint.util

import java.util.{ArrayList, Date, List}


class RelatorioXLS {

  var nome: String = _
  var periodo: String = _
  var jornada: Date = _
  var horaEntrada: String = _
  var horaSaida: String = _
  var horaExtra: String = _
  var listaPontos: List[PontoXLS] = new ArrayList[PontoXLS]();
  var nomeXLS: String = _

}
