package factory;

import java.awt.Dimension;

import javax.swing.JTable;

import eventListener.InfoBtnEvent_PurchasePrice;
import eventListener.deleteRowEvent;
import eventListener.infoBtnEvent_Company;
import eventListener.InfoBtnEvent_ReceivedOrder;
import eventListener.miniTableEvent_R_ReceivedOrder;
import image.url;
import layoutSetting.basicTabbedPane;
import layoutSetting.miniTable;
import pages.companyManagement;
import pages.ReceivedOrderManagement;
import setTableCell.numberCellEditor;
import setTableCell.columnWidth;
import setTableCell.iconBtnCell;
import setTableCell.setPriceColumn;
import setTableCell.setQuantityColumn;

public class miniTableFactory{
	miniTable minitable;
	public miniTableFactory() {
		// TODO Auto-generated constructor stub
	}
	public miniTable ReceivedOrderProductTable() {
		String[] col = { "id","모델","Info","product_key","lotNo","규격","단위", "수량", "단가", "비고", "×" };
		minitable = new miniTable(col,1,"RO");
		minitable.setPreferredSize(new Dimension(0, 170));
		minitable.setLineVisible(false);
		//SetUp Table Cell
		iconBtnCell iconBtnCell_info= new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable,"ROPRODUCT"));
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		minitable.table.getColumn("수량").setCellRenderer(new setQuantityColumn());
		minitable.table.getColumn("수량").setCellEditor(new numberCellEditor());
		minitable.table.getColumn("단가").setCellRenderer(new setPriceColumn());
		minitable.table.getColumn("단가").setCellEditor(new numberCellEditor());
		//add mouseListener
		minitable.table.addMouseListener(new miniTableEvent_R_ReceivedOrder(minitable));
		minitable.table.addKeyListener(new miniTableEvent_R_ReceivedOrder(minitable));
		
		new columnWidth().setColumnWidth(minitable.table, "×", 5);
		new columnWidth().setColumnWidth(minitable.table, "Info", 5);
		new columnWidth().setColumnWidth(minitable.table, "모델", 180);
		new columnWidth().setColumnWidth(minitable.table, "비고", 170);
		new columnWidth().setColumnWidth(minitable.table, "수량", 40);
		new columnWidth().setColumnWidth(minitable.table, "단가", 40);
		new columnWidth().setColumnWidth(minitable.table, "단위", 40);
		new columnWidth().setColumnWidth(minitable.table, "규격", 180);
		
		return minitable;
	}
	public miniTable ReceivedOrderManagementTable(ReceivedOrderManagement orderManagement) {
		String [] col = {"id","title","Info","담당자","주문 업체","주문 날짜","완료 기한","계약 유형","×"};
		minitable = new miniTable(col,2,8);
		iconBtnCell iconBtnCell_info= new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell_info.setEvent(new InfoBtnEvent_ReceivedOrder(orderManagement));
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable,"ORDER"));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		
		new columnWidth().setColumnWidth(minitable.table, "×", 5);
		new columnWidth().setColumnWidth(minitable.table, "Info", 5);
		return minitable;
	}
	public miniTable companyManagementTable(companyManagement companyManagement) {
		String [] col = {"id","회사 구분","회사명","Info","대표명","사업 번호","업 태","종 목","Tel","Fax","×"};
		minitable = new miniTable(col, 3,10);
		iconBtnCell iconBtnCell_info=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell_info.setEvent(new infoBtnEvent_Company(companyManagement,minitable,0));
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable,"COMPANY"));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		
		
		new columnWidth().setColumnWidth(minitable.table, "×", 5);
		new columnWidth().setColumnWidth(minitable.table, "Info", 5);
		return minitable;
	}
	public miniTable PurchasePriceManagement(basicTabbedPane tabbedPane) {
		String [] col = {"id","매입품","Info","거래처","Info ","단가","×"};
		minitable = new miniTable(col,2,4,6);
		iconBtnCell iconBtnCell_info=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell_info.setEvent(new InfoBtnEvent_PurchasePrice(tabbedPane, minitable, 0, false));
		iconBtnCell iconBtnCell_info2=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell_info2.setEvent(new InfoBtnEvent_PurchasePrice(tabbedPane, minitable, 0, true));
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable,"PURCHASEPRICE"));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		minitable.table.getColumn("Info ").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info ").setCellEditor(iconBtnCell_info2);
		minitable.table.getColumn("단가").setCellRenderer(new setPriceColumn());
		minitable.table.getColumn("단가").setCellEditor(new numberCellEditor());
		return minitable;
	}

	public miniTable PurchasePriceManagement_byCompany(basicTabbedPane tabbedPane) {
		String [] col = {"id","매입품","Info","product_key","단가","×"};
		minitable = new miniTable(col,1,null);
		iconBtnCell iconBtnCell_info=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		minitable.table.getColumn("단가").setCellRenderer(new setPriceColumn());
		minitable.table.getColumn("단가").setCellEditor(new numberCellEditor());
		return minitable;
	}
	public miniTable PurchasePriceManagement_byProduct(basicTabbedPane tabbedPane) {
		String [] col = {"id","거래처","Info","company_key","단가","×"};
		minitable = new miniTable(col,1,null);
		iconBtnCell iconBtnCell_info=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell_info.setEvent(new infoBtnEvent_Company(tabbedPane, minitable, 3));
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		minitable.table.getColumn("단가").setCellRenderer(new setPriceColumn());
		minitable.table.getColumn("단가").setCellEditor(new numberCellEditor());
		return minitable;
	}
	public miniTable ProductionPlanManagement(basicTabbedPane tabbedPane) {
		String [] col= {"id","수주 정보","Info","제품","수량","주문 날짜","완료 기한","예상 소요 시간","비고","×"};
		minitable = new miniTable(col, 2, 8, 9);
		
		iconBtnCell iconBtnCell_info=new iconBtnCell(url.LINK_ICON, 12, 12);
		iconBtnCell iconBtnCell_del= new iconBtnCell(url.DELETE_ICON, 12, 12);
		iconBtnCell_del.setEvent(new deleteRowEvent(minitable));
		
		minitable.table.getColumn("×").setCellRenderer(new iconBtnCell(url.DELETE_ICON, 12, 12));
		minitable.table.getColumn("×").setCellEditor(iconBtnCell_del);
		minitable.table.getColumn("Info").setCellRenderer(new iconBtnCell(url.LINK_ICON, 12, 12));
		minitable.table.getColumn("Info").setCellEditor(iconBtnCell_info);
		
		return minitable;
	}
}