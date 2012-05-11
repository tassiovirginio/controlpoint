package br.controlpoint.entities

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

import org.hibernate.annotations.Type
import org.joda.time.DateTime
import scala.reflect.BeanProperty

@Entity
@SerialVersionUID(1L)
class Ponto extends Serializable{
	
	@Id
	@GeneratedValue
	var id:Long = _
	
	@Type (`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	var dataInicio:DateTime = _
	
	@Type (`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	var dataFim:DateTime = _
	
	@ManyToOne(fetch=FetchType.LAZY)
	var usuario:Usuario = _
	
	var ipInicio:String = _
	
	var ipFim:String = _
	
	var total:Integer = _
}