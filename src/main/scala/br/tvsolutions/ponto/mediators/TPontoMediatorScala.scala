package br.tvsolutions.ponto.mediators
import br.tvsolutions.ponto.daos._
import br.tvsolutions.ponto.entities.{Usuario, Ponto}
import java.util.List
import org.joda.time.DateTime

trait TPontoMediatorScala {
	
	def salvarPonto(ponto:Ponto):Unit
	
	def deletePonto(ponto:Ponto):Unit
	
	def sizePonto():Long 

	def getDaoPonto():DaoPontoScala 

	def setDaoUsuario(daoPonto:DaoPontoScala):Unit 
	
	def listaPontoUsuario(usuario:Usuario, dataDiaInicio:DateTime):List[Ponto] 
	
	def listaPontoUsuario(dataDiaInicio:DateTime):List[Ponto]
	
	def listaPontoUsuario(usuario:Usuario,dataDiaInicio:DateTime,dataDiaFim:DateTime):List[Ponto]

}