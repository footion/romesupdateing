package excelFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Company;
import entity.Received_order_history;
import entity.ReceivedOrderProduct;
import factory.instanceofFactory;
import hibernate.hibernate;
import message.errorMessage;
import message.successMessage;

public class writeEstimate {
	@SuppressWarnings("resource")
	XSSFRow row;
	XSSFCell cell;
	XSSFSheet sheet;
	XSSFFormulaEvaluator ev;
	int FIRST_MODEL_ROW_NO=14;
	public String Filename;
	public writeEstimate(int order_data_id) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			
			InputStream inp = new FileInputStream("excelForm/견적서 양식.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(inp);
			sheet = workbook.getSheetAt(0);
			ev = workbook.getCreationHelper().createFormulaEvaluator();
			
			Received_order_history order_data= session.get(Received_order_history.class, order_data_id);

			
			selectCell(5, 1);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			cell.setCellValue(format.format(date));
			selectCell(6, 1);
			cell.setCellValue(order_data.getOrdered_company().getCEO_Name());
			selectCell(8, 1);
			cell.setCellValue(order_data.getOrdered_company().getAddress());
			
			int i=0;
			for(ReceivedOrderProduct model:order_data.getProducts()) {
					writeModelDate(model, FIRST_MODEL_ROW_NO+i);
					i++;
			}
			ev.evaluateAll();
			
			// 입력된 내용 파일로 쓰기
	        File file = new File("C:/Users/user/Desktop/ROMES__Excel_Output");
	        file.mkdirs();
	        File Internalfile = new File("excelOutput");
	        Internalfile.mkdirs();
	        FileOutputStream fos = null;
			Filename= order_data.getTitle()+"_견적서"+".xlsx";
	        try {
	        	//Desktop
	            fos = new FileOutputStream(file+File.separator+Filename);
	            workbook.write(fos);
	            //Internalfile
	            fos = new FileOutputStream(Internalfile+File.separator+Filename);
	            workbook.write(fos);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if(workbook!=null) workbook.close();
	                if(fos!=null) fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			JOptionPane.showMessageDialog(null, "견적서 생성 완료.","Success",JOptionPane.INFORMATION_MESSAGE);
			hibernate.transaction.commit();
		} catch (Exception e) {
            new errorMessage("save a estimate file");
			e.printStackTrace();
		}
	}
	void selectCell(int ROW, int CELL) {
		row = sheet.getRow(ROW);
		cell = row.getCell(CELL);
	}
	void setCellValue_checkParseInt(String string) {
		if(instanceofFactory.isStringInteger(string)) {
			cell.setCellValue(Integer.parseInt(string));	
		}
	}
	void writeModelDate(ReceivedOrderProduct model,int rowNo) {
		selectCell(rowNo, 1);
		cell.setCellValue(instanceofFactory.removeNull(model.getProduct().getName()));
		selectCell(rowNo, 2);
		cell.setCellValue(instanceofFactory.removeNull(model.getSize()));
		selectCell(rowNo, 3);
		cell.setCellValue(instanceofFactory.removeNull(model.getUnit()));
		selectCell(rowNo, 4);
		setCellValue_checkParseInt(model.getQuantity());
		selectCell(rowNo, 5);
		setCellValue_checkParseInt(model.getPrice());
		
		if(instanceofFactory.isStringInteger(model.getPrice())
				&& instanceofFactory.isStringInteger(model.getQuantity())){
					selectCell(rowNo, 6);
					cell.setCellValue((int)(Integer.parseInt(model.getPrice())*Integer.parseInt(model.getQuantity())));
				}
		
		selectCell(rowNo, 7);
		cell.setCellValue(instanceofFactory.removeNull(model.getRemarks()));
	}
}
