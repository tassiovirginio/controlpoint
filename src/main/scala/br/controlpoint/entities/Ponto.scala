package br.controlpoint.entities

import org.joda.time.DateTime
import java.io.Serializable
import scala.slick.direct.AnnotationMapper.column
import br.controlpoint.entities.Implicits._
import java.sql.Date
import scala.slick.driver.HsqldbDriver.simple._
import Database.threadLocalSession

object Implicits {
  
  var database = Database.forURL("jdbc:hsqldb:mem:controlpoint", user ="sa", password="", driver = "org.hsqldb.jdbcDriver")
  
  implicit def DateToDateTime(t:Date):DateTime = new DateTime(t.getTime())
  implicit def DateTimeToDate(d:DateTime):Date = new Date(d.getMillis())
  
  implicit object JodaTimeSQLMapper
    extends scala.slick.lifted.MappedTypeMapper[org.joda.time.DateTime, java.sql.Timestamp]
    with scala.slick.lifted.BaseTypeMapper[org.joda.time.DateTime] {
    def map(j: org.joda.time.DateTime) = new java.sql.Timestamp(j.getMillis)
    def comap(s: java.sql.Timestamp) = new org.joda.time.DateTime(s.getTime)
    override def sqlType = Some(java.sql.Types.TIMESTAMP)
    override def sqlTypeName = Some("timestamp")
  }
}

object Pontos extends Table[Ponto]("Ponto") {
  def id = column[Long]("id", O.PrimaryKey)
  def usuario_id = column[Long]("usuario_id")
  def ipInicio = column[String]("ipInicio")
  def ipFim = column[String]("ipFim")
  def total = column[Int]("total")
  def dataInicio = column[DateTime]("ipInicio")
  def dataFim = column[DateTime]("ipInicio")

  val byId = createFinderBy(_.id)

  def * = id ~ usuario_id ~ ipInicio ~ ipFim ~ total ~ dataInicio ~ dataFim <> (Ponto, Ponto.unapply _)

  def findById(id: Long): Option[Ponto] = database.withSession {
    Pontos.byId(id).firstOption
  }

  def update(ponto: Ponto) = database.withSession {
    Pontos.where(_.id === ponto.id).update(ponto)
  }

  def delete(id: Long) = database.withSession {
    Pontos.where(_.id === id).delete
  }

  def salvar(ponto: Ponto):Unit = database.withSession {
    Pontos.insert(ponto)
  }

  def all:List[Ponto] = database.withSession {
    (for(p <- Pontos) yield p).list()
  }

  def all(u:Usuario):List[Ponto] = database.withSession {
    (for(p <- Pontos if p.usuario_id === u.id) yield p).list()
  }

  def all(u:Usuario, di:DateTime, df:DateTime):List[Ponto] = database.withSession {
    (for(p <- Pontos if p.usuario_id === u.id && p.dataInicio >= di && p.dataFim <= df) yield p).list()
  }

  def all(di:DateTime, df:DateTime):List[Ponto] = database.withSession {
    (for(p <- Pontos if p.dataInicio >= di && p.dataFim <= df) yield p).list()
  }
}

case class Ponto(
    var id:Long,
    var usuario_id:Long,
    var ipInicio:String,
    var ipFim:String,
    var total:Int,
    var dataInicio:DateTime,
    var dataFim: DateTime
)extends Serializable {
  def this() = this(0,0,"","",0,null,null)
  def usuario = Usuarios.findById(usuario_id).get
  def usuario_=(u:Usuario):Unit = {usuario_id = u.id} 
  override def equals(that: Any): Boolean = that match {
    case p: Ponto => this.id == p.id
    case _ => false
  }
  override def hashCode = this.id.hashCode
  override def toString = "Ponto(" + this.id + ")"
}