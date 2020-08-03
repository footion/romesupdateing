package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.menu.MenuJLabel;
import gui.menu.SubmenuJLabel;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	public JPanel getContentPane() {
		return contentPane;
	}

	
	private JLabel lblCsMes;
	private MenuPanel menupanel;

	private static MainFrame frame;

	public static MainFrame getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final MainFrame INSTANCE = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		menupanel.setSize(new Dimension(140, getSize().height - 86));
		super.paint(g);
		//system.out.println(getSize());
		//system.out.println("panel size :" + menupanel.getSize());
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(new Dimension(1400, 760));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		BorderLayout bl1 = new BorderLayout(0, 0);

		contentPane.setLayout(bl1);

		HeadPanel panel = new HeadPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(panel, BorderLayout.NORTH);

		menupanel = new MenuPanel();

		contentPane.add(menupanel, BorderLayout.WEST);

		contentPane.add(MainPagePanel.getInstance(), BorderLayout.CENTER);
	}

}
