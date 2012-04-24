package br.controlpoint.mediators

import br.controlpoint.entities.{Usuario, Ponto}
import org.joda.time.DateTime
import java.util.List

trait TPontoMediator {
	
	def salvarPonto(ponto:Ponto):Unit
	
	def deletePonto(ponto:Ponto):Unit
	
	def sizePonto():Long
	
	def listaPontoUsuario(usuario:Usuario, dataDiaInicio:DateTime):List[Ponto] 
	
	def listaPontoUsuario(dataDiaInicio:DateTime):List[Ponto]
	
	def listaPontoUsuario(usuario:Usuario,dataDiaInicio:DateTime,dataDiaFim:DateTime):List[Ponto]

}