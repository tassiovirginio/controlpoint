package br.tvsolutions.ponto.daos
import br.tvsolutions.ponto.entities.Ponto

import java.io.Serializable
import java.util.{List => jList}
import org.hibernate.criterion.{Restrictions, Order}
import org.joda.time.DateTime
import org.springframework.stereotype.Component
import scala.collection.JavaConversions._

@Component
class DaoPonto extends DaoAbstract[Ponto,java.lang.Long]  with Serializable{

	override def getDomain() = classOf[Ponto]
	
	def buscarPontos(ponto:Ponto,dataDiaInicio:DateTime,dataDiaFim:DateTime):jList[Ponto] = {
		var dateTime1 = dataDiaInicio.withTime(00,00,00,00)
		var dateTime2 = dataDiaInicio.withTime(23,59,59,00)
		if(dataDiaFim != null){
			dateTime2 = dataDiaFim.withTime(23,59,59,00)
		}
		return getCurrentSession().createCriteria(classOf[Ponto])
		.add(Restrictions.eq("usuario", ponto.getUsuario()))
		.add(Restrictions.between("dataInicio", dateTime1, dateTime2))
		.addOrder(Order.asc("dataInicio"))
		.list().asInstanceOf[jList[Ponto]]
	}
	
	def buscarPontos(dataDiaInicio:DateTime,dataDiaFim:DateTime):jList[Ponto] = {
		var dateTime1 = dataDiaInicio.withTime(00,00,00,00)
		var dateTime2 = dataDiaInicio.withTime(23,59,59,00)
		if(dataDiaFim != null){dateTime2 = dataDiaFim.withTime(23,59,59,00)}

		var lista = getCurrentSession().createCriteria(classOf[Ponto])
		.add(Restrictions.between("dataInicio", dateTime1, dateTime2))
		.add(Restrictions.isNull("dataFim"))
		.addOrder(Order.asc("dataInicio"))
		.list().asInstanceOf[jList[Ponto]]
		
		for (ponto <- lista) {
			ponto.getUsuario().nome
		}

		return lista
	}
}

object DaoPontoScala{val serialVersionUID:Long = 1L;}