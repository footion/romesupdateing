package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.MasterUser;
import gui.menu.SubmenuJLabel;
import gui.pages.MainPageDefaultPanel;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class NewFrameForMASTER extends JFrame {

	public static ColoredPanel contentPane;
	private JTable table;

	/**
	 * 
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFrameForMASTER frame = new NewFrameForMASTER();
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
	public NewFrameForMASTER() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 850);
		contentPane = new ColoredPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		ColoredPanel headPanel = new HeadPanel();
		contentPane.add(headPanel, BorderLayout.NORTH);

		ColoredPanel menuPanel = new ColoredPanel();
		menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		menuPanel.setPreferredSize(new Dimension(160, 0));
		contentPane.add(menuPanel, BorderLayout.WEST);
		menuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		MenuPanel menuPn = new MenuPanel();
		menuPanel.add(menuPn);

		contentPane.add(MESCardLayout.getInstance(), BorderLayout.CENTER);
		SubmenuJLabel.changeFrame(MESCardLayout.ProductCreate);

	}

}
