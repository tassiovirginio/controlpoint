package br.tvsolutions.ponto

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import br.tvsolutions.ponto.entities.Ponto;
import br.tvsolutions.ponto.entities.Usuario;
import br.tvsolutions.ponto.mediators.TPontoMediatorScala;

import java.io.Serializable

class PontoListPanelScala(usuarioSelecionado:Usuario,usuarioLogado:Usuario,dateBuscaInicio:DateTime,dateBuscaFim:DateTime,editavel:Boolean,pageOrigem:PontoBasePageScala) extends Panel("pontoListPanel") with Serializable{
	
	var serialVersionUID:Long = 1L;

	@SpringBean
	var pontoMediatorScala:TPontoMediatorScala =_
	
	var btAdd:Button =_
	var btEntrada:Button =_
	var btSaida:Button =_
	var pontoParaFechar:Ponto =_
	var periodTotal:Period =_
	var lbPeriodTotal:Label =_
	var container:WebMarkupContainer =_
	var listData:List[Ponto] =_
	var editarPontoWinModal:ModalWindow =_
	

	var infoWeb:WebClientInfo = getSession().getClientInfo().asInstanceOf[WebClientInfo]
	periodTotal = new Period();
	lbPeriodTotal = new Label("periodTotal");
	lbPeriodTotal.setOutputMarkupId(true);
	add(lbPeriodTotal);
		
	listData = pontoMediatorScala.listaPontoUsuario(usuarioSelecionado, dateBuscaInicio, dateBuscaFim);
	
