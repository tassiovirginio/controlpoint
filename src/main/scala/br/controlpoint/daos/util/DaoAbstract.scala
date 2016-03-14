package br.controlpoint.daos.util

import java.io.Serializable
import java.util.{List => JList}

import org.apache.commons.logging.LogFactory
import org.hibernate.SessionFactory
import org.hibernate.criterion._
import org.springframework.beans.factory.annotation.Autowired

abstract class DaoAbstract[D, K <: Serializable] {

  val LOG = LogFactory.getLog(getClass())

  @Autowired var sessionFactory: SessionFactory = _

  protected def currentSession = sessionFactory.getCurrentSession()
  protected def criteria = currentSession.createCriteria(domain)
  protected def domain: Class[D] = classOf[D]

  implicit def rest(x: String): SRestrictions = new SRestrictions(x)

  def getAll(offset: Int, maxResult: Int) = criteria.setMaxResults(maxResult).setFirstResult(offset).list.asInstanceOf[JList[D]]
  def getAll = criteria.list.asInstanceOf[JList[D]]

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

  def delete(d: D) = currentSession.delete(d)
  def getById(id: K):D = currentSession.get(domain,id).asInstanceOf[D]
  def totalCount = criteria.setProjection(Projections.rowCount).asInstanceOf[Long]
  def size = criteria.setProjection(Projections.rowCount).asInstanceOf[Long]
  def save(d: D) = currentSession.saveOrUpdate(d)

}