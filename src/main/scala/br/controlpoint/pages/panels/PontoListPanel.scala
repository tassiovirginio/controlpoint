package br.controlpoint.pages.panels

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.{Button,Form}
import org.apache.wicket.markup.html.list.{ListItem,ListView}
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.Model
import org.apache.wicket.protocol.http.request.WebClientInfo
import org.apache.wicket.spring.injection.annot.SpringBean
import org.joda.time.{DateTime,Period}
import org.joda.time.format.PeriodFormatterBuilder
import br.controlpoint.entities.{Ponto,Usuario}
import br.controlpoint.mediators.TPontoMediator
import br.controlpoint.pages.base.PontoBasePage
import br.controlpoint.pages.{PontoPage,EditPontoPage}
import java.io.Serializable
import com.google.code.jqwicket.ui.prettypopin.PrettyPopinBehavior
import com.google.code.jqwicket.ui.prettypopin.PrettyPopinOptions
import org.apache.wicket.markup.html.link.Link


class PontoListPanel(usuarioSelecionado:Usuario,usuarioLogado:Usuario,dateBuscaInicio:DateTime,dateBuscaFim:DateTime,editavel:Boolean,pageOrigem:PontoBasePage) extends Panel("pontoListPanel") with Serializable{
	
	var serialVersionUID:Long = 1L;

	@SpringBean
	var pontoMediator:TPontoMediator =_

	var pontoParaFechar:Ponto =_

	var infoWeb:WebClientInfo = getSession().getClientInfo().asInstanceOf[WebClientInfo]
	var periodTotal = new Period();
	var lbPeriodTotal = new Label("periodTotal");
	lbPeriodTotal.setOutputMarkupId(true);
	add(lbPeriodTotal);
		
	var listData = pontoMediator.listaPontoUsuario(usuarioSelecionado, dateBuscaInicio, dateBuscaFim);
	
	var btAdd = new Button("btAdd") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			var novoPonto = new Ponto()
			novoPonto.setDataInicio(dateBuscaInicio)
			novoPonto.setUsuario(usuarioSelecionado)
			novoPonto.setIpInicio(infoWeb.getProperties().getRemoteAddress())
			pontoMediator.salvarPonto(novoPonto)
			setResponsePage(new PontoPage(usuarioLogado,usuarioSelecionado,dateBuscaInicio,dateBuscaFim,editavel))
		}
	};
	var formAdd = new Form("formAdd")
	formAdd.add(btAdd)
	add(formAdd)
	formAdd.setVisible(usuarioLogado.adm.asInstanceOf[Boolean])


  var btSaida: Button = _
  var btEntrada: Button = _

	btEntrada = new Button("btEntrada") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			var novoPonto = new Ponto()
			novoPonto.setDataInicio(new DateTime())
			novoPonto.setUsuario(usuarioLogado)
			novoPonto.setIpInicio(infoWeb.getProperties().getRemoteAddress())
			pontoMediator.salvarPonto(novoPonto)
			this.setVisible(false)
			btSaida.setVisible(true)
			setResponsePage(new PontoPage(usuarioLogado,editavel))
		}
	};

	btSaida = new Button("btSaida") {
		val serialVersionUID:Long = 1L
		override def onSubmit() {
			pontoParaFechar.setDataFim(new DateTime())
			pontoParaFechar.setIpFim(infoWeb.getProperties().getRemoteAddress())
			pontoMediator.salvarPonto(pontoParaFechar)
			pontoParaFechar = null
			btEntrada.setVisible(true)
			this.setVisible(false)
			setResponsePage(new PontoPage(usuarioLogado,editavel))
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
			item.add(new LinkDate("dataInicio", ponto, usuarioLogado.adm,LinkDate.ENTRADA))
			if (ponto.getDataFim() == null) {
				btEntrada.setVisible(false)
				btSaida.setVisible(true)
				pontoParaFechar = ponto;
				item.add(new LinkDate("dataFim", ponto, false,LinkDate.BRANCO))
			} else {
				item.add(new LinkDate("dataFim", ponto, usuarioLogado.adm,LinkDate.SAIDA))
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
			if(usuarioLogado.externo.asInstanceOf[Boolean]){
				if(dateBuscaFim == null && listData.size() > 1 && ponto.getDataFim() != null){
					container.setVisible(false)
				}else{
					container.setVisible(editavel)
				}
			}
			item.add(new LinkDelete("del", ponto, usuarioLogado.adm))
		}
	});
	
	var formEntrada = new Form("formEntrada")
	formEntrada.add(btEntrada)
	formEntrada.add(btSaida)
		
	var container = new WebMarkupContainer("container")
	container.add(formEntrada)
	add(container)
	
	container.setVisible(editavel)
    
    var editarPontoWinModal = new ModalWindow("modalEditPonto")
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


	private class LinkDate(id:String,ponto:Ponto,clickavel:java.lang.Boolean,tipo:java.lang.Integer) extends Link[String](id){
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
		
		var prettyOpt = new PrettyPopinOptions()
		var pretty = new PrettyPopinBehavior(prettyOpt.width(300).height(180).followScroll(false))
		add(pretty);  
		
		def onClick(){
//		  new EditPontoPanel(editarPontoWinModal.getContentId(),this.ponto,editarPontoWinModal,pageOrigem);
		  setResponsePage(new EditPontoPage(this.ponto,pageOrigem))
		}
		
	}
	//Pode existir a Class e o Object-Class
	object LinkDate extends Serializable{
		val serialVersionUID:Long = 1L
		val ENTRADA:java.lang.Integer = 1
		val SAIDA:java.lang.Integer = 2
		val BRANCO:java.lang.Integer = 3

	}
	
	private class LinkDelete(id:String,ponto:Ponto,clickavel:java.lang.Boolean) extends AjaxLink[String](id){
		val serialVersionUID:Long = 1L;
		
		@SpringBean
		var pontoMediator:TPontoMediator =_
	
		setVisible(clickavel.asInstanceOf[Boolean])
		add(new Label("label", new Model[String]() {
			override def getObject:String = "Del"
		}));

		def onClick(target:AjaxRequestTarget) {
			pontoMediator.deletePonto(ponto)
			setResponsePage(new PontoPage(usuarioSelecionado,usuarioLogado,dateBuscaInicio,dateBuscaFim,editavel));
		}
	}
	
	private def getFormatter() = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours()
				.appendSeparator(":").appendMinutes().appendSeparator(":").appendSeconds().toFormatter()

}