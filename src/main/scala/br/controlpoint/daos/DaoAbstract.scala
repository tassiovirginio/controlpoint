package br.controlpoint.daos

import java.io.Serializable
import java.util.{ List => JList }
import org.apache.commons.logging.LogFactory
import org.hibernate.{ SessionFactory, Session }
import org.hibernate.criterion._


abstract class DaoAbstract[D, K <: Serializable] {
  
  implicit def rest(x:String):SRestrictions = new SRestrictions(x)

  val LOG = LogFactory.getLog(getClass())
  
  var sessionFactory: SessionFactory = _
  
  protected def domain: Class[D]
  
  protected def currentSession = sessionFactory.getCurrentSession()

  protected def createCriteria = currentSession.createCriteria(domain)

  def delete(d: D) = currentSession.delete(d)

  def getAll(offset: Int, maxResult: Int): JList[D] = createCriteria
    .setMaxResults(maxResult)
    .setFirstResult(offset)
    .list().asInstanceOf[JList[D]]

  def getAll(): JList[D] = createCriteria.list().asInstanceOf[JList[D]]

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): JList[D] = {
    var c = createCriteria
      .setMaxResults(max)
      .setFirstResult(offset)
    if (asc) {
      c.addOrder(Order.asc(order))
    } else {
      c.addOrder(Order.desc(order))
    }
    c.list().asInstanceOf[JList[D]]
  }

  def getById(id: K): D = currentSession.get(domain, id).asInstanceOf[D]

  def totalCount: Long = {
    var result = createCriteria.setProjection(Projections.rowCount())
    if (result == null) return 0L
    if (result.isInstanceOf[Integer]) { return result.asInstanceOf[Integer].longValue() }
    return result.asInstanceOf[Long].longValue
  }

  def size: Long = {
    var result = createCriteria.setProjection(Projections.rowCount())
    if (result == null) return 0L
    if (result.isInstanceOf[Integer]) { return result.asInstanceOf[Integer].longValue() }
    return result.asInstanceOf[Long].longValue
  }

  def save(d: D): Unit = currentSession.saveOrUpdate(d)

}