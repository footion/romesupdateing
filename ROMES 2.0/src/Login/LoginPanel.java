package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import org.hibernate.Session;
import entity.Login;
import factory.colorFactory;
import hibernate.hibernate;
import layoutSetting.UI_Button;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicPanel;
import layoutSetting.basicTextField;
import mainFrame.mainFrame;

@SuppressWarnings("serial")
public class LoginPanel extends basicPanel{
	JTextField idtext;
	JPasswordField pwtext;
	public static boolean Confirmlogin = false;
	public static String name="로그인 정보 없음";
	public static boolean confirm_admin=false;
	public static int ID;
	public LoginPanel() {
		// TODO Auto-generated constructor stub
		JLabel title = new JLabel("로그인");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		basicPanel titlepanel = new basicPanel();
		titlepanel.add(title);
		JLabel id = new JLabel("  ID : ");
		JLabel pw = new JLabel(" PW : ");
		id.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		pw.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		idtext= new JTextField(25);
		pwtext = new JPasswordField(25);
		//setUI
		idtext.setBorder(new BevelBorder(BevelBorder.LOWERED));
		idtext.setBackground(colorFactory.TEXTFIELD_COLOR);
		pwtext.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pwtext.setBackground(colorFactory.TEXTFIELD_COLOR);
		
		idtext.setPreferredSize(new Dimension(36,36));
		pwtext.setPreferredSize(new Dimension(36,36));
		basicPanel idpanel = new basicPanel();
		idpanel.add(id);idpanel.add(idtext);
		basicPanel pwpanel = new basicPanel();
		pwpanel.add(pw);pwpanel.add(pwtext);
		basicPanel westpanel = new basicPanel();
		westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS));
		westpanel.add(idpanel);westpanel.add(pwpanel);
		UI_Button loginButton = new UI_Button("로그인");
		loginButton.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		loginButton.setPreferredSize(new Dimension(110,85));
		basicPanel loginpanel = new basicPanel();
		loginpanel.add(westpanel);
		loginpanel.add(loginButton);
		this.setLayout(new BorderLayout(0,40));
		this.add(new JLabel(" "),BorderLayout.NORTH);
		this.add(new JLabel(" "),BorderLayout.PAGE_END);
		this.setVisible(true);
		this.setSize(350,160);
		
		ImageIcon originIcon = new ImageIcon("ICON/ROMES2.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(150, 30, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JLabel icon = new JLabel(Icon);JLabel ver = new JLabel("   Ver 1.0");
		icon.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		basicPanel iconpanel = new basicPanel();
		iconpanel.add(icon);iconpanel.add(ver);
		basicPanel p = new basicPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(titlepanel);
		p.add(loginpanel);
		p.add(iconpanel);
		
		basicBorderPanel centerpanel = new basicBorderPanel();
		LineBorder border = new LineBorder(colorFactory.BORDER_COLOR, 2);
		centerpanel.setBorder(border);
		centerpanel.setLayout(new BorderLayout(0,15));
		centerpanel.add(new JLabel(" "),BorderLayout.NORTH);
		centerpanel.add(p,BorderLayout.CENTER);
		centerpanel.add(new JLabel(" "),BorderLayout.PAGE_END);
		basicPanel panel = new basicPanel();
		panel.setLayout(new BorderLayout(150,80));
		panel.add(new JLabel(" "),BorderLayout.NORTH);
		panel.add(new JLabel(" "),BorderLayout.PAGE_END);
		panel.add(new JLabel(" "),BorderLayout.EAST);
		panel.add(new JLabel(" "),BorderLayout.WEST);
		panel.add(centerpanel,BorderLayout.CENTER);
		this.add(panel,BorderLayout.CENTER);
		this.add(new JLabel(" "),BorderLayout.NORTH);
		this.add(new JLabel(" "),BorderLayout.PAGE_END);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Login();
			}
		});
		idtext.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login();
				}
			}
		});
		pwtext.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	void Login() {
		ArrayList<Login> loginID = null;
		String id = null;
		String pw = null;
		boolean confirmWrongPW = false;
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			loginID = (ArrayList<Login>)session.createCriteria(Login.class).list();
			id =idtext.getText();
			pw = new String(pwtext.getPassword());
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		for(Login log : loginID) {
			if(log.getLoginId().equals(id)) {
				if(log.getLoginPw().equals(pw)) {
					Confirmlogin=true;
					name =log.getName();
					ID=log.getId();
					confirm_admin = log.isAdmin_authority();
					componentSetting();
					idtext.setText("");
					pwtext.setText("");
					//log_history.save_log("로그인");
					break;
				}else if(!log.getLoginPw().equals(pw)) {
					confirmWrongPW = true;
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","Wrong PassWord",JOptionPane.ERROR_MESSAGE);
					pwtext.setText("");
					//log_history.save_log("로그인 실패 - 비밀번호 불일치 (아이디 : "+id+")");
				}
				
			}
		}
		if(Confirmlogin==false&&confirmWrongPW==false) {
			JOptionPane.showMessageDialog(null, "회원정보에 일치하는 ID가 없습니다.","Worng ID",JOptionPane.ERROR_MESSAGE);
			idtext.setText("");
			pwtext.setText("");
		}
	}
	void componentSetting() {
		mainFrame.searchBar.setVisible(true);
		mainFrame.LoginInfo.setVisible(true);
		mainFrame.LoginInfo.refresh();
		mainFrame.title.setVisible(true);
		mainFrame.card.show(mainFrame.Container, "ReceipedOrder");
		mainFrame.title.setText("수주 관리");
	}
}
