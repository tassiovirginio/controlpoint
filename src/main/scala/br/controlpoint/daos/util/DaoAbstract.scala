package br.controlpoint.daos.util

import java.io.Serializable
import java.util.{List => JList}

import org.apache.commons.logging.LogFactory
import org.hibernate.SessionFactory
import org.hibernate.criterion._
import org.springframework.beans.factory.annotation.Autowired

abstract class DaoAbstract[D, K <: Serializable] {

  implicit def rest(x: String): SRestrictions = new SRestrictions(x)

  val LOG = LogFactory.getLog(getClass())

  @Autowired var sessionFactory: SessionFactory = _

  protected def domain: Class[D]

  protected def currentSession = sessionFactory.getCurrentSession()

  protected def criteria = currentSession.createCriteria(domain)

  def delete(d: D) = currentSession.delete(d)

  def getAll(offset: Int, maxResult: Int): JList[D] = criteria
    .setMaxResults(maxResult)
    .setFirstResult(offset)
    .list().asInstanceOf[JList[D]]

  def getAll(): JList[D] = criteria.list().asInstanceOf[JList[D]]

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): JList[D] = {
    val c = criteria
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
    return criteria.setProjection(Projections.rowCount()).asInstanceOf[Long]
  }

  def size: Long = {
    return criteria.setProjection(Projections.rowCount()).asInstanceOf[Long]
  }

  def save(d: D): Unit = currentSession.saveOrUpdate(d)

}