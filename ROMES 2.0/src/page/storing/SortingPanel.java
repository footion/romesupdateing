package page.storing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import entity.StoredReleased;
import entity.dao.DynamicDAO;
import gui.pages.MyDefaultTableModel;
import gui.pages.SearchPanel;
import hibernate.HibernateServer;
import mainprograme.R;
import mesString.MesString;
import pages.utils.ColoredPanel;



public class SortingPanel extends ColoredPanel {

	public JTable table;
	private MyDefaultTableModel2 model;
	public TableRowSorter<MyDefaultTableModel2> sorter;
	private boolean DEBUG = true;
	
	private JPanel subFuncPanel;
	JPanel searchPanel;
	JScrollPane scrollPane;
	private JButton newRowBt;
	private JButton saveRowBt;
	
	public JButton getSaveRowBt() {
		return saveRowBt;
	}

	public void setSaveRowBt(JButton saveRowBt) {
		this.saveRowBt = saveRowBt;
	}

	public JButton getNewRowBt() {
		return newRowBt;
	}

	public void setNewRowBt(JButton newRowBt) {
		this.newRowBt = newRowBt;
	}

	private JPanel crudBtPanel;

	public JPanel getCudBtPanel() {
		return crudBtPanel;
	}

	public MyDefaultTableModel2 getModel() {
		return model;
	}

	public void setModel(MyDefaultTableModel2 model) {
		this.model = model;
	}

	public JPanel getSubFuncPanel() {
		return subFuncPanel;
	}

	private ArrayList<Object> emptyArrayList;

	
	public ActionListener getNewBtAction() {
		return newBtAction;
	}

	public void setNewBtAction(ActionListener newBtAction) {
		this.newBtAction = newBtAction;
	}

	ActionListener newBtAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
				model.addRow();
				
				// System.out.println(table.getColumnCount());
				for (int j = table.getColumnCount() - 1; j >= 0; j--) {
					System.out.println("j: " + j);
					System.out.println(table.getValueAt(1, j));
					Object selectedData = table.getValueAt(1, j);
					// System.out.println("columName : " + table.getColumnName(j));
					// System.out.println("data: " + selectedData);
					// 이름 붙여줘야 한다.
					String txt = table.getColumnName(j);
					String checkingColumName = MesString.makingColumprefix(j, txt);
					
				}
				//emptyArrayList = model.getRealDatas();
				
			
		}
		
	};
	ActionListener saveBtAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int[] selectedRow = table.getSelectedRows();
//					for (int i : selectedRow) {
//						//system.out.println(i);
//					}
			

		}

		

	};
	private Class<StoredReleased> class1;
	
	/**
	 * Create the panel.
	 * 
	 * @param <T>
	 * @wbp.parser.constructor
	 */
	public <T> SortingPanel() {

		// system.out.println("생성자 실행");
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		

		searchPanel = new ColoredPanel();
		searchPanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		searchPanel.setPreferredSize(new Dimension(0, 45));
		this.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(0, 0));

		JPanel pageButtonPanel = new ColoredPanel();

		pageButtonPanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		pageButtonPanel.setPreferredSize(new Dimension(0, 40));
		this.add(pageButtonPanel, BorderLayout.SOUTH);
		pageButtonPanel.setLayout(new BorderLayout(0, 0));

		JPanel pageningBtPanel = new ColoredPanel();
		pageButtonPanel.add(pageningBtPanel, BorderLayout.CENTER);
		pageningBtPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		crudBtPanel = new ColoredPanel();
		// cudBtPanel.setPreferredSize(new Dimension(230, 0));
		// pageButtonPanel.add(cudBtPanel, BorderLayout.EAST);
		crudBtPanel.setLayout(new FlowLayout());

		newRowBt = new JButton(new ImageIcon("add.png"));
		newRowBt.setToolTipText("데이터 추가");
		newRowBt.addActionListener(newBtAction);

		saveRowBt = new JButton(new ImageIcon("savebt.png"));
		saveRowBt.setToolTipText("데이터 저장");
		saveRowBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selectedRow = table.getSelectedRows();
