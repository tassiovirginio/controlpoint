package br.controlpoint.entities

import javax.persistence.{GeneratedValue, Id}

import scala.beans.BeanProperty

/**
  * Created by tassio on 08/03/16.
  */
trait Identifiable extends Serializable {
  @Id @GeneratedValue @BeanProperty var id: Long = _
}
