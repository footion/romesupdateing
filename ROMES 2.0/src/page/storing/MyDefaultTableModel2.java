package page.storing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

public class MyDefaultTableModel2 extends AbstractTableModel {

	private ArrayList<String> columNameArrayList = new ArrayList<String>();
	private LinkedList<ArrayList<Object>> dataList = new LinkedList<ArrayList<Object>>();
	public boolean isTranslate;
	private void makingTestData() {
		// TODO Auto-generated method stub
	
		
		/*
		 * columNameArrayList.add("lot");
		columNameArrayList.add("입/출고");
		columNameArrayList.add("발주 lot");
		columNameArrayList.add("제품");
		columNameArrayList.add("판매사");
		columNameArrayList.add("발주 담당자");
		columNameArrayList.add("입고 갯수");
		columNameArrayList.add("입출고 담당자");
		 */
		Object[] data1={ "200703001", "입고", "200629011", "노즐캡", "노즐회사", "장덕현",new Integer(300),"정지" };
		ArrayList<Object> data1Array = new ArrayList<Object>();
		data1Array.addAll(Arrays.asList(data1));
		
		dataList.add(data1Array);
		
		
	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();
	
		for (int i = 0; i < numRows; i++) {
			//system.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				//system.out.print("  " + dataList.get(i).get(j));
			}
			//system.out.println();
		}
		//system.out.println("--------------------------");
	}

	public MyDefaultTableModel2() {
		super();

		columNameArrayList.add("lot");
		columNameArrayList.add("입/출고");
		columNameArrayList.add("발주 lot");
		columNameArrayList.add("제품");
		columNameArrayList.add("판매사");
		columNameArrayList.add("발주 담당자");
		columNameArrayList.add("입고 갯수");
		columNameArrayList.add("입출고 담당자");
		
		makingTestData();

	}

	public ArrayList<String> getColumList() {
		return columNameArrayList;
	}

	//	private Object[][] data = { { "Kathy", "Smith", new Integer(5), "정지원 진행중" },
//			{ "John", "Doe", new Integer(3), "김지원 완료" }, { "Sue", "Black", new Integer(2), "완료" },
//			{ "Jane", "White", new Integer(20), "완료" }, { "Joe", "Brown", new Integer(10), "대" } };

	public int getColumnCount() {
		return columNameArrayList.size();
	}
	public void addRow() {
		Object[] data = { "", "", "", new Integer(0), new Boolean(false) };
		ArrayList<Object> dataArray = new ArrayList<Object>();
		dataArray.addAll(Arrays.asList(data));
		dataList.addFirst(dataArray);
		fireTableDataChanged();
		
	}
	public int getRowCount() {
		return dataList.size();
	}

	public String getColumnName(int col) {
		return columNameArrayList.get(col);
	}

	public Object getValueAt(int row, int col) {
		return dataList.get(row).get(col);
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		

		dataList.get(row).set(col,value);
		fireTableCellUpdated(row, col);

		
	}
}
