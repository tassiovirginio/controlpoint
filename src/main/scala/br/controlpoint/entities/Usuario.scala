package br.controlpoint.entities

import org.joda.time.{DateTime,LocalTime}
import java.io.Serializable
import scala.slick.direct.AnnotationMapper._
import scala.slick.driver.HsqldbDriver.simple._
import java.sql.Timestamp
import org.joda.time.DateTimeZone
import Implicits._
import java.sql.Date

import Database.threadLocalSession

object Usuarios extends Table[Usuario]("Usuario") {
  
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def acesso = column[Boolean]("acesso")
  def nome = column[String]("nome")
  def email = column[String]("email")
  def login = column[String]("login")
  def senha = column[String]("senha")
  def ips = column[String]("ips")
  def externo = column[Boolean]("externo")
  def gtalk = column[Boolean]("gtalk")
  def adm = column[Boolean]("adm")
  def wallpaper = column[String]("wallpaper")
  def horaEntrada = column[DateTime]("horaEntrada")
  def horaSaida = column[DateTime]("horaSaida")
  def jornada = column[DateTime]("jornada")
  def alerta1 = column[DateTime]("alerta1")
  
  val byId = createFinderBy(_.id)
  val byName = createFinderBy(_.nome)
  val byLogin = createFinderBy(_.login)
  
  def * = id ~ acesso ~ nome ~ email ~ login ~ senha ~ ips ~ externo ~ gtalk ~ adm ~ wallpaper ~ horaEntrada ~ horaSaida ~ jornada ~ alerta1 <> (Usuario, Usuario.unapply _)
  
  def findById(id: Long): Option[Usuario] = database.withSession {
    Usuarios.byId(id).firstOption
  }
 
  def findByName(name: String): Option[Usuario] = database.withSession {
    Usuarios.byName(name).firstOption
  }
  
  def findByLogin(login: String): Option[Usuario] = database.withSession {
    Usuarios.byLogin(login).firstOption
  }
 
  def update(usuario: Usuario) = database.withSession { 
    Usuarios.where(_.id === usuario.id).update(usuario)
  }
 
  def delete(usuarioId: Long) = database.withSession {
    Usuarios.where(_.id === usuarioId).delete
  }
  
  def salvar(usuario: Usuario):Unit = database.withSession {
    Usuarios.insert(usuario)
  }

  def all:List[Usuario] = database.withSession {
    (for(u <- Usuarios) yield u).list()
  }
}


case class Usuario(
	var id:Long,
	var acesso:Boolean,
	var nome:String,
	var email:String,
	var login:String,
	var senha:String,
	var ips:String,
	var externo:Boolean,
	var gtalk:Boolean,
	var adm:Boolean,
	var wallpaper:String,
	var horaEntrada:DateTime,
	var horaSaida:DateTime,
	var jornada:DateTime,
	var alerta1:DateTime
	)extends Serializable {
    
  def this() = this(0,false,"","","","","",false,false,false,"",null,null,null,null)
  
	override def equals(that: Any): Boolean = that match {
    case u:Usuario => this.id == u.id
    case _ => false
  }
	
  override def hashCode = this.id.hashCode
  override def toString = "Usuario("+this.id+")"
}