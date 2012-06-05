package br.controlpoint.util

import java.util.Date
import org.apache.wicket.Response
import org.apache.wicket.extensions.yui.calendar.DateTimeField
import org.apache.wicket.markup.{ComponentTag,MarkupStream}
import org.apache.wicket.model.IModel

class DateTimeField24h(id:String,model:IModel[Date]) extends DateTimeField(id,model){
	
	val serialVersionUID:Long = 1L
	
	override def toString():String = super.toString
	
	override def toString(d:Boolean):String = super.toString(d)
	
	override def renderAll(m:MarkupStream) = super.renderAll(m)
	
	override def renderPlaceholderTag(t:ComponentTag,r:Response) = super.renderPlaceholderTag(t,r)
	
	def this(id:String) = this(id,null)
	
	override def use12HourFormat():Boolean = false
	
	override def onRender(m:MarkupStream) = super.onRender(m)
	
}