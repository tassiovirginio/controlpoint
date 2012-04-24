package br.controlpoint.util

import java.util.Date
import org.apache.wicket.Response
import org.apache.wicket.extensions.yui.calendar.DateTimeField
import org.apache.wicket.markup.{ComponentTag,MarkupStream}
import org.apache.wicket.model.IModel

class DateTimeField24h(id:String,model:IModel[Date]) extends DateTimeField(id,model){
	
	val serialVersionUID:Long = 1L
	
	override def toString():String = super.toString
	
	override def toString(detailed:Boolean):String = super.toString(detailed)
	
	override def renderAll(markupStream:MarkupStream) = super.renderAll(markupStream)
	
	override def renderPlaceholderTag(tag:ComponentTag,response:Response) = super.renderPlaceholderTag(tag, response)
	
	def this(id:String) = this(id,null)
	
	override def use12HourFormat():Boolean = false
	
	override def onRender(markupStream:MarkupStream) = super.onRender(markupStream)
	
}

object DateTimeField24h{val serialVersionUID:Long = 1L;}
