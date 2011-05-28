package br.tvsolutions.ponto.util;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import br.tvsolutions.ponto.entities.Ponto;
//import br.tvsolutions.ponto.entities.Usuario;
//import br.tvsolutions.ponto.mediators.TPontoMediatorScala;

//@Component
public class GerarXLS {
	
//	@Autowired
//	private TPontoMediatorScala pontoMediatorScala;
//	
//	private String uri;
//	
//	public byte[] GerarXLSnow(Usuario usuario, DateTime dataInicio, DateTime dataFim){
//		
//		 try{
//	        	File arquivo = new File("pontoonline.txt");
//	        	boolean ok = arquivo.createNewFile();
//	        	if(ok){
//	            	System.out.println("Arquivo criado com sucesso.");
//	        	} 
//	        	else{
//	            	System.out.println("Nao foi possivel criar o arquivo.");
//	        	}
//	    	}
//	    	catch(IOException e){
//	    		e.printStackTrace();
//	    	}
//		
//		
//		RelatorioXLS relatorio = new RelatorioXLS();
//		
//		String horaEntradaMinutos = "";
//		if(String.valueOf(usuario.getHoraEntrada().getMinuteOfHour()).length() == 1){
//			horaEntradaMinutos = "0" + String.valueOf(usuario.getHoraEntrada().getMinuteOfHour());
//		}else{
//			horaEntradaMinutos = String.valueOf(usuario.getHoraEntrada().getMinuteOfHour());
//		}
//		
//		String horaSaidaMinutos = "";
//		if(String.valueOf(usuario.getHoraSaida().getMinuteOfHour()).length() == 1){
//			horaSaidaMinutos = "0" + String.valueOf(usuario.getHoraSaida().getMinuteOfHour());
//		}else{
//			horaSaidaMinutos = String.valueOf(usuario.getHoraSaida().getMinuteOfHour());
//		}
//		
//		relatorio.setHoraEntrada(usuario.getHoraEntrada().getHourOfDay() + ":" + horaEntradaMinutos);
//		usuario.getHoraEntrada().getMinuteOfHour();
//		relatorio.setHoraSaida(usuario.getHoraSaida().getHourOfDay() + ":" + horaSaidaMinutos);
//		relatorio.setJornada(usuario.getJornada().toDate());
//		relatorio.setNome(usuario.getNome());
//		relatorio.setNomeXLS(usuario.getLogin() + ".xls");
//		relatorio.setPeriodo(dataInicio.toString("dd/MM/yyy") + " a " + dataFim.toString("dd/MM/yyy"));
//		
//		DateTime dtime = new DateTime(dataInicio);
//		while (dtime.isBefore(dataFim.withTime(23, 59, 59, 00))) {
//			PontoXLS pontoXLS2 = new PontoXLS();
//			List<Ponto> lista = pontoMediatorScala.listaPontoUsuario(usuario, dtime);
//			if(lista.size() == 0){
//				pontoXLS2.setDia(dtime.getDayOfMonth()+"");
//				relatorio.getListaPontos().add(pontoXLS2);
//			}
//			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
//				Ponto ponto = (Ponto) iterator.next();
//				PontoXLS pontoXLS = new PontoXLS();
//				pontoXLS.setDia("");
//				if(ponto.getDataInicio() != null){
//					pontoXLS.setDia(ponto.getDataInicio().toDate().getDate()+"");
//					pontoXLS.setHoraEntrada1(ponto.getDataInicio().toDate());
//				}
//				if(ponto.getDataFim() != null){
//					pontoXLS.setHoraSaida1(ponto.getDataFim().toDate());
//				}
//				if(iterator.hasNext()){
//					Ponto ponto2 = (Ponto) iterator.next();
//					
//					if(ponto2.getDataInicio() != null){
//						pontoXLS.setHoraEntrada2(ponto2.getDataInicio().toDate());
//					}
//					if(ponto2.getDataFim() != null){
//						pontoXLS.setHoraSaida2(ponto2.getDataFim().toDate());
//					}
//				}
//				relatorio.getListaPontos().add(pontoXLS);
//			}
//			dtime = dtime.plusDays(1);
//		}
//		
//		byte[] retornoByte = null;
//		
//		try{
//			retornoByte = GerarXLS(relatorio);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return retornoByte;
//	}
//	
//	public GerarXLS() {
//	}
//	
//	private byte[] GerarXLS(RelatorioXLS relatorio) {
//		POIFSFileSystem fs;
//		HSSFWorkbook wb = null;
//		FileInputStream fileBese = null;
//		
//		try {
//			fileBese = new FileInputStream(uri+"relatorioBase.xls");
//			fs = new POIFSFileSystem(fileBese);
//			wb = new HSSFWorkbook(fs);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//	    HSSFSheet sheet = wb.getSheetAt(0);
//	    
//	    CreationHelper createHelper = wb.getCreationHelper();
//	    CellStyle styleformulaToral = wb.createCellStyle();
//	    styleformulaToral.setBorderTop(CellStyle.BORDER_THIN);
//	    styleformulaToral.setDataFormat(createHelper.createDataFormat().getFormat("HH:MM;[RED]-HH:MM"));
//
//	    CellStyle style = wb.createCellStyle();
//	    style.setBorderBottom(CellStyle.BORDER_THIN);
//	    style.setBorderLeft(CellStyle.BORDER_THIN);
//	    style.setBorderRight(CellStyle.BORDER_THIN);
//	    style.setDataFormat(createHelper.createDataFormat().getFormat("HH:mm;[red]-HH:mm"));
//	    
//	    CellStyle styleDireita = wb.createCellStyle();
//	    styleDireita.setBorderBottom(CellStyle.BORDER_THIN);
//	    styleDireita.setBorderLeft(CellStyle.BORDER_THIN);
//	    styleDireita.setBorderRight(CellStyle.BORDER_MEDIUM);
//	    styleDireita.setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    
//	    CellStyle styleEsquerda = wb.createCellStyle();
//	    styleEsquerda.setBorderBottom(CellStyle.BORDER_THIN);
//	    styleEsquerda.setBorderLeft(CellStyle.BORDER_MEDIUM);
//	    styleEsquerda.setBorderRight(CellStyle.BORDER_THIN);
//	    styleEsquerda.setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    
//	    CellStyle styleFimLinha = wb.createCellStyle();
//	    styleFimLinha.setBorderBottom(CellStyle.BORDER_MEDIUM);
//	    styleFimLinha.setBorderLeft(CellStyle.BORDER_THIN);
//	    styleFimLinha.setBorderRight(CellStyle.BORDER_THIN);
//	    styleFimLinha.setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    
//	    CellStyle styleFimLinhaDireita = wb.createCellStyle();
//	    styleFimLinhaDireita.setBorderBottom(CellStyle.BORDER_MEDIUM);
//	    styleFimLinhaDireita.setBorderLeft(CellStyle.BORDER_THIN);
//	    styleFimLinhaDireita.setBorderRight(CellStyle.BORDER_MEDIUM);
//	    styleFimLinhaDireita.setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    
//	    CellStyle styleFimLinhaEsquerda = wb.createCellStyle();
//	    styleFimLinhaEsquerda.setBorderBottom(CellStyle.BORDER_MEDIUM);
//	    styleFimLinhaEsquerda.setBorderLeft(CellStyle.BORDER_MEDIUM);
//	    styleFimLinhaEsquerda.setBorderRight(CellStyle.BORDER_THIN);
//	    styleFimLinhaEsquerda.setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    
//	    
//	    sheet.getRow(4).getCell(0).setCellValue("PERÍODO DE " + relatorio.getPeriodo());
//	    sheet.getRow(5).getCell(0).setCellValue("FUNCIONÁRIO (A): "+ relatorio.getNome().toUpperCase());
//	    sheet.getRow(6).getCell(2).setCellValue(relatorio.getJornada());
//	    sheet.getRow(6).getCell(2).setCellStyle(style);
//	    sheet.getRow(7).getCell(2).setCellValue(relatorio.getHoraEntrada());
//	    sheet.getRow(7).getCell(2).setCellStyle(style);
//	    sheet.getRow(7).getCell(4).setCellValue(relatorio.getHoraSaida());
//	    sheet.getRow(7).getCell(4).setCellStyle(style);
//	    
//	    
//	    List<PontoXLS> listaPontos = relatorio.getListaPontos();
//	    int cont = 10;
//	    for (PontoXLS ponto : listaPontos) {
//	    	sheet.getRow(cont).getCell(0).setCellValue(ponto.getDia());
//	    	if(ponto.getHoraEntrada1() != null)
//	    	sheet.getRow(cont).getCell(1).setCellValue(ponto.getHoraEntrada1());
//	    	if(ponto.getHoraSaida1() != null)
//	    	sheet.getRow(cont).getCell(3).setCellValue(ponto.getHoraSaida1());
//	    	if(ponto.getHoraEntrada2() != null)
//	    	sheet.getRow(cont).getCell(4).setCellValue(ponto.getHoraEntrada2());
//	    	if(ponto.getHoraSaida2() != null)
//	    	sheet.getRow(cont).getCell(5).setCellValue(ponto.getHoraSaida2());
//	    	sheet.getRow(cont).getCell(7).getCellStyle().setDataFormat(createHelper.createDataFormat().getFormat("h:mm;[red]-h:mm"));
//	    	sheet.getRow(cont).getCell(7).setCellFormula("((D" + (cont+1) +"-B" + (cont+1) +")+(F" + (cont+1) +"-E" + (cont+1) +"))-C7");
//	    	cont++;
//		}
//	    
//	    int linhas = listaPontos.size();
//	    
//	    for (int i = 10; i < (linhas+10); i++) {
//	    	for (int j = 0; j < 9; j++) {
//	    		if(i == (linhas+10-1)){
//	    			if(j == 0){
//	    				sheet.getRow(i).getCell(j).setCellStyle(styleFimLinhaEsquerda);
//	    			}else if(j == 8){
//	    				sheet.getRow(i).getCell(j).setCellStyle(styleFimLinhaDireita);
//	    			}else{
//	    				sheet.getRow(i).getCell(j).setCellStyle(styleFimLinha);
//	    			}
//	    		}else{
//	    			if(j == 0){
//	    				sheet.getRow(i).getCell(j).setCellStyle(styleEsquerda);
//	    			}else if(j == 8){
//	    				sheet.getRow(i).getCell(j).setCellStyle(styleDireita);
//	    			}else{
//	    				sheet.getRow(i).getCell(j).setCellStyle(style);
//	    			}
//	    		}
//	    		
//			}
//		}
//	    try {
//			fileBese.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	    return wb.getBytes();
//	    
//	}
//
//	public TPontoMediatorScala getPontoMediator() {
//		return pontoMediatorScala;
//	}
//
//	public void setPontoMediator(TPontoMediatorScala pontoMediator) {
//		this.pontoMediatorScala = pontoMediator;
//	}
//
//	public String getUri() {
//		return uri;
//	}
//
//	public void setUri(String uri) {
//		this.uri = uri;
//	}

}
