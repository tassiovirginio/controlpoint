package br.tvsolutions.ponto.mediators

import br.tvsolutions.ponto.daos.DaoUsuario
import br.tvsolutions.ponto.entities._

trait TUsuarioMediator {
	
	def getDaoUsuario():DaoUsuario

	def setDaoUsuario(daoUsuario:DaoUsuario):Unit
	
	def salvarUsuario(usuario:Usuario):Unit
	
	def sizeUsuario:Long
	
	def fazerLogin(usuario:Usuario):Usuario
	
	def getUsuarioForId(id:Long):Usuario
	
	def listaUsuarios:java.util.List[Usuario]

}