	btAdd = new Button("btAdd") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			var novoPonto = new Ponto()
			novoPonto.setDataInicio(dateBuscaInicio)
			novoPonto.setUsuario(usuarioSelecionado)
			novoPonto.setIpInicio(infoWeb.getProperties().getRemoteAddress())
			pontoMediatorScala.salvarPonto(novoPonto)
			setResponsePage(new PontoPageScala(usuarioLogado,usuarioSelecionado,dateBuscaInicio,dateBuscaFim,editavel))
		}
	};
	var formAdd = new Form("formAdd")
	formAdd.add(btAdd)
	add(formAdd)
	formAdd.setVisible(usuarioLogado.getAdm().asInstanceOf[Boolean])
		
	btEntrada = new Button("btEntrada") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			var novoPonto = new Ponto()
			novoPonto.setDataInicio(new DateTime())
			novoPonto.setUsuario(usuarioLogado)
			novoPonto.setIpInicio(infoWeb.getProperties().getRemoteAddress())
			pontoMediatorScala.salvarPonto(novoPonto)
			btEntrada.setVisible(false)
			btSaida.setVisible(true)
			setResponsePage(new PontoPageScala(usuarioLogado,editavel))
		}
	};

	btSaida = new Button("btSaida") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			pontoParaFechar.setDataFim(new DateTime())
			pontoParaFechar.setIpFim(infoWeb.getProperties().getRemoteAddress())
			pontoMediatorScala.salvarPonto(pontoParaFechar)
			pontoParaFechar = null
			btEntrada.setVisible(true)
			btSaida.setVisible(false)
			setResponsePage(new PontoPageScala(usuarioLogado,editavel))
		}
	};
		
	btEntrada.setVisible(editavel);
	btSaida.setVisible(false);
	
	add(new ListView[Ponto]("listaPonto", listData) {
		val serialVersionUID:Long = 1L;

		override protected def onBeforeRender() {
			periodTotal = new Period();
			super.onBeforeRender();
		}

		def populateItem(item:ListItem[Ponto]) = {
			var ponto = item.getModelObject()
			item.add(new LinkDate("dataInicio", ponto, usuarioLogado.getAdm(),LinkDate.ENTRADA))
			if (ponto.getDataFim() == null) {
				btEntrada.setVisible(false)
				btSaida.setVisible(true)
				pontoParaFechar = ponto;
				item.add(new LinkDate("dataFim", ponto, false,LinkDate.BRANCO))
			} else {
				item.add(new LinkDate("dataFim", ponto, usuarioLogado.getAdm(),LinkDate.SAIDA))
			}
			if (ponto.getDataFim() == null) {
				item.add(new Label("total", ""))
			} else {
				var period = new Period(ponto.getDataInicio(), ponto.getDataFim())
				periodTotal = periodTotal.plus(period)
				lbPeriodTotal.setDefaultModel(new Model(periodTotal.toPeriod().normalizedStandard().toString(getFormatter())))
				period = period.normalizedStandard()
				item.add(new Label("total", period.toString(getFormatter())))
			}
			//Regra para quando chegar no tamanho maximo travar o ponto max 2 por dia se for ext)
			if(usuarioLogado.getExterno().asInstanceOf[Boolean]){
				if(dateBuscaFim == null && listData.size() > 1 && ponto.getDataFim() != null){
					container.setVisible(false)
				}else{
					container.setVisible(editavel)
				}
			}
			item.add(new LinkDelete("del", ponto, usuarioLogado.getAdm()))
		}
	});
	
	var formEntrada = new Form("formEntrada")
	formEntrada.add(btEntrada)
	formEntrada.add(btSaida)
		
	container = new WebMarkupContainer("container")
	container.add(formEntrada)
	add(container)
	
	container.setVisible(editavel)
    
    editarPontoWinModal = new ModalWindow("modalEditPonto")
    editarPontoWinModal.setTitle("Editar Ponto")
    editarPontoWinModal.setCookieName("modalEditPonto")
    editarPontoWinModal.setPageMapName("modalEditPonto")
    editarPontoWinModal.setHeightUnit("100")
    editarPontoWinModal.setWidthUnit("100")
    editarPontoWinModal.setResizable(true)
    editarPontoWinModal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback(){
            def onClose(target:AjaxRequestTarget) = setResponsePage(pageOrigem)
        })
	add(editarPontoWinModal);
	
	private class LinkDate(id:String,ponto:Ponto,clickavel:java.lang.Boolean,tipo:Integer) extends AjaxLink[String](id){
		if(clickavel == true){
			setEnabled(true);
		}else{
			setEnabled(false);
		}
		add(new Label("label", new Model[String]() {
            override def getObject():String = {
            	var retorno = "";
            	if(tipo == LinkDate.ENTRADA){retorno = ponto.getDataInicio().toString("dd/MM/YYYY HH:mm:ss")}
            	if(tipo == LinkDate.SAIDA){
            		retorno = ponto.getDataFim().toString("dd/MM/YYYY HH:mm:ss")}
            	return retorno;
            }
		}));
		
		def onClick(target:AjaxRequestTarget) = {
			editarPontoWinModal.setContent(new EditPontoPanelScala(editarPontoWinModal.getContentId(),this.ponto,editarPontoWinModal,pageOrigem));
			editarPontoWinModal.show(target);
		}
	}
	//Pode existir a Class e o Object-Class
	object LinkDate extends Serializable{
		val serialVersionUID:Long = 1L
		val ENTRADA:Integer = 1
		val SAIDA:Integer = 2
		val BRANCO:Integer = 3
	}
	
	private class LinkDelete(id:String,ponto:Ponto,clickavel:java.lang.Boolean) extends AjaxLink[String](id){
		val serialVersionUID:Long = 1L;
		
		@SpringBean
		var pontoMediatorScala:TPontoMediatorScala =_
	
		setVisible(clickavel.asInstanceOf[Boolean])
		add(new Label("label", new Model[String]() {
			override def getObject:String = "Del"
		}));

		def onClick(target:AjaxRequestTarget) {
			pontoMediatorScala.deletePonto(ponto)
			setResponsePage(new PontoPageScala(usuarioSelecionado,usuarioLogado,dateBuscaInicio,dateBuscaFim,editavel));
		}
	}
	
	private def getFormatter() = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours()
				.appendSeparator(":").appendMinutes().appendSeparator(":").appendSeconds().toFormatter()

}