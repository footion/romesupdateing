package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pages.companyManagement;
import registrationForm.R_company;

public class newCompanyEvent implements ActionListener{
	companyManagement CompanyManagement;
	public newCompanyEvent(companyManagement companyManagement) {
		CompanyManagement=companyManagement;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		R_company companyRegistration = new R_company(CompanyManagement);
		CompanyManagement.addCancelableTab("new Company", companyRegistration);
		for(ActionListener actionListener:companyRegistration.doubleButtonPanel.leftBtn.getActionListeners()) {
			companyRegistration.doubleButtonPanel.leftBtn.removeActionListener(actionListener);
		}
		companyRegistration.doubleButtonPanel.leftBtn.addActionListener(new R_CompanyEvent(companyRegistration, CompanyManagement));
	}

}
