package components.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import entity.Company;
import entity.dao.CompanyDAO;

public class PlusBtListener implements ActionListener{

	private HasListComboBox hasListComboBox;
	private JComboBox combobox;
	private Integer pkey;
	private String selectString;

	public PlusBtListener(HasListComboBox hasListComboBox) {
		this.hasListComboBox=hasListComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.combobox = hasListComboBox.getComboBox();
		this.selectString = (String) combobox.getSelectedItem();
		this.pkey=hasListComboBox.getNameKeyMap().get(selectString);
		System.out.println("selectString - "+ selectString);
		System.out.println("pkey - "+ pkey);
		
		addComponetsToPanel();
		
	}

	private void addComponetsToPanel() {
		CompanyDAO dao = new CompanyDAO();
		//Company company = dao.findByPkey(pkey);
		
		hasListComboBox.getRightpanel().add(new JTextField(selectString));
		hasListComboBox.repaint();
		hasListComboBox.revalidate();
		
	}
}