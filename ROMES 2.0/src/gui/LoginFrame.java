package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.MasterUser;
import entity.dao.DynamicDAO;
import mainprograme.MESMain;

public class LoginFrame extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,255,255));
		contentPane.setBorder(new EmptyBorder(40, 50, 5, 50));
		contentPane.setLayout(new BorderLayout(25, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,7));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(new Color(255,255,255));
		
		JLabel label = new JLabel("로그인");
		label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label,BorderLayout.PAGE_END);
		
		JLabel label2 = new JLabel("ROMES SERVER & MANAGER");
		label2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
		label2.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label2,BorderLayout.NORTH);
		
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerEastLoginPanel = new JPanel();
		centerEastLoginPanel.setBorder(new EmptyBorder(30,0,30,20));
		centerEastLoginPanel.setLayout(new GridLayout(0, 1, 0, 0));
		centerEastLoginPanel.setPreferredSize(new Dimension(100,0));
		centerPanel.add(centerEastLoginPanel, BorderLayout.EAST);
		
		
		JButton loginBt = new JButton("로그인");
		loginBt.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		centerEastLoginPanel.add(loginBt);
		
		JPanel panel_3 = new JPanel();
		centerPanel.add(panel_3, BorderLayout.SOUTH);
		
		JPanel centerInputPanel = new JPanel();
		centerInputPanel.setBorder(new EmptyBorder(30,0,30,0));
		centerInputPanel.setLayout(new GridLayout(0, 1, 0, 0));
		centerPanel.add(centerInputPanel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0,20,0,20));
		centerInputPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setPreferredSize(new Dimension(50,0));
		panel_1.add(idLabel, BorderLayout.WEST);
		
		idField = new JTextField();
		panel_1.add(idField, BorderLayout.CENTER);
		idField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		centerInputPanel.add(panel_4);
		panel_4.setBorder(new EmptyBorder(0,20,0,20));
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setPreferredSize(new Dimension(50, 0));
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(pwLabel, BorderLayout.WEST);
		
		pwField = new JPasswordField();
		panel_4.add(pwField, BorderLayout.CENTER);
		pwField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		
		ImageIcon originIcon = new ImageIcon("ROMES2.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(100, 20, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JLabel icon = new JLabel(Icon);JLabel ver = new JLabel("   Ver 1.0");
		JPanel p = new JPanel();
		p.add(icon);p.add(ver);
		panel.setBackground(new Color(255,255,255));
		p.setBackground(new Color(255,255,255));
		panel_2.add(p);
		panel_2.setBackground(new Color(255,255,255));
		panel_3.setBackground(new Color(255,255,255));
		centerPanel.setBackground(new Color(255,255,255));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_1.setBackground(new Color(255,255,255));
		panel_4.setBackground(new Color(255,255,255));
		centerEastLoginPanel.setBackground(new Color(255,255,255));
		centerInputPanel.setBackground(new Color(255,255,255));
		
		pwField.addKeyListener(this);
		idField.addKeyListener(this);
		loginBt.addKeyListener(this);
		loginBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if (e.getSource() instanceof JButton) {
			JButton button = (JButton)e.getSource();
			String btString=button.getText();
			if(btString.equals("로그인")) {
				String idString = idField.getText();
				String pwString =new String(pwField.getPassword()) ;
				boolean islogin;
//				//system.out.println("id : "+idString);
//				//system.out.println("pw : "+pwString);
				DynamicDAO dao = new DynamicDAO();
				islogin = dao.getObjects(MasterUser.class).stream().anyMatch(mu->
				
				(mu.getColumn_00UserId().equals(idString)&&mu.getColumn_01UserPw().equals(pwString))==true);
				if(islogin) {
					MESMain.setIsLogin(true);
					this.dispose();
				}else {
					System.out.println("이거 이거");
					JOptionPane.showMessageDialog(null, "아이디와 페스워드가 틀렸습니다.");
				}
				
//				dao.getObjects(MasterUser.class).forEach(mu->{
//					//system.out.println("id : "+idString);
//					//system.out.println("pw : "+pwString);
//					//system.out.println(mu);
//					if(mu.getUserId().equals(idString)&&mu.getUserPw().equals(pwString)) {
//						CsMain.setIsLogin(true);
//						this.dispose();
//						
//					}else {
//						JOptionPane.showMessageDialog(null, "아이디와 페스워드가 틀렸습니다.");
//					}
//				});
				
			}
			
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		////system.out.println(key);
		if(key== KeyEvent.VK_ENTER) {
			String idString = idField.getText();
			String pwString =new String(pwField.getPassword()) ;
			boolean islogin;
//			//system.out.println("id : "+idString);
//			//system.out.println("pw : "+pwString);
			DynamicDAO dao = new DynamicDAO();
			islogin = dao.getObjects(MasterUser.class).stream().anyMatch(mu->
			(mu.getColumn_00UserId().equals(idString)&&mu.getColumn_01UserPw().equals(pwString))==true);
			if(islogin) {
				MESMain.setIsLogin(true);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "아이디와 페스워드가 틀렸습니다.");
			}
			
//			dao.getObjects(MasterUser.class).forEach(mu->{
//				if(mu.getUserId().equals(idString)&&mu.getUserPw().equals(pwString)) {
//					CsMain.setIsLogin(true);
//					this.dispose();
//				}else {
//					JOptionPane.showMessageDialog(null, "아이디와 페스워드가 틀렸습니다.");
//				}
//			});
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
