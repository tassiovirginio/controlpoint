package br.controlpoint.mediators

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import br.controlpoint.entities.{Usuario, Ponto}
import org.joda.time.DateTime
import br.controlpoint.daos.TDaoPonto

@Component
@Transactional
class PontoMediator extends TPontoMediator {
	
	@Autowired
	private var daoPonto:TDaoPonto =_
	
	def salvarPonto(ponto:Ponto) = daoPonto.save(ponto)
	
	def deletePonto(ponto:Ponto) = daoPonto.delete(ponto)
	
	def sizePonto() = daoPonto.totalCount
	
	def listaPontoUsuario(u:Usuario, di:DateTime):List[Ponto] = {
		daoPonto.buscarPontos(u,di,null)
	}
	
	def listaPontoUsuario(di:DateTime):List[Ponto]={
		daoPonto.buscarPontos(di,null)
	}
	
	def listaPontoUsuario(u:Usuario,di:DateTime,df:DateTime):List[Ponto]={
		daoPonto.buscarPontos(u,di,df)
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