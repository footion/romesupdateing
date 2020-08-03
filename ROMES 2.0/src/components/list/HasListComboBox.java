package components.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.ss.formula.functions.T;

import entity.Company;
import entity.MESEntity;
import gui.ColoredPanel;

public class HasListComboBox extends ColoredPanel {
	int width;
	private ArrayList<Company> itemList;
	private Class className;
	private PlusBtListener btListener;
	private JComboBox comboBox;
	private HashMap<String, Integer> nameKeyMap;
	private ColoredPanel rightpanel; 
	
	public HasListComboBox(int width,ArrayList<Company> itemList) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		this.width= width;
		this.itemList=itemList;
		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(0, 60));
		JPanel ComboBoxAndPlusButtonPanel = new ColoredPanel();
		ComboBoxAndPlusButtonPanel.setPreferredSize(new Dimension(width, 0));
		add(ComboBoxAndPlusButtonPanel, BorderLayout.WEST);
		ComboBoxAndPlusButtonPanel.setLayout(new BorderLayout(0, 0));
		
		this.comboBox = new JComboBox();
		this.nameKeyMap = new HashMap<String, Integer>();
		itemList.forEach(itemObject -> {
			nameKeyMap.put(itemObject.getCompanyName(),itemObject.getId());
			comboBox.addItem(itemObject.getCompanyName());
		});
		comboBox.setPreferredSize(new Dimension(150, 0));
		ComboBoxAndPlusButtonPanel.add(comboBox, BorderLayout.WEST);
		
		JButton btnNewButton = new MesComboButton("+");
		
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		btnNewButton.setPreferredSize(new Dimension(30,30));
		btnNewButton.setMaximumSize(new Dimension(30, 30));
		ComboBoxAndPlusButtonPanel.add(btnNewButton, BorderLayout.CENTER);
		this.btListener=new PlusBtListener(this);
		btnNewButton.addActionListener(btListener);
		
		this.rightpanel=new ColoredPanel();
		rightpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		add(rightpanel,BorderLayout.EAST);
	
	}
	public static HasListComboBox makingTestComponet() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		int width= 210;
		ArrayList<Company> itemList = new ArrayList<Company>();
		Company company= new Company();
		company.setId(1);company.setCompanyName("로보게이트");
		itemList.add(company);
		company= new Company();
		company.setId(2);company.setCompanyName("행복주식회사");
		itemList.add(company);
		return new HasListComboBox(width, itemList);
	}
	private void addClassName(Class<T> className) {
		this.className=className;
		
	}
	private HasListComboBox(){
		
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		try {
			frame.getContentPane().add(HasListComboBox.makingTestComponet(),BorderLayout.NORTH);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame.getContentPane().add(new JButton("dfdf"),BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public Class getClassName() {
		return className;
	}
	public void setClassName(Class className) {
		this.className = className;
	}
	public PlusBtListener getBtListener() {
		return btListener;
	}
	public void setBtListener(PlusBtListener btListener) {
		this.btListener = btListener;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	public ColoredPanel getRightpanel() {
		return rightpanel;
	}
	public void setRightpanel(ColoredPanel rightpanel) {
		this.rightpanel = rightpanel;
	}
	public HashMap<String, Integer> getNameKeyMap() {
		return nameKeyMap;
	}
	public void setNameKeyMap(HashMap<String, Integer> nameKeyMap) {
		this.nameKeyMap = nameKeyMap;
	}
	public ArrayList<Company> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<Company> itemList) {
		this.itemList = itemList;
	}
	
}
