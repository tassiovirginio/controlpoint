package br.controlpoint.daos

import java.io.Serializable
import java.util.List
import org.apache.commons.logging.LogFactory
import org.hibernate.{SessionFactory, Session}
import org.hibernate.criterion._
import scala.reflect.BeanProperty

abstract class DaoAbstract[D,K <: Serializable](implicit m: scala.reflect.Manifest[D]) {
	
  val LOG = LogFactory.getLog(getClass())

	@BeanProperty
	var sessionFactory:SessionFactory = _
 
	protected def currentSession = sessionFactory.getCurrentSession()
 
	def delete(d:D) = currentSession.delete(d)
	
	def getAll(offset:Int,amount:Int):java.util.List[D] = currentSession
	.createCriteria(m.erasure)//Duas maneiras de fazer... getDOmain e implicit Manifest
	.setMaxResults(amount)
	.setFirstResult(offset)
	.list().asInstanceOf[java.util.List[D]]

	
	def getAll():java.util.List[D] = currentSession
	.createCriteria(m.erasure).list().asInstanceOf[java.util.List[D]]

	
	def getAll(offset:Int, amount:Int,orderBy:String,asc:Boolean):List[D] = {
		var c = currentSession.createCriteria(domain) //Duas maneiras de fazer... getDOmain e implicit Manifest
		c.setMaxResults(amount)
		c.setFirstResult(offset)
		if(asc){
			c.addOrder(Order.asc(orderBy))
		}else{
			c.addOrder(Order.desc(orderBy))	
		}
		c.list().asInstanceOf[java.util.List[D]]
	}
	
	def getById(id:K):D = currentSession.get(domain, id).asInstanceOf[D]
	
	def domain:Class[_]
 
	def totalCount:Long = {
		var result = currentSession.createCriteria(domain).setProjection(Projections.rowCount())
		if (result == null) return 0L
		if (result.isInstanceOf[Integer]){return result.asInstanceOf[Integer].longValue()}
        return result.asInstanceOf[Long].longValue
	}
	
	def size:Long = {
		var result = currentSession.createCriteria(domain).setProjection(Projections.rowCount())
		if (result==null) return 0L
		if (result.isInstanceOf[Integer]){return result.asInstanceOf[Integer].longValue()}
        return result.asInstanceOf[Long].longValue
	}
 
	def save(d:D):Unit = currentSession.saveOrUpdate(d)
	
}