package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class WorkerMainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerMainFrame frame = new WorkerMainFrame();
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
	public WorkerMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0xEA,0xEF,0xF8));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(25, 25));
		
		JPanel panel = new JPanel();
		panel.setSize((int) (contentPane.getSize().height*0.2),this.getSize().width-5);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 10));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("자 동 납 땜");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(254, 0));
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("남은 수량 : 1302");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 29));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_2);
		
		JButton button = new JButton("작업시작");
		button.setSize(new Dimension(0, 0));
		panel_3.add(button);
		
		JButton btnNewButton_1 = new JButton("납변경");
		
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("기종변경");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Object ob = btnNewButton.getSize();
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("불량발생");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setSize((Dimension) ob);
		panel_3.add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("현재 작업 기종 : AD-12DKVXXX");
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		String column[] = { "번호","바코드넘버" , "PCB 갯수", "내용" };
		DefaultTableModel tableData = null;
		if (tableData == null) {
			tableData = new DefaultTableModel(column, 0) {
				@Override
				public Class getColumnClass(int column) {
					switch (column) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return Integer.class;
					default:
						return String.class;
					}
				}
			};
		} else {
			tableData.setRowCount(0);
		}
		table = new JTable(tableData);
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(30, 30));
		
		JLabel lblNewLabel = new JLabel("현재 시간 : 00:00:00");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("근무자 : 정지원");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		panel_2.add(lblNewLabel_1, BorderLayout.EAST);
	}
	
	

}
