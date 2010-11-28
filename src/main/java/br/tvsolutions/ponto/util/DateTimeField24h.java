package br.tvsolutions.ponto.util;

import java.util.Date;

import org.apache.wicket.Response;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;

public class DateTimeField24h extends DateTimeField{
	private static final long serialVersionUID = 1L;

	public DateTimeField24h(String id){
		super(id);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toString(boolean detailed) {
		return super.toString(detailed);
	}
	
	@Override
	protected void renderAll(MarkupStream markupStream) {
		super.renderAll(markupStream);
	}
	
	@Override
	protected void renderPlaceholderTag(ComponentTag tag, Response response) {
		super.renderPlaceholderTag(tag, response);
	}
	
	public DateTimeField24h(String id, IModel<Date> model){
		super(id,model);
	}
	
	public boolean use12HourFormat()
	{
		return false;
	}
	
	@Override
	protected void onRender(MarkupStream markupStream) {
		super.onRender(markupStream);
	}
}
