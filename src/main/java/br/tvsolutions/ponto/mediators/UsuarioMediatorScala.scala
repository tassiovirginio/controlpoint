package br.tvsolutions.ponto.mediators

import java.util.List

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.tvsolutions.ponto.daos.DaoUsuarioScala
import br.tvsolutions.ponto.entities.Usuario

import scala.reflect.BeanProperty

@Component
@Transactional
class UsuarioMediatorScala extends TUsuarioMediatorScala{
	
	@Autowired
	var daoUsuarioScala:DaoUsuarioScala=_
	
	def getDaoUsuario = daoUsuarioScala

	def setDaoUsuario(daoUsuario:DaoUsuarioScala) = {this.daoUsuarioScala = daoUsuario}
	
	def salvarUsuario(usuario:Usuario)=	daoUsuarioScala.save(usuario)
	
	def sizeUsuario = daoUsuarioScala.size()
	 
	def fazerLogin(usuario:Usuario) = daoUsuarioScala.buscarUsuarioPorLogin(usuario)
	
	def getUsuarioForId(id:Long)= daoUsuarioScala.getById(id)
	
	def listaUsuarios:java.util.List[Usuario] = daoUsuarioScala.getAll()

}