package br.tvsolutions.ponto.mediators

import br.tvsolutions.ponto.daos.{DaoUsuarioScala, DaoPontoScala}
import br.tvsolutions.ponto.entities.{Usuario, Ponto};

import java.util.List
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PontoMediatorScala extends TPontoMediatorScala {
	
	@Autowired
	var daoPontoScala:DaoPontoScala=_
	
	@Autowired
	var daoUsuarioScala:DaoUsuarioScala=_
	
	def salvarPonto(ponto:Ponto):Unit = daoPontoScala.save(ponto)
	
	def deletePonto(ponto:Ponto):Unit = daoPontoScala.delete(ponto)
	
	def sizePonto():Long = daoPontoScala.getTotalCount()

	def getDaoPonto():DaoPontoScala = daoPontoScala

	def setDaoUsuario(daoPonto:DaoPontoScala):Unit = {this.daoPontoScala = daoPonto}
	
	def listaPontoUsuario(usuario:Usuario, dataDiaInicio:DateTime):List[Ponto] = {
		var ponto = new Ponto()
		ponto.setUsuario(usuario)
		return this.daoPontoScala.buscarPontos(ponto,dataDiaInicio,null)
	}
	
	def listaPontoUsuario(dataDiaInicio:DateTime):List[Ponto]={
		return this.daoPontoScala.buscarPontos(dataDiaInicio,null);
	}
	
	def listaPontoUsuario(usuario:Usuario,dataDiaInicio:DateTime,dataDiaFim:DateTime):List[Ponto]={
		var ponto = new Ponto();
		ponto.setUsuario(usuario);
		return this.daoPontoScala.buscarPontos(ponto,dataDiaInicio,dataDiaFim);
	}

}