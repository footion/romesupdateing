package Login;

import javax.swing.JButton;

import Menu.MenuWriter;
import Menu.SubMenu;

public class Log {
	public static void excutefunction(JButton button) {
		String EXCUTEFUNCTION;
		if(MenuWriter.menuwriterHistory.size()>0) {
			EXCUTEFUNCTION = MenuWriter.menuwriterHistory.get(0).menu.getText()+
					"→"+SubMenu.submenuHistory.get(0).getText().substring(3)+"→"+button.getText();
		}else {
			EXCUTEFUNCTION = button.getText();
			if(!button.getText().equals("로그인")&&!button.getText().equals("로그아웃")
					&&!button.getText().equals("시스템 종료")) {
				EXCUTEFUNCTION="생산 계획 관리→수주 목록→"+button.getText();
			}
		}
		log_history.save_log(EXCUTEFUNCTION);
	}
}
