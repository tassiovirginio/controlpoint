package br.controlpoint.mediators

import br.controlpoint.daos.{DaoUsuario, DaoPonto}
import br.controlpoint.entities.{Usuario, Ponto}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.joda.time.DateTime
import br.controlpoint.entities.{Usuario, Ponto}
import org.joda.time.DateTime
import br.controlpoint.daos.TDaoPonto

@Component
@Transactional
class PontoMediator extends TPontoMediator {
	
	@Autowired
	private var daoPonto:TDaoPonto =_
	
	@Autowired
	private var daoUsuario:DaoUsuario =_
	
	def salvarPonto(ponto:Ponto): Unit = daoPonto.save(ponto)
	
	def deletePonto(ponto:Ponto): Unit = daoPonto.delete(ponto)
	
	def sizePonto() = daoPonto.totalCount
	
	def listaPontoUsuario(u:Usuario, di:DateTime):List[Ponto] = {
		return this.daoPonto.buscarPontos(u,di,null)
	}
	
	def listaPontoUsuario(di:DateTime):List[Ponto]={
		return this.daoPonto.buscarPontos(di,null)
	}
	
	def listaPontoUsuario(u:Usuario,di:DateTime,df:DateTime):List[Ponto]={
		return this.daoPonto.buscarPontos(u,di,df)
	}

}

trait TPontoMediator {
	def salvarPonto(ponto:Ponto):Unit
	def deletePonto(ponto:Ponto):Unit
	def sizePonto():Long
	def listaPontoUsuario(usuario:Usuario, dataDiaInicio:DateTime):List[Ponto] 
	def listaPontoUsuario(dataDiaInicio:DateTime):List[Ponto]
	def listaPontoUsuario(usuario:Usuario,dataDiaInicio:DateTime,dataDiaFim:DateTime):List[Ponto]
}