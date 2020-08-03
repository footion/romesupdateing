package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import factory.fontFactory;
import image.icon;
import image.url;

public class Info extends JFrame{
	
	public Info() {
		//JLabel title = new JLabel("Information");
		JLabel version = new JLabel("¡ß  Version : 1.0.0");
		JLabel uri = new JLabel("¡ß  https://www.robogates.com");
		JLabel date = new JLabel("¡ß  Date : 2020/00/00");
		JLabel developer = new JLabel("¡ß  Developer : ROBOGATES / HJ & JW");
		JLabel name = new JLabel("¡ß  Manufacturing Execution System");
		version.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,12));
		uri.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,12));
		date.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,12));
		developer.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,12));
		name.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,12));
		Box box = Box.createVerticalBox();
		box.add(name);
		box.add(developer);
		box.add(date);
		box.add(version);
		box.add(uri);
		
		JLabel robo = new JLabel(new icon(url.ROMES, 175, 35));
		JPanel panel = new JPanel();
		panel.add(robo);
		panel.add(box);
		this.setTitle("Information");
		this.setLayout(new BorderLayout(0,0));
		//this.add(title,BorderLayout.NORTH);
		this.add(panel,BorderLayout.CENTER);
		this.setVisible(true);
		this.setSize(450,150);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		//title.setBackground(Color.WHITE);
		
	}

}
