package br.controlpoint.mediators

import java.util.List
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.{Component,Service}
import org.springframework.transaction.annotation.Transactional
import br.controlpoint.daos.DaoUsuario
import br.controlpoint.entities.Usuario
import scala.reflect.BeanProperty

@Component
@Transactional
class UsuarioMediator extends TUsuarioMediator{
	
	@Autowired
	var daoUsuario:DaoUsuario = _
	
	def getDaoUsuario = daoUsuario

	def setDaoUsuario(daoUsuario:DaoUsuario) = {this.daoUsuario = daoUsuario}
	
	def salvarUsuario(usuario:Usuario) = daoUsuario.save(usuario)
	
	def sizeUsuario = daoUsuario.size()
	 
	def fazerLogin(usuario:Usuario) = daoUsuario.buscarUsuarioPorLogin(usuario)
	
	def getUsuarioForId(id:Long)= daoUsuario.getById(id)
	
	def listaUsuarios:java.util.List[Usuario] = daoUsuario.getAll()

}