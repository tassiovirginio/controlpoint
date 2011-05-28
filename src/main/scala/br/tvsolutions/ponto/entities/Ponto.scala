package br.tvsolutions.ponto.entities

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
class Ponto extends Serializable{
	
	@Id
	@GeneratedValue
	@BeanProperty
	var id:Long = _
	
	@Type (`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	@BeanProperty
	var dataInicio:DateTime = _
	
	@Type (`type`="org.joda.time.contrib.hibernate.PersistentDateTime")
	@BeanProperty
	var dataFim:DateTime = _
	
	@ManyToOne(fetch=FetchType.LAZY)
	@BeanProperty
	var usuario:Usuario = _
	
	@BeanProperty
	var ipInicio:String = _
	
	@BeanProperty
	var ipFim:String = _
	
	@BeanProperty
	var total:Integer = _
}

object Ponto{
	val serialVersionUID:Long = 1L;
}