package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import mainprograme.MESMain;

public class HeadPanel extends ColoredPanel {

	static private JLabel titleLabel;

	
	public static JLabel getTitleLabel() {
		return titleLabel;
	}


	/**
	 * Create the panel.
	 */
	public HeadPanel() {
		setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(0, 70));
		this.setLayout(new BorderLayout(0, 0));
		
		ColoredPanel logoPanel = new ColoredPanel();
		logoPanel.setPreferredSize(new Dimension(180, 0));
		this.add(logoPanel, BorderLayout.WEST);
		logoPanel.setLayout(new BorderLayout(0, 0));

		ImageIcon imgIcon = new ImageIcon("ROMES2.png");
		Image originImg = imgIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(120, 24, Image.SCALE_SMOOTH );
		imgIcon = new ImageIcon(changedImg);
		
		ImageIcon infoIcon= new ImageIcon("info.png");
		JLabel infoiconJLabel = new JLabel(infoIcon);
		infoiconJLabel.setToolTipText("정보");
		infoiconJLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JDialog infoDialog = new Info();
				infoDialog.setVisible(true);
			}
		});
		infoiconJLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		infoiconJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			
		JLabel lblLogo = new JLabel(imgIcon,SwingUtilities.CENTER);
		lblLogo.setVerticalAlignment(SwingConstants.TOP);
		lblLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogo.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

		lblLogo.setBorder(new EmptyBorder(0, 20, 0, 0));
		logoPanel.add(lblLogo, BorderLayout.WEST);
		JPanel emptypanel= new ColoredPanel();
		emptypanel.setPreferredSize(new Dimension(2,10));
		logoPanel.add(emptypanel,BorderLayout.CENTER);
		logoPanel.add(infoiconJLabel,BorderLayout.NORTH);
		
		ColoredPanel logoutPanel = new ColoredPanel();
		logoutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		logoutPanel.setPreferredSize(new Dimension(100, 0));

		this.add(logoutPanel, BorderLayout.EAST);
		logoutPanel.setLayout(new GridLayout(1, 0, 0, 0));
//		JButton infoBt = new JButton("info");
//		infoBt.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				JDialog infoDialog = new Info();
//				infoDialog.setVisible(true);
//			}
//		});
//		
		JButton logoutBt = new JButton(new ImageIcon("logout.png"));
		logoutBt.setToolTipText("로그아웃");
		logoutBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(logoutBt);
				mainFrame.dispose();
				//로그인 창으로 돌아가야함.
				//system.out.println("to login");
				MESMain.setIsLogin(false);
				MESMain.setLoginFrameAlive(false);
				MESMain.setMainFrameAlive(false);
			}
		});
		//logoutPanel.add(infoBt);
		logoutPanel.add(logoutBt);
		
		ColoredPanel titlePanel = new ColoredPanel();

		this.add(titlePanel, BorderLayout.CENTER);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		titlePanel.add(titleLabel);
	}

}
