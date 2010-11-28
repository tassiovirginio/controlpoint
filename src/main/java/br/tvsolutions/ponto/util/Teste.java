package br.tvsolutions.ponto.util;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;




public class Teste {

	public void Teste(){
		Workbook wb = new HSSFWorkbook();
		
		Sheet sheet = wb.createSheet("new sheet");
		
	    Row row = sheet.createRow((short)0);
	    Cell cell1 = row.createCell(0);
	    cell1.setCellValue("FOLHA DE PONTO");
	    
//	    cell1.setCellValue(new XSSFRichTextString("Align It"));
        CellStyle cellStyle = wb.createCellStyle();
        cell1.setCellStyle(cellStyle);
        
        Row row2 = sheet.createRow((short)1);
	    Cell cell1_2 = row2.createCell(0);//Dia
	    Cell cell2_2 = row2.createCell(1);//Hora de Entrada
	    Cell cell3_2 = row2.createCell(2);//Assinatura
	    Cell cell4_2 = row2.createCell(3);//Saida/Descan�o
	    Cell cell5_2 = row2.createCell(4);//Entrada/Descan�o
	    Cell cell6_2 = row2.createCell(5);//Hora da saida
	    Cell cell7_2 = row2.createCell(6);//Assinatura
	    Cell cell8_2 = row2.createCell(7);//Hora Extra
	    Cell cell9_2 = row2.createCell(8);//Autoriza��o
	    
	    cell1_2.setCellValue("DIA");
	    cell2_2.setCellValue("HORA DE ENTRADA");
	    cell3_2.setCellValue("ASSINATURA");
	    cell4_2.setCellValue("SAIDA");
	    cell5_2.setCellValue("ENTRADA");
	    cell6_2.setCellValue("HORA DA SAIDA");
	    cell7_2.setCellValue("ASSINATURA");
	    cell8_2.setCellValue("HORA EXTRA");
	    cell9_2.setCellValue("AUTORIZA��O");
	    
	    
	    
	    
//	    row.createCell(1).setCellValue(1.2);
//	    row.createCell(2).setCellValue("This is a string");
//	    row.createCell(3).setCellValue(true);
	    
//	    sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
//	    wb.setRepeatingRowsAndColumns(0,0,2,-1,-1);
//	    wb.setRepeatingRowsAndColumns(0,0,8,1,10);
	    
//	    Sheet sheet2 = wb.createSheet("second sheet");
//	    wb.setRepeatingRowsAndColumns(0,4,5,1,2);
	    
	    // Style the cell with borders all around.
	    CellStyle style = wb.createCellStyle();
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLUE.getIndex());
	    style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    cell4_2.setCellStyle(style);
	    
		
	    
	    try {
	    	FileOutputStream fileOut = new FileOutputStream("workbook.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}

}
