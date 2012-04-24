package br.tvsolutions.ponto.mediators

import br.tvsolutions.ponto.daos.{DaoUsuarioScala, DaoPontoScala}
import br.tvsolutions.ponto.entities.{Usuario, Ponto}
import java.util.List
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import br.tvsolutions.ponto.daos.DaoPonto
import br.tvsolutions.ponto.daos.DaoUsuario

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