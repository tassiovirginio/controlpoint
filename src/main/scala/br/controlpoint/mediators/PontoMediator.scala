package br.controlpoint.mediators

import br.controlpoint.daos.{DaoUsuario, DaoPonto}
import br.controlpoint.entities.{Usuario, Ponto}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.joda.time.DateTime
import java.util.List

@Component
@Transactional
class PontoMediator extends TPontoMediator {
	
	@Autowired
	var daoPonto:DaoPonto =_
	
	@Autowired
	var daoUsuario:DaoUsuario =_
	
	def salvarPonto(ponto:Ponto): Unit = daoPonto.save(ponto)
	
	def deletePonto(ponto:Ponto): Unit = daoPonto.delete(ponto)
	
	def sizePonto() = daoPonto.getTotalCount()
	
	def listaPontoUsuario(usuario:Usuario, dataDiaInicio:DateTime):List[Ponto] = {
		var ponto = new Ponto()
		ponto.setUsuario(usuario)
		return this.daoPonto.buscarPontos(ponto,dataDiaInicio,null)
	}
	
	def listaPontoUsuario(dataDiaInicio:DateTime):List[Ponto]={
		return this.daoPonto.buscarPontos(dataDiaInicio,null);
	}
	
	def listaPontoUsuario(usuario:Usuario,dataDiaInicio:DateTime,dataDiaFim:DateTime):List[Ponto]={
		var ponto = new Ponto();
		ponto.setUsuario(usuario);
		return this.daoPonto.buscarPontos(ponto,dataDiaInicio,dataDiaFim);
	}

}