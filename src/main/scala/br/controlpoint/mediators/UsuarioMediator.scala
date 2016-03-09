package br.controlpoint.mediators

import br.controlpoint.daos.DaoUsuario
import br.controlpoint.entities.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UsuarioMediator {

  @Autowired var daoUsuario: DaoUsuario = _

  def getDaoUsuario = daoUsuario

  def setDaoUsuario(daoUsuario: DaoUsuario) = {
    this.daoUsuario = daoUsuario
  }

  def salvarUsuario(usuario: Usuario) = daoUsuario.save(usuario)

  def sizeUsuario = daoUsuario.size

  def fazerLogin(usuario: Usuario) = daoUsuario.buscarUsuarioPorLogin(usuario)

  def buscarUsuarioPorLogin(login: String): Usuario = daoUsuario.buscarUsuarioPorLogin(login)

  def getUsuarioForId(id: Long) = daoUsuario.getById(id)

  def listaUsuarios: java.util.List[Usuario] = daoUsuario.getAll()

}