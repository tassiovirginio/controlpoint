package br.controlpoint.entities

import javax.persistence.{Entity,GeneratedValue,Id}

import org.hibernate.annotations.Type
import org.joda.time.{DateTime,LocalTime}
import scala.reflect.BeanProperty
import java.lang.{Boolean => JBoolean}

import java.io.Serializable

@Entity
case class Usuario extends Serializable{
	
	@Id
	@GeneratedValue
	var id:Long = _

	var acesso:Boolean = _

	var nome:String = _

	var email:String = _

	var login:String = _

	var senha:String = _

	var ips:String = _

	var externo:JBoolean = _

	var gtalk:JBoolean = _

	var adm:Boolean = _

	var wallpaper:String = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	var horaEntrada:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	var horaSaida:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	var jornada:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentTimeOfDay")
	var alerta1:LocalTime = _
	
	override def equals(that: Any): Boolean = that match {
     case u:Usuario => this.id == u.id
     case _ => false
    }
	
    override def hashCode = this.id.hashCode
    
    override def toString = "Usuario("+this.id+")"

}