package br.controlpoint.daos

import java.io.Serializable
import java.util.{ List => JList }
import org.apache.commons.logging.LogFactory
import scala.reflect.BeanProperty


abstract class DaoAbstract[D, K <: Serializable] extends TDaoAbstract[D, K] {
  
  implicit def rest(x:String):SRestrictions = new SRestrictions(x)

  val LOG = LogFactory.getLog(getClass())
  
  protected def domain: Class[D] 

  def delete(d: D) = null

  def getAll(offset: Int, maxResult: Int): JList[D] = null

  def getAll(): JList[D] = null

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): JList[D] = null

  def getById(id: K): D = null.asInstanceOf[D]

  def totalCount: Long = {
    0
  }

  def size: Long = {
    0
  }

  def save(d: D): Unit = null

}


trait TDaoAbstract[D, K <: Serializable] {

  def delete(d: D)

  def getAll(offset: Int, maxResult: Int): JList[D] 

  def getAll(): JList[D]

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): JList[D] 
  
  def getById(id: K): D

  def totalCount: Long 

  def size: Long

  def save(d: D): Unit

}