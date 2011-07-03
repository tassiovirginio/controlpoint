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
	var id:Long = _

	var acesso:Boolean = _

	var nome:String = _

	var email:String = _

	var login:String = _

	var senha:String = _

	var ips:String = _

	var externo:java.lang.Boolean = _

	var gtalk:java.lang.Boolean = _

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

}

object Usuario{
	val serialVersionUID:Long = 1L;
}