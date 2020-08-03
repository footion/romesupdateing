package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import excelFunctions.writeEstimate;
import message.errorMessage;
import message.successMessage;
import registrationForm.testEmail;


public class sendEstimateEvent implements ActionListener{
	JTable Table;
	int ID_COL_NO=0;
	public sendEstimateEvent(JTable table) {
		Table=table;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			writeEstimate writeEstimate=new writeEstimate((int) Table.getValueAt(Table.getSelectedRow(), ID_COL_NO));
			new testEmail(writeEstimate.Filename);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