//				for (int i : selectedRow) {
//					//system.out.println(i);
//				}
				String selectedData = null;
				

			}

			

			class Checking {
				boolean isInstanceInDB;
				int count;
			}

			

		});

		JButton delButton = new JButton(new ImageIcon("minus.png"));
		delButton.setToolTipText("데이터 삭제");
		delButton.addActionListener(new ActionListener() {
			// 삭제 메소드 작성
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selectedRow = table.getSelectedRows();
//				for (int i : selectedRow) {
//					//system.out.println(i);
//				}
				String selectedData = null;
				
			}

			
		});

		crudBtPanel.add(saveRowBt);
		crudBtPanel.add(newRowBt);
		crudBtPanel.add(delButton);

		JPanel mainTablePanel = new ColoredPanel();
		mainTablePanel.setPreferredSize(new Dimension(0, 150));
		mainTablePanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));

		this.add(mainTablePanel, BorderLayout.CENTER);
		mainTablePanel.setLayout(new BorderLayout(0, 0));

		// setting Table
		scrollPane = new JScrollPane();
		mainTablePanel.add(scrollPane, BorderLayout.CENTER);
		settingTable(new MyDefaultTableModel2());

		Object[] emtpyList = (Object[]) model.getColumList().toArray();
		String[] columList = new String[emtpyList.length];
		for (int i = 0; i < emtpyList.length; i++) {
			columList[i] = (String) emtpyList[i];
		}
		//SearchPanel panel = new SearchPanel(columList, this);
//		panel.add(crudBtPanel, BorderLayout.WEST);
//		searchPanel.add(panel);

		JButton leftPageBt = new JButton("<");
		pageningBtPanel.add(leftPageBt);

//		JLabel currentPage = new JLabel((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
//		pageningBtPanel.add(currentPage);

		JButton rightPageBt = new JButton(">");
		pageningBtPanel.add(rightPageBt);
		rightPageBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				model.nextPage();
//				currentPage.setText((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
			}
		});
		leftPageBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				model.prePage();
//				currentPage.setText((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
			}
		});
	}

	public SortingPanel(Class<R> className, String[] workplanStrings) {
		// TODO Auto-generated constructor stub
		// system.out.println("생성자 실행");
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		this.class1 = StoredReleased.class;

		searchPanel = new ColoredPanel();
		searchPanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		searchPanel.setPreferredSize(new Dimension(0, 45));
		this.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(0, 0));

		JPanel pageButtonPanel = new ColoredPanel();

		pageButtonPanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		pageButtonPanel.setPreferredSize(new Dimension(0, 40));
		this.add(pageButtonPanel, BorderLayout.SOUTH);
		pageButtonPanel.setLayout(new BorderLayout(0, 0));

		JPanel pageningBtPanel = new ColoredPanel();
		pageButtonPanel.add(pageningBtPanel, BorderLayout.CENTER);
		pageningBtPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		crudBtPanel = new ColoredPanel();
		// cudBtPanel.setPreferredSize(new Dimension(230, 0));
		// pageButtonPanel.add(cudBtPanel, BorderLayout.EAST);
		crudBtPanel.setLayout(new FlowLayout());

		newRowBt = new JButton(new ImageIcon("add.png"));
		newRowBt.setToolTipText("데이터 추가");
		newRowBt.addActionListener(newBtAction);

		saveRowBt = new JButton(new ImageIcon("savebt.png"));
		saveRowBt.setToolTipText("데이터 저장");
		saveRowBt.addActionListener(saveBtAction);

		JButton delButton = new JButton(new ImageIcon("minus.png"));
		delButton.setToolTipText("데이터 삭제");
		delButton.addActionListener(new ActionListener() {
			// 삭제 메소드 작성
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selectedRow = table.getSelectedRows();
//						for (int i : selectedRow) {
//							//system.out.println(i);
//						}
				String selectedData = null;
				try {
					try {
						delete(class1.newInstance(), selectedRow);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch 1block
						e1.printStackTrace();
					}
				} catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void delete(StoredReleased storedReleased, int[] selectedRow) {
				// TODO Auto-generated method stub
				int objIdNumber = 0;

				for (int i = 0; i < selectedRow.length; i++) {
					//emptyArrayList = model.getRealDatas();
					Object obj = emptyArrayList.get(table.convertRowIndexToModel(selectedRow[i]));
					emptyArrayList.remove(table.convertRowIndexToModel(selectedRow[i]));
					// system.out.println("선택한 칼럼 : " + selectedRow[i]);
					// system.out.println("잘 됫으면 좋겠당 : " +
					// table.convertRowIndexToModel(selectedRow[i]));

					// system.out.println("객체출력 : " + obj.toString());
					new DynamicDAO().deleteObject(obj);
					for (int j = 0; j < model.getColumnCount(); j++) {
						// //system.out.println(model.getValueAt(selectedRow[i], j));
						// //system.out.println(sorter.);
					}

				}
//				model.setRealDatas(emptyArrayList);
//				model.refreshmodel();
			}
		});

		crudBtPanel.add(saveRowBt);
		crudBtPanel.add(newRowBt);
		crudBtPanel.add(delButton);

		JPanel mainTablePanel = new ColoredPanel();
		mainTablePanel.setPreferredSize(new Dimension(0, 150));
		mainTablePanel.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));

		this.add(mainTablePanel, BorderLayout.CENTER);
		mainTablePanel.setLayout(new BorderLayout(0, 0));

		// setting Table
		scrollPane = new JScrollPane();
		mainTablePanel.add(scrollPane, BorderLayout.CENTER);
		settingTable(new MyDefaultTableModel2());

		Object[] emtpyList = (Object[]) model.getColumList().toArray();
		String[] columList = new String[emtpyList.length];
		for (int i = 0; i < emtpyList.length; i++) {
			columList[i] = (String) emtpyList[i];
		}
