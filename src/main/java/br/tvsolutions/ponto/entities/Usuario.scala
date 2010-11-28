package br.tvsolutions.ponto.entities

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import org.hibernate.annotations.Type
import org.joda.time.DateTime
import org.joda.time.LocalTime
import scala.reflect.BeanProperty
import java.lang.Boolean

@Entity
class Usuario extends Serializable {
	
	@Id
	@GeneratedValue
	@BeanProperty 
	var id:Long = _

	@BeanProperty
	var acesso:Boolean = _
	
	@BeanProperty
	var nome:String = _
	
	@BeanProperty
	var email:String = _
	
	@BeanProperty
	var login:String = _
	
	@BeanProperty
	var senha:String = _
	
	@BeanProperty
	var ips:String = _
	
	@BeanProperty
	var externo:java.lang.Boolean = _
	
	@BeanProperty
	var gtalk:java.lang.Boolean = _
	
	@BeanProperty
	var adm:Boolean = _
	
	@BeanProperty
	var wallpaper:String = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	@BeanProperty
	var horaEntrada:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	@BeanProperty
	var horaSaida:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	@BeanProperty
	var jornada:DateTime = _
	
	@Type(`type`="org.joda.time.contrib.hibernate.PersistentTimeOfDay")
	@BeanProperty
	var alerta1:LocalTime = _

}

object Usuario{
	val serialVersionUID:Long = 1L;
}