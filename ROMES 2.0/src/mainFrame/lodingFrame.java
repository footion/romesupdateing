package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class lodingFrame extends JFrame{
	JPanel panel = new JPanel();
	JLabel label = new JLabel("�����͸� �ҷ��������Դϴ�..");
	JLabel label2 = new JLabel("��ø� ��ٷ��ּ���..");
	JLabel label3 = new JLabel("Waiting for loading ..");
	static Box box = Box.createVerticalBox();
	public lodingFrame() {
		box.add(label);
		box.add(Box.createVerticalStrut(5));
		box.add(label2);
		panel.add(box);
		label.setFont(new Font("����",Font.BOLD,13));
		label2.setFont(new Font("����",Font.BOLD,13));
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(label3,BorderLayout.NORTH);
		label3.setHorizontalAlignment(label3.CENTER);
		this.setTitle("Loading Data");
		this.setVisible(true);
		this.setSize(300,100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		panel.setBackground(Color.white);
	}

}
