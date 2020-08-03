package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Info extends JDialog implements ActionListener{
	
	public Info() {
		
		JLabel title = new JLabel("Information");
		JLabel version = new JLabel("Version : 1.0.0");
		JLabel uri = new JLabel("https://www.robogates.com");
		JLabel date = new JLabel("Date : 2020/02/26");
		JLabel developer = new JLabel("만든 놈 : ROBOGATES / HJ / G1");
		JLabel name = new JLabel("ROMES");
		title.setFont(new Font("Mongolian Baiti",Font.BOLD,18));
		title.setHorizontalAlignment(JLabel.CENTER);
		Box box = Box.createVerticalBox();
		box.add(name);
		box.add(developer);
		box.add(date);
		box.add(version);
		box.add(uri);
		
		ImageIcon originIcon = new ImageIcon("ROMES2.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(150, 30, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		
		JLabel robo = new JLabel(Icon);
		JPanel panel = new JPanel();
		robo.setPreferredSize(new Dimension(150, 30));
		this.setTitle("Info");
		
		getContentPane().setLayout(new BorderLayout(0,0));
		getContentPane().add(title,BorderLayout.NORTH);
		getContentPane().add(panel,BorderLayout.CENTER);
		this.setVisible(true);
		this.setSize(460,210);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		
		JButton button = new JButton("나가기");
		button.addActionListener(this);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(button))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(robo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
							.addComponent(box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(42))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(robo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(88, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addComponent(box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addGap(13))
		);
		
		JLabel label = new JLabel("email : help@robogates.com");
		box.add(label);
		panel.setLayout(gl_panel);
		title.setBackground(Color.WHITE);
		
	}

	public Info(ActionListener actionListener, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
}
