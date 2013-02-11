package br.controlpoint.daos

trait TDaoAbstract[D, K] {

  def delete(d: D)

  def getAll(offset: Int, maxResult: Int): List[D]

  def getAll(): List[D]

  def getAll(offset: Int, max: Int, order: String, asc: Boolean): List[D]
  
  def getById(id: K): D

  def totalCount:K

  def size:K

  def save(d: D): Unit

}