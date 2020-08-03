package selectFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.hibernate.Session;

import Dao.ProductDAO;
import entity.Product;
import entity.Company;
import eventListener.cancelEvent;
import factory.colorFactory;
import hibernate.hibernate;
import layoutSetting.UI_Button;
import layoutSetting.basicFrame;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class selectFrame extends basicFrame{
	selectTypeBtn selectTypeBtn;
	public enumTypePanel enumTypePanel;
	public scrollList scrollList;
	public UI_Button okBtn;
	public UI_Button cancelBtn;
	selectFrame selectFrame;
	ActionListener actionListener;
	public JList NameList;
	public int[] KeyList;
	private int KEY;
	public selectFrame() {
		super("Select Frame",null);
		selectFrame=this;
	}
	void drawFrame() {
		//NorthPanel
				basicPanel northPanel = new basicPanel(null);
				northPanel.setLayout(new BorderLayout());
				northPanel.add(selectTypeBtn.enumTypeBtn,BorderLayout.NORTH);
				northPanel.add(enumTypePanel,BorderLayout.CENTER);
				//CenterPanel
				basicPanel centerPanel = new basicPanel(null);
				centerPanel.setLayout(new BorderLayout(12,0));
				centerPanel.add(selectTypeBtn.listTypeBtn,BorderLayout.NORTH);
				centerPanel.add(new JLabel("   "),BorderLayout.WEST);
				centerPanel.add(new JLabel(""),BorderLayout.EAST);
				centerPanel.add(scrollList,BorderLayout.CENTER);
				
				//PageEndPanel
				basicPanel pageEndPanel = new basicPanel(null);
				pageEndPanel.setLayout(new BorderLayout(0,10));
				basicPanel plusPanel = new basicPanel(null);
				okBtn =new UI_Button("OK");
				cancelBtn = new UI_Button("Cancel");
				plusPanel.add(okBtn);
				plusPanel.add(cancelBtn);
				plusPanel.add(new JLabel(" "));
				basicLabel message =new basicLabel("Each line in the above list represents"
						+ " the name    ");
				message.setHorizontalAlignment(JLabel.RIGHT);
				pageEndPanel.add(message,BorderLayout.NORTH);
				pageEndPanel.add(plusPanel,BorderLayout.EAST);
				
				settingStart();
				setEvent();
				
				this.setLayout(new BorderLayout());
				this.add(northPanel,BorderLayout.NORTH);
				this.add(centerPanel,BorderLayout.CENTER);
				this.add(pageEndPanel,BorderLayout.PAGE_END);
				this.setVisible(true);
				this.setSize(580,470);
				this.setLocationRelativeTo(null);
				this.setResizable(false);
	}
	void checkRadioButton(JRadioButton radioButton, enumTypePanel enumTypePanel,
			scrollList scrollList,boolean isEnumtypePanel,boolean isScrollList) {
		if(radioButton.isSelected()) {
			//setEnabled enumTypePanel
			enumTypePanel.button.setEnabled(isEnumtypePanel);
			enumTypePanel.textField.setEditable(isEnumtypePanel);
			if(isEnumtypePanel==true) {
				enumTypePanel.textField.setForeground(Color.black);
				enumTypePanel.textField.setBackground(colorFactory.TEXTFIELD_COLOR);
				okBtn.setEnabled(false);
			}else {
				enumTypePanel.textField.setForeground(Color.LIGHT_GRAY);
				enumTypePanel.textField.setBackground(colorFactory.ENABLE_COLOR);
				okBtn.setEnabled(true);
			}
			//setEnabled to scrollList
			scrollList.getHorizontalScrollBar().setEnabled(isScrollList);
			scrollList.getVerticalScrollBar().setEnabled(isScrollList);
			scrollList.getViewport().getView().setEnabled(isScrollList);
		}
	}
	
	void settingStart() {
		selectTypeBtn.listTypeBtn.setSelected(true);
		checkRadioButton(selectTypeBtn.listTypeBtn, enumTypePanel, scrollList,
				false, true);
	}
	
	void setEvent() {
		actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==selectTypeBtn.enumTypeBtn) {
					checkRadioButton(selectTypeBtn.enumTypeBtn, enumTypePanel, scrollList,
							true, false);
				} else if(e.getSource()==selectTypeBtn.listTypeBtn) {
					checkRadioButton(selectTypeBtn.listTypeBtn, enumTypePanel, scrollList,
							false, true);
				}
			}
		};
		selectTypeBtn.enumTypeBtn.addActionListener(actionListener);
		selectTypeBtn.listTypeBtn.addActionListener(actionListener);
		cancelBtn.addActionListener(new cancelEvent(selectFrame));
	}
	
	public void saveDatakey(int key) {
		KEY = key;
	}
	public int returnKey() {
		return KEY;
	}
	
	public void setCompanyType() {
		Session session=hibernate.factory.openSession();
		ArrayList<Company> companies = (ArrayList<Company>)session.createCriteria(Company.class).list();
		String nameList [] = new String[companies.size()];
		int idList [] = new int[companies.size()];
		for(int i=0;i<companies.size();i++) {
			Company company = companies.get(i);
			nameList[i]=company.getCompanyName();
			idList[i]=company.getId();
		}
		selectTypeBtn = new selectTypeBtn("Company"+" enum type :","Company"+" list :");
		enumTypePanel = new enumTypePanel("Input", 35);
		NameList=new JList(nameList);
		KeyList=idList;
		scrollList = new scrollList(NameList);
		drawFrame();
	}
	
	public void setProductType() {
		ArrayList<Product> products =new ProductDAO().getClearUpProducts();
		String nameList [] = new String[products.size()];
		int idList [] = new int[products.size()];
		for(int i=0;i<products.size();i++) {
			Product product = products.get(i);
			nameList[i]=product.getName();
			idList[i]=product.getId();
		}
		selectTypeBtn = new selectTypeBtn("Product"+" enum type :","Product"+" list :");
		enumTypePanel = new enumTypePanel("Input", 35);
		NameList=new JList(nameList);
		KeyList=idList;
		scrollList = new scrollList(NameList);
		drawFrame();
	}
}