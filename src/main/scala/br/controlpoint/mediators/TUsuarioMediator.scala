package br.controlpoint.mediators

import br.controlpoint.daos.DaoUsuario
import br.controlpoint.entities._

trait TUsuarioMediator {
	
	def getDaoUsuario():DaoUsuario

	def setDaoUsuario(daoUsuario:DaoUsuario):Unit
	
	def salvarUsuario(usuario:Usuario):Unit
	
	def sizeUsuario:Long
	
	def fazerLogin(usuario:Usuario):Usuario
	
	def getUsuarioForId(id:Long):Usuario
	
	def listaUsuarios:java.util.List[Usuario]

}