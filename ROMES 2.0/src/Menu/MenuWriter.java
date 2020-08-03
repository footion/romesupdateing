package Menu;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import Login.LoginPanel;
import factory.fontFactory;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class MenuWriter extends basicPanel implements MouseListener{
	public ArrayList<SubMenu> SubMenuList;
	Boolean Click = false;
	SubMenu submenu;
	public MainMenu menu;
	public static ArrayList<MenuWriter> menuwriterHistory = new ArrayList<>();
	public MenuWriter(String string) {
		// TODO Auto-generated constructor stub
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		SubMenuList = new ArrayList<SubMenu>();
		menu = new MainMenu(string);
		add(menu);
		menu.addMouseListener(this);
		setBorder(new EmptyBorder(0, 13, 16, 5));
	}
	public void addSubMenu(String string) {
		// TODO Auto-generated method stub
		submenu = new SubMenu(string);
		SubMenuList.add(submenu);
		this.add(submenu);
	}
	public static void checkedMainmenu(ArrayList<MenuWriter> menuwriterHistory) {
		if(menuwriterHistory.size()>0) {
			menuwriterHistory.get(0).menu.setFont(new Font(fontFactory.TITLE_FONT, Font.BOLD, 21));
			menuwriterHistory.get(0).Click = false;
			for(SubMenu subMenu : menuwriterHistory.get(0).SubMenuList) {
				subMenu.setVisible(false);
			}
			menuwriterHistory.remove(0);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(LoginPanel.Confirmlogin==true) {
			if(arg0.getSource() instanceof MainMenu) {
				if(Click==false) {
					checkedMainmenu(menuwriterHistory);
					Click = true;
					for(SubMenu submenu : SubMenuList) {
						menu.setFont(new Font(fontFactory.TITLE_FONT, Font.BOLD, 21));
						submenu.setVisible(true);
					}
					menuwriterHistory.add(this);
				}else {
					Click = false;
					for(SubMenu submenu : SubMenuList) {
						menu.setFont(new Font(fontFactory.TITLE_FONT, Font.BOLD, 21));
						submenu.setVisible(false);
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "로그인 후,  사용해주세요.","로그인 정보 없음",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
