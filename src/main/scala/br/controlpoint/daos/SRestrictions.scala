package br.controlpoint.daos

import org.hibernate.criterion.Restrictions
import org.hibernate.criterion.Order.asc
import org.hibernate.criterion.Order.desc
import org.hibernate.criterion.Order
import org.hibernate.criterion.Criterion

class SRestrictions(s:String) {
  
  def eq_(v:Object):Criterion = Restrictions.eq(s,v)
  
  def between(v1:Object, v2:Object):Criterion = Restrictions.between(s,v1,v2)
  
  def orderAsc:Order = Order.asc(s)
  
  def orderDesc:Order = Order.desc(s)
  
  def isNull:Criterion = Restrictions.isNull(s)
  
  def isEmpty:Criterion = Restrictions.isEmpty(s)
  
  def isNotEmpty:Criterion = Restrictions.isNotEmpty(s)

}