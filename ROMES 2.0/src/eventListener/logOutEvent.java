package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Login.LoginPanel;
import Login.log_history;
import Login.login_management;
import Menu.MenuWriter;
import Menu.SubMenu;
import mainFrame.mainFrame;

public class logOutEvent implements ActionListener{
	public logOutEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		LoginPanel.Confirmlogin=false;
		mainFrame.card.show(mainFrame.Container, "Login");
		mainFrame.title.setText("ROMES");
		//mainFrame.doubleButtonPanel.leftBtn.setVisible(false);
		login_management.admin_authority.setVisible(false);
		MenuWriter.checkedMainmenu(MenuWriter.menuwriterHistory);
		SubMenu.clickedLabel(SubMenu.submenuHistory);
		log_history.save_log("로그아웃");
		LoginPanel.name="로그인 정보 없음";
		componentSetting();
	}

	void componentSetting() {
		mainFrame.searchBar.setVisible(false);
		mainFrame.LoginInfo.setVisible(false);
		mainFrame.title.setVisible(false);
	}
}
