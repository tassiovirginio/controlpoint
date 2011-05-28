package br.tvsolutions.ponto.mediators

import br.tvsolutions.ponto.daos.DaoUsuarioScala
import br.tvsolutions.ponto.entities._

trait TUsuarioMediatorScala {
	
	def getDaoUsuario():DaoUsuarioScala

	def setDaoUsuario(daoUsuario:DaoUsuarioScala):Unit
	
	def salvarUsuario(usuario:Usuario):Unit
	
	def sizeUsuario:Long
	
	def fazerLogin(usuario:Usuario):Usuario
	
	def getUsuarioForId(id:Long):Usuario
	
	def listaUsuarios:java.util.List[Usuario]

}