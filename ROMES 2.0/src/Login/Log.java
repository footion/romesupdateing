package Login;

import javax.swing.JButton;

import Menu.MenuWriter;
import Menu.SubMenu;

public class Log {
	public static void excutefunction(JButton button) {
		String EXCUTEFUNCTION;
		if(MenuWriter.menuwriterHistory.size()>0) {
			EXCUTEFUNCTION = MenuWriter.menuwriterHistory.get(0).menu.getText()+
					"��"+SubMenu.submenuHistory.get(0).getText().substring(3)+"��"+button.getText();
		}else {
			EXCUTEFUNCTION = button.getText();
			if(!button.getText().equals("�α���")&&!button.getText().equals("�α׾ƿ�")
					&&!button.getText().equals("�ý��� ����")) {
				EXCUTEFUNCTION="���� ��ȹ ��������� ��ϡ�"+button.getText();
			}
		}
		log_history.save_log(EXCUTEFUNCTION);
	}
}