//		SearchPanel panel = new SearchPanel(columList, this);
//		panel.add(crudBtPanel, BorderLayout.WEST);
//		searchPanel.add(panel);

		JButton leftPageBt = new JButton("<");
		pageningBtPanel.add(leftPageBt);

//		JLabel currentPage = new JLabel((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
//		pageningBtPanel.add(currentPage);

		JButton rightPageBt = new JButton(">");
		pageningBtPanel.add(rightPageBt);
		rightPageBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				model.nextPage();
//				currentPage.setText((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
			}
		});
		leftPageBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				model.prePage();
//				currentPage.setText((model.getCurrentPage() + 1) + "/" + model.getMaxPageCount());
			}
		});
	}

	

	public <T> void settingTable(MyDefaultTableModel2 myDefaultTableModel) {
		// TODO Auto-generated method stub
		model = myDefaultTableModel;
		sorter = new TableRowSorter<MyDefaultTableModel2>(model);
		if (table == null)
			table = new JTable();
		table.setModel(model);
		table.setOpaque(true);
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(0xEA, 0xEF, 0xF8));
		table.setRowSorter(sorter);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//model.refreshmodel();
		// HashMap<String, JPanel> filterPanelMap = new HashMap<String, JPanel>();
		table.getTableHeader().setBackground(new Color(0xBF, 0xD0, 0xE6));
		scrollPane.setViewportView(table);
	}

	private void callSetter(Object obj, String fieldName, Object value)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		PropertyDescriptor pd;

		if (!model.isTranslate) {
			pd = new PropertyDescriptor(fieldName, obj.getClass());
			System.out.println(fieldName);
		} else {
			System.out.println(fieldName.substring(9));
			System.out.println(fieldName.substring(0, 9));
			pd = new PropertyDescriptor(fieldName.substring(0, 9) ,
					obj.getClass());
		}

		try {
			pd.getWriteMethod().invoke(obj, value);
		} catch (java.lang.IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("=========");
			System.out.println("obj.getClass() : " + obj.getClass());
			System.out.println(obj.toString());
			System.out.println("value : " + value);
			System.out.println("value.getClass() : " + value.getClass());
			System.out.println("fieldName : " + fieldName);
			System.out.println("=========");
			String v = (String) value;
			if (v.contains(".")) {
				pd.getWriteMethod().invoke(obj, Double.parseDouble((String) value));
			} else
				pd.getWriteMethod().invoke(obj, Integer.parseInt((String) value));
		}
	}

	/**
	 * Update the row filter regular expression from the expression in the text box.
	 * 
	 * @param jTextField
	 * @param i
	 */
	private void newFilter(JTextField jTextField, int i) {
		RowFilter<MyDefaultTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			rf = RowFilter.regexFilter(jTextField.getText(), i);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		//sorter.setRowFilter(rf);
	}

	public void remomveDefaultNewBowBtActionListener() {
		// TODO Auto-generated method stub
		newRowBt.removeActionListener(newBtAction);
	}

}